package labotary.transformer;

public class TransformerBlock {
    private final LayerNorm attentionNorm;
    private final MultiHeadAttention attention;
    private final Residual attentionResidual;

    private final LayerNorm feedForwardNorm;
    private final FeedForwardNetwork feedForward;
    public TransformerBlock(LayerNorm attentionNorm,
                            MultiHeadAttention attention,
                            Residual attentionResidual,
                            LayerNorm feedForwardNorm,
                            FeedForwardNetwork feedForward) {
        this.attentionNorm = attentionNorm;
        this.attention = attention;
        this.attentionResidual = attentionResidual;
        this.feedForwardNorm = feedForwardNorm;
        this.feedForward = feedForward;
    }

    public double[][] forwardSequence(double[][] input) {
        double[][] normalizedInput =
                attentionNorm.forwardSequence(input);

        double[][] attentionOutput =
                attention.forwardSequence(normalizedInput);

        double[][] afterAttention = new double[input.length][];

        for (int i = 0; i < input.length; i++) {
            afterAttention[i] = attentionResidual.forward(
                    input[i],
                    attentionOutput[i]
            );
        }

        double[][] normalizedForFeedForward =
                feedForwardNorm.forwardSequence(afterAttention);

        double[][] feedForwardOutput =
                feedForward.forwardSequence(normalizedForFeedForward);

        return Residual.addSequence(
                afterAttention,
                feedForwardOutput
        );
    }
}
