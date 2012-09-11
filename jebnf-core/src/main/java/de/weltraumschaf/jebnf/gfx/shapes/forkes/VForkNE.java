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

import de.weltraumschaf.jebnf.gfx.shapes.curves.CurveNE;
import de.weltraumschaf.jebnf.gfx.shapes.other.StraightNS;

/**
 * Vertical fork with straight line from north to south and curve from north to east..
 *
 * Schematic:
 * <pre>
 *    |
 *    |\__
 *    |
 *    |
 * </pre>
 *
 * @author Sven Strittmatter <weltraumschaf@googlemail.com>
 */
public class VForkNE extends AbstractFork {

    /**
     * Initializes {@link #straight} with {@link StraightNS}
     * and {@link #curve} with {@link CurveNE}.
     */
    public VForkNE() {
        super(new StraightNS(), new CurveNE());
    }

}