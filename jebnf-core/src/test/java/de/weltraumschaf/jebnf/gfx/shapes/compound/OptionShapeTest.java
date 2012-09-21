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
import static de.weltraumschaf.jebnf.gfx.shapes.ShapeFactory.column;
import static de.weltraumschaf.jebnf.gfx.shapes.ShapeFactory.terminal;
import de.weltraumschaf.jebnf.gfx.shapes.other.CurveShape;
import de.weltraumschaf.jebnf.gfx.shapes.forkes.HForkSouthEastShape;
import de.weltraumschaf.jebnf.gfx.shapes.forkes.HForkSouthWestShape;
import de.weltraumschaf.jebnf.gfx.shapes.other.EmptyShape;
import de.weltraumschaf.jebnf.gfx.shapes.other.StraightShape;
import de.weltraumschaf.jebnf.gfx.shapes.text.TextShape;
import static org.junit.Assert.*;
import org.junit.Test;

/**
 *
 * @author Sven Strittmatter <weltraumschaf@googlemail.com>
 */
public class OptionShapeTest {

    @Test public void setOptional() {
        final OptionShape option = new OptionShape();
        final GridLayoutShape grid = option.getGrid();
        ColumnLayoutShape split;
        ColumnLayoutShape join;

        assertEquals(2, grid.counRows());
        assertEquals(3, grid.countCols());
        assertTrue(grid.get(0, 0) instanceof ColumnLayoutShape);
        assertTrue(grid.get(1, 0) instanceof EmptyShape);
        assertTrue(grid.get(2, 0) instanceof ColumnLayoutShape);
        assertTrue(grid.get(0, 1) instanceof CurveShape);
        assertEquals(CurveShape.Directions.NORTH_EAST, ((CurveShape) grid.get(0, 1)).getDirection());
        assertTrue(grid.get(1, 1) instanceof StraightShape);
        assertEquals(StraightShape.Directions.WEST_EAST, ((StraightShape) grid.get(1, 1)).getDirection());
        assertTrue(grid.get(2, 1) instanceof CurveShape);
        assertEquals(CurveShape.Directions.NORTH_WEST, ((CurveShape) grid.get(2, 1)).getDirection());

        split = (ColumnLayoutShape) grid.get(0, 0);
        assertEquals(1, split.countShapes());
        assertTrue(split.get(0) instanceof HForkSouthWestShape);
        join = (ColumnLayoutShape) grid.get(2, 0);
        assertEquals(1, join.countShapes());
        assertTrue(join.get(0) instanceof HForkSouthEastShape);

        final TextShape term = terminal("foo");
        option.setOptional(term);
        assertEquals(2, grid.counRows());
        assertEquals(3, grid.countCols());
        assertTrue(grid.get(0, 0) instanceof ColumnLayoutShape);
        assertSame(grid.get(1, 0), term);
        assertTrue(grid.get(2, 0) instanceof ColumnLayoutShape);
        assertTrue(grid.get(0, 1) instanceof CurveShape);
        assertEquals(CurveShape.Directions.NORTH_EAST, ((CurveShape) grid.get(0, 1)).getDirection());
        assertTrue(grid.get(1, 1) instanceof StraightShape);
        assertEquals(StraightShape.Directions.WEST_EAST, ((StraightShape) grid.get(1, 1)).getDirection());
        assertTrue(grid.get(2, 1) instanceof CurveShape);
        assertEquals(CurveShape.Directions.NORTH_WEST, ((CurveShape) grid.get(2, 1)).getDirection());

        // taller optional
        final ColumnLayoutShape greatColumn = column();
        greatColumn.setSize(new Size().setHeight(Size.DEFAULT_HEIGHT * 3));
        option.setOptional(greatColumn);
        assertEquals(2, grid.counRows());
        assertEquals(3, grid.countCols());
        assertTrue(grid.get(0, 0) instanceof ColumnLayoutShape);
        assertSame(grid.get(1, 0), greatColumn);
        assertTrue(grid.get(2, 0) instanceof ColumnLayoutShape);
        assertTrue(grid.get(0, 1) instanceof CurveShape);
        assertEquals(CurveShape.Directions.NORTH_EAST, ((CurveShape) grid.get(0, 1)).getDirection());
        assertTrue(grid.get(1, 1) instanceof StraightShape);
        assertEquals(StraightShape.Directions.WEST_EAST, ((StraightShape) grid.get(1, 1)).getDirection());
        assertTrue(grid.get(2, 1) instanceof CurveShape);
        assertEquals(CurveShape.Directions.NORTH_WEST, ((CurveShape) grid.get(2, 1)).getDirection());

        split = (ColumnLayoutShape) grid.get(0, 0);
        assertEquals(3, split.countShapes());
        assertTrue(split.get(0) instanceof HForkSouthWestShape);
        assertTrue(split.get(1) instanceof StraightShape);
        assertEquals(StraightShape.Directions.NORT_SOUTH, ((StraightShape) split.get(1)).getDirection());
        assertTrue(split.get(2) instanceof StraightShape);
        assertEquals(StraightShape.Directions.NORT_SOUTH, ((StraightShape) split.get(2)).getDirection());

        join = (ColumnLayoutShape) grid.get(2, 0);
        assertEquals(3, join.countShapes());
        assertTrue(join.get(0) instanceof HForkSouthEastShape);
        assertTrue(join.get(1) instanceof StraightShape);
        assertEquals(StraightShape.Directions.NORT_SOUTH, ((StraightShape) join.get(1)).getDirection());
        assertTrue(join.get(2) instanceof StraightShape);
        assertEquals(StraightShape.Directions.NORT_SOUTH, ((StraightShape) join.get(2)).getDirection());
    }

}
