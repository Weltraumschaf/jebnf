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
 * Vertical fork with straight line from north to south and curve from south to west..
 *
 * Schematic:
 * <pre>
 *    |
 * __ |
 *   \|
 *    |
 * </pre>
 *
 * @author Sven Strittmatter <weltraumschaf@googlemail.com>
 */
public class VForkSW extends AbstractFork {

    /**
     * Initializes {@link #straight} with {@link StraightNS}
     * and {@link #curve} with {@link CurveSW}.
     */
    public VForkSW() {
        super(new StraightNS(), new CurveSW());
    }

}
