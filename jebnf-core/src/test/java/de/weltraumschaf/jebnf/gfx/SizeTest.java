/*
 * LICENSE
 *
 * "THE BEER-WARE LICENSE" (Revision 42):
 * "Sven Strittmatter" <weltraumschaf@googlemail.com> wrote this file.
 * As long as you retain this notice you can do whatever you want with
 * this stuff. If we meet some day, and you think this stuff is worth it,
 * you can buy me a beer in return.
 *
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

    final Size sut = new Size();

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
        assertThat(new Size(23, 42).toString(), is("Size{width=23, height=42}"));
    }

    @Test public void testEquals() {
        assertThat(new Size().equals(new Size()), is(true));
        assertThat(new Size(23, 42).equals(new Size(23, 42)), is(true));
        assertThat(new Size(23, 42).equals(new Size(42, 23)), is(false));

    }

    @Test public void testHashCode() {
        assertThat(new Size().hashCode(), is(new Size().hashCode()));
        assertThat(new Size(23, 42).hashCode(), is(new Size(23, 42).hashCode()));
        assertThat(new Size(23, 42).hashCode(), is(not(new Size(42, 23).hashCode())));
    }

}
