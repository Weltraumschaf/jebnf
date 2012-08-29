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
import de.weltraumschaf.jebnf.gfx.shapes.curves.CurveNE;
import de.weltraumschaf.jebnf.gfx.shapes.curves.CurveNW;
import de.weltraumschaf.jebnf.gfx.shapes.forkes.HForkSE;
import de.weltraumschaf.jebnf.gfx.shapes.forkes.HForkSW;
import de.weltraumschaf.jebnf.gfx.shapes.forkes.VForkNE;
import de.weltraumschaf.jebnf.gfx.shapes.forkes.VForkNW;

/**
 * Choice shape.
 *
 * A choice can have as many shapes as choices as needed.
 *
 * @author Sven Strittmatter <weltraumschaf@googlemail.com>
 */
public class Choice extends AbstractCompund {

    /**
     * Initializes the {@link GridLayout} with three empty columns.
     */
    public Choice() {
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
    public Choice addChoice(final Shape shape) {
        final GridLayout grid = getGrid();
        final int rowCount = grid.counRows();
        Shape first;
        Shape last;

        if (rowCount == 0) {
            first = new HForkSW();
            last  = new HForkSE();
        } else {
            if (rowCount > 1) {
                grid.set(0, rowCount - 1, new VForkNE());
                grid.set(2, rowCount - 1, new VForkNW());
            }

            first =  new CurveNE();
            last  =  new CurveNW();
        }

        grid.set(0, rowCount, first);
        grid.set(1, rowCount, shape);
        grid.set(2, rowCount, last);
        return this;
    }

}
