import java.util.Arrays;
import java.util.Comparator;

public class CpuManagerImpl implements CpuManager {

    private Cpu[] arrId, arrManufc, arrCores;
    private int elem, count;
    private CpuByManufcComparator cmprByManufc;
    private CpuByNumberOfCoresComparator cmprByCores;

    public CpuManagerImpl() {
    }

    public CpuManagerImpl(Cpu[] extArr) {
        if (extArr == null || extArr.length == 0) {
            throw new IllegalArgumentException("Empty CPU array is given");
        }
        
        initArrs(extArr.length);
        cmprByManufc = new CpuByManufcComparator();
        cmprByCores = new CpuByNumberOfCoresComparator();

        for (Cpu cpu : extArr) {
            if (cpu != null) { // option: throw exception
                add(cpu);
            }
        }
    }

    @Override
    public boolean add(Cpu cpu) {
        // option: Cpu cpu fields check
        elem = -(findElem(arrId, cpu)) - 1;
        if (elem < 0) {
            throw new IllegalArgumentException("Addition failed: CPU is already present");
        }

        if (count == arrId.length) {
            extendArrs();
        }

        performAddition(arrId, cpu);
        performAddition(arrManufc, cpu, cmprByManufc);
        performAddition(arrCores, cpu, cmprByCores);
        count++;
        return true;

    }

    @Override
    public boolean remove(int id) {

        Cpu dummyId = new Cpu(id, "", "", new int[]{0, 0}, new int[]{0, 0});
        elem = findElem(arrId, dummyId);
        if (elem < 0) {
            throw new IllegalArgumentException("Removal failed: CPU id=" + id + " not found");
        }
        Cpu cpu = arrId[elem];

        performRemoval(arrId);
        performRemoval(arrManufc, cpu, cmprByManufc);
        performRemoval(arrCores, cpu, cmprByCores);
        count--;
        return true;

    }

    @Override
    public Cpu[] getAll() {
        return Arrays.copyOfRange(arrId, 0, count);
    }

    @Override
    public Cpu[] getAllByManufc() {
        return Arrays.copyOfRange(arrManufc, 0, count);
    }

    @Override
    public Cpu[] getAllByNumberOfCores() {
        return Arrays.copyOfRange(arrCores, 0, count);
    }

    @Override
    public Cpu[] get(int minCores, int maxCores) {

        if (minCores > maxCores) {
            throw new IllegalArgumentException("Wrong request: wrong number of cores range");
        }

        Cpu dummyMin
                = new Cpu(Integer.MIN_VALUE, "", "",
                new int[]{minCores, 0}, new int[]{0, 0});
        int from = -findElem(arrCores, dummyMin, cmprByCores) - 1;

        Cpu dummyMax
                = new Cpu(Integer.MAX_VALUE, "", "",
                new int[]{maxCores, 0}, new int[]{0, 0});
        int to = -findElem(arrCores, dummyMax, cmprByCores) - 1;

        if (from == to) {
            return Arrays.copyOfRange(arrCores, from, from + 1);
        }
        return Arrays.copyOfRange(arrCores, from, to);

    }

    // Utils
    private void initArrs(int extArrLength) {
        int initArrLength = Math.max(extArrLength, 16);
        arrId = new Cpu[initArrLength];
        arrManufc = new Cpu[initArrLength];
        arrCores = new Cpu[initArrLength];
    }

    private void extendArrs() {
        arrId = Arrays.copyOf(arrId, arrId.length * 2);
        arrManufc = Arrays.copyOf(arrManufc, arrManufc.length * 2);
        arrCores = Arrays.copyOf(arrCores, arrCores.length * 2);
    }

    private int findElem(Cpu[] arr, Cpu cpu) {
        return Arrays.binarySearch(arr, 0, count, cpu);
    }

    private int findElem(Cpu[] arr, Cpu cpu, Comparator<Cpu> cmpr) {
        return Arrays.binarySearch(arr, 0, count, cpu, cmpr);
    }

    private void performAddition(Cpu[] arr, Cpu cpu) {
        for (int i = count - 1; i >= elem; i--) {
            arr[i + 1] = arr[i];
        }
        arr[elem] = cpu;
    }

    private void performAddition(Cpu[] arr, Cpu cpu, Comparator<Cpu> cmpr) {
        elem = -(findElem(arr, cpu, cmpr)) - 1;
        performAddition(arr, cpu);
    }

    private void performRemoval(Cpu[] arr) {
        for (int i = elem; i < count; i++) {
            arr[i] = arr[i + 1];
        }
    }

    private void performRemoval(Cpu[] arr, Cpu cpu, Comparator<Cpu>cmpr) {
        elem = findElem(arr, cpu, cmpr);
        performRemoval(arr);
    }

}
