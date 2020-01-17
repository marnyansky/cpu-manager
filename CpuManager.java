public interface CpuManager {
    boolean add(Cpu cpu);
    boolean remove(int id);
    Cpu[] getAll();
    Cpu[] getAllByManufc();
    Cpu[] getAllByNumberOfCores();
    Cpu[] get(int minCores, int maxCores);
}
