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

import de.weltraumschaf.jebnf.gfx.shapes.AbstractShape;
import de.weltraumschaf.jebnf.gfx.shapes.Adjustable;
import de.weltraumschaf.jebnf.gfx.shapes.Shape;
import java.awt.Dimension;
import java.awt.Graphics2D;

/**
 * Abstract layouts are {@link Sequence}, {@link ColumnLayout}, and {@link GridLayout}.
 *
 * @author Sven Strittmatter <weltraumschaf@googlemail.com>
 */
public abstract class AbstractLayout extends AbstractShape {

    /**
     * Initializes size with (0, 0).
     */
    public AbstractLayout() {
        super();
        setSize(new Dimension(0, 0));
    }

    /**
     * Adjust given shape.
     *
     * @param shape Shape to adjust.
     * @param graphics Graphics context to adjust by.
     */
    protected void adjustShape(final Shape shape, final Graphics2D graphics) {
        try {
            ((Adjustable) shape).adjust(graphics);
        } catch (ClassCastException ex) { // NOPMD
            // Ignore not adjustable shapes.
        }
    }

}
