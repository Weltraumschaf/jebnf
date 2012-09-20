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

package de.weltraumschaf.jebnf.gfx.shapes.curves;

import de.weltraumschaf.jebnf.gfx.Point;
import de.weltraumschaf.jebnf.gfx.Size;
import de.weltraumschaf.jebnf.gfx.shapes.CurveShapes;
import java.awt.geom.Arc2D;

/**
 *
 * @author Sven Strittmatter <weltraumschaf@googlemail.com>
 */
public class CurveShape extends AbstractCurveShape {

    private final CurveShapes direction;

    public CurveShape(final CurveShapes direction) {
        this.direction = direction;
    }

    @Override
    protected Point calcArcPosition() {
        final Point pos = getPosition();
        final Size size = getSize();

        switch (direction) {
            case NORTH_EAST:
                return new Point(pos.getX() + size.getWidth() / 2, pos.getY() - (size.getHeight() / 2) - 1);
            case NORTH_WEST:
                return new Point(pos.getX() - size.getWidth() / 2 - 1, pos.getY() - (size.getHeight() / 2) - 1);
            case SOUTH_EAST:
                return new Point(pos.getX() + size.getWidth() / 2, pos.getY() + size.getHeight() / 2);
            case SOUTH_WEST:
                return new Point(pos.getX() - (size.getWidth() / 2) - 1, pos.getY() + size.getHeight() / 2);
            default:
                throw new IllegalArgumentException(String.format("Unsupported straight type: %s!", direction));
        }
    }

    @Override
    protected Size calcArcDimenson() {
        switch (direction) {
            case NORTH_EAST:
                return getSize().setWidth(getSize().getWidth() + 1);
            case NORTH_WEST:
                return getSize().setWidth(getSize().getWidth() - 1);
            case SOUTH_EAST:
                return new Size(getSize().getWidth() + 1, getSize().getHeight() + 1);
            case SOUTH_WEST:
                return getSize().setHeight(getSize().getHeight() + 1);
            default:
                throw new IllegalArgumentException(String.format("Unsupported straight type: %s!", direction));
        }
    }

    @Override
    protected Arc2D createArc() {
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


}
