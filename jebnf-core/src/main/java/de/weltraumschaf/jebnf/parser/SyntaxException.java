/*
 * LICENSE
 *
 * "THE BEER-WARE LICENSE" (Revision 42):
 * "Sven Strittmatter" <ich(at)weltraumschaf(dot)de> wrote this file.
 * As long as you retain this notice you can do whatever you want with
 * this stuff. If we meet some day, and you think this stuff is worth it,
 * you can buy me a beer in return.
 *
 */

package de.weltraumschaf.jebnf.parser;

/**
 * Exception for signaling syntax errors.
 *
 * Provides a {@link Position} where in the input stream the syntax error occurred.
 *
 * @author Sven Strittmatter <weltraumschaf@googlemail.com>
 */
public class SyntaxException extends Exception {

    /**
     * Version id for serialization.
     */
    private static final long serialVersionUID = 1L;

    /**
     * Where in the source the exception occurred.
     */
    private final Position position;

    /**
     * Initializes error without cause.
     *
     * @param message The error message.
     * @param pos     Where the error occurred.
     */
    public SyntaxException(final String message, final Position pos) {
        this(message, pos, null);
    }

    /**
     * Dedicated constructor.
     *
     * @param message The error message.
     * @param pos     Where the error occurred.
     * @param cause   Optional previous exception.
     */
    public SyntaxException(final String message, final Position pos, final Throwable cause) {
        super(message, cause);
        position = pos;
    }

    /**
     * Returns the position where the error occurred.
     *
     * @return Return the position object.
     */
    public final Position getPosition() {
        return position;
    }

    @Override
    public final String toString() {
        final StringBuilder str = new StringBuilder("Syntax error: ");
        str.append(getMessage())
            .append(" at ")
            .append(position)
            .append("!");
        return str.toString();
    }

}
