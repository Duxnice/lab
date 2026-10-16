package labotary;

public class RoPE {
    public static double[] rotate(double[] vector, int position, double base) {
        int dim = vector.length;
        double[] rotated = new double[dim];
        for(int i = 0; i < dim/2; i++) {
            int index = i*2;
            double x = vector[index];
            double y = vector[index+1];
            double frequency = 1.0 / Math.pow(base, (2.0 * i) / dim);
            double angle = frequency * position;
            double cos = Math.cos(angle);
            double sin = Math.sin(angle);
            rotated[index] = x * cos - y * sin;
            rotated[index+1] = y * sin + y * cos;
        }
        return rotated;
    }

    public static double[][] rotateSequence(double[][] vectors, double base) {
        double[][] result = new double[vectors.length][];
        for(int i = 0; i < vectors.length; i++) {
            result[i] = rotate(vectors[i], i, base);
        }
        return result;
    }
}
