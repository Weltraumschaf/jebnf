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
package de.weltraumschaf.jebnf.gfx;


import java.awt.Font;
import static org.junit.Assert.assertNotSame;
import static org.junit.Assert.assertSame;
import org.junit.Test;

/**
 *
 * @author Sven Strittmatter <weltraumschaf@googlemail.com>
 */
public class FontsTest {

    @Test public void create() {
        final Font font1 = Fonts.MONOSPACED.create();
        assertSame(font1, Fonts.MONOSPACED.create());
        final Font font2 = Fonts.MONOSPACED.create(12);
        assertSame(font2, Fonts.MONOSPACED.create());
        assertNotSame(font1, Fonts.MONOSPACED.create());
    }
}
