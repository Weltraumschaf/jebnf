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
import de.weltraumschaf.jebnf.gfx.shapes.forkes.HForkNorthEastShape;
import de.weltraumschaf.jebnf.gfx.shapes.forkes.HForkNorthWestShape;
import de.weltraumschaf.jebnf.gfx.shapes.forkes.HForkSouthEastShape;
import de.weltraumschaf.jebnf.gfx.shapes.forkes.HForkSouthWestShape;
import de.weltraumschaf.jebnf.gfx.shapes.forkes.VForkNorthEastShape;
import de.weltraumschaf.jebnf.gfx.shapes.forkes.VForkNorthWestShape;
import de.weltraumschaf.jebnf.gfx.shapes.forkes.VForkSouthEastShape;
import de.weltraumschaf.jebnf.gfx.shapes.forkes.VForkSouthWestShape;
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
     * @param curve Type of curve.
     * @return Always return new instance.
     */
    private static Shape verticalFork(final CurveShape.Directions curve) {
        switch (curve) {
            case NORTH_EAST:
                return new VForkNorthEastShape();
            case NORTH_WEST:
                return new VForkNorthWestShape();
            case SOUTH_EAST:
                return new VForkSouthEastShape();
            case SOUTH_WEST:
                return new VForkSouthWestShape();
            default:
                throw new IllegalArgumentException("Unsupported curve: " + curve + "!");
        }
    }

    /**
     * Creates a fork with straight railroad from west to east.
     *
     * @param curve Type of curve.
     * @return Always return new instance.
     */
    private static Shape horizontalFork(final CurveShape.Directions curve) {
        switch (curve) {
            case NORTH_EAST:
                return new HForkNorthEastShape();
            case NORTH_WEST:
                return new HForkNorthWestShape();
            case SOUTH_EAST:
                return new HForkSouthEastShape();
            case SOUTH_WEST:
                return new HForkSouthWestShape();
            default:
                throw new IllegalArgumentException("Unsupported curve: " + curve + "!");
        }
    }

}
