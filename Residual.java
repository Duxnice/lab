package labotary;

public class Residual {
    public Linear outputProjection;

    public Residual(double[][] w0, double[] b0) {
        outputProjection = new Linear(w0, b0);
    }

    public double[] forward(double[] H, double[] Z) {

        double[] A = outputProjection.forward(Z);

        double[] U = new double[A.length];

        for(int i = 0; i < A.length; i++) {
            U[i] = A[i] + H[i];
        }
        return U;
    }

    public static double[] addOne(double[] U, double[] F) {
        double[] out = new double[U.length];
        for(int i = 0 ; i < U.length; i++) {
            out[i] = U[i] + F[i];
        }
        return out;
    }

    public static double[][] addSequence(double[][] U, double[][] F) {
        double[][] out = new double[U.length][];
        for(int i = 0; i < U.length; i++) {
            out[i] = addOne(U[i], F[i]);
        }
        return out;
    }
}
