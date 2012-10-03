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
import de.weltraumschaf.jebnf.gfx.Strokes;
import java.awt.Color;
import java.awt.Graphics2D;
import org.junit.Test;
import static org.mockito.Mockito.*;

/**
 *
 * @author Sven Strittmatter <weltraumschaf@googlemail.com>
 */
public class AbstractEndpointShapeTest {

    static class AbstractEndpointImpl extends AbstractEndpointShape {

    }

    @Test public void paint() {
        final Graphics2D graphics = mock(Graphics2D.class);
        final AbstractEndpointShape sut = new AbstractEndpointImpl();

        sut.setPosition(Point.ZERO);
        sut.setSize(Size.DEFAULT);
        sut.paint(graphics);

        verify(graphics, times(1)).setColor(Color.BLACK);
        verify(graphics, times(1)).setStroke(Strokes.createForRail(true));
        verify(graphics, times(1)).drawLine(15, 3, 15, 27);
    }
}
