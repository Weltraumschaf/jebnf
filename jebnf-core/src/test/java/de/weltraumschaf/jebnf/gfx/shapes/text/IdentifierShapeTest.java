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
import static de.weltraumschaf.jebnf.gfx.shapes.ShapeFactory.identifier;
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
public class IdentifierShapeTest {

    static class IdentifierStub extends IdentifierShape {

        Size calcTextSize = Size.DEFAULT;

        public IdentifierStub(final String text) {
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
        final String value = "foobar";
        final TextShape ident = identifier(value);
        assertEquals(value, ident.getText());
        assertEquals(StringPainter.SANSERIFIT, ident.getFont());
    }

    @Test public void calcBoxSize() {
        final IdentifierStub ident = new IdentifierStub("foobar");
        assertEquals(Size.valueOf(41, 31), ident.calcBoxSize(null));
        ident.setCalculatedTextSize(Size.valueOf(100, 16));
        assertEquals(Size.valueOf(110, 16), ident.calcBoxSize(null));
    }

    @Test public void adjust() {
        final IdentifierStub ident = new IdentifierStub("foobar");
        ident.adjust(null);
        assertEquals(Size.valueOf(62, 31), ident.getSize());

        ident.setCalculatedTextSize(Size.valueOf(100, 16));
        ident.adjust(null);
        assertEquals(Size.valueOf(124, 31), ident.getSize());
    }

    @Test public void paint() {
        final String value = "foobar";
        final FontMetrics metrics = mock(FontMetrics.class);
        when(metrics.stringWidth(value)).thenReturn(80);
        when(metrics.getAscent()).thenReturn(12);
        when(metrics.getDescent()).thenReturn(4);

        final Graphics2D graphics = mock(Graphics2D.class);
        when(graphics.getFontMetrics()).thenReturn(metrics);

        final IdentifierStub ident = new IdentifierStub(value);
        ident.setCalculatedTextSize(Size.valueOf(100, 16));
        ident.paint(graphics);

        verify(graphics, times(1)).setColor(Color.BLACK);
        verify(graphics, times(1)).setStroke(Strokes.createForBox());
        verify(graphics, times(1)).drawRect(7, 7, 110, 16);
        verify(graphics, times(1)).setStroke(Strokes.createForRail());
        verify(graphics, times(1)).drawLine(-1, 15, 7, 15);
        verify(graphics, times(1)).drawLine(117, 15, 124, 15);
        verify(graphics, times(1)).setFont(StringPainter.SANSERIFIT);
        verify(graphics, times(1)).drawString(value, 22, 19);
    }

}
