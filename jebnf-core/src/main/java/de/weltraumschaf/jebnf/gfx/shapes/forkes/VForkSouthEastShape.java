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

import de.weltraumschaf.jebnf.gfx.shapes.curves.CurveSouthEastShape;
import de.weltraumschaf.jebnf.gfx.shapes.other.StraightNorthSouthShape;

/**
 * Vertical fork with straight line from north to south and curve from south to east..
 *
 * Schematic:
 * <pre>
 *    |
 *    | __
 *    |/
 *    |
 * </pre>
 *
 * @author Sven Strittmatter <weltraumschaf@googlemail.com>
 */
public class VForkSouthEastShape extends AbstractForkShape {

    /**
     * Initializes {@link #straight} with {@link StraightNorthSouthShape}
     * and {@link #curve} with {@link CurveSouthEastShape}.
     */
    public VForkSouthEastShape() {
        super(new StraightNorthSouthShape(), new CurveSouthEastShape());
    }

}
