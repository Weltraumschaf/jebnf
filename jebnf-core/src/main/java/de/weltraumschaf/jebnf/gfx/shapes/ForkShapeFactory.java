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

package de.weltraumschaf.jebnf.gfx.shapes;

import de.weltraumschaf.jebnf.gfx.shapes.other.CurveShape;
import de.weltraumschaf.jebnf.gfx.shapes.other.ForkShape;
import de.weltraumschaf.jebnf.gfx.shapes.other.StraightShape;

/**
 * Creates fork shapes.
 *
 * @author Sven Strittmatter <weltraumschaf@googlemail.com>
 */
public final class ForkShapeFactory {

    /**
     * Private constructor for pure static utility class.
     */
    private ForkShapeFactory() {
        super();
    }

    /**
     * Creates a fork railroad shape.
     *
     * A fork is a combination of a straight and curve.
     *
     * @param orientation Type of straight.
     * @param curve Type of curve.
     * @return Always return new instance.
     */
    public static Shape fork(final StraightShape.Directions orientation, final CurveShape.Directions curve) {
        switch (orientation) {
            case NORT_SOUTH:
                return verticalFork(curve);
            case WEST_EAST:
                return horizontalFork(curve);
            default:
                throw new IllegalArgumentException("Unsupported orientation: " + orientation + "!");
        }
    }

    /**
     * Creates a fork with straight railroad from north to south.
     *
     * @deprecated Use ForkShape#newVerticalXXX() instead.
     * @param curve Type of curve.
     * @return Always return new instance.
     */
    @Deprecated
    private static Shape verticalFork(final CurveShape.Directions curve) {
        switch (curve) {
            case NORTH_EAST:
                return ForkShape.newVerticalNorthEast();
            case NORTH_WEST:
                return ForkShape.newVerticalNorthWest();
            case SOUTH_EAST:
                return ForkShape.newVerticalSouthEast();
            case SOUTH_WEST:
                return ForkShape.newVerticalSouthWest();
            default:
                throw new IllegalArgumentException("Unsupported curve: " + curve + "!");
        }
    }

    /**
     * Creates a fork with straight railroad from west to east.
     *
     * @deprecated Use ForkShape#newHorizontalXXX() instead.
     * @param curve Type of curve.
     * @return Always return new instance.
     */
    @Deprecated
    private static Shape horizontalFork(final CurveShape.Directions curve) {
        switch (curve) {
            case NORTH_EAST:
                return ForkShape.newHorizontalNorthEast();
            case NORTH_WEST:
                return ForkShape.newHorizontalNorthWest();
            case SOUTH_EAST:
                return ForkShape.newHorizontalSouthEast();
            case SOUTH_WEST:
                return ForkShape.newHorizontalSouthWest();
            default:
                throw new IllegalArgumentException("Unsupported curve: " + curve + "!");
        }
    }

}
