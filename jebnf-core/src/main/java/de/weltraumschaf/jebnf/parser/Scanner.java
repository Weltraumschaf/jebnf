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

import java.io.IOException;

/**
 * Interface for scanner.
 *
 * @author Sven Strittmatter <weltraumschaf@googlemail.com>
 */
public interface Scanner {

    /**
     * Get the scanned file name.
     *
     * This may be any string, if not scanning from a real file.
     *
     * @return Return file name string.
     */
    String getFile();

    /**
     * Close the scanned input.
     *
     * @throws IOException On IO errors.
     */
    void close() throws IOException;

    /**
     * Return true if the scanner has more tokens.
     *
     * @return Return true if ther are more tokens.
     */
    boolean hasNextToken();

    /**
     * Let the scanner scans the next token.
     *
     * @throws SyntaxException On syntax errors.
     * @throws IOException On IO errors.
     */
    void nextToken() throws SyntaxException, IOException;

    /**
     * Peeks one token w/o advancing current token.
     *
     * @return Return peeked token.
     * @throws SyntaxException On syntax errors.
     * @throws IOException On IO errors.
     */
    Token peekToken() throws SyntaxException, IOException;

    /**
     * Get the current scanned token.
     *
     * @return Return current token object.
     */
    Token getCurrentToken();

    /**
     * Backtrack current token one token.
     *
     * TODO: Return void.
     *
     * @return Return token.
     */
    Token backtrackToken();

    /**
     * Backtrack current token n token.
     *
     * TODO: Return void.
     *
     * @param count How many tokens to back track.
     * @return Return token.
     */
    Token backtrackToken(int count);

}
