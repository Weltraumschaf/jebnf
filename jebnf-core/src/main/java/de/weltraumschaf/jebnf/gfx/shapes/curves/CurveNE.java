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
import java.awt.Dimension;
import java.awt.geom.Arc2D;

/**
 *
 * @author Sven Strittmatter <weltraumschaf@googlemail.com>
 */
public class CurveNE extends AbstractCurve {

    @Override
    protected Arc2D createArc() {
        return createArc(START_180_DEGREE, EXTEND_90_DEGREE);
    }

    @Override
    protected Point calcArcPosition() {
        final Point pos      = getPosition();
        final Dimension size = getSize();
        return new Point(pos.getX() + size.width / 2, pos.getY() - (size.height / 2) - 1);
    }

    @Override
    protected Dimension calcArcDimenson() {
        final Dimension size = getSize();
        return new Dimension(size.width + 1, size.height);
    }

}
