package labotary.transformer;

public class LayerNorm {
    private final double[] gamma;
    private final double[] beta;

    private final int dim;
    private final double epsilon;

    public LayerNorm(int d, double e) {
        this.dim = d;
        this.epsilon = e;

        gamma = new double[d];
        beta = new double[d];

        for(int i = 0; i < d; i++) {
            gamma[i] = 1.0;
            beta[i] = 0.0;
        }
    }

    public double[] forwardOne(double[] input) {
        double sum = 0.0;
        for(int i = 0; i < dim; i++) {
            sum += input[i];
        }
        double mean  = sum/dim;
        double varianceSum = 0.0;
        for(int i = 0; i < dim; i++) {
            double diff = input[i] - mean ;
            varianceSum += diff * diff;
        }
        double variance = varianceSum/dim;
        double denominator = Math.sqrt(variance + epsilon);
        double[] output = new double[dim];
        for(int i = 0; i < dim; i++) {
            double normalized = (input[i] - mean) /denominator;
            output[i] = gamma[i] * normalized + beta[i];
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
}
