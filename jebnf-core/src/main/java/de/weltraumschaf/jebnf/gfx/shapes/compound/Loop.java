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

import static de.weltraumschaf.jebnf.gfx.shapes.Curves.*;
import de.weltraumschaf.jebnf.gfx.shapes.Shape;
import static de.weltraumschaf.jebnf.gfx.shapes.ShapeFactory.*;
import static de.weltraumschaf.jebnf.gfx.shapes.Straights.WEST_EAST;
import java.awt.Dimension;

/**
 * Railroad loop shape.
 *
 * <pre>
 * -----[ looped shape ]------
 *   /                   \
 *  |                     |
 *   \___________________/
 * </pre>
 *
 * With additional shape.
 * <pre>
 * ------[ looped shape ]------
 *   /                      \
 *  |                        |
 *   \_[ additional shape ]_/
 * </pre>
 *
 * @author Sven Strittmatter <weltraumschaf@googlemail.com>
 */
public class Loop extends AbstractCompund {

    /**
     * Initializes the grid layout with some common base shapes.
     */
    public Loop() {
        super(grid().set(0, 0, column().append(fork(WEST_EAST, SOUTH_EAST)))
                    .set(0, 1, column(curve(NORTH_EAST)))
                    .set(1, 0, empty())
                    .set(1, 1, straight(WEST_EAST))
                    .set(2, 0, column().append(fork(WEST_EAST, SOUTH_WEST)))
                    .set(2, 1, column().append(curve(NORTH_WEST))));
    }

    /**
     * Set the looped shape.
     *
     * @param shape Looped shape.
     */
    public void setLooped(final Shape shape) {
        grid.set(1, 0, shape);
        final Dimension size = shape.getSize();
        extendColumnWithStraightNS(size.height, new int[]{0, 2}, 0);
    }

    /**
     * Set the optional additional shape.
     *
     * @param shape Additional shape.
     */
    public void setAdditional(final Shape shape) {
        grid.set(1, 1, shape);
        final Dimension size = shape.getSize();
        extendColumnWithEmpty(size.height, new int[]{0, 2}, 1);
    }

}
