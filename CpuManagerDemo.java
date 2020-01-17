import java.util.Arrays;

public class CpuManagerDemo {

    public static void main(String[] args) {

        Cpu[] cpus = new Cpu[10];
        cpus[0] = new Cpu(
                1001, "AMD", "Ryzen 9 3950X",
                new int[]{16, 32}, new int[]{3500, 4700}
        );
        cpus[1] = new Cpu(
                1002, "AMD", "Ryzen 9 3900X",
                new int[]{12, 24}, new int[]{3800, 4600}
        );
        cpus[3] = new Cpu(
                1003, "AMD", "Ryzen 9 3900",
                new int[]{12, 24}, new int[]{3100, 4300}
        );

        cpus[4] = new Cpu(
                2001, "Intel", "Core i9-9900KS",
                new int[]{8, 16}, new int[]{4000, 5000}
        );
        cpus[5] = new Cpu(
                2002, "Intel", "Core i9-9900K",
                new int[]{8, 16}, new int[]{3600, 5000}
        );
        cpus[6] = new Cpu(
                2003, "Intel", "Core i9-9900",
                new int[]{8, 16}, new int[]{3100, 5000}
        );
        CpuManager cpuMgr = new CpuManagerImpl(cpus);

        System.out.println("Sorted arrays:");
        System.out.println(Arrays.toString(cpuMgr.getAll()));
        System.out.println(Arrays.toString(cpuMgr.getAllByManufc()));
        System.out.println(Arrays.toString(cpuMgr.getAllByNumberOfCores()));
        System.out.println("= = =\n\nadd()");
        cpuMgr.add(new Cpu(
                1004, "AMD", "Ryzen Threadripper 3990X",
                new int[]{64, 128}, new int[]{2900, 4600}
        ));
        System.out.println(Arrays.toString(cpuMgr.get(64, 128)));
        System.out.println("= = =\n\nremove()");
        System.out.println("id=1004 removed? " + cpuMgr.remove(1004));

    }

}
