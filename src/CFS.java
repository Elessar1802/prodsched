import java.security.InvalidParameterException;
import java.util.ArrayList;
import java.util.Arrays;

public class CFS implements Scheduler {
    /* Complete Fair Scheduler */
    /* Initialize Indexed-MinPQ */
    /* Third party-library used here */
    private int N;
    private int ex[], pd[], id[];
    IndexMinPQ<Integer> rp;



    @Override
    public boolean schedable() {
        /* TODO: Implement actual feasibility check */
        /* Assumption: only valid inputs are being provided. */
        return true;
    }



    @Override
    public boolean setTasks(Tasks tasks) {
        N = tasks.size();
        ex = new int[N];
        pd = new int[N];
        id = new int[N];

        /* Instead of using simple arrays we can use Indexed MinPQ */
        /* The notable difference is that O(1) min search */
        /* And we can adjust the priorities of elements by using unique ids */
        rp = new IndexMinPQ<>(N);

        int counter = 0;

        for (Task t : tasks) {
            ex[counter] = t.ex;
            pd[counter] = t.pd;
            id[counter] = t.id;
            rp.insert(counter, t.pd);
            counter++;
        }

        return true;
    }



    @Override
    public void run(int rounds) {
        if (rounds < 0)
            throw new InvalidParameterException();

        while (rounds-- > 0) {
            Integer G = rp.minKey();
            ArrayList<Double> shares = calcShares(G);
    
            System.out.println(Arrays.toString(shares.toArray()));
            
            /* Schedule */
            
            rp.forEach(i -> {
                rp.decreaseKey(i, rp.keyOf(i) - G);
                if (rp.keyOf(i) == 0) rp.changeKey(i, pd[i]);
            });
            
            rp.forEach(item -> System.out.println("rp: " + item + " " + rp.keyOf(item)));
        }
    }


    
    private ArrayList<Double> calcShares(Integer G) {
        ArrayList<Double> shares = new ArrayList<>(N);
    
        for (int i = 0; i < N; i++)
            shares.add((ex[i] * 1.0) / pd[i] * G);
        shares.sort(null);

        return shares;
    }

}
