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
 * Defines all token types.
 *
 * @author Sven Strittmatter <weltraumschaf@googlemail.com>
 */
public enum TokenType {

    /**
     * Special type for literal tokens.
     */
    LITERAL,

    /**
     * Special type for comment tokens.
     */
    COMMENT,

    /**
     * Special type for identifier tokens.
     */
    IDENTIFIER,

    /**
     * Special type for end of file token.
     */
    EOF,

    /**
     * Operator types for: ':==' or ':' or '='.
     */
    ASIGN,

    /**
     * Operator types for: '.' or ';'.
     */
    END_OF_RULE,

    /**
     * Operator types for: '('.
     */
    L_PAREN,

    /**
     * Operator types for: '['.
     */
    L_BRACK,

    /**
     * Operator types for: '{'.
     */
    L_BRACE,

    /**
     * Operator types for: ')'.
     */
    R_PAREN,

    /**
     * Operator types for: ']'.
     */
    R_BRACK,

    /**
     * Operator types for: '}'.
     */
    R_BRACE,

    /**
     * Operator types for: '..'.
     */
    RANGE,

    /**
     * Operator types for: '|'.
     */
    CHOICE;

}
