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

package de.weltraumschaf.jebnf.gfx.shapes.compound;

import de.weltraumschaf.jebnf.gfx.Size;
import de.weltraumschaf.jebnf.gfx.shapes.Shape;
import static de.weltraumschaf.jebnf.gfx.shapes.ShapeFactory.empty;
import static de.weltraumschaf.jebnf.gfx.shapes.ShapeFactory.row;
import de.weltraumschaf.jebnf.gfx.shapes.other.Empty;
import java.awt.Graphics2D;
import static org.junit.Assert.*;
import org.junit.Test;
import static org.mockito.Mockito.*;

/**
 *
 * @author Sven Strittmatter <weltraumschaf@googlemail.com>
 */
public class SequenceTest {

    @Test public void testGetSize() {
        final RowLayout sequence = row();
        Size size = sequence.getSize();
        assertEquals(0, size.getWidth());
        assertEquals(0, size.getHeight());

        sequence.append(empty());
        sequence.adjust(mock(Graphics2D.class, CALLS_REAL_METHODS));
        size = sequence.getSize();
        assertEquals(Size.DEFAULT_WIDTH, size.getWidth());
        assertEquals(Size.DEFAULT_HEIGHT, size.getHeight());

        sequence.append(empty());
        sequence.adjust(mock(Graphics2D.class, CALLS_REAL_METHODS));
        size = sequence.getSize();
        assertEquals(2 * Size.DEFAULT_WIDTH, size.getWidth());
        assertEquals(Size.DEFAULT_WIDTH, size.getHeight());

        sequence.append(empty());
        sequence.adjust(mock(Graphics2D.class, CALLS_REAL_METHODS));
        size = sequence.getSize();
        assertEquals(3 * Size.DEFAULT_WIDTH, size.getWidth());
        assertEquals(Size.DEFAULT_HEIGHT, size.getHeight());

        sequence.adjust(mock(Graphics2D.class, CALLS_REAL_METHODS));
        sequence.set(0, empty());
        size = sequence.getSize();
        assertEquals(3 * Size.DEFAULT_WIDTH, size.getWidth());
        assertEquals(Size.DEFAULT_HEIGHT, size.getHeight());
    }

    @Test public void testSetShape() {
        final Shape empty0 = empty();
        final Shape empty1 = empty();
        final Shape empty2 = empty();
        final Shape empty3 = empty();
        final Shape empty7 = empty();
        final RowLayout sequence = row();
        assertEquals(0, sequence.countShapes());

        sequence.append(empty0, empty1, empty2, empty3);
        assertEquals(4, sequence.countShapes());

        sequence.set(7, empty7);
        assertEquals(8, sequence.countShapes());
        assertSame(empty0, sequence.get(0));
        assertSame(empty1, sequence.get(1));
        assertSame(empty2, sequence.get(2));
        assertSame(empty3, sequence.get(3));
        assertSame(empty7, sequence.get(7));
        assertTrue(sequence.get(4) instanceof Empty);
        assertTrue(sequence.get(5) instanceof Empty);
        assertTrue(sequence.get(6) instanceof Empty);
    }

    @Test public void testAppendShape() {
        final RowLayout sequence = row();
        assertEquals(0, sequence.countShapes());
        sequence.append(empty());
        assertEquals(1, sequence.countShapes());
        sequence.append(empty());
        assertEquals(2, sequence.countShapes());
        sequence.append(empty());
        assertEquals(3, sequence.countShapes());
    }

    @Test public void testPaint() {
        final RowLayout sequence = row();
        final Graphics2D graphics = mock(Graphics2D.class);
        sequence.paint(graphics);

        final Shape shape1 = mock(Shape.class);
        when(shape1.getSize()).thenReturn(new Size());
        sequence.append(shape1);
        final Shape shape2 = mock(Shape.class);
        when(shape2.getSize()).thenReturn(new Size());
        sequence.append(shape2);
        sequence.paint(graphics);
        verify(shape1, times(1)).paint(graphics);
        verify(shape2, times(1)).paint(graphics);
    }
}
