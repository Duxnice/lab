package labotary;

public class LayerNorm {
    private double[] gamma;
    private double[] beta;

    private int dim;
    private int epsilon;

    public LayerNorm(int d, int e) {
        this.dim = d;
        this.epsilon = e;

        gamma = new double[d];
        beta = new double[d];

        for(int i = 0; i < d; i++) {
            gamma[i] = 1;
            beta[i] = 0;
        }
    }

    public double[] forwardOne(double[] input) {
        double sum = 0.0;
        for(int i = 0; i < dim; i++) {
            sum += input[i];
        }
        double tb = sum/dim;
        double varianceSum = 0.0;
        for(int i = 0; i < dim; i++) {
            double diff = input[i] - tb;
            varianceSum += diff*diff;
        }
        double variance = varianceSum/dim;
        double denominator = Math.sqrt(variance + epsilon);
        double[] output = new double[dim];
        for(int i = 0; i < dim; i++) {
            output[i] = gamma[i] * ((input[i] - sum) /denominator) * beta[i];
        }
        return output;
    }

    public double[][] forwardSequence(double[][] input) {
        double[][] output = new double[dim][];
        for(int i = 0; i < dim; i++) {
            output[i] = forwardOne(input[i]);
        }
        return output;
    }
}
