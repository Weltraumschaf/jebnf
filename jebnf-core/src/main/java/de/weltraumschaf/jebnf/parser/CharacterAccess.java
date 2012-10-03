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

import java.io.IOException;

/**
 * Defines methods to access scanned input stream by characters.
 *
 * @author Sven Strittmatter <weltraumschaf@googlemail.com>
 */
interface CharacterAccess {

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
     * Returns next character without advancing the cursor.
     *
     * @return Returns peeked character.
     * @throws IOException On IO errors of scanned source.
     */
    char peekCharacter() throws IOException;

}
