package labotary;

public class AttentionScore {

    public static double[] causalScoresOne(double[] qRotated, double[][] kRotated, int queryPosition) {
        int dim = qRotated.length;

        double[] scores = new double[queryPosition + 1];

        double scale = Math.sqrt(dim);

        for(int i = 0; i <= queryPosition; i++) {
            double dot = 0.0;
            for(int j = 0; j < dim; j++) {
                dot += qRotated[j] * kRotated[i][j];
            }
            scores[i] = dot/scale;
        }
        return scores;
    }

    public static double[][] causalScores(double[][] qRotated, double[][] kRotated) {
        int times = qRotated.length;
        double[][] output = new double[times][];
        for(int i = 0; i < times; i++) {
            output[i] = causalScoresOne(qRotated[i], kRotated, i);
        }
        return output;
    }
}
