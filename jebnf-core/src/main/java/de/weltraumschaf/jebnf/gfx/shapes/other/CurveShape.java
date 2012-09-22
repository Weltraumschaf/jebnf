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
    private static final int MAGIC_VOODOO = 1;

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
     * Arc extend to 90 degree.
     */
    private static final int QUARTER_CLOCKWISE = 90;

    /**
     * Start arc at 0 degree.
     */
    protected static final int SOUTH = 0;

    /**
     * Start arc at 90 degree.
     */
    protected static final int EAST = 90;

    /**
     * Start arc at 180 degree.
     */
    protected static final int NORTH = 180;

    /**
     * Start arc at 270 degree.
     */
    protected static final int WEST = 270;

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

    private Arc2D createArc() {
        switch (direction) {
            case NORTH_EAST:
                return createArc(NORTH);
            case NORTH_WEST:
                return createArc(WEST);
            case SOUTH_EAST:
                return createArc(EAST);
            case SOUTH_WEST:
                return createArc(SOUTH);
            default:
                throw new IllegalArgumentException(String.format("Unsupported straight type: %s!", direction));
        }
    }

    /**
     * Creates arc object.
     *
     * @param start Start of arc.
     * @return Returns arc object.
     */
    protected Arc2D createArc(final int start) {
        final Point pos = calcArcPosition();
        final Size size = calcArcDimenson();
        return new Arc2D.Float(pos.getX(),
                               pos.getY(),
                               size.getWidth(),
                               size.getHeight(),
                               start,
                               QUARTER_CLOCKWISE,
                               Arc2D.OPEN);
    }

    protected Point calcArcPosition() {
        final Point pos = getPosition();
        final Size size = getSize();

        switch (direction) {
            case NORTH_EAST:
                return new Point(pos.getX() + size.getWidth() / 2, pos.getY() - (size.getHeight() / 2) - MAGIC_VOODOO);
            case NORTH_WEST:
                return new Point(pos.getX() - size.getWidth() / 2 - MAGIC_VOODOO, pos.getY() - (size.getHeight() / 2) - MAGIC_VOODOO);
            case SOUTH_EAST:
                return new Point(pos.getX() + size.getWidth() / 2, pos.getY() + size.getHeight() / 2);
            case SOUTH_WEST:
                return new Point(pos.getX() - (size.getWidth() / 2) - MAGIC_VOODOO, pos.getY() + size.getHeight() / 2);
            default:
                throw new IllegalArgumentException(String.format("Unsupported straight type: %s!", direction));
        }
    }

    protected Size calcArcDimenson() {
        switch (direction) {
            case NORTH_EAST:
                return getSize().setWidth(getSize().getWidth() + MAGIC_VOODOO);
            case NORTH_WEST:
                return getSize().setWidth(getSize().getWidth() - MAGIC_VOODOO);
            case SOUTH_EAST:
                return new Size(getSize().getWidth() + MAGIC_VOODOO, getSize().getHeight() + MAGIC_VOODOO);
            case SOUTH_WEST:
                return getSize().setHeight(getSize().getHeight() + MAGIC_VOODOO);
            default:
                throw new IllegalArgumentException(String.format("Unsupported straight type: %s!", direction));
        }
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
