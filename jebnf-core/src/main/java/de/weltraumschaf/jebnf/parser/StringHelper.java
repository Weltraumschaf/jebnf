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

/**
 * Helper clas to deal wih strings.
 *
 * @author Sven Strittmatter <weltraumschaf@googlemail.com>
 */
public final class StringHelper {

    /**
     * Private constructor for pure static utility class.
     */
    private StringHelper() {
        super();
    }

    /**
     * Remove leading and trailing quotes from a string.
     *
     * @param str String to unquote.
     * @return     Return unquoted string.
     */
    public static String unquoteString(final String str) {
        int start = 0;
        int length = str.length();

        if (CharacterHelper.isQuote(str.charAt(start))) {
            start++;
        }

        if (CharacterHelper.isQuote(str.charAt(length - 1))) {
            length--;
        }

        return str.substring(start, length);
    }

}
