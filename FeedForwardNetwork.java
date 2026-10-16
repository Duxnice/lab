package labotary;

public class FeedForwardNetwork {
    public int dim;
    public int hdim;

    public double[][] w1;
    public double[] b1;

    public double[][] w2;
    public double[] b2;

    public FeedForwardNetwork(double[][] w1, double[] b1, double[][] w2, double[] b2) {
        this.w1 = w1;
        this.b1 = b1;
        this.w2 = w2;
        this.b2 = b2;
    }

    public double ReLU(double x) {
        return Math.max(0, x);
    }
}
