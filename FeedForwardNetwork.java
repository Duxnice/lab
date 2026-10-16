package labotary;

public class FeedForwardNetwork {
    public int dim;
    public int hdim;

    public double[][] w1;
    public double[] b1;

    public double[][] w2;
    public double[] b2;

    public FeedForwardNetwork(double[][] w1, double[] b1, double[][] w2, double[] b2) {
        this.hdim = w1.length;
        this.dim = w1[0].length;
        this.w1 = w1;
        this.b1 = b1;
        this.w2 = w2;
        this.b2 = b2;
    }

    public double[] forwardOne(double[] input) {
        double[] hidden = new double[hdim];
        for(int i = 0; i < hdim; i++) {
            double sum = b1[i];
            for(int j = 0; j < dim; j++) {
                sum += w1[i][j] * input[j];
            }
            hidden[i] = ReLU(sum);
        }

        double[] output = new double[dim];
        for(int i = 0; i < dim; i++) {
            double sum = b2[i];
            for(int j = 0; j < hdim; j++) {
                sum += w2[i][j] * hidden[j];
            }
            output[i] = sum;
        }
        return output;
    }

    public double[][] forwardSequence(double[][] input) {
        double[][] output = new double[input.length][];
        for(int i = 0; i < input.length; i++) {
            output[i] = forwardOne(input[i]);
        }
        return output;
    }

    public double ReLU(double x) {
        return Math.max(0.0, x);
    }

    private static double[][] copyMatrix(double[][] source) {
        double[][] copy = new double[source.length][];

        for (int i = 0; i < source.length; i++) {
            copy[i] = source[i].clone();
        }

        return copy;
    }
}
