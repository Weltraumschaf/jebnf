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

import static org.junit.Assert.assertEquals;
import org.junit.Test;

/**
 * Unit test for SyntaxtError.
 *
 * @author Sven Strittmatter <weltraumschaf@googlemail.com>
 */
public class SyntaxExceptionTest {

    @Test public void testToString() {
        SyntaxException error;
        error = new SyntaxException("foo bar", new Position(3, 4));
        assertEquals("Syntax error: foo bar at (3, 4)!", error.toString());
        error = new SyntaxException("foo bar", new Position(3, 4, "foo.ebnf"));
        assertEquals("Syntax error: foo bar at foo.ebnf (3, 4)!", error.toString());
    }

}
