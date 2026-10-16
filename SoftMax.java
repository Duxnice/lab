package labotary;

public class SoftMax {
    public static double[] forward(double[] scores) {
        double max = scores[0];
        for(double s : scores) {
            max = Math.max(max, s);
        }
        double[] probabilities = new double[scores.length];
        double sum = 0.0;
        for(int i = 0; i < scores.length; i++) {
            probabilities[i] = Math.exp(scores[i] - max);
            sum += probabilities[i];
        }
        for(int i = 0; i < scores.length; i++) {
            probabilities[i] /= sum;
        }
        return probabilities;
    }
}
