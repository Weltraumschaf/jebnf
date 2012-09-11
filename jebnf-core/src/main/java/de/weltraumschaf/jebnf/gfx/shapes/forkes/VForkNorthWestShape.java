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
import de.weltraumschaf.jebnf.gfx.shapes.other.StraightNorthSouthShape;

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
     * Initializes {@link #straight} with {@link StraightNorthSouthShape}
     * and {@link #curve} with {@link CurveNorthWestShape}.
     */
    public VForkNorthWestShape() {
        super(new StraightNorthSouthShape(), new CurveNorthWestShape());
    }

}
