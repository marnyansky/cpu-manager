import java.util.Comparator;

public class CpuByManufcComparator implements Comparator<Cpu> {
    @Override
    public int compare(Cpu o1, Cpu o2) {
        int res = o1.getManufc().compareTo(o2.getManufc());
        if (res == 0) {
            res = o1.getModel().compareTo(o2.getModel());
            if (res == 0) {
                res = Integer.compare(o1.getId(), o2.getId());
            }
        }
        return res;
    }
}
