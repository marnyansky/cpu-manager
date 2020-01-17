import java.util.Comparator;

public class CpuByNumberOfCoresComparator implements Comparator<Cpu> {
    @Override
    public int compare(Cpu o1, Cpu o2) {
        int cores1 = o1.getCoresNthreads()[0];
        int threads1 = o1.getCoresNthreads()[1];
        int cores2 = o2.getCoresNthreads()[0];
        int threads2 = o2.getCoresNthreads()[1];

        int res = Integer.compare(cores1, cores2);
        if (res == 0) {
            res = Integer.compare(threads1, threads2);
            if (res == 0) {
                res = Integer.compare(o1.getId(), o2.getId());
            }
        }

        return res;
    }
}
