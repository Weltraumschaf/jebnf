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
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.font.FontRenderContext;
import java.awt.geom.Rectangle2D;
import static org.junit.Assert.*;
import org.junit.Test;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

/**
 *
 * @author Sven Strittmatter <weltraumschaf@googlemail.com>
 */
public class AbstractTextShapeTest {

    static class AbstractTextShapeStub extends AbstractTextShape {

        public AbstractTextShapeStub(final String text, final Font font) {
            super(text, font);
        }

        public AbstractTextShapeStub(final String text) {
            super(text);
        }

        @Override
        public void adjust(final Graphics2D graphic) {
            // stubbed method;
        }

    }

    @Test public void createObject() {
        AbstractTextShape sut = new AbstractTextShapeStub("foobar");
        assertSame(StringPainter.SANSERIF, sut.getFont());
        assertEquals("foobar", sut.getText());

        sut = new AbstractTextShapeStub("snafu", StringPainter.MONOSPACED);
        assertSame(StringPainter.MONOSPACED, sut.getFont());
        assertEquals("snafu", sut.getText());
    }

    @Test public void calculateWidth() {
        try {
            AbstractTextShape.calculateWidth(-1);
            fail("Expected exception not thrown!");
        } catch (IllegalArgumentException ex) {
            assertEquals("box width need to be greater or equal zero!", ex.getMessage());
        }

        assertEquals(1 * Size.DEFAULT_WIDTH, AbstractTextShape.calculateWidth(0));
        assertEquals(1 * Size.DEFAULT_WIDTH, AbstractTextShape.calculateWidth(1));
        assertEquals(1 * Size.DEFAULT_WIDTH, AbstractTextShape.calculateWidth(15));
        assertEquals(1 * Size.DEFAULT_WIDTH,
                     AbstractTextShape.calculateWidth(Size.DEFAULT_WIDTH - 11));
        assertEquals(2 * Size.DEFAULT_WIDTH,
                     AbstractTextShape.calculateWidth(Size.DEFAULT_WIDTH));
        assertEquals(3 * Size.DEFAULT_WIDTH,
                     AbstractTextShape.calculateWidth(3 * Size.DEFAULT_WIDTH - 11));
        assertEquals(4 * Size.DEFAULT_WIDTH,
                     AbstractTextShape.calculateWidth(3 * Size.DEFAULT_WIDTH));
    }

    @Test public void createStringPainter() {
        final Graphics2D graphics1 = mock(Graphics2D.class);
        final Graphics2D graphics2 = mock(Graphics2D.class);
        final AbstractTextShape sut = new AbstractTextShapeStub("");
        StringPainter painter = sut.createStringPainter(graphics1);
        assertSame(painter, sut.createStringPainter(graphics1));
        assertSame(painter, sut.createStringPainter(graphics1));
        assertNotSame(painter, sut.createStringPainter(graphics2));
        painter = sut.createStringPainter(graphics2);
        assertSame(painter, sut.createStringPainter(graphics2));
        assertNotSame(painter, sut.createStringPainter(graphics1));
    }

    @Test public void calculateTextSize() {
        final Graphics2D graphics = mock(Graphics2D.class);
        final FontRenderContext context = mock(FontRenderContext.class);
        when(graphics.getFontRenderContext()).thenReturn(context);
        final Font font = mock(Font.class);
        final String str = "fobar";
        final Rectangle2D rect = new Rectangle2D.Float(0, 0, 10, 20);
        when(font.getStringBounds(str, context)).thenReturn(rect);
        final AbstractTextShape sut = new AbstractTextShapeStub(str, font);
        assertEquals(Size.valueOf(10, 20), sut.calculateTextSize(graphics));
    }
}
