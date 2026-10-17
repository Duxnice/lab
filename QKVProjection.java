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

        public VectorResult(
                double[] q,
                double[] k,
                double[] v) {

            this.q = q;
            this.k = k;
            this.v = v;
        }
    }

    public static final class Result {
        public final double[][] q;
        public final double[][] k;
        public final double[][] v;

        public Result(
                double[][] q,
                double[][] k,
                double[][] v) {

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

    public Result forwardSequence(double[][] embeddings) {
        int timeStep = embeddings.length;
        double[][] q = new double[timeStep][];
        double[][] k = new double[timeStep][];
        double[][] v = new double[timeStep][];
        for(int t = 0; t < timeStep; t++) {
            VectorResult vt = forwardOne(embeddings[t]);
            q[t] = vt.q;
            k[t] = vt.k;
            v[t] = vt.v;
        }
        return new Result(q, k ,v);
    }

    public Result forward(double[][] embeddings) {
        return forwardSequence(embeddings);
    }
}
