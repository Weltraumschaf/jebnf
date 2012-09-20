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
import de.weltraumschaf.jebnf.gfx.Strokes;
import de.weltraumschaf.jebnf.gfx.shapes.other.EmptyShape;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.geom.Arc2D;

/**
 * Common functionality for curves.
 *
 * @author Sven Strittmatter <weltraumschaf@googlemail.com>
 */
public abstract class AbstractCurveShape extends EmptyShape implements CurveShape {

    /**
     * Arc extend to 90 degree.
     */
    protected static final int QUARTER_CLOCKWISE = 90;

    protected static final int SOUTH = 0;

    /**
     * Arc start at 90 degree.
     */
    protected static final int EAST = 90;

    /**
     * Arc start at 180 degree.
     */
    protected static final int NORTH = 180;
    protected static final int WEST = 270;

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
     * Calculate position of the curve arc.
     *
     * @return Returns point.
     */
    protected abstract Point calcArcPosition();

    /**
     * Calculate curve arc dimension.
     *
     * @return Returns dimension.
     */
    protected abstract Size calcArcDimenson();

    /**
     * Creates the curve arc.
     *
     * @return Returns arc object.
     */
    protected abstract Arc2D createArc();

    /**
     * Creates arc object.
     *
     * @param start Start of arc.
     * @return Returns arc object.
     */
    protected Arc2D createArc(final int start) {
        return createArc(calcArcPosition(), calcArcDimenson(), start);
    }

    /**
     * Creates arc object.
     *
     * @param pos Position of arc.
     * @param size size of arc.
     * @param start Start of arc.
     * @return Returns arc object.
     */
    protected Arc2D createArc(final Point pos, final Size size, final int start) {
        return new Arc2D.Float(pos.getX(), pos.getY(), size.getWidth(), size.getHeight(), start, QUARTER_CLOCKWISE, Arc2D.OPEN);
    }

}
