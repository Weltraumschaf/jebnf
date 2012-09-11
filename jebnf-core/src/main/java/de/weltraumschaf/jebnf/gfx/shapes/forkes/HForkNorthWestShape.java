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

import de.weltraumschaf.jebnf.gfx.shapes.curves.CurveNorthWestShape;
import de.weltraumschaf.jebnf.gfx.shapes.other.StraightWestEastShape;

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
public class HForkNorthWestShape extends AbstractForkShape {

    /**
     * Initializes {@link #straight} with {@link StraightWestEastShape}
     * and {@link #curve} with {@link CurveNorthWestShape}.
     */
    public HForkNorthWestShape() {
        super(new StraightWestEastShape(), new CurveNorthWestShape());
    }

}
