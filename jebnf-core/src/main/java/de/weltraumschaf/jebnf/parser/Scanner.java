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
 * TODO: Extract CharacterAccess interface.
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

    /**
     * Creates a {Position} from the current line and column in the input stream.
     *
     * @return Return always new instance.
     */
    Position createPosition();

    /**
     * Returns the character at the current cursor from the input stream.
     *
     * @return The current character.
     */
    char getCurrentCharacter();

    /**
     * Returns if there is a next character in the input stream.
     *
     * @return If there is one more character.
     */
    boolean hasNextCharacter();

    /**
     * Increments the character cursor.
     *
     * @throws IOException On IO errors caused by the input reader.
     */
    void nextCharacter() throws IOException;

    /**
     * Decrements the character cursor.
     */
    void backupCharacter();

    /**
     * Checks if the current character is a new line character (\n or \r)
     * and if it is increments the line counter and resets the column counter to 0.
     */
    void checkNewline();

    /**
     * Returns next character without advancing the cursor.
     *
     * @return Returns peeked character.
     * @throws IOException On IO errors of scanned source.
     */
    char peekCharacter() throws IOException;

    /**
     * Throws a {SyntaxException} with the current {Position} in the input stream.
     *
     * @param msg Describes raised error..
     * @throws SyntaxException On syntax errors.
     */
    void raiseError(final String msg) throws SyntaxException;

}
