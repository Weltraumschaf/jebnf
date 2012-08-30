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

import com.google.common.collect.Sets;
import java.util.Set;

/**
 * Helper methods for the scanner.
 *
 * @author Sven Strittmatter <weltraumschaf@googlemail.com>
 */
public final class CharacterHelper {

    /**
     * EBNF operators.
     */
    private static final Set<Character> OPERATORS = Sets.newHashSet('(', ')', '[', ']', '{',
        '}', '=', '.', ';', '|', ',', '-', ':');

    /**
     * Private constructor for pure static utility class.
     */
    private CharacterHelper() {
        super();
    }

    /**
     * Checks whether a character is inside a given character range (included).
     *
     * Throws IllegalArgumentException if end is less than start.
     *
     * @param character Character to check.
     * @param start Including range.
     * @param end Including range.
     * @return Return true if character is in range, unless false.
     */
    public static boolean isCharInRange(final char character, final char start, final char end) {
        if (end < start) {
            throw new IllegalArgumentException("End must be greater or equal than start!");
        }

        return start <= character && character <= end;
    }

    /**
     * Checks whether a character is a letter [a-zA-Z].
     *
     * @param character A single character.
     * @return Return true if character is a letter, unless false.
     */
    public static boolean isAlpha(final char character) {
        return isCharInRange(character, 'a', 'z') || isCharInRange(character, 'A', 'Z');
    }

    /**
     * Checks whether a character is a number [0-9].
     *
     * @param character A single character.
     *
     * @return Return true if character is a number, unless false.
     */
    public static boolean isNum(final char character) {
        return isCharInRange(character, '0', '9');
    }

    /**
     * Checks whether a character is a number or alpha [0-9a-zA-Z].
     *
     * @param character A single character.
     *
     * @return Return true if character is a letter or number, unless false.
     */
    public static boolean isAlphaNum(final char character) {
        return isAlpha(character) || isNum(character);
    }

    /**
     * Checks whether a character is a operator.
     *
     * @param character A single character.
     * @return Return true if character is an EBNF operator, unless false.
     */
    public static boolean isOperator(final char character) {
        return OPERATORS.contains(character);
    }

    /**
     * Checks whether a character is a whitespace.
     *
     * White spaces are \t, \n, \r, and ' '.
     *
     * @param character A single character.
     * @return Return true if character is a whitespace character, unless false.
     */
    public static boolean isWhiteSpace(final char character) {
        return ' ' == character || '\t' == character || '\n' == character || '\r' == character;
    }

    /**
     * Checks whether a character is a quote ["|'].
     *
     * @param character A single character.
     * @return Return true if character is a single or double quote character, unless false.
     */
    public static boolean isQuote(final char character) {
        return '\'' == character || '"' == character;
    }

    /**
     * Tests a given character if it is equal to one of the passed test characters.
     *
     * @param character Character to test.
     * @param characters Array of characters to test against.
     * @return Return true if character is characters array, unless false.
     */
    public static boolean isEquals(final char character, final char[] characters) {
        for (int i = 0; i < characters.length; ++i) {
            if (character == characters[i]) {
                return true;
            }
        }

        return false;
    }

}
