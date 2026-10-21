package labotary.transformer;

public class CausalLanguageModel {
    private final EmbeddingSpace embeddingSpace;
    private final TransformerBackbone backbone;
    private final OutputHead outputHead;

    public CausalLanguageModel(
            EmbeddingSpace embeddingSpace,
            TransformerBackbone backbone,
            OutputHead outputHead) {
        this.embeddingSpace = embeddingSpace;
        this.backbone = backbone;
        this.outputHead = outputHead;
    }

    public double[][] forwardSequence(int[] inputIds) {
        double[][] embeddings = embeddingSpace.forward(inputIds);
        double[][] hidden = backbone.forwardSequence(embeddings);
        return outputHead.forwardSequence(hidden);
    }

    public double[] forwardNextToken(int[] inputIds) {
        double[][] logits = forwardSequence(inputIds);
        return logits[logits.length - 1];
    }
}
