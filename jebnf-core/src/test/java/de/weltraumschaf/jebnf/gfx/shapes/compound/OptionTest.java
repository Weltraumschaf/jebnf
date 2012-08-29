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

import de.weltraumschaf.jebnf.gfx.shapes.Shape;
import static de.weltraumschaf.jebnf.gfx.shapes.ShapeFactory.column;
import static de.weltraumschaf.jebnf.gfx.shapes.ShapeFactory.terminal;
import de.weltraumschaf.jebnf.gfx.shapes.curves.CurveNE;
import de.weltraumschaf.jebnf.gfx.shapes.curves.CurveNW;
import de.weltraumschaf.jebnf.gfx.shapes.forkes.HForkSE;
import de.weltraumschaf.jebnf.gfx.shapes.forkes.HForkSW;
import de.weltraumschaf.jebnf.gfx.shapes.other.Empty;
import de.weltraumschaf.jebnf.gfx.shapes.other.StraightNS;
import de.weltraumschaf.jebnf.gfx.shapes.other.StraightWE;
import de.weltraumschaf.jebnf.gfx.shapes.text.TextShape;
import java.awt.Dimension;
import static org.junit.Assert.*;
import org.junit.Test;

/**
 *
 * @author Sven Strittmatter <weltraumschaf@googlemail.com>
 */
public class OptionTest {

    @Test public void setOptional() {
        final Option option = new Option();
        final GridLayout grid = option.getGrid();
        ColumnLayout split;
        ColumnLayout join;

        assertEquals(2, grid.counRows());
        assertEquals(3, grid.countCols());
        assertTrue(grid.get(0, 0) instanceof ColumnLayout);
        assertTrue(grid.get(1, 0) instanceof Empty);
        assertTrue(grid.get(2, 0) instanceof ColumnLayout);
        assertTrue(grid.get(0, 1) instanceof CurveNE);
        assertTrue(grid.get(1, 1) instanceof StraightWE);
        assertTrue(grid.get(2, 1) instanceof CurveNW);

        split = (ColumnLayout) grid.get(0, 0);
        assertEquals(1, split.countShapes());
        assertTrue(split.get(0) instanceof HForkSW);
        join = (ColumnLayout) grid.get(2, 0);
        assertEquals(1, join.countShapes());
        assertTrue(join.get(0) instanceof HForkSE);

        final TextShape term = terminal("foo");
        option.setOptional(term);
        assertEquals(2, grid.counRows());
        assertEquals(3, grid.countCols());
        assertTrue(grid.get(0, 0) instanceof ColumnLayout);
        assertSame(grid.get(1, 0), term);
        assertTrue(grid.get(2, 0) instanceof ColumnLayout);
        assertTrue(grid.get(0, 1) instanceof CurveNE);
        assertTrue(grid.get(1, 1) instanceof StraightWE);
        assertTrue(grid.get(2, 1) instanceof CurveNW);

        // taller optional
        final ColumnLayout greatColumn = column();
        greatColumn.setSize(new Dimension(Shape.DEFAULT_WIDTH, Shape.DEFAULT_HEIGHT * 3));
        option.setOptional(greatColumn);
        assertEquals(2, grid.counRows());
        assertEquals(3, grid.countCols());
        assertTrue(grid.get(0, 0) instanceof ColumnLayout);
        assertSame(grid.get(1, 0), greatColumn);
        assertTrue(grid.get(2, 0) instanceof ColumnLayout);
        assertTrue(grid.get(0, 1) instanceof CurveNE);
        assertTrue(grid.get(1, 1) instanceof StraightWE);
        assertTrue(grid.get(2, 1) instanceof CurveNW);

        split = (ColumnLayout) grid.get(0, 0);
        assertEquals(3, split.countShapes());
        assertTrue(split.get(0) instanceof HForkSW);
        assertTrue(split.get(1) instanceof StraightNS);
        assertTrue(split.get(2) instanceof StraightNS);
        join = (ColumnLayout) grid.get(2, 0);
        assertEquals(3, join.countShapes());
        assertTrue(join.get(0) instanceof HForkSE);
        assertTrue(join.get(1) instanceof StraightNS);
        assertTrue(join.get(2) instanceof StraightNS);
    }

}
