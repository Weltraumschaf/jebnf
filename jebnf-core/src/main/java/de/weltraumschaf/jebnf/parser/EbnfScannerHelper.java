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

import static de.weltraumschaf.jebnf.parser.CharacterHelper.*;
import java.io.IOException;

/**
 * Utilities for scanning tokens.
 *
 * TODO Methods should accept {@link Scanner} instead of {@link EbnfScanner}.
 *
 * @author Sven Strittmatter <weltraumschaf@googlemail.com>
 */
final class EbnfScannerHelper {

    /**
     * Special characters allowed in identifiers.
     */
    private static final char[] SPECIAL_CHARS =  {'-', '_'};

    /**
     * Private constructor for pure static utility class.
     */
    private EbnfScannerHelper() {
        super();
    }

    /**
     * Scans an identifier [a-zA-Z\-_].
     *
     * @param scanner Scanner to get characters from.
     * @return Return scanned identifier token.
     * @throws IOException On input stream IO errors.
     */
    public static Token scanIdentifier(final EbnfScanner scanner) throws IOException {
        final Position position = scanner.createPosition();
        final StringBuilder value = new StringBuilder();
        value.append(scanner.getCurrentCharacter());

        while (scanner.hasNextCharacter()) {
            scanner.nextCharacter();

            if (isAlphaNum(scanner.getCurrentCharacter())
                    || isEquals(scanner.getCurrentCharacter(), SPECIAL_CHARS)) {
                value.append(scanner.getCurrentCharacter());
            } else {
                scanner.backupCharacter();
                break;
            }
        }

        return new Token(TokenType.IDENTIFIER, value.toString(), position);
    }

    /**
     * Scans a literal (any character inside single or double quotes.
     *
     * @param scanner Scanner to get characters from.
     * @return Return scanned literal token.
     * @throws IOException On input stream IO errors.
     */
    public static Token scanLiteral(final EbnfScanner scanner) throws IOException {
        final Position position = scanner.createPosition();
        final char start = scanner.getCurrentCharacter();
        final StringBuilder value = new StringBuilder();
        value.append(start);

        while (scanner.hasNextCharacter()) {
            scanner.nextCharacter();
            value.append(scanner.getCurrentCharacter());

            // Ensure that a lieral opened with " is not temrinated by ' and vice versa.
            if (isQuote(scanner.getCurrentCharacter()) && scanner.getCurrentCharacter() == start) {
                break;
            }
        }

        return new Token(TokenType.LITERAL, value.toString(), position);
    }

    /**
     * Scans a comment (any character inside '(*' and '*)'.
     *
     * @param scanner Scanner to get characters from.
     * @return Return scanned comment token.
     * @throws IOException On input stream IO errors.
     */
    public static Token scanComment(final EbnfScanner scanner) throws IOException {
        final Position postition = scanner.createPosition();
        final StringBuilder value = new StringBuilder();
        value.append(scanner.getCurrentCharacter());

        while (scanner.hasNextCharacter()) {
            scanner.nextCharacter();
            value.append(scanner.getCurrentCharacter());

            if ('*' == scanner.getCurrentCharacter() && ')' == scanner.peekCharacter()) {
                scanner.nextCharacter();
                value.append(scanner.getCurrentCharacter());
                break;
            }

            scanner.checkNewline(); // // Comments cann be multiline.
        }

        return new Token(TokenType.COMMENT, value.toString(), postition);
    }

    /**
     * Scans an operator.
     *
     * @param scanner Scanner to get characters from.
     * @return Return scanned operator token.
     * @throws SyntaxException On syntax errors.
     * @throws IOException On input stream IO errors.
     */
    public static Token scanOperator(final EbnfScanner scanner) throws SyntaxException, IOException {
        final Position position   = scanner.createPosition();
        final StringBuilder value = new StringBuilder();
        value.append(scanner.getCurrentCharacter());
        scanner.peekCharacter(); // FIXME If remove tests fail. Must not hapen. This call must be removed.
        TokenType type = null;

        switch (scanner.getCurrentCharacter()) {
            case ':':
                type = scanColonOperator(scanner, value);
                break;
            case '=':
                type = TokenType.ASIGN;
                break;
            case '.':
                type = scanDotOperator(scanner, value);
                break;
            case ';':
                type = TokenType.END_OF_RULE;
                break;
            case '(':
                type = TokenType.L_PAREN;
                break;
            case '[':
                type = TokenType.L_BRACK;
                break;
            case '{':
                type = TokenType.L_BRACE;
                break;
            case ')':
                type = TokenType.R_PAREN;
                break;
            case ']':
                type = TokenType.R_BRACK;
                break;
            case '}':
                type = TokenType.R_BRACE;
                break;
            case '|':
                type = TokenType.CHOICE; break;
            default:
                scanner.raiseError(String.format("Unexpected operator character '%s'",
                                                 scanner.getCurrentCharacter()));
        }

        return new Token(type, value.toString(), position);
    }

    /**
     * Scans all operators beginning with ':' character.
     *
     * Colon operators are: ':' and ':=='.
     *
     * @param scanner Scanner to get characters from.
     * @param value   Operator token as string.
     * @return Return type of token.
     * @throws SyntaxException On syntax errors.
     * @throws IOException On input stream IO errors.
     */
    private static TokenType scanColonOperator(final EbnfScanner scanner,
                                               final StringBuilder value) throws SyntaxException,
                                                                                 IOException {
        TokenType type;

        if ('=' == scanner.peekCharacter()) {
            scanner.nextCharacter();
            value.append(scanner.getCurrentCharacter());
            scanner.nextCharacter();

            if (scanner.getCurrentCharacter() != '=') {
                scanner.raiseError(String.format("Expecting '=' but seen '%s'",
                                                 scanner.getCurrentCharacter()));
            }

            value.append(scanner.getCurrentCharacter());
            type = TokenType.ASIGN;
        } else {
            type = TokenType.ASIGN;
        }

        return type;
    }

    /**
     * Scans all operators beginning with .:' character.
     *
     * Colon operators are: '.' and '..'.
     *
     * @param scanner Scanner to get characters from.
     * @param value Operator token as string.
     * @return Return type of token.
     * @throws IOException On input stream IO errors.
     */
    private static TokenType scanDotOperator(final EbnfScanner scanner,
                                             final StringBuilder value) throws IOException {
        TokenType type;
        // range or end of rule
        if ('.' == scanner.peekCharacter()) {
            scanner.nextCharacter();
            value.append(scanner.getCurrentCharacter());
            type = TokenType.RANGE;
        } else {
            type = TokenType.END_OF_RULE;
        }

        return type;
    }

}
