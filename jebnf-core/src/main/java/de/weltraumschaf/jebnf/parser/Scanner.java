/*
 * LICENSE
 *
 * "THE BEER-WARE LICENSE" (Revision 42):
 * "Sven Strittmatter" <weltraumschaf@googlemail.com> wrote this file.
 * As long as you retain this notice you can do whatever you want with
 * this stuff. If we meet some day, and you think this stuff is worth it,
 * you can buy me a beer in return.
 *
 */

package de.weltraumschaf.jebnf.parser;

import java.io.Closeable;

/**
 * Interface for scanner.
 *
 * @author Sven Strittmatter <weltraumschaf@googlemail.com>
 */
public interface Scanner extends CharacterAccess, TokenAccess, Closeable {

    /**
     * End of file character.
     */
    char EOF = (char) 0;

    /**
     * Get the scanned file name.
     *
     * This may be any string, if not scanning from a real file.
     *
     * @return Return file name string.
     */
    String getFile();

    /**
     * Creates a {Position} from the current line and column in the input stream.
     *
     * @return Return always new instance.
     */
    Position createPosition();

    /**
     * Checks if the current character is a new line character (\n or \r)
     * and if it is increments the line counter and resets the column counter to 0.
     */
    void checkNewline();

    /**
     * Throws a {SyntaxException} with the current {Position} in the input stream.
     *
     * @param msg Describes raised error..
     * @throws SyntaxException On syntax errors.
     */
    void raiseError(final String msg) throws SyntaxException;

}
