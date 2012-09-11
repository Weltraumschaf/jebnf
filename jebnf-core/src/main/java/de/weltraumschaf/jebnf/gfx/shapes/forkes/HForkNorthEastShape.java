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

import de.weltraumschaf.jebnf.gfx.shapes.curves.CurveNorthEastShape;
import de.weltraumschaf.jebnf.gfx.shapes.other.StraightWestEastShape;

/**
 * Horizontal fork with straight line from west to east and curve from north to east..
 *
 * Schematic:
 * <pre>
 *    |
 * ____\___
 *
 *
 * </pre>
 *
 * @author Sven Strittmatter <weltraumschaf@googlemail.com>
 */
public class HForkNorthEastShape extends AbstractForkShape {

    /**
     * Initializes {@link #straight} with {@link StraightWestEastShape}
     * and {@link #curve} with {@link CurveNorthEastShape}.
     */
    public HForkNorthEastShape() {
        super(new StraightWestEastShape(), new CurveNorthEastShape());
    }

}
