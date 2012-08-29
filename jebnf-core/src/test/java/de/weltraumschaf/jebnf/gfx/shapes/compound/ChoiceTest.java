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

import static de.weltraumschaf.jebnf.gfx.shapes.ShapeFactory.choice;
import static de.weltraumschaf.jebnf.gfx.shapes.ShapeFactory.terminal;
import de.weltraumschaf.jebnf.gfx.shapes.curves.CurveNE;
import de.weltraumschaf.jebnf.gfx.shapes.curves.CurveNW;
import de.weltraumschaf.jebnf.gfx.shapes.forkes.HForkSE;
import de.weltraumschaf.jebnf.gfx.shapes.forkes.HForkSW;
import de.weltraumschaf.jebnf.gfx.shapes.forkes.VForkNE;
import de.weltraumschaf.jebnf.gfx.shapes.forkes.VForkNW;
import de.weltraumschaf.jebnf.gfx.shapes.other.StraightWE;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import org.junit.Ignore;
import org.junit.Test;

/**
 *
 * @author Sven Strittmatter <weltraumschaf@googlemail.com>
 */
public class ChoiceTest {

    private void assertInitialGrid(final Choice choice) {
        final GridLayout grid = choice.getGrid();
        assertEquals(0, grid.counRows());
        assertEquals(3, grid.countCols());
        assertEquals(grid.get(0).countShapes(), 0);
        assertEquals(grid.get(1).countShapes(), 0);
        assertEquals(grid.get(2).countShapes(), 0);
    }

    @Ignore
    @Test public void addChoice() {
        final Choice choice = choice();
        final GridLayout grid = choice.getGrid();
        assertInitialGrid(choice);

        // normal sized
        choice.addChoice(terminal("foo"));
        assertEquals(1, grid.counRows());
        assertEquals(3, grid.countCols());
        ColumnLayout split = (ColumnLayout) grid.get(0, 0);
        assertEquals(1, split.countShapes());
        assertTrue(split.get(0) instanceof StraightWE);
        ColumnLayout join = (ColumnLayout) grid.get(2, 0);
        assertEquals(1, join.countShapes());
        assertTrue(join.get(0) instanceof StraightWE);

        choice.addChoice(terminal("bar"));
        assertEquals(2, grid.counRows());
        assertEquals(3, grid.countCols());
        // 1. row
        split = (ColumnLayout) grid.get(0, 0);
        assertEquals(1, split.countShapes());
        assertTrue(split.get(0) instanceof HForkSW);
        join = (ColumnLayout) grid.get(2, 0);
        assertEquals(1, join.countShapes());
        assertTrue(join.get(0) instanceof HForkSE);
        // 2. row
        ColumnLayout split1 = (ColumnLayout) grid.get(0, 1);
        assertEquals(1, split1.countShapes());
        assertTrue(split1.get(0) instanceof CurveNE);
        ColumnLayout join1 = (ColumnLayout) grid.get(2, 1);
        assertEquals(1, join1.countShapes());
        assertTrue(join1.get(0) instanceof CurveNW);

        choice.addChoice(terminal("baz"));
        assertEquals(3, grid.counRows());
        assertEquals(3, grid.countCols());
        split = (ColumnLayout) grid.get(0, 0);
        assertEquals(1, split.countShapes());
        // 1. row
        assertTrue(split.get(0) instanceof HForkSW);
        join = (ColumnLayout) grid.get(2, 0);
        assertEquals(1, join.countShapes());
        assertTrue(join.get(0) instanceof HForkSE);
        // 2. row
        split1 = (ColumnLayout) grid.get(0, 1);
        assertEquals(1, split1.countShapes());
        assertTrue(split1.get(0) instanceof VForkNE);
        join1 = (ColumnLayout) grid.get(2, 1);
        assertEquals(1, join1.countShapes());
        assertTrue(join1.get(0) instanceof VForkNW);
        // 3. row
        ColumnLayout split2 = (ColumnLayout) grid.get(0, 2);
        assertEquals(1, split2.countShapes());
        assertTrue(split2.get(0) instanceof CurveNE);
        ColumnLayout join2 = (ColumnLayout) grid.get(2, 2);
        assertEquals(1, join2.countShapes());
        assertTrue(join2.get(0) instanceof CurveNW);

        // grat ones
    }
}
