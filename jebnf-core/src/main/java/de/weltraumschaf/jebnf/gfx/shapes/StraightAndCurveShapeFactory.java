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

import de.weltraumschaf.jebnf.gfx.shapes.curves.CurveShape;
import de.weltraumschaf.jebnf.gfx.shapes.curves.CurveNorthEastShape;
import de.weltraumschaf.jebnf.gfx.shapes.curves.CurveNorthWestShape;
import de.weltraumschaf.jebnf.gfx.shapes.curves.CurveSouthEastShape;
import de.weltraumschaf.jebnf.gfx.shapes.curves.CurveSouthWestShape;
import de.weltraumschaf.jebnf.gfx.shapes.other.StraightNorthSouthShape;
import de.weltraumschaf.jebnf.gfx.shapes.other.StraightWestEastShape;

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
     * Creates one of the {@link CurveShapes "curves"}.
     *
     * @param type Type of curve to create.
     * @return Always return new instance.
     */
    public static CurveShape curve(final CurveShapes type) {
        CurveShape curve;

        switch (type) {
            case NORTH_EAST:
                curve = new CurveNorthEastShape();
                break;
            case NORTH_WEST:
                curve = new CurveNorthWestShape();
                break;
            case SOUTH_EAST:
                curve = new CurveSouthEastShape();
                break;
            case SOUTH_WEST:
                curve = new CurveSouthWestShape();
                break;
            default:
                throw new IllegalArgumentException(String.format("Unsupported type: %s!", type));
        }

        return curve;
    }

    /**
     * Create one of the {@link StraightShapes "striaghts"}.
     *
     * @param type Type of straight to create.
     * @return Always return new instance.
     */
    public static Shape straight(final StraightShapes type) {
        switch (type) {
            case NORT_SOUTH:
                return new StraightNorthSouthShape();
            case WEST_EAST:
                return new StraightWestEastShape();
            default:
                throw new IllegalArgumentException("Unsupported type: " + type + "!");
        }
    }

}
