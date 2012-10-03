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

import static org.hamcrest.core.IsEqual.equalTo;
import static org.hamcrest.core.IsNot.not;
import static org.junit.Assert.*;
import org.junit.Test;

/**
 *
 * @author Sven Strittmatter <weltraumschaf@googlemail.com>
 */
public class LineTest {

    @Test public void create() {
        final Point start = Point.valueOf(5, 6);
        final Point end = Point.valueOf(50, 60);
        final Line sut = Line.valueOf(start, end);
        assertEquals(start, sut.getStart());
        assertEquals(end, sut.getEnd());
    }

    @Test public void testToString() {
        final Point start = Point.valueOf(5, 6);
        final Point end = Point.valueOf(50, 60);
        final Line sut = Line.valueOf(start, end);
        assertEquals(String.format("Line{start=%s, end=%s}", start, end), sut.toString());
    }

    @Test public void testHashCode() {
        final Line line1 = Line.valueOf(11, 22, 33, 44);
        final Line line2 = Line.valueOf(11, 22, 33, 44);
        final Line line3 = Line.valueOf(1, 2, 3, 4);

        assertThat(line1.hashCode(), equalTo(line2.hashCode()));
        assertThat(line1.hashCode(), not(equalTo(line3.hashCode())));
        assertThat(line2.hashCode(), not(equalTo(line3.hashCode())));
    }

    @Test public void testEquals() {
        final Line line1 = Line.valueOf(11, 22, 33, 44);
        final Line line2 = Line.valueOf(11, 22, 33, 44);
        final Line line3 = Line.valueOf(1, 2, 3, 4);
        final Line nullLine = null;

        assertFalse(line1.equals(nullLine));
        assertFalse(line2.equals(nullLine));
        assertFalse(line3.equals(nullLine));

        assertFalse(line1.equals(new Object()));
        assertFalse(line2.equals(new Object()));
        assertFalse(line3.equals(new Object()));

        assertThat(line1, equalTo(line2));
        assertThat(line1, not(equalTo(line3)));
        assertThat(line2, not(equalTo(line3)));
    }

}
