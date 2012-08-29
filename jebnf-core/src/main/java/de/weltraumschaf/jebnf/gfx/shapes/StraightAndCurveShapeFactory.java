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

import de.weltraumschaf.jebnf.gfx.shapes.curves.Curve;
import de.weltraumschaf.jebnf.gfx.shapes.curves.CurveNE;
import de.weltraumschaf.jebnf.gfx.shapes.curves.CurveNW;
import de.weltraumschaf.jebnf.gfx.shapes.curves.CurveSE;
import de.weltraumschaf.jebnf.gfx.shapes.curves.CurveSW;
import de.weltraumschaf.jebnf.gfx.shapes.other.StraightNS;
import de.weltraumschaf.jebnf.gfx.shapes.other.StraightWE;

/**
 * Creates straight and curve shapes.
 *
 * @author Sven Strittmatter <weltraumschaf@googlemail.com>
 */
public final class StraightAndCurveShapeFactory {

    /**
     * Private constructor for pure static utility class.
     */
    private StraightAndCurveShapeFactory() {
        super();
    }

    /**
     * Creates one of the {@link Curves "curves"}.
     *
     * @param type Type of curve to create.
     * @return Always return new instance.
     */
    public static Curve curve(final Curves type) {
        Curve curve;

        switch (type) {
            case NORTH_EAST:
                curve = new CurveNE();
                break;
            case NORTH_WEST:
                curve = new CurveNW();
                break;
            case SOUTH_EAST:
                curve = new CurveSE();
                break;
            case SOUTH_WEST:
                curve = new CurveSW();
                break;
            default:
                throw new IllegalArgumentException(String.format("Unsupported type: %s!", type));
        }

        return curve;
    }

    /**
     * Create one of the {@link Straights "striaghts"}.
     *
     * @param type Type of straight to create.
     * @return Always return new instance.
     */
    public static Shape straight(final Straights type) {
        switch (type) {
            case NORT_SOUTH:
                return new StraightNS();
            case WEST_EAST:
                return new StraightWE();
            default:
                throw new IllegalArgumentException("Unsupported type: " + type + "!");
        }
    }

}
