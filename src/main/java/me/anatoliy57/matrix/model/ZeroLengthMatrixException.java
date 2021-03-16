package me.anatoliy57.matrix.model;

/**
 * ZeroLengthMatrixException throw when the side of the matrix is 0
 *
 * @see Matrix
 * @author Udarczev Anatoliy
 */
public class ZeroLengthMatrixException extends Exception {

    public ZeroLengthMatrixException() {
        super("Size is 0");
    }

    public ZeroLengthMatrixException(String message) {
        super(message);
    }
}
