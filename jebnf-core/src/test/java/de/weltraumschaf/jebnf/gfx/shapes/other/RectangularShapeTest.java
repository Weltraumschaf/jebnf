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
package de.weltraumschaf.jebnf.gfx.shapes.other;

import de.weltraumschaf.jebnf.gfx.Point;
import de.weltraumschaf.jebnf.gfx.Size;
import static de.weltraumschaf.jebnf.gfx.shapes.ShapeFactory.empty;
import java.awt.Color;
import java.awt.Graphics2D;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import org.junit.Test;
import static org.mockito.Mockito.*;

/**
 *
 * @author Sven Strittmatter <weltraumschaf@googlemail.com>
 */
public class RectangularShapeTest {
    private final Graphics2D graphics = mock(Graphics2D.class);
    private final RectangularShape empty         = empty();
    private final Point pos           = empty.getPosition();
    private final Size size           = empty.getSize();

    @Test public void paintNotTransparent() {
        assertFalse(empty.isDebug());
        assertFalse(empty.isTransparent());
        empty.paint(graphics);
        verify(graphics, times(1)).setColor(Color.WHITE);
        verify(graphics, times(1)).fillRect(pos.getX(), pos.getY(), size.getWidth(), size.getHeight());
    }

    @Test public void paintTransparent() {
        assertFalse(empty.isDebug());
        empty.setTransparent(true);
        assertTrue(empty.isTransparent());
        empty.paint(graphics);
        verify(graphics, never()).setColor(Color.WHITE);
        verify(graphics, never()).fillRect(pos.getX(), pos.getY(), size.getWidth(), size.getHeight());
    }

    @Test public void paintDebug() {
        empty.setTransparent(true);
        assertTrue(empty.isTransparent());
        empty.setDebug(true);
        assertTrue(empty.isDebug());
        empty.paint(graphics);
        verify(graphics, times(1)).drawLine(0, 15, 30, 15);
        verify(graphics, times(1)).drawLine(15, 0, 15, 30);
        verify(graphics, times(1)).drawRect(0, 0, 30, 30);
    }
}
