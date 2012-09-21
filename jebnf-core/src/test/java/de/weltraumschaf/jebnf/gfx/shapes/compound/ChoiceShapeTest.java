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
import de.weltraumschaf.jebnf.gfx.shapes.curves.CurveNorthEastShape;
import de.weltraumschaf.jebnf.gfx.shapes.curves.CurveNorthWestShape;
import de.weltraumschaf.jebnf.gfx.shapes.curves.CurveShape;
import de.weltraumschaf.jebnf.gfx.shapes.forkes.HForkSouthEastShape;
import de.weltraumschaf.jebnf.gfx.shapes.forkes.HForkSouthWestShape;
import de.weltraumschaf.jebnf.gfx.shapes.forkes.VForkNorthEastShape;
import de.weltraumschaf.jebnf.gfx.shapes.forkes.VForkNorthWestShape;
import de.weltraumschaf.jebnf.gfx.shapes.other.StraightShape;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import org.junit.Ignore;
import org.junit.Test;

/**
 *
 * @author Sven Strittmatter <weltraumschaf@googlemail.com>
 */
public class ChoiceShapeTest {

    private void assertInitialGrid(final ChoiceShape choice) {
        final GridLayoutShape grid = choice.getGrid();
        assertEquals(0, grid.counRows());
        assertEquals(3, grid.countCols());
        assertEquals(grid.get(0).countShapes(), 0);
        assertEquals(grid.get(1).countShapes(), 0);
        assertEquals(grid.get(2).countShapes(), 0);
    }

    @Ignore
    @Test public void addChoice() {
        final ChoiceShape choice = choice();
        final GridLayoutShape grid = choice.getGrid();
        assertInitialGrid(choice);

        // normal sized
        choice.addChoice(terminal("foo"));
        assertEquals(1, grid.counRows());
        assertEquals(3, grid.countCols());
        ColumnLayoutShape split = (ColumnLayoutShape) grid.get(0, 0);
        assertEquals(1, split.countShapes());
        assertTrue(split.get(0) instanceof StraightShape);
        assertEquals(StraightShape.Directions.WEST_EAST, ((StraightShape) split.get(0)).getDirection());
        ColumnLayoutShape join = (ColumnLayoutShape) grid.get(2, 0);
        assertEquals(1, join.countShapes());
        assertTrue(join.get(0) instanceof StraightShape);
        assertEquals(StraightShape.Directions.WEST_EAST, ((StraightShape) join.get(0)).getDirection());

        choice.addChoice(terminal("bar"));
        assertEquals(2, grid.counRows());
        assertEquals(3, grid.countCols());
        // 1. row
        split = (ColumnLayoutShape) grid.get(0, 0);
        assertEquals(1, split.countShapes());
        assertTrue(split.get(0) instanceof HForkSouthWestShape);
        join = (ColumnLayoutShape) grid.get(2, 0);
        assertEquals(1, join.countShapes());
        assertTrue(join.get(0) instanceof HForkSouthEastShape);
        // 2. row
        ColumnLayoutShape split1 = (ColumnLayoutShape) grid.get(0, 1);
        assertEquals(1, split1.countShapes());
        assertTrue(split1.get(0) instanceof CurveShape);
        assertEquals(CurveShape.Directions.NORTH_EAST, ((CurveShape) split1.get(0)).getDirection());
        ColumnLayoutShape join1 = (ColumnLayoutShape) grid.get(2, 1);
        assertEquals(1, join1.countShapes());
        assertTrue(join1.get(0) instanceof CurveShape);
        assertEquals(CurveShape.Directions.NORTH_WEST, ((CurveShape) join1.get(0)).getDirection());

        choice.addChoice(terminal("baz"));
        assertEquals(3, grid.counRows());
        assertEquals(3, grid.countCols());
        split = (ColumnLayoutShape) grid.get(0, 0);
        assertEquals(1, split.countShapes());
        // 1. row
        assertTrue(split.get(0) instanceof HForkSouthWestShape);
        join = (ColumnLayoutShape) grid.get(2, 0);
        assertEquals(1, join.countShapes());
        assertTrue(join.get(0) instanceof HForkSouthEastShape);
        // 2. row
        split1 = (ColumnLayoutShape) grid.get(0, 1);
        assertEquals(1, split1.countShapes());
        assertTrue(split1.get(0) instanceof VForkNorthEastShape);
        join1 = (ColumnLayoutShape) grid.get(2, 1);
        assertEquals(1, join1.countShapes());
        assertTrue(join1.get(0) instanceof VForkNorthWestShape);
        // 3. row
        ColumnLayoutShape split2 = (ColumnLayoutShape) grid.get(0, 2);
        assertEquals(1, split2.countShapes());
        assertTrue(split2.get(0) instanceof CurveShape);
        assertEquals(CurveShape.Directions.NORTH_EAST, ((CurveShape) split2.get(0)).getDirection());
        ColumnLayoutShape join2 = (ColumnLayoutShape) grid.get(2, 2);
        assertEquals(1, join2.countShapes());
        assertTrue(join2.get(0) instanceof CurveShape);
        assertEquals(CurveShape.Directions.NORTH_WEST, ((CurveShape) join2.get(0)).getDirection());

        // grat ones
    }
}
