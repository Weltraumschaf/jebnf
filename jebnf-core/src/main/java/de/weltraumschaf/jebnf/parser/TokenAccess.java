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
 * Defines methods to access scanned input stream by tokens.
 *
 * @author Sven Strittmatter <weltraumschaf@googlemail.com>
 */
interface TokenAccess {

    /**
     * Returns if there are more tokens.
     *
     * This is always true if never {@link Scanner#nextToken()}
     * was called. No more tokens are indicated if the current token is
     * of type {@link TokenType#EOF}.
     *
     * @return True if {@link #nextToken()} will advance one more token.
     */
    boolean hasNextToken();

    /**
     * Start the scanning of the next token.
     *
     * This method should be called until {@link #hasNextToken()} returns false.
     *
     * @throws SyntaxException On syntax errors.
     * @throws IOException     On input stream IO errors.
     */
    void nextToken() throws SyntaxException, IOException;

     /**
     * Returns the next token without advancing the internal pointer (aka. lookahead).
     *
     * A call to {@link Scanner#nextToken()} will return this token ahead.
     *
     * @return Return peeked token.
     * @throws SyntaxException On syntax errors.
     * @throws IOException     On input stream IO errors.
     */
    Token peekToken() throws SyntaxException, IOException;

    /**
     * Returns the current scanned token.
     *
     * May be null if never {@link Scanner#nextToken()} was called.
     *
     * @return Returns always same instance until the current token is advanced by {@link #nextToken()}.
     */
    Token getCurrentToken();

    /**
     * Backtrack current token one token.
     */
    void backtrackToken();

    /**
     * Backtrack current token n token.
     *
     * @param count How many tokens to back track.
     */
    void backtrackToken(int count);

}
