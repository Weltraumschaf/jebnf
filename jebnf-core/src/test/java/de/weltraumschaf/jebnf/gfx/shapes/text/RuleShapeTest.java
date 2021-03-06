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
import static de.weltraumschaf.jebnf.gfx.shapes.ShapeFactory.rule;
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
public class RuleShapeTest {

    static class RuleStub extends RuleShape {

        Size calcTextSize = Size.DEFAULT;

        public RuleStub(final String text) {
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
        final String name = "foobar";
        final TextShape rule = rule(name);
        assertEquals(name, rule.getText());
        assertEquals(StringPainter.SANSERIF, rule.getFont());
    }

    @Test public void adjust() {
        final RuleStub rule = new RuleStub("foobar");
        rule.adjust(null);
        assertEquals(Size.valueOf(62, 31), rule.getSize());

        rule.setCalculatedTextSize(Size.valueOf(100, 16));
        rule.adjust(null);
        assertEquals(Size.valueOf(124, 31), rule.getSize());
    }

    @Test public void paint() {
        final String name = "foobar";
        final FontMetrics metrics = mock(FontMetrics.class);
        when(metrics.stringWidth(name)).thenReturn(80);
        when(metrics.getAscent()).thenReturn(12);
        when(metrics.getDescent()).thenReturn(4);

        final Graphics2D graphics = mock(Graphics2D.class);
        when(graphics.getFontMetrics()).thenReturn(metrics);

        final RuleStub rule = new RuleStub(name);
        rule.setCalculatedTextSize(Size.valueOf(100, 16));
        rule.paint(graphics);

        verify(graphics, times(1)).setColor(Color.BLACK);
        verify(graphics, times(1)).setFont(StringPainter.SANSERIF);
        verify(graphics, times(1)).drawString(name, 15, 19);
    }

}
