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

package de.weltraumschaf.jebnf.gfx.shapes.forkes;

import de.weltraumschaf.jebnf.gfx.shapes.other.CurveShape;
import de.weltraumschaf.jebnf.gfx.shapes.other.StraightShape;

/**
 * Vertical fork with straight line from north to south and curve from north to west..
 *
 * Schematic:
 * <pre>
 *    |
 * __/|
 *    |
 *    |
 * </pre>
 *
 * @author Sven Strittmatter <weltraumschaf@googlemail.com>
 */
public class VForkNorthWestShape extends AbstractForkShape {

    /**
     * Initializes {@link #straight} with {@link StraightShape} from north to south
     * and {@link #curve} with {@link CurveShape "north west direction"}.
     */
    public VForkNorthWestShape() {
        super(new StraightShape(StraightShape.Directions.NORT_SOUTH),
              new CurveShape(CurveShape.Directions.NORTH_WEST));
    }

}
