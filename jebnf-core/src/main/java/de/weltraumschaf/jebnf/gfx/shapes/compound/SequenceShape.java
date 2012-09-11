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

import de.weltraumschaf.jebnf.gfx.shapes.AdjustableShape;
import de.weltraumschaf.jebnf.gfx.shapes.Shape;

/**
 *
 * @author Sven Strittmatter <weltraumschaf@googlemail.com>
 */
public interface SequenceShape extends AdjustableShape, Shape {

    /**
     * Get shape at given index.
     *
     * @param index Index beginning with 0.
     * @return Return shape object.
     */
    Shape get(int index);

    /**
     * Set shape at given index.
     *
     * @param index Index beginning with 0.
     * @param shape Shape to add.
     * @return Return itself for method chaining.
     */
    SequenceShape set(int index, Shape shape);

    /**
     * Append a shapes at the bottom of the column.
     *
     * @param shapes Shapes to append.
     * @return Return itself for method chaining.
     */
    SequenceShape append(final Shape... shapes);

    /**
     * Append a shape at the bottom of the column.
     *
     * @param shape Shape to append.
     * @return Return itself for method chaining.
     */
    SequenceShape append(final Shape shape);

    /**
     * Count containing shapes.
     *
     * @return Return count of shapes.
     */
    int countShapes();

}
