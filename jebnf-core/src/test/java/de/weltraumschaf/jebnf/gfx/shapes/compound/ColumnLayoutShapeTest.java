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
import static de.weltraumschaf.jebnf.gfx.shapes.ShapeFactory.column;
import static de.weltraumschaf.jebnf.gfx.shapes.ShapeFactory.empty;
import de.weltraumschaf.jebnf.gfx.shapes.other.RectangularShape;
import java.awt.Graphics2D;
import static org.junit.Assert.*;
import org.junit.Test;
import static org.mockito.Mockito.*;

/**
 *
 * @author Sven Strittmatter <weltraumschaf@googlemail.com>
 */
public class ColumnLayoutShapeTest {

    @Test public void testGetSize() {
        final ColumnLayoutShape column = column();
        Size size = column.getSize();
        assertEquals(0, size.getWidth());
        assertEquals(0, size.getHeight());

        column.append(empty());
        column.adjust(mock(Graphics2D.class, CALLS_REAL_METHODS));
        size = column.getSize();
        assertEquals(Size.DEFAULT_WIDTH, size.getWidth());
        assertEquals(Size.DEFAULT_HEIGHT, size.getHeight());

        column.append(empty());
        column.adjust(mock(Graphics2D.class, CALLS_REAL_METHODS));
        size = column.getSize();
        assertEquals(Size.DEFAULT_WIDTH, size.getWidth());
        assertEquals(2 * Size.DEFAULT_WIDTH, size.getHeight());

        column.append(empty());
        column.adjust(mock(Graphics2D.class, CALLS_REAL_METHODS));
        size = column.getSize();
        assertEquals(Size.DEFAULT_WIDTH, size.getWidth());
        assertEquals(3 * Size.DEFAULT_HEIGHT, size.getHeight());

        column.adjust(mock(Graphics2D.class, CALLS_REAL_METHODS));
        column.set(0, empty());
        size = column.getSize();
        assertEquals(Size.DEFAULT_WIDTH, size.getWidth());
        assertEquals(3 * Size.DEFAULT_HEIGHT, size.getHeight());
    }

    @Test public void testSetShape() {
        final Shape empty0 = empty();
        final Shape empty1 = empty();
        final Shape empty2 = empty();
        final Shape empty3 = empty();
        final Shape empty7 = empty();
        final ColumnLayoutShape column = column();
        assertEquals(0, column.countShapes());

        column.append(empty0, empty1, empty2, empty3);
        assertEquals(4, column.countShapes());

        column.set(7, empty7);
        assertEquals(8, column.countShapes());
        assertSame(empty0, column.get(0));
        assertSame(empty1, column.get(1));
        assertSame(empty2, column.get(2));
        assertSame(empty3, column.get(3));
        assertSame(empty7, column.get(7));
        assertTrue(column.get(4) instanceof RectangularShape);
        assertTrue(column.get(5) instanceof RectangularShape);
        assertTrue(column.get(6) instanceof RectangularShape);
    }

    @Test public void testAppendShape() {
        final ColumnLayoutShape column = column();
        assertEquals(0, column.countShapes());
        column.append(empty());
        assertEquals(1, column.countShapes());
        column.append(empty());
        assertEquals(2, column.countShapes());
        column.append(empty());
        assertEquals(3, column.countShapes());
    }

    @Test public void testPaint() {
        final ColumnLayoutShape column = column();
        final Graphics2D graphics = mock(Graphics2D.class);
        column.paint(graphics);

        final Shape shape1 = mock(Shape.class);
        when(shape1.getSize()).thenReturn(Size.DEFAULT);
        column.append(shape1);
        final Shape shape2 = mock(Shape.class);
        when(shape2.getSize()).thenReturn(Size.DEFAULT);
        column.append(shape2);
        column.paint(graphics);
        verify(shape1, times(1)).paint(graphics);
        verify(shape2, times(1)).paint(graphics);
    }
}
