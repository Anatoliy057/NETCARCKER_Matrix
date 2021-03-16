package me.anatoliy57.matrix.model;

import java.util.Arrays;

public class Matrix {

    private final int[][] matrix;

    public Matrix(int rows, int columns) throws ZeroLengthMatrixException {
        if (rows == 0 || columns == 0) {
            throw new ZeroLengthMatrixException();
        }
        matrix = new int[rows][columns];
    }

    public Matrix(int[][] matrix) throws ZeroLengthMatrixException {
        if (matrix.length == 0 || matrix[0].length == 0) {
            throw new ZeroLengthMatrixException();
        }
        this.matrix = matrix;
    }

    public int get(int i, int j) {
        return matrix[i][j];
    }

    public void set(int i, int j, int val) {
        matrix[i][j] = val;
    }

    public int rows() {
        return matrix.length;
    }

    public int columns() {
        return matrix[0].length;
    }

    public Matrix multi(Matrix matrix, int maxTreads) throws MatrixIncompatibilityException {
        if (rows() != matrix.columns() || columns() != matrix.rows()) {
            throw new MatrixIncompatibilityException();
        }

        Matrix result = null;
        try {
            result = new Matrix(rows(), matrix.columns());
        } catch (ZeroLengthMatrixException ignore) {
        }
        Matrix finalResult = result;

        TaskThreading taskThreading = new TaskThreading(maxTreads);
        for (int i = 0; i < result.rows(); i++) {
            for (int j = 0; j < result.columns(); j++) {
                final int x = i, y = j;
                taskThreading.addTask(() -> {
                    int cell = 0;
                    for (int k = 0; k < columns(); k++) {
                        cell += get(x, k) * matrix.get(k, y);
                    }
                    finalResult.set(x, y, cell);
                });
            }
        }
        try {
            taskThreading.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        taskThreading.stop();

        return result;
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < matrix.length; i++) {
            builder.append(Arrays.toString(matrix[i]));
            if(i + 1 < matrix.length) {
                builder.append('\n');
            }
        }

        return builder.toString();
    }
}
