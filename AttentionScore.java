package labotary;

public class AttentionScore {

    public static double[][] causalScores(double[][] qRotated, double[][] kRotated) {
        int times = qRotated.length;
        int dim = qRotated[0].length;

        double[][] scores = new double[times][times];

        double scale = Math.sqrt(dim);

        for(int i = 0; i < times; i++) {
            for(int j = 0; j <= i; j++) {
                double dot = 0.0;
                for(int d = 0; d < dim; d++) {
                    dot += qRotated[i][d] * kRotated[j][d];
                }

                scores[i][j] = dot/scale;
            }
        }
        return scores;
    }
}
