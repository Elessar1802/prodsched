import java.io.File;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Scanner;

public class Tasks implements Iterable<Task> {
    private ArrayList<Task> tasks;

    
    public Tasks(File file) throws Exception {
        Scanner fr = new Scanner(file);
        tasks = new ArrayList<>();

        try {
            while (fr.hasNextLine()) {
                int[] values = {0, 0, 0};
                int counter = 0;
                for (String item : fr.nextLine().split(","))
                    values[counter++] = Integer.parseInt(item);
                Task t = new Task(values[0], values[1], values[2]);
                tasks.add(t);
            }
        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        } finally {
            fr.close();
        }
    }


    public Tasks(String filepath) throws Exception {
        this(new File(filepath));
    }


    public int size() {
        return tasks.size();
    }


    @Override
    public Iterator<Task> iterator() {
        return new Iterator<>() {
            private int curr = 0;

            public Task next() {
                return tasks.get(curr++);
            }
            
            public boolean hasNext() {
                return curr < tasks.size();
            }
        };
    }
}
