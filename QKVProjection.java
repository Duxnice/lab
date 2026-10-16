package labotary;

public class QKVProjection {
    private final Linear queryProjection;
    private final Linear keyProjection;
    private final Linear valueProjection;

    public QKVProjection(Linear Q, Linear K, Linear V) {
        this.queryProjection = Q;
        this.keyProjection = K;
        this.valueProjection = V;
    }

    public static final class Result {
        public final double[][] q;
        public final double[][] k;
        public final double[][] v;

        private Result(
                double[][] q,
                double[][] k,
                double[][] v) {

            this.q = q;
            this.k = k;
            this.v = v;
        }
    }

    public Result forward(double[][] embeddings) {
        int timeStep = embeddings[0].length;
        double[][] q = new double[timeStep][queryProjection.getOutputDim()];
        double[][] k = new double[timeStep][queryProjection.getOutputDim()];
        double[][] v = new double[timeStep][queryProjection.getOutputDim()];
        for(int t = 0; t < timeStep; t++) {
            q[t] = queryProjection.forward(embeddings[t]);
            k[t] = keyProjection.forward(embeddings[t]);
            v[t] = valueProjection.forward(embeddings[t]);
        }
        return new Result(q,k,v);
    }

}
