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

package de.weltraumschaf.jebnf.gfx.shapes.other;

import de.weltraumschaf.jebnf.gfx.Point;
import de.weltraumschaf.jebnf.gfx.shapes.AbstractShape;
import de.weltraumschaf.jebnf.gfx.shapes.Paintable;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics2D;

/**
 * Empty shape.
 *
 * @author Sven Strittmatter <weltraumschaf@googlemail.com>
 */
public class Empty extends AbstractShape {

    /**
     * Whether it have transparent background or not.
     */
    private boolean transparent;

    /**
     * Initializes the object with transparent flag.
     *
     * @param transparent Whether it have transparent background or not.
     */
    public void setTransparent(final boolean transparent) {
        this.transparent = transparent;
    }

    /**
     * Whether it have transparent background or not.
     *
     * @return Return true if background is transparent, unless false.
     */
    public boolean isTransparent() {
        return transparent;
    }

    @Override
    public void paint(final Graphics2D graphic) {
        backupColorAndStroke(graphic);
        final Point pos = getPosition();
        final Dimension size = getSize();

        if (!transparent) {
            graphic.setColor(Color.WHITE);
            graphic.fillRect(pos.getX(), pos.getY(), size.width, size.height);
        }

        debugShape(graphic, new Paintable() {
            @Override
            public void paint(final Graphics2D graphic) {
                drawHorizontalLines(graphic);
                drawVerticalLines(graphic);
                graphic.drawRect(pos.getX(), pos.getY(), size.width - 1, size.height - 1);
            }

            private void drawHorizontalLines(final Graphics2D graphic) {
                final int step = (int) Math.ceil(DEFAULT_HEIGHT / 2);
                final int maxYPosition = pos.getY() + size.height - 1;

                for (int yPosition = pos.getY() + step; yPosition < maxYPosition; yPosition += step) {
                    graphic.drawLine(pos.getX(), yPosition, pos.getX() + size.width - 1, yPosition);
                }
            }

            private void drawVerticalLines(final Graphics2D graphic) {
                final int step = (int) Math.ceil(DEFAULT_WIDTH / 2);
                final int maxXPosition = pos.getX() + size.width - 1;

                for (int xPosition = pos.getX() + step; xPosition < maxXPosition; xPosition += step) {
                    graphic.drawLine(xPosition, pos.getY(), xPosition, pos.getY() + size.height - 1);
                }
            }
        });

        resotreColorAndStroke(graphic);
    }

}
