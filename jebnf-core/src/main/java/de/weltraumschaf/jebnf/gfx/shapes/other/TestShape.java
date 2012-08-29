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
import de.weltraumschaf.jebnf.gfx.Size;
import de.weltraumschaf.jebnf.gfx.Strokes;
import java.awt.Color;
import java.awt.Graphics2D;

/**
 * Paint blue dots on each second pixel.
 *
 * This shape is like the test picture on television.
 *
 * Schematic:
 * <pre>
 * x x x x
 *  x x x x
 * x x x x
 *  x x x x
 * </pre>
 *
 * @author Sven Strittmatter <weltraumschaf@googlemail.com>
 */
public class TestShape extends Empty {

    @Override
    public void paint(final Graphics2D graphic) {
        super.paint(graphic);
        final Point pos = getPosition();
        final Size size = getSize();
        backupColorAndStroke(graphic);
        graphic.setColor(Color.BLUE);
        graphic.setStroke(Strokes.createForDebug());

        for (int i = 0; i < size.getWidth(); ++i) {
            for (int j = 0; j < size.getHeight(); j++) {
                final int xPosition = pos.getX() + i;
                final int yPosition = pos.getY() + j;

                if (shouldPaint(i, j)) {
                    graphic.drawLine(xPosition, yPosition, xPosition, yPosition);
                }
            }
        }

        resotreColorAndStroke(graphic);
    }

    /**
     * Determines whether to paint a pixel.
     *
     * Only paints every second pixel and shifts start one pixel per line.
     *
     * @param horizIteration Horizontal iteration in pixel.
     * @param verticalIteration Vertical iteration in pixel.
     * @return Return true if a pixel should be painted
     */
    protected boolean shouldPaint(final int horizIteration, final int verticalIteration) {
        if (horizIteration < 0) {
            throw new IllegalArgumentException(String.format(
                    "Passed horizIteration must be greater equal than 0! %d given.",
                    horizIteration));
        }

        if (verticalIteration < 0) {
            throw new IllegalArgumentException(String.format(
                    "Passed verticalIteration must be greater equal than 0! %d given.",
                    verticalIteration));
        }

        if (horizIteration % 2 == 1 && verticalIteration % 2 == 1) {
            return true;
        }

        if (horizIteration % 2 == 0 && verticalIteration % 2 == 0) {
            return true;
        }

        return false;
    }
}
