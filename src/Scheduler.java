public interface Scheduler {
    public boolean schedable(); /* Feasibility check */
    public boolean setTasks(Tasks tasks); /* Returns False if scheduling is infeasible */
    public void run(int rounds); /* Scheduler keeps execution until t secs have passed */
}
