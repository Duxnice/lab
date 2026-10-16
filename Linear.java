package labotary;

import java.util.Arrays;

public class Linear {
    private final double[][] weights;

    private final double[] bias;

    private final int inputDim;
    private final int outputDim;

    public Linear(double[][] initialWeights, double[] initialBias) {
        this.outputDim = initialWeights.length;
        this.inputDim = initialWeights[0].length;

        weights = new double[outputDim][inputDim];

        bias = initialBias.clone();

        for(int out = 0; out < outputDim; out++) {
            weights[out] = Arrays.copyOf(initialWeights[out], inputDim);
        }
    }

    public double[] forward(double[] input) {
        double[] output = new double[outputDim];
        for(int i = 0; i < outputDim; i++) {
            double sum = bias[i];
            for(int j = 0; j < inputDim; j++ ) {
                sum += weights[i][j] * input[j];
            }
            output[i] = sum;
        }
        return output;
    }

    public int getInputDim() {
        return this.inputDim;
    }

    public int getOutputDim() {
        return this.outputDim;
    }
}
