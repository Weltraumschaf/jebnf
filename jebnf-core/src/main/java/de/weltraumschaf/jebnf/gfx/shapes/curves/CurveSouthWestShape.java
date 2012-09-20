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
import java.awt.geom.Arc2D;

/**
 *
 * @author Sven Strittmatter <weltraumschaf@googlemail.com>
 */
public class CurveSouthWestShape extends AbstractCurveShape {

    @Override
    protected Arc2D createArc() {
        return createArc(0, QUARTER_CLOCKWISE);
    }

    @Override
    protected Point calcArcPosition() {
        final Point pos      = getPosition();
        final Size size = getSize();
        return new Point(pos.getX() - (size.getWidth() / 2) - 1, pos.getY() + size.getHeight() / 2);
    }

    @Override
    protected Size calcArcDimenson() {
        return getSize().setHeight(getSize().getHeight() + 1);
    }

}
