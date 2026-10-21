package labotary.transformer;

public class TransformerBackbone {
    private final TransformerBlock[] blocks;
    private final LayerNorm finalNorm;

    public TransformerBackbone(
            TransformerBlock[] blocks,
            LayerNorm finalNorm) {
        this.blocks = blocks;
        this.finalNorm = finalNorm;
    }

    public double[][] forwardSequence(double[][] input) {
        double[][] hidden = input;

        for (int i = 0; i < blocks.length; i++) {
            hidden = blocks[i].forwardSequence(hidden);
        }

        return finalNorm.forwardSequence(hidden);
    }
}
