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
import java.awt.Graphics2D;

/**
 *
 * @author Sven Strittmatter <weltraumschaf@googlemail.com>
 */
public class End extends AbstractEndpoint {

    @Override
    public void paint(final Graphics2D graphic) {
        super.paint(graphic);
        backupColorAndStroke(graphic);

        graphic.setColor(Color.BLACK);
        graphic.setStroke(Strokes.createForRail());
        final Point pos = getPosition();
        final int xStart = pos.getX();
        final int xEnd = xStart + getSize().width / 2;
        final int yCenter = getCenterY();
        graphic.drawLine(xStart, yCenter, xEnd, yCenter);

        resotreColorAndStroke(graphic);
    }

}
