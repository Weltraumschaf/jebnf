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

import de.weltraumschaf.jebnf.gfx.shapes.curves.CurveShape;
import de.weltraumschaf.jebnf.gfx.shapes.other.StraightShape;

/**
 * Horizontal fork with straight line from west to east and curve from north to east..
 *
 * Schematic:
 * <pre>
 *
 * _______
 *    /
 *    |
 * </pre>
 *
 * @author Sven Strittmatter <weltraumschaf@googlemail.com>
 */
public class HForkSouthEastShape extends AbstractForkShape {

    /**
     * Initializes {@link #straight} with {@link StraightShape} from west to east
     * and {@link #curve} with {@link CurveShape "south east direction"}.
     */
    public HForkSouthEastShape() {
        super(new StraightShape(StraightShape.Directions.WEST_EAST),
              new CurveShape(CurveShape.Directions.SOUTH_EAST));
    }

}
