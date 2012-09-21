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
import static de.weltraumschaf.jebnf.gfx.shapes.ShapeFactory.*;
import de.weltraumschaf.jebnf.gfx.shapes.other.CurveShape;
import de.weltraumschaf.jebnf.gfx.shapes.other.StraightShape;

/**
 * OptionShape shape.
 *
 * ---- [ optional shape ]------
 *   \                      /
 *    |                    |
 *    \____________________/
 *
 * @author Sven Strittmatter <weltraumschaf@googlemail.com>
 */
public class OptionShape extends AbstractCompundShape {

    /**
     * Initializes {@link GridLayoutShape "grid layout"} with common shapes for option.
     */
    public OptionShape() {
        super(grid().set(0, 0, column().append(fork(StraightShape.Directions.WEST_EAST,
                                                    CurveShape.Directions.SOUTH_WEST)))
                    .set(0, 1, curve(CurveShape.Directions.NORTH_EAST))
                    .set(1, 0, empty())
                    .set(1, 1, straight(StraightShape.Directions.WEST_EAST))
                    .set(2, 0, column().append(fork(StraightShape.Directions.WEST_EAST,
                                                    CurveShape.Directions.SOUTH_EAST)))
                    .set(2, 1, curve(CurveShape.Directions.NORTH_WEST)));
    }

    /**
     * Sets the optional shape.
     *
     * @param shape Optional shape.
     */
    public void setOptional(final Shape shape) {
        getGrid().set(1, 0, shape);
        final Size size = shape.getSize();
        extendColumnWithStraightNS(size.getHeight(), new int[]{0, 2}, 0);
    }

}
