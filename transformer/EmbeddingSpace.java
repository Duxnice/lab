package labotary.transformer;

import java.util.Arrays;
import java.util.Random;

public class EmbeddingSpace {
    private final double[][] weights;
    private final int unitSize;
    private final int dimension;

    public EmbeddingSpace(int unitSize, int dimension, Random random, double initStd) {
        this.unitSize = unitSize;
        this.dimension = dimension;
        weights = new double[unitSize][dimension];
        for(int i = 0; i < unitSize; i++) {
            for(int v = 0; v < dimension; v++) {
                weights[i][v] = random.nextGaussian() * initStd;
            }
        }
    }

    public double[][] forward(int[] ids) {
        double[][] output = new double[ids.length][];
        for(int i = 0; i < ids.length; i++) {
            output[i] = getVector(ids[i]);
        }
        return output;
    }

    public double[] getVector(int id) {
        return Arrays.copyOf(weights[id], dimension);
    }

    public int getUnitSize() {
        return this.unitSize;
    }

    public int getDimension() {
        return this.dimension;
    }
}
