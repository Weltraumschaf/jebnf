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
 * Type of straight railroad shapes.
 *
 * TODO: Combine all straights into one class and inject the enum to configure shape.
 *
 * @author Sven Strittmatter <weltraumschaf@googlemail.com>
 */
public enum StraightShapes {

     /**
    * Straight railroad from north to south.
     */
    NORT_SOUTH,

    /**
     * Straight railroad from west to east.
     */
    WEST_EAST;

}
