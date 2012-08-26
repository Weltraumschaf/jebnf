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
public class HForkSE extends AbstractFork {

    /**
     * Initializes {@link #straight} with {@link StraightWE}
     * and {@link #curve} with {@link CurveSE}.
     */
    public HForkSE() {
        super(new StraightWE(), new CurveSE());
    }

}
