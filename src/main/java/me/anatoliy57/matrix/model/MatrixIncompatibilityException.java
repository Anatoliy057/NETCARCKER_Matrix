package me.anatoliy57.matrix.model;

public class MatrixIncompatibilityException extends Exception {

    public MatrixIncompatibilityException() {
        super("Matrices are not compatible with each other");
    }

    public MatrixIncompatibilityException(String message) {
        super(message);
    }
}
