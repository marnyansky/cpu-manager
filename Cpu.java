import java.util.Arrays;

public class Cpu implements Comparable<Cpu> {

    private int id;
    private String manufc;
    private String model;
    private int[] coresNthreads;
    private int[] baseNturboFreq;


    public Cpu(int id, String manufc, String model, int[] coresNthreads, int[] baseNturboFreq) {
        // assume that input is valid
        this.id = id;
        this.manufc = manufc;
        this.model = model;
        this.coresNthreads = coresNthreads;
        this.baseNturboFreq = baseNturboFreq;
    }

    public int getId() {
        return id;
    }

    public String getManufc() {
        return manufc;
    }

    public String getModel() {
        return model;
    }

    public int[] getCoresNthreads() {
        return coresNthreads;
    }

    public int[] getBaseNturboFreq() {
        return baseNturboFreq;
    }

    @Override
    public final boolean equals(Object obj) {
        if (!(obj instanceof Cpu)) {
            return false;
        }
        if (this == obj) {
            return true;
        }
        Cpu other = (Cpu) obj;
        return id == other.id;
    }

    @Override
    public int compareTo(Cpu p) {
        return Integer.compare(id, p.id);
    }

    @Override
    public String toString() {
        return "\n#" + id + " " + manufc + " " + model + " "
                + coresNthreads[0] + "c/" + coresNthreads[1] + "t "
                + baseNturboFreq[0] + "/" + baseNturboFreq[1] + "MHz";
    }

}
