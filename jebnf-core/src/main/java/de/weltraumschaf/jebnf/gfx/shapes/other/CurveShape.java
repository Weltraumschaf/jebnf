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
import java.awt.geom.Arc2D;

/**
 * Draw curves.
 *
 * @author Sven Strittmatter <weltraumschaf@googlemail.com>
 */
public class CurveShape extends RectangularShape {

    /**
     * Don't know why I need this. W/o it looks bad in output.
     */
    private static final int MAGIC = 1;

    /**
     * Type of curve railroad shapes.
     *
     * @author Sven Strittmatter <weltraumschaf@googlemail.com>
     */
    public enum Directions {

        /**
         * Curve railroad from north to west.
         */
        NORTH_WEST,
        /**
         * Curve railroad from north to east.
         */
        NORTH_EAST,
        /**
         * Curve railroad from south to west.
         */
        SOUTH_WEST,
        /**
         * Curve railroad from south to east.
         */
        SOUTH_EAST;

    }

    /**
     * Start arc at 0 degree.
     */
    private static final int SOUTH = 0;

    /**
     * Start arc at 90 degree.
     */
    private static final int EAST = 90;

    /**
     * Start arc at 180 degree.
     */
    private static final int NORTH = 180;

    /**
     * Start arc at 270 degree.
     */
    private static final int WEST = 270;


    /**
     * Arc extend to 90 degree.
     */
    private static final int QUARTER_CLOCKWISE = 90;

    /**
     * Direction of curve arc (e.g. from south to east.
     */
    private final Directions direction;

    /**
     * Initializes the curve with the direction type.
     *
     * @param direction Direction from where to start and end the curve.
     */
    public CurveShape(final Directions direction) {
        this.direction = direction;
    }

    @Override
    public void paint(final Graphics2D graphic) {
        super.paint(graphic);
        backupColorAndStroke(graphic);
        graphic.setStroke(Strokes.createForRail());
        graphic.setColor(Color.BLACK);
        graphic.draw(createArc());
        resotreColorAndStroke(graphic);
    }

    /**
     * Creates the arc for the curve depending on {@link #direction}.
     *
     * @return Returns AWT arc object.
     */
    protected Arc2D createArc() {
        final Point shapePosition = getPosition();
        final Size size = getSize();
        int startAngle;
        Point arcPosition;
        Size arcSize;

        switch (direction) {
            case NORTH_EAST:
                startAngle = NORTH;
                arcPosition = Point.valueOf(shapePosition.getX() + size.getWidth() / 2,
                                            shapePosition.getY() - (size.getHeight() / 2) - MAGIC);
                arcSize = getSize().setWidth(getSize().getWidth() + MAGIC);
                break;
            case NORTH_WEST:
                startAngle = WEST;
                arcPosition = Point.valueOf(shapePosition.getX() - size.getWidth() / 2 - MAGIC,
                                            shapePosition.getY() - (size.getHeight() / 2) - MAGIC);
                arcSize = getSize().setWidth(getSize().getWidth() - MAGIC);
                break;
            case SOUTH_EAST:
                startAngle = EAST;
                arcPosition = Point.valueOf(shapePosition.getX() + size.getWidth() / 2,
                                            shapePosition.getY() + size.getHeight() / 2);
                arcSize = new Size(getSize().getWidth() + MAGIC, getSize().getHeight() + MAGIC);
                break;
            case SOUTH_WEST:
                startAngle = SOUTH;
                arcPosition = Point.valueOf(shapePosition.getX() - (size.getWidth() / 2) - MAGIC,
                                            shapePosition.getY() + size.getHeight() / 2);
                arcSize = getSize().setHeight(getSize().getHeight() + MAGIC);
                break;
            default:
                startAngle = -1;
                arcPosition = null;
                arcSize = null;
                break;
        }

        if (-1 == startAngle) {
            throw new IllegalArgumentException(String.format("Unsupported straight type: %s!", direction));
        }

        return new Arc2D.Float(arcPosition.getX(),
                               arcPosition.getY(),
                               arcSize.getWidth(),
                               arcSize.getHeight(),
                               startAngle,
                               QUARTER_CLOCKWISE,
                               Arc2D.OPEN);
    }

    /**
     * Get the direction type.
     *
     * @return Enum type of direction.
     */
    public Directions getDirection() {
        return direction;
    }

}
