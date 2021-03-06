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
package de.weltraumschaf.jebnf.gfx.shapes.text;

import de.weltraumschaf.jebnf.gfx.Size;
import de.weltraumschaf.jebnf.gfx.StringPainter;
import de.weltraumschaf.jebnf.gfx.Strokes;
import static de.weltraumschaf.jebnf.gfx.shapes.ShapeFactory.terminal;
import java.awt.Color;
import java.awt.FontMetrics;
import java.awt.Graphics2D;
import static org.junit.Assert.assertEquals;
import org.junit.Test;
import static org.mockito.Mockito.*;

/**
 *
 * @author Sven Strittmatter <weltraumschaf@googlemail.com>
 */
public class TerminalShapeTest {

    static class TerminalStub extends TerminalShape {

        Size calcTextSize = Size.DEFAULT;

        public TerminalStub(final String text) {
            super(text);
        }

        @Override
        protected Size calculateTextSize(final Graphics2D graphic) {
            return calcTextSize;
        }

        void setCalculatedTextSize(final Size calcTextSize) {
            this.calcTextSize = calcTextSize;
        }

    }

    @Test public void font() {
        final TextShape term = terminal("foobar");
        assertEquals("foobar", term.getText());
        assertEquals(StringPainter.MONOSPACED, term.getFont());
    }

    @Test public void calcBoxSize() {
        final TerminalStub term = new TerminalStub("foobar");
        assertEquals(Size.valueOf(51, 35), term.calcBoxSize(null));
        term.setCalculatedTextSize(Size.valueOf(100, 16));
        assertEquals(Size.valueOf(120, 20), term.calcBoxSize(null));
    }

    @Test public void adjust() {
        final TerminalStub term = new TerminalStub("foobar");
        term.adjust(null);
        assertEquals(Size.valueOf(62, 31), term.getSize());

        term.setCalculatedTextSize(Size.valueOf(100, 16));
        term.adjust(null);
        assertEquals(Size.valueOf(155, 31), term.getSize());
    }

    @Test public void paint() {
        final String value = "foobar";
        final FontMetrics metrics = mock(FontMetrics.class);
        when(metrics.stringWidth(value)).thenReturn(80);
        when(metrics.getAscent()).thenReturn(12);
        when(metrics.getDescent()).thenReturn(4);

        final Graphics2D graphics = mock(Graphics2D.class);
        when(graphics.getFontMetrics()).thenReturn(metrics);

        final TerminalStub term = new TerminalStub(value);
        term.setCalculatedTextSize(Size.valueOf(100, 16));
        term.paint(graphics);

        verify(graphics, times(1)).setColor(Color.BLACK);
        verify(graphics, times(1)).setStroke(Strokes.createForBox());
        verify(graphics, times(1)).drawRoundRect(17, 5, 120, 20, 25, 25);
        verify(graphics, times(1)).setStroke(Strokes.createForRail());
        verify(graphics, times(1)).drawLine(-1, 15, 17, 15);
        verify(graphics, times(1)).drawLine(137, 15, 155, 15);
        verify(graphics, times(1)).setFont(StringPainter.MONOSPACED);
        verify(graphics, times(1)).drawString(value, 37, 19);
    }

}
