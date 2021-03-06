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

import java.awt.Graphics2D;
import java.awt.RenderingHints;
import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;
import org.junit.Test;

/**
 *
 * @author Sven Strittmatter <weltraumschaf@googlemail.com>
 */
public class AntialiaserTest {

    private final Graphics2D sut = ImageAndGraficsHelper.newGraphics();

    @Test
    public void turnOn() {
        final Object defaultValue = sut.getRenderingHint(Antialiaser.HINT_KEY);
        assertThat(defaultValue, is(not(RenderingHints.VALUE_ANTIALIAS_ON)));

        final Object oldValue = Antialiaser.turnOn(sut);
        assertEquals(RenderingHints.VALUE_ANTIALIAS_ON, sut.getRenderingHint(Antialiaser.HINT_KEY));
        assertEquals(defaultValue, oldValue);
    }

    @Test
    public void turnOff() {
        final Object defaultValue = sut.getRenderingHint(Antialiaser.HINT_KEY);
        assertThat(defaultValue, is(not(RenderingHints.VALUE_ANTIALIAS_ON)));

        final Object oldValue = Antialiaser.turnOff(sut);
        assertEquals(RenderingHints.VALUE_ANTIALIAS_OFF, sut.getRenderingHint(Antialiaser.HINT_KEY));
        assertEquals(defaultValue, oldValue);
    }

}