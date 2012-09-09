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

import java.awt.Graphics2D;
import java.awt.RenderingHints;
import static org.junit.Assert.*;
import org.junit.Test;

/**
 *
 * @author Sven Strittmatter <weltraumschaf@googlemail.com>
 */
public class AntialiaserTest {

    private final Graphics2D sut = Helper.newGraphics();

    @Test
    public void turnOn() {
        assertEquals(RenderingHints.VALUE_ANTIALIAS_DEFAULT, sut.getRenderingHint(Antialiaser.HINT_KEY));
        Antialiaser.turnOn(sut);
        assertEquals(RenderingHints.VALUE_ANTIALIAS_ON, sut.getRenderingHint(Antialiaser.HINT_KEY));
    }

    @Test
    public void turnOff() {
        assertEquals(RenderingHints.VALUE_ANTIALIAS_DEFAULT, sut.getRenderingHint(Antialiaser.HINT_KEY));
        Antialiaser.turnOff(sut);
        assertEquals(RenderingHints.VALUE_ANTIALIAS_OFF, sut.getRenderingHint(Antialiaser.HINT_KEY));
    }

}