package me.anatoliy57.matrix.model;

/**
 * MatrixIncompatibilityException throw when two presented matrices cannot be multiplied
 *
 * @see Matrix#multi(Matrix, int)
 * @author Udarczev Anatoliy
 */
public class MatrixIncompatibilityException extends Exception {

    public MatrixIncompatibilityException() {
        super("Matrices are not compatible with each other");
    }

    public MatrixIncompatibilityException(String message) {
        super(message);
    }
}
