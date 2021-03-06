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

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;
import org.junit.Test;

/**
 * Test cases for {@link Size}.
 *
 * @author Sven Strittmatter <weltraumschaf@googlemail.com>
 */
public class SizeTest {

    final Size sut = Size.DEFAULT;

    @Test public void testGetWidth() {
        assertThat(sut.getWidth(), is(Size.DEFAULT_WIDTH));
    }

    @Test public void testSetWidth() {
        final int expectedWidth = 23;
        assertThat(sut.setWidth(expectedWidth).getWidth(), is(expectedWidth));
    }

    @Test public void testGetHeight() {
        assertThat(sut.getHeight(), is(Size.DEFAULT_HEIGHT));
    }

    @Test public void testSetHeight() {
        final int expectedHeight = 23;
        assertThat(sut.setHeight(expectedHeight).getHeight(), is(expectedHeight));
    }

    @Test public void testToString() {
        assertThat(Size.valueOf(23, 42).toString(), is("Size{width=23, height=42}"));
    }

    @Test public void testEquals() {
        assertThat(Size.DEFAULT.equals(null), is(false));
        assertThat(Size.DEFAULT.equals(new Object()), is(false));
        assertThat(Size.DEFAULT.equals(Size.DEFAULT), is(true));
        assertThat(Size.valueOf(23, 42).equals(Size.valueOf(23, 42)), is(true));
        assertThat(Size.valueOf(23, 42).equals(Size.valueOf(42, 23)), is(false));

    }

    @Test public void testHashCode() {
        assertThat(Size.DEFAULT.hashCode(), is(Size.DEFAULT.hashCode()));
        assertThat(Size.valueOf(23, 42).hashCode(), is(Size.valueOf(23, 42).hashCode()));
        assertThat(Size.valueOf(23, 42).hashCode(), is(not(Size.valueOf(42, 23).hashCode())));
    }

}
