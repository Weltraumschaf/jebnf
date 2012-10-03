/*
 * LICENSE
 *
 * "THE BEER-WARE LICENSE" (Revision 43):
 * "Sven Strittmatter" <weltraumschaf(at)googlemail(dot)com> wrote this file.
 * As long as you retain this notice you can do whatever you want with
 * this stuff. If we meet some day, and you think this stuff is worth it,
 * you can buy me a non alcohol free beer in return.
 *
 * Copyright (C) 2012 "Sven Strittmatter" <weltraumschaf(at)googlemail(dot)com>
 */
package de.weltraumschaf.jebnf.gfx.shapes.other;

import de.weltraumschaf.jebnf.gfx.Point;
import de.weltraumschaf.jebnf.gfx.Size;
import de.weltraumschaf.jebnf.gfx.shapes.AbstractShape;
import de.weltraumschaf.jebnf.gfx.shapes.Paintable;
import java.awt.Color;
import java.awt.Graphics2D;

/**
 * RectangularShape shape.
 *
 * FIXME: Class is obsolete because background should be set in the image format. Maybe useful as spacer.
 *
 * @author Sven Strittmatter <weltraumschaf@googlemail.com>
 */
public class RectangularShape extends AbstractShape {

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
        final Size size = getSize();

        if (!transparent) {
            graphic.setColor(Color.WHITE);
            graphic.fillRect(pos.getX(), pos.getY(), size.getWidth(), size.getHeight());
        }

        debugShape(graphic, new Paintable() {
            @Override
            public void paint(final Graphics2D graphic) {
                drawHorizontalLines(graphic);
                drawVerticalLines(graphic);
                graphic.drawRect(pos.getX(), pos.getY(), size.getWidth() - 1, size.getHeight() - 1);
            }

            private void drawHorizontalLines(final Graphics2D graphic) {
                final int step = (int) Math.ceil(Size.DEFAULT_HEIGHT / 2);
                final int maxYPosition = pos.getY() + size.getHeight() - 1;

                for (int yPosition = pos.getY() + step; yPosition < maxYPosition; yPosition += step) {
                    graphic.drawLine(pos.getX(), yPosition, pos.getX() + size.getWidth() - 1, yPosition);
                }
            }

            private void drawVerticalLines(final Graphics2D graphic) {
                final int step = (int) Math.ceil(Size.DEFAULT_WIDTH / 2);
                final int maxXPosition = pos.getX() + size.getWidth() - 1;

                for (int xPosition = pos.getX() + step; xPosition < maxXPosition; xPosition += step) {
                    graphic.drawLine(xPosition, pos.getY(), xPosition, pos.getY() + size.getHeight() - 1);
                }
            }
        });

        resotreColorAndStroke(graphic);
    }

}
