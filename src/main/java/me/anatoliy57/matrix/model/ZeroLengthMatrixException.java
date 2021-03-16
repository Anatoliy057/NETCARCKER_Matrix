package me.anatoliy57.matrix.model;

public class ZeroLengthMatrixException extends Exception {

    public ZeroLengthMatrixException() {
        super("Size is 0");
    }

    public ZeroLengthMatrixException(String message) {
        super(message);
    }
}
