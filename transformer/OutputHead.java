package labotary.transformer;

public class OutputHead {
    private final Linear outputProjection;

    public OutputHead(Linear outputProjection) {
        this.outputProjection = outputProjection;
    }

    public double[] forwardOne(double[] hidden) {
        return outputProjection.forwardOne(hidden);
    }

    public double[][] forwardSequence(double[][] hidden) {
        return outputProjection.forwardSequence(hidden);
    }
}
