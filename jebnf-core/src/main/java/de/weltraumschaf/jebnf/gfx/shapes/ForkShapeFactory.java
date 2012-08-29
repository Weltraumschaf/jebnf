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

import de.weltraumschaf.jebnf.gfx.shapes.forkes.HForkNE;
import de.weltraumschaf.jebnf.gfx.shapes.forkes.HForkNW;
import de.weltraumschaf.jebnf.gfx.shapes.forkes.HForkSE;
import de.weltraumschaf.jebnf.gfx.shapes.forkes.HForkSW;
import de.weltraumschaf.jebnf.gfx.shapes.forkes.VForkNE;
import de.weltraumschaf.jebnf.gfx.shapes.forkes.VForkNW;
import de.weltraumschaf.jebnf.gfx.shapes.forkes.VForkSE;
import de.weltraumschaf.jebnf.gfx.shapes.forkes.VForkSW;

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
    public static Shape fork(final Straights orientation, final Curves curve) {
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
    private static Shape verticalFork(final Curves curve) {
        switch (curve) {
            case NORTH_EAST:
                return new VForkNE();
            case NORTH_WEST:
                return new VForkNW();
            case SOUTH_EAST:
                return new VForkSE();
            case SOUTH_WEST:
                return new VForkSW();
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
    private static Shape horizontalFork(final Curves curve) {
        switch (curve) {
            case NORTH_EAST:
                return new HForkNE();
            case NORTH_WEST:
                return new HForkNW();
            case SOUTH_EAST:
                return new HForkSE();
            case SOUTH_WEST:
                return new HForkSW();
            default:
                throw new IllegalArgumentException("Unsupported curve: " + curve + "!");
        }
    }

}
