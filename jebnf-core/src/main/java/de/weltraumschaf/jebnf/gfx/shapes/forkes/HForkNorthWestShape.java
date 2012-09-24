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
 * Horizontal fork with straight line from west to east and curve from north to east..
 *
 * Schematic:
 * <pre>
 *    |
 * __/____
 *
 *
 * </pre>
 *
 * @author Sven Strittmatter <weltraumschaf@googlemail.com>
 */
@Deprecated
public class HForkNorthWestShape extends ForkShape {

    /**
     * Initializes {@link #straight} with {@link StraightShape} from west to east
     * and {@link #curve} with {@link CurveShape "north west direction"}.
     */
    public HForkNorthWestShape() {
        super(new StraightShape(StraightShape.Directions.WEST_EAST),
              new CurveShape(CurveShape.Directions.NORTH_WEST));
    }

}
