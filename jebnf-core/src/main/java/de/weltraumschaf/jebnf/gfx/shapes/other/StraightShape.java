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
 * Paints a straight line either from north to south or from west to east.
 *
 * @author Sven Strittmatter <weltraumschaf@googlemail.com>
 */
public class StraightShape extends RectangularShape {

    /**
     * Type of straight railroad shapes.
     *
     * @author Sven Strittmatter <weltraumschaf@googlemail.com>
     */
    public static enum Directions {

        /**
         * Straight railroad from north to south.
         */
        NORT_SOUTH,

        /**
         * Straight railroad from west to east.
         */
        WEST_EAST;

    }

    /**
     * Direction of the straight line: Either from north to south or from west to east.
     */
    private final Directions direction;

    /**
     * Initializes the straight shape with the type of direction.
     * @param type Either {@link Directions#NORT_SOUTH} or {@link Directions#WEST_EAST}.
     */
    public StraightShape(final Directions type) {
        this.direction = type;
    }

    @Override
    public void paint(final Graphics2D graphic) {
        super.paint(graphic);

        if (Directions.NORT_SOUTH == direction) {
            paintNorthSouth(graphic);
        } else if (Directions.WEST_EAST == direction) {
            paintWestEast(graphic);
        } else {
            throw new IllegalArgumentException(String.format("Unsupported straight type: %s!", direction));
        }
    }

    private void paintNorthSouth(final Graphics2D graphic) {
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

    private void paintWestEast(final Graphics2D graphic) {
        final Point pos = getPosition();
        final Size size = getSize();

        // Disable antialiasing for straight lines.
        final Object oldRenderingHint = Antialiaser.turnOff(graphic);
        backupColorAndStroke(graphic);

        graphic.setStroke(Strokes.createForRail());
        graphic.setColor(Color.BLACK); // TODO Remove this to somwhere.

        final int yPosition = pos.getY() + size.getHeight() / 2;
        final int xPosition = pos.getX();
        graphic.drawLine(xPosition, yPosition, xPosition + size.getWidth(), yPosition);

        // Restore previous settings
        resotreColorAndStroke(graphic);
        Antialiaser.setHintOnGraphics(graphic, oldRenderingHint);
    }

    public Directions getDirection() {
        return direction;
    }

}
