/*
 * LICENSE
 *
 * "THE BEER-WARE LICENSE" (Revision 43):
 * "Sven Strittmatter" <weltraumschaf(at)googlemail(dot)com> wrote this file.
 * As long as you retain this notice you can do whatever you want with
 * this stuff. If we meet some day, and you think this stuff is worth it,
 * you can buy me a non alcohol free beer in return.
 *
 * Copyright (C) 2012 "Sven Strittmatter" <weltraumschaf(at)googlemail(dot)com>
 */
package de.weltraumschaf.jebnf.gfx.shapes.compound;

import de.weltraumschaf.jebnf.gfx.Size;
import de.weltraumschaf.jebnf.gfx.shapes.Shape;
import static de.weltraumschaf.jebnf.gfx.shapes.ShapeFactory.*;
import de.weltraumschaf.jebnf.gfx.shapes.other.CurveShape;
import de.weltraumschaf.jebnf.gfx.shapes.other.StraightShape;

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
public class LoopShape extends AbstractCompundShape {

    /**
     * Initializes the grid layout with some common base shapes.
     */
    public LoopShape() {
        super(grid().set(0, 0, column().append(fork(StraightShape.Directions.WEST_EAST,
                                                    CurveShape.Directions.SOUTH_EAST)))
                    .set(0, 1, column(curve(CurveShape.Directions.NORTH_EAST)))
                    .set(1, 0, empty())
                    .set(1, 1, straight(StraightShape.Directions.WEST_EAST))
                    .set(2, 0, column().append(fork(StraightShape.Directions.WEST_EAST,
                                                    CurveShape.Directions.SOUTH_WEST)))
                    .set(2, 1, column().append(curve(CurveShape.Directions.NORTH_WEST))));
    }

    /**
     * Set the looped shape.
     *
     * @param shape Looped shape.
     */
    public void setLooped(final Shape shape) {
        getGrid().set(1, 0, shape);
        final Size size = shape.getSize();
        extendColumnWithStraightNS(size.getHeight(), new int[]{0, 2}, 0);
    }

    /**
     * Set the optional additional shape.
     *
     * @param shape Additional shape.
     */
    public void setAdditional(final Shape shape) {
        getGrid().set(1, 1, shape);
        final Size size = shape.getSize();
        extendColumnWithEmpty(size.getHeight(), new int[]{0, 2}, 1);
    }

}
