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
import de.weltraumschaf.jebnf.gfx.Strokes;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics2D;

/**
 * Endpoint shapes are either the start or the end of a single railroad line.
 *
 * Paints the common vertical line common for {@link Start} and {@link End}.
 *
 * @author Sven Strittmatter <weltraumschaf@googlemail.com>
 */
public abstract class AbstractEndpoint extends Empty {

    /**
     * Pad top from shape border.
     */
    private static final int TOP_PADDING = 3;

    /**
     * Pad bottom from shape border.
     */
    private static final int BOTTOM_PADDING = 4;

    @Override
    public void paint(final Graphics2D graphic) {
        super.paint(graphic);
        backupColorAndStroke(graphic);

        graphic.setColor(Color.BLACK);
        graphic.setStroke(Strokes.createForRail(true));
        final Point pos = getPosition();
        final Dimension size = getSize();
        final int xCenter = getCenterX();
        graphic.drawLine(xCenter, pos.y + TOP_PADDING, xCenter, pos.y + size.height - BOTTOM_PADDING);

        resotreColorAndStroke(graphic);
    }
}
