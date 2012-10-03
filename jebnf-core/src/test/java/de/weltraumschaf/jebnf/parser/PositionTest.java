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
 * Unit test for Position.
 *
 * @author Sven Strittmatter <weltraumschaf@googlemail.com>
 */
public class PositionTest {

    @Test public void testToString() {
        Position position;
        position = new Position(5, 10);
        assertEquals("(5, 10)", position.toString());

        position = new Position(7, 8, "/foo/bar/baz.ebnf");
        assertEquals("/foo/bar/baz.ebnf (7, 8)", position.toString());
    }

}
