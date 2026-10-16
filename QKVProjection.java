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

    public static final class VectorResult {
        public final double[] q;
        public final double[] k;
        public final double[] v;

        private VectorResult(
                double[] q,
                double[] k,
                double[] v) {

            this.q = q;
            this.k = k;
            this.v = v;
        }
    }

    public VectorResult forwardOne(double[] embedding) {
        double[] q = queryProjection.forward(embedding);
        double[] k = keyProjection.forward(embedding);
        double[] v = valueProjection.forward(embedding);
        return new VectorResult(q,k,v);
    }

    public VectorResult[] forwardSequence(double[][] embeddings) {
        int timeStep = embeddings.length;
        VectorResult[] result = new VectorResult[timeStep];
        for(int t = 0; t < timeStep; t++) {
            result[t] = forwardOne(embeddings[t]);
        }
        return result;
    }

    public VectorResult[] forward(double[][] embeddings) {
        return forwardSequence(embeddings);
    }
}
