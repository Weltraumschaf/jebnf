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
 *
 * @author Sven Strittmatter <weltraumschaf@googlemail.com>
 */
public class StringHelperTest {

    @Test  public void testUnquoteString() {
        assertEquals("a test string", StringHelper.unquoteString("\"a test string\""));
        assertEquals("a \"test\" string", StringHelper.unquoteString("\"a \"test\" string\"")); // NOPMD
        assertEquals("a \"test\" string", StringHelper.unquoteString("\"a \"test\" string\""));
        assertEquals("a test string", StringHelper.unquoteString("'a test string'"));
        assertEquals("a 'test' string", StringHelper.unquoteString("'a 'test' string'"));
        assertEquals("a 'test' string", StringHelper.unquoteString("'a \'test\' string'"));
    }

}
