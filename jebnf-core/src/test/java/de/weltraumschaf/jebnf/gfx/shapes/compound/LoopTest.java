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
import de.weltraumschaf.jebnf.gfx.shapes.curves.CurveNE;
import de.weltraumschaf.jebnf.gfx.shapes.curves.CurveNW;
import de.weltraumschaf.jebnf.gfx.shapes.forkes.HForkSE;
import de.weltraumschaf.jebnf.gfx.shapes.forkes.HForkSW;
import de.weltraumschaf.jebnf.gfx.shapes.other.Empty;
import de.weltraumschaf.jebnf.gfx.shapes.other.StraightNS;
import de.weltraumschaf.jebnf.gfx.shapes.other.StraightWE;
import de.weltraumschaf.jebnf.gfx.shapes.text.Terminal;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import org.junit.Test;

/**
 *
 * @author Sven Strittmatter <weltraumschaf@googlemail.com>
 */
public class LoopTest {

    private void assertInitialGrid(final Loop loop) {
        assertBaseGrid(loop);
        assertTrue(loop.getGrid().get(1, 0) instanceof Empty);
        assertTrue(loop.getGrid().get(1, 1) instanceof StraightWE);
    }

    private void assertBaseGrid(final Loop loop) {
        final GridLayout grid = loop.getGrid();
        assertEquals(2, grid.counRows());
        assertEquals(3, grid.countCols());
        assertTrue(grid.get(0, 0) instanceof ColumnLayout);
        // col 2 differs
        assertTrue(grid.get(2, 0) instanceof ColumnLayout);
        // 2. row
        assertTrue(grid.get(0, 1) instanceof ColumnLayout);
        // col 2 differs if additional set
        assertTrue(grid.get(2, 1) instanceof ColumnLayout);

        assertSplitAndJoin(loop);
        assertCurves(loop);
    }

    private void assertSplitAndJoin(final Loop loop) {
        final GridLayout grid = loop.getGrid();
        final ColumnLayout split = (ColumnLayout) grid.get(0, 0);
        assertEquals(1, split.countShapes());
        assertTrue(split.get(0) instanceof HForkSE);

        final ColumnLayout join  = (ColumnLayout) grid.get(2, 0);
        assertEquals(1, join.countShapes());
        assertTrue(join.get(0) instanceof HForkSW);
    }

    private void assertCurves(final Loop loop) {
        final GridLayout grid = loop.getGrid();
        final ColumnLayout firstCurve = (ColumnLayout) grid.get(0, 1);
        assertEquals(1, firstCurve.countShapes());
        assertTrue(firstCurve.get(0) instanceof CurveNE);

        final ColumnLayout lastCurve  = (ColumnLayout) grid.get(2, 1);
        assertEquals(1, lastCurve.countShapes());
        assertTrue(lastCurve.get(0) instanceof CurveNW);
    }

    @Test public void setLooped() {
        final Loop loop = loop();
        final GridLayout grid = loop.getGrid();
        assertInitialGrid(loop);

        loop.setLooped(terminal("foo"));
        assertBaseGrid(loop);
        assertTrue(grid.get(1, 0) instanceof Terminal);
        assertTrue(grid.get(1, 1) instanceof StraightWE);

        final Shape greatOne = terminal("bar");
        greatOne.setSize(new Size().setHeight(Size.DEFAULT_HEIGHT * 3));
        loop.setLooped(greatOne);
        assertTrue(grid.get(1, 0) instanceof Terminal);
        assertTrue(grid.get(1, 1) instanceof StraightWE);

        final ColumnLayout split = (ColumnLayout) grid.get(0, 0);
        assertEquals(3, split.countShapes());
        assertTrue(split.get(0) instanceof HForkSE);
        assertTrue(split.get(1) instanceof StraightNS);
        assertTrue(split.get(2) instanceof StraightNS);
        final ColumnLayout join  = (ColumnLayout) grid.get(2, 0);
        assertEquals(3, join.countShapes());
        assertTrue(join.get(0) instanceof HForkSW);
        assertTrue(join.get(1) instanceof StraightNS);
        assertTrue(join.get(2) instanceof StraightNS);
        assertCurves(loop);
    }

    @Test public void setAdditional() {
        final Loop loop = loop();
        final GridLayout grid = loop.getGrid();
        assertInitialGrid(loop);

        loop.setAdditional(terminal("bar"));
        assertBaseGrid(loop);
        assertTrue(grid.get(1, 0) instanceof Empty);
        assertTrue(grid.get(1, 1) instanceof Terminal);

        final Shape greatOne = terminal("bar");
        greatOne.setSize(new Size().setHeight(Size.DEFAULT_HEIGHT * 3));
        loop.setAdditional(greatOne);
        assertTrue(grid.get(1, 0) instanceof Empty);
        assertTrue(grid.get(1, 1) instanceof Terminal);
        final ColumnLayout firstCurve = (ColumnLayout) grid.get(0, 1);
        assertEquals(3, firstCurve.countShapes());
        assertTrue(firstCurve.get(0) instanceof CurveNE);
        assertTrue(firstCurve.get(1) instanceof Empty);
        assertTrue(firstCurve.get(2) instanceof Empty);

        final ColumnLayout lastCurve  = (ColumnLayout) grid.get(2, 1);
        assertEquals(3, lastCurve.countShapes());
        assertTrue(lastCurve.get(0) instanceof CurveNW);
        assertTrue(lastCurve.get(1) instanceof Empty);
        assertTrue(lastCurve.get(2) instanceof Empty);
        assertSplitAndJoin(loop);
    }

}
