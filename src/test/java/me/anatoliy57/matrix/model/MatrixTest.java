package me.anatoliy57.matrix.model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class MatrixTest {

    @Test
    public void multi_multiMatrixWithFourThreads_correctResult() throws MatrixIncompatibilityException, ZeroLengthMatrixException {
        Matrix A = new Matrix(new int[][]{{1, 2, 3}, {4, 5, 6}});
        Matrix B = new Matrix(new int[][]{{7, 8}, {9, 1}, {2, 3}});

        Matrix C = A.multi(B, 4);

        assertEquals(C, new Matrix(new int[][]{{31, 19}, {85, 55}}));
    }

    @Test
    public void multi_multiMatrixWithOneThreads_correctResult() throws MatrixIncompatibilityException, ZeroLengthMatrixException {
        Matrix A = new Matrix(new int[][]{{1, 2, 3}, {4, 5, 6}});
        Matrix B = new Matrix(new int[][]{{7, 8}, {9, 1}, {2, 3}});

        Matrix C = A.multi(B, 1);

        assertEquals(C, new Matrix(new int[][]{{31, 19}, {85, 55}}));
    }

    @Test
    public void multi_multiIncompatibleMatrix_throwException() throws ZeroLengthMatrixException {
        Matrix A = new Matrix(new int[][]{{1, 2, 3}, {4, 5, 6}});
        Matrix B = new Matrix(new int[][]{{7, 8}, {9, 1}});

        assertThrows(MatrixIncompatibilityException.class, () -> A.multi(B, 1));
    }
}