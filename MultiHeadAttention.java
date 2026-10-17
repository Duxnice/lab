package labotary;

public class MultiHeadAttention {
    private final int modelDim;
    private final int numHeads;
    private final int headDim;
    private final double ropeBase;
    private final QKVProjection qkvProjection;

    public MultiHeadAttention(int modelDim, int numHeads, int calculatedHeadDim, double ropeBase, QKVProjection qkvProjection) {
        this.modelDim = modelDim;
        this.numHeads = numHeads;
        this.headDim = calculatedHeadDim;
        this.ropeBase = ropeBase;
        this.qkvProjection = qkvProjection;
    }

    public double[][] forwardSequence(double[][] input) {
        int sequenceLength = input.length;
        QKVProjection.Result projected = qkvProjection.forwardSequence(input);

        double[][] concatenated = new double[sequenceLength][modelDim];
        for(int head = 0; head < numHeads; head++) {
            processOneHead(projected, head, concatenated);
        }
        return concatenated;
    }

    private void processOneHead(QKVProjection.Result projected, int headIndex, double[][] concatenated) {
        int start = headIndex * headDim;
        double[][] qHead = extractHeadSequence(projected.q, start);
        double[][] kHead = extractHeadSequence(projected.k, start);
        double[][] vHead = extractHeadSequence(projected.v, start);
        double[][] qRotated = applyRoPE(qHead);
        double[][] kRotated = applyRoPE(kHead);
        double[][] scores = computeCausalScores(qRotated, kRotated);

        double[][] weights = computeAttentionWeights(scores);

        double[][] headOutput = computeHeadOutput(weights, vHead);

        appendHeadOutput(concatenated, headOutput, start);
    }

    private double[][] extractHeadSequence(double[][] vectors, int start) {
        double[][] result = new double[vectors.length][];
        for (int i = 0; i < vectors.length; i++) {
            result[i] = extractHeadVector(
                    vectors[i], start
            );
        }
        return result;
    }

    private double[] extractHeadVector(double[] vector, int start) {
        double[] result = new double[headDim];
        System.arraycopy(vector, start, result, 0, headDim);
        return result;
    }

    private double[][] computeCausalScores(double[][] qRotated, double[][] kRotated) {
        int sequenceLength = qRotated.length;
        double[][] scores = new double[sequenceLength][];
        for (int i = 0; i < sequenceLength; i++) {
            scores[i] = AttentionScore.causalScoresOne(qRotated[i], kRotated, i);
        }
        return scores;
    }

    private double[][] computeAttentionWeights(double[][] scoresByQuery) {
        double[][] weights = new double[scoresByQuery.length][];
        for (int i = 0; i < scoresByQuery.length; i++) {
            weights[i] = SoftMax.forwardOne(scoresByQuery[i]);
        }
        return weights;
    }

    private double[][] computeHeadOutput(double[][] weightsByQuery, double[][] values) {
        double[][] output = new double[weightsByQuery.length][];
        for (int i = 0; i < weightsByQuery.length; i++) {
            output[i] = AttentionOutput.getZ(weightsByQuery[i], values);
        }
        return output;
    }

    private void appendHeadOutput(double[][] concatenated, double[][] headOutput, int start) {
        for (int i = 0; i < headOutput.length; i++) {
            System.arraycopy(headOutput[i], 0, concatenated[i], start, headDim);
        }
    }


    private double[][] applyRoPE(double[][] vectors) {
        return RoPE.rotateSequence(vectors, ropeBase);
    }

    public int getModelDim() {
        return modelDim;
    }

    public int getNumHeads() {
        return numHeads;
    }

    public int getHeadDim() {
        return headDim;
    }
}
