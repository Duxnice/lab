package labotary;

public class Residual {
    public double[][] w0;
    public double[] b0;

    public Residual(double[][] w0, double[] b0) {
        this.w0 = w0;
        this.b0 = b0;
    }

    public double[] forward(double[] H, double[] Z) {
        Linear linear = new Linear(w0, b0);

        double[] A = linear.forward(Z);

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
