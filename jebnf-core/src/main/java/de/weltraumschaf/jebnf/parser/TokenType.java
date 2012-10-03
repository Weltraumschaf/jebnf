/*
 * LICENSE
 *
 * "THE BEER-WARE LICENSE" (Revision 43):
 * "Sven Strittmatter" <weltraumschaf(at)googlemail(dot)com> wrote this file.
 * As long as you retain this notice you can do whatever you want with
 * this stuff. If we meet some day, and you think this stuff is worth it,
 * you can buy me a non alcohol free beer in return.
 *
 * Copyright (C) 2012 "Sven Strittmatter" <weltraumschaf(at)googlemail(dot)com>
 */
package de.weltraumschaf.jebnf.parser;

import java.util.Arrays;
import java.util.List;

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
    ASSIGN,

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

    /**
     * Possible token strings for assign tokens.
     */
    public static final List<String> ASSIGN_STRINGS = Arrays.asList("=", ":", ":==");

    /**
     * Possible token strings for end of rule token.
     */
    public static final List<String> END_OF_RULE_STRINGS = Arrays.asList(".", ";");

}
