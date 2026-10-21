package labotary.transformer;

public class CrossEntropyLoss {
    public static final class OneResult {
        public final double loss;
        public final double[] dLogits;

        private OneResult(double loss, double[] dLogits) {
            this.loss = loss;
            this.dLogits = dLogits;
        }
    }

    public static final class SequenceResult {
        public final double loss;
        public final double[][] dLogits;

        private SequenceResult(
                double loss,
                double[][] dLogits) {
            this.loss = loss;
            this.dLogits = dLogits;
        }
    }

    public static OneResult forwardOne(double[] logits, int targetId) {
        double max = logits[0];
        for (int i = 1; i < logits.length; i++) {
            max = Math.max(max, logits[i]);
        }

        double[] probabilities = new double[logits.length];
        double expSum = 0.0;

        for (int i = 0; i < logits.length; i++) {
            probabilities[i] = Math.exp(logits[i] - max);
            expSum += probabilities[i];
        }

        for (int i = 0; i < probabilities.length; i++) {
            probabilities[i] /= expSum;
        }

        double loss =
                max + Math.log(expSum) - logits[targetId];

        probabilities[targetId] -= 1.0;

        return new OneResult(loss, probabilities);
    }

    public static SequenceResult forwardSequence(
            double[][] logits,
            int[] targets) {

        double totalLoss = 0.0;
        double[][] dLogits = new double[logits.length][];

        for (int i = 0; i < logits.length; i++) {
            OneResult result =
                    forwardOne(logits[i], targets[i]);

            totalLoss += result.loss;
            dLogits[i] = result.dLogits;
        }

        double scale = 1.0 / logits.length;

        for (int i = 0; i < dLogits.length; i++) {
            for (int j = 0; j < dLogits[i].length; j++) {
                dLogits[i][j] *= scale;
            }
        }

        return new SequenceResult(
                totalLoss * scale,
                dLogits
        );
    }
}
