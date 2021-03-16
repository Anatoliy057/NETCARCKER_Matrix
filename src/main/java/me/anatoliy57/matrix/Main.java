package me.anatoliy57.matrix;

import me.anatoliy57.matrix.model.Matrix;
import me.anatoliy57.matrix.model.MatrixIncompatibilityException;
import me.anatoliy57.matrix.model.ZeroLengthMatrixException;

public class Main {

    public static void main(String[] args) throws ZeroLengthMatrixException, MatrixIncompatibilityException {
        Matrix A = new Matrix(new int[][]{{1, 2, 3}, {4, 5, 6}});
        Matrix B = new Matrix(new int[][]{{7, 8}, {9, 1}, {2, 3}});
        Matrix C = A.multi(B, 4);

        System.out.println(C);
    }
}
