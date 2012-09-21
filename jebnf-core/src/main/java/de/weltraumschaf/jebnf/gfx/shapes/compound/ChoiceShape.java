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
import static de.weltraumschaf.jebnf.gfx.shapes.ShapeFactory.*;
import de.weltraumschaf.jebnf.gfx.shapes.curves.CurveShape;
import de.weltraumschaf.jebnf.gfx.shapes.forkes.HForkSouthEastShape;
import de.weltraumschaf.jebnf.gfx.shapes.forkes.HForkSouthWestShape;
import de.weltraumschaf.jebnf.gfx.shapes.forkes.VForkNorthEastShape;
import de.weltraumschaf.jebnf.gfx.shapes.forkes.VForkNorthWestShape;

/**
 * ChoiceShape shape.
 *
 * A choice can have as many shapes as choices as needed.
 *
 * @author Sven Strittmatter <weltraumschaf@googlemail.com>
 */
public class ChoiceShape extends AbstractCompundShape {

    /**
     * Initializes the {@link GridLayoutShape} with three empty columns.
     */
    public ChoiceShape() {
        super(grid().append(column())
                    .append(column())
                    .append(column()));
    }

    /**
     * Add a shape as choice.
     *
     * @param shape Shape to add as a choice.
     * @return Return itself for method chaining.
     */
    public ChoiceShape addChoice(final Shape shape) {
        final GridLayoutShape grid = getGrid();
        final int rowCount = grid.counRows();
        Shape first;
        Shape last;

        if (rowCount == 0) {
            first = new HForkSouthWestShape();
            last  = new HForkSouthEastShape();
        } else {
            if (rowCount > 1) {
                grid.set(0, rowCount - 1, new VForkNorthEastShape());
                grid.set(2, rowCount - 1, new VForkNorthWestShape());
            }

            first =  new CurveShape(CurveShape.Directions.NORTH_EAST);
            last  =  new CurveShape(CurveShape.Directions.NORTH_WEST);
        }

        grid.set(0, rowCount, first);
        grid.set(1, rowCount, shape);
        grid.set(2, rowCount, last);
        return this;
    }

}
