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
import static de.weltraumschaf.jebnf.gfx.shapes.ShapeFactory.loop;
import static de.weltraumschaf.jebnf.gfx.shapes.ShapeFactory.terminal;
import de.weltraumschaf.jebnf.gfx.shapes.other.CurveShape;
import de.weltraumschaf.jebnf.gfx.shapes.other.ForkShape;
import de.weltraumschaf.jebnf.gfx.shapes.other.RectangularShape;
import de.weltraumschaf.jebnf.gfx.shapes.other.StraightShape;
import de.weltraumschaf.jebnf.gfx.shapes.text.TerminalShape;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import org.junit.Test;

/**
 *
 * @author Sven Strittmatter <weltraumschaf@googlemail.com>
 */
public class LoopShapeTest {

    private void assertInitialGrid(final LoopShape loop) {
        assertBaseGrid(loop);
        assertTrue(loop.getGrid().get(1, 0) instanceof RectangularShape);
        assertTrue(loop.getGrid().get(1, 1) instanceof StraightShape);
        assertEquals(StraightShape.Directions.WEST_EAST, ((StraightShape) loop.getGrid().get(1, 1)).getDirection());
    }

    private void assertBaseGrid(final LoopShape loop) {
        final GridLayoutShape grid = loop.getGrid();
        assertEquals(2, grid.counRows());
        assertEquals(3, grid.countCols());
        assertTrue(grid.get(0, 0) instanceof ColumnLayoutShape);
        // col 2 differs
        assertTrue(grid.get(2, 0) instanceof ColumnLayoutShape);
        // 2. row
        assertTrue(grid.get(0, 1) instanceof ColumnLayoutShape);
        // col 2 differs if additional set
        assertTrue(grid.get(2, 1) instanceof ColumnLayoutShape);

        assertSplitAndJoin(loop);
        assertCurves(loop);
    }

    private void assertSplitAndJoin(final LoopShape loop) {
        final GridLayoutShape grid = loop.getGrid();
        final ColumnLayoutShape split = (ColumnLayoutShape) grid.get(0, 0);
        assertEquals(1, split.countShapes());
        assertTrue(split.get(0) instanceof ForkShape);
        assertEquals(StraightShape.Directions.WEST_EAST, ((ForkShape) split.get(0)).getStraight().getDirection());
        assertEquals(CurveShape.Directions.SOUTH_EAST, ((ForkShape) split.get(0)).getCurve().getDirection());

        final ColumnLayoutShape join  = (ColumnLayoutShape) grid.get(2, 0);
        assertEquals(1, join.countShapes());
        assertTrue(join.get(0) instanceof ForkShape);
        assertEquals(StraightShape.Directions.WEST_EAST, ((ForkShape) join.get(0)).getStraight().getDirection());
        assertEquals(CurveShape.Directions.SOUTH_WEST, ((ForkShape) join.get(0)).getCurve().getDirection());
    }

    private void assertCurves(final LoopShape loop) {
        final GridLayoutShape grid = loop.getGrid();
        final ColumnLayoutShape firstCurve = (ColumnLayoutShape) grid.get(0, 1);
        assertEquals(1, firstCurve.countShapes());
        assertTrue(firstCurve.get(0) instanceof CurveShape);
        assertEquals(CurveShape.Directions.NORTH_EAST, ((CurveShape) firstCurve.get(0)).getDirection());

        final ColumnLayoutShape lastCurve  = (ColumnLayoutShape) grid.get(2, 1);
        assertEquals(1, lastCurve.countShapes());
        assertTrue(lastCurve.get(0) instanceof CurveShape);
        assertEquals(CurveShape.Directions.NORTH_WEST, ((CurveShape) lastCurve.get(0)).getDirection());
    }

    @Test public void setLooped() {
        final LoopShape loop = new LoopShape();
        final GridLayoutShape grid = loop.getGrid();
        assertInitialGrid(loop);

        loop.setLooped(terminal("foo"));
        assertBaseGrid(loop);
        assertTrue(grid.get(1, 0) instanceof TerminalShape);
        assertTrue(grid.get(1, 1) instanceof StraightShape);
        assertEquals(StraightShape.Directions.WEST_EAST, ((StraightShape) grid.get(1, 1)).getDirection());

        final Shape greatOne = terminal("bar");
        greatOne.setSize(Size.DEFAULT.setHeight(Size.DEFAULT_HEIGHT * 3));
        loop.setLooped(greatOne);
        assertTrue(grid.get(1, 0) instanceof TerminalShape);
        assertTrue(grid.get(1, 1) instanceof StraightShape);
        assertEquals(StraightShape.Directions.WEST_EAST, ((StraightShape) grid.get(1, 1)).getDirection());

        final ColumnLayoutShape split = (ColumnLayoutShape) grid.get(0, 0);
        assertEquals(3, split.countShapes());
        assertTrue(split.get(0) instanceof ForkShape);
        assertEquals(StraightShape.Directions.WEST_EAST, ((ForkShape) split.get(0)).getStraight().getDirection());
        assertEquals(CurveShape.Directions.SOUTH_EAST, ((ForkShape) split.get(0)).getCurve().getDirection());

        assertTrue(split.get(1) instanceof StraightShape);
        assertEquals(StraightShape.Directions.NORT_SOUTH, ((StraightShape) split.get(1)).getDirection());

        assertTrue(split.get(2) instanceof StraightShape);
        assertEquals(StraightShape.Directions.NORT_SOUTH, ((StraightShape) split.get(2)).getDirection());

        final ColumnLayoutShape join  = (ColumnLayoutShape) grid.get(2, 0);
        assertEquals(3, join.countShapes());
        assertTrue(join.get(0) instanceof ForkShape);
        assertEquals(StraightShape.Directions.WEST_EAST, ((ForkShape) join.get(0)).getStraight().getDirection());
        assertEquals(CurveShape.Directions.SOUTH_WEST, ((ForkShape) join.get(0)).getCurve().getDirection());

        assertTrue(join.get(1) instanceof StraightShape);
        assertEquals(StraightShape.Directions.NORT_SOUTH, ((StraightShape) join.get(1)).getDirection());

        assertTrue(join.get(2) instanceof StraightShape);
        assertEquals(StraightShape.Directions.NORT_SOUTH, ((StraightShape) join.get(1)).getDirection());
        assertCurves(loop);
    }

    @Test public void setAdditional() {
        final LoopShape loop = new LoopShape();
        final GridLayoutShape grid = loop.getGrid();
        assertInitialGrid(loop);

        loop.setAdditional(terminal("bar"));
        assertBaseGrid(loop);
        assertTrue(grid.get(1, 0) instanceof RectangularShape);
        assertTrue(grid.get(1, 1) instanceof TerminalShape);

        final Shape greatOne = terminal("bar");
        greatOne.setSize(Size.DEFAULT.setHeight(Size.DEFAULT_HEIGHT * 3));
        loop.setAdditional(greatOne);
        assertTrue(grid.get(1, 0) instanceof RectangularShape);
        assertTrue(grid.get(1, 1) instanceof TerminalShape);
        final ColumnLayoutShape firstCurve = (ColumnLayoutShape) grid.get(0, 1);
        assertEquals(3, firstCurve.countShapes());
        assertTrue(firstCurve.get(0) instanceof CurveShape);
        assertEquals(CurveShape.Directions.NORTH_EAST, ((CurveShape) firstCurve.get(0)).getDirection());
        assertTrue(firstCurve.get(1) instanceof RectangularShape);
        assertTrue(firstCurve.get(2) instanceof RectangularShape);

        final ColumnLayoutShape lastCurve  = (ColumnLayoutShape) grid.get(2, 1);
        assertEquals(3, lastCurve.countShapes());
        assertTrue(lastCurve.get(0) instanceof CurveShape);
        assertEquals(CurveShape.Directions.NORTH_WEST, ((CurveShape) lastCurve.get(0)).getDirection());
        assertTrue(lastCurve.get(1) instanceof RectangularShape);
        assertTrue(lastCurve.get(2) instanceof RectangularShape);
        assertSplitAndJoin(loop);
    }

}
