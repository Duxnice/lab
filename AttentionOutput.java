package labotary;

public class AttentionOutput {
    public static double[] getZ(double[] W, double[][] V) {
        int dim = V[0].length;
        double[] output = new double[dim];
        for(int i = 0; i < W.length; i++) {
            for(int j = 0; j < dim; j++) {
                output[j] += W[i] * V[i][j];
            }
        }
        return output;
    }

    public static double[][] forwardSequence(double[][] W, double[][] V) {
        int times = W.length;
        double[][] output = new double[times][];
        for(int i = 0; i < times; i++) {
            output[i] = getZ(W[i], V);
        }
        return output;
    }
}
