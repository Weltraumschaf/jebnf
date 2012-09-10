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

import de.weltraumschaf.jebnf.gfx.Antialiaser;
import de.weltraumschaf.jebnf.gfx.Point;
import de.weltraumschaf.jebnf.gfx.Size;
import de.weltraumschaf.jebnf.gfx.Strokes;
import java.awt.Color;
import java.awt.Graphics2D;

/**
 *
 * @author Sven Strittmatter <weltraumschaf@googlemail.com>
 */
public class StraightNS extends Empty {

    @Override
    public void paint(final Graphics2D graphic) {
        super.paint(graphic);
        final Point pos = getPosition();
        final Size size = getSize();

        // Disable antialiasing for straight lines.
        final Object oldRenderingHint = Antialiaser.turnOff(graphic);
        backupColorAndStroke(graphic);

        graphic.setStroke(Strokes.createForRail());
        graphic.setColor(Color.BLACK);

        final int xPosition = pos.getX() + size.getWidth() / 2;
        graphic.drawLine(xPosition, pos.getY(), xPosition, pos.getY() + size.getHeight());

        // Restore previous settings
        resotreColorAndStroke(graphic);
        Antialiaser.setHintOnGraphics(graphic, oldRenderingHint);
    }

}
