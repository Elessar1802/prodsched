public class Main {
    private static int rounds = 10;
    
    public static void main(String[] args) {
        Tasks tasks;
        Scheduler scheduler;
        
        if (args.length != 1) {
            System.out.println("Usage: java Main ../path/to/input.csv");
            return;
        }

        try {
            tasks = new Tasks(args[0]);
        } catch (Exception e) {
            System.out.println("Invalid input provided. Sorry!😓");
            return;
        }

        /* Initialize scheduler to be used */
        scheduler = new CFS();

        if (scheduler.setTasks(tasks) == false) {
            System.out.println("Scheduling the given tasks is infeasible!😓");
            return;
        }

        /* Start scheduling for t secs */
        scheduler.run(rounds);
    }
}