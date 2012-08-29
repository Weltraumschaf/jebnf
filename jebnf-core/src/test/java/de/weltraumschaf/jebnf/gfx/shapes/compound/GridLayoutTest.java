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
import static de.weltraumschaf.jebnf.gfx.shapes.ShapeFactory.*;
import java.awt.Graphics2D;
import static org.junit.Assert.*;
import org.junit.Test;
import static org.mockito.Mockito.*;

/**
 *
 * @author Sven Strittmatter <weltraumschaf@googlemail.com>
 */
public class GridLayoutTest {

    @Test public void testGetSize() {
        Size size;
        final GridLayout grid = grid();

        size = grid.getSize();
        assertEquals(0, size.getWidth());
        assertEquals(0, size.getHeight());

        grid.append(column().append(empty(), empty(), empty()));
        grid.adjust(mock(Graphics2D.class, CALLS_REAL_METHODS));
        size = grid.getSize();
        assertEquals(Size.DEFAULT_WIDTH, size.getWidth());
        assertEquals(3 * Size.DEFAULT_HEIGHT, size.getHeight());

        grid.append(column().append(empty(), empty(), empty()));
        grid.adjust(mock(Graphics2D.class, CALLS_REAL_METHODS));
        size = grid.getSize();
        assertEquals(2 * Size.DEFAULT_WIDTH, size.getWidth());
        assertEquals(3 * Size.DEFAULT_HEIGHT, size.getHeight());

        grid.append(column().append(empty(), empty(), empty(), empty()));
        grid.adjust(mock(Graphics2D.class, CALLS_REAL_METHODS));
        size = grid.getSize();
        assertEquals(3 * Size.DEFAULT_WIDTH, size.getWidth());
        assertEquals(4 * Size.DEFAULT_WIDTH, size.getHeight());

    }

    @Test public void testPaintGrid() {
        final GridLayout grid = grid();
        final Graphics2D graphic = mock(Graphics2D.class);
        final ColumnLayout row1 = mock(ColumnLayout.class);
        when(row1.getSize()).thenReturn(new Size());
        grid.append(row1);
        final ColumnLayout row2 = mock(ColumnLayout.class);
        when(row2.getSize()).thenReturn(new Size());
        grid.append(row2);
        grid.paint(graphic);
        verify(row1, times(1)).paint(graphic);
        verify(row2, times(1)).paint(graphic);
    }

    @Test public void testSetShape() {
        final GridLayout grid = grid();
        assertEquals(0, grid.countCols());
        assertEquals(0, grid.counRows());

        grid.set(0, 0, empty());
        assertEquals(1, grid.countCols());
        assertEquals(1, grid.counRows());

        grid.set(0, 1, empty());
        assertEquals(1, grid.countCols());
        assertEquals(2, grid.counRows());

        grid.set(1, 0, empty());
        assertEquals(2, grid.countCols());
        assertEquals(2, grid.counRows());

        grid.set(1, 1, empty());
        assertEquals(2, grid.countCols());
        assertEquals(2, grid.counRows());
    }

    @Test public void testAppendColumnLayout() {
        final GridLayout grid = grid();
        assertEquals(0, grid.countCols());
        assertEquals(0, grid.counRows());

        grid.append(column(empty()));
        assertEquals(1, grid.countCols());
        assertEquals(1, grid.counRows());

        grid.append(column(empty()), column(empty()));
        assertEquals(3, grid.countCols());
        assertEquals(1, grid.counRows());
    }

    @Test public void throwExceptionsOnBadRowOrColumnIndex() {
        final GridLayout grid = grid();

        try {
            grid.get(5);
            fail("Expected exception not thrown!");
        } catch (IllegalArgumentException ex) {
            assertEquals("The column at columnIndex 5 is not present!", ex.getMessage());
        }

        grid.set(0, 0, empty());

        try {
            grid.get(0, 5);
            fail("Expected exception not thrown!");
        } catch (IllegalArgumentException ex) {
            assertEquals("The shape at columnIndex 0 and rowIndex 5 is not present!", ex.getMessage());
        }
    }

}
