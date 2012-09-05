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

package de.weltraumschaf.jebnf.gfx.shapes.compound;

import de.weltraumschaf.jebnf.gfx.shapes.Adjustable;
import de.weltraumschaf.jebnf.gfx.shapes.Shape;

/**
 * Compound shapes have child shapes and can adjust them self.
 *
 * @author Sven Strittmatter <weltraumschaf@googlemail.com>
 */
public interface Grid extends Shape, Adjustable {

    /**
     * Get the compounds grid layout.
     *
     * @return The grid layout instance.
     */
    GridLayout getGrid();

}