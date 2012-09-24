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
import de.weltraumschaf.jebnf.gfx.shapes.other.CurveShape;
import de.weltraumschaf.jebnf.gfx.shapes.other.RectangularShape;
import de.weltraumschaf.jebnf.gfx.shapes.other.StraightShape;
import java.awt.Graphics2D;

/**
 * Forks are a combination of either a
 * {@link de.weltraumschaf.jebnf.gfx.shapes.other.StraightShape "straight shape"} with direction
 * {@link StraightShape.Directions#NORT_SOUTH} or {@link StraightShape.Directions#WEST_EAST} shape
 * and a {@link CurveShape "curve shape"} with direction  {@link CurveShape.Directions#North_EAST},
 * {@link CurveShape.Directions#North_WEST}, {@link CurveShape.Directions#SOUTH_EAST},
 * or {@link CurveShape.Directions#SOUTH_WEST}.
 *
 * @author Sven Strittmatter <weltraumschaf@googlemail.com>
 */
public class ForkShape extends RectangularShape {

    /**
     * The straight shape part.
     */
    final RectangularShape straight;

    /**
     * The curve shape part.
     */
    final RectangularShape curve;

    /**
     * Initializes {@link #straight} and {@link  #curve}.
     *
     * @param straight {@link de.weltraumschaf.jebnf.gfx.shapes.other.StraightNorthSouthShape} or
     * {@link de.weltraumschaf.jebnf.gfx.shapes.other.StraightWestEastShape}.
     * @param curve One of {@link de.weltraumschaf.jebnf.gfx.shapes.curves.CurveNorthEastShape},
     *              {@link de.weltraumschaf.jebnf.gfx.shapes.curves.CurveNorthWestShape},
     *              {@link de.weltraumschaf.jebnf.gfx.shapes.curves.CurveSouthEastShape}, or
     * {@link de.weltraumschaf.jebnf.gfx.shapes.curves.CurveSouthWestShape}.
     */
    public ForkShape(final RectangularShape straight, final RectangularShape curve) {
        super();
        this.straight = straight;
        this.curve = curve;
    }

    @Override
    public void paint(final Graphics2D graphic) {
        super.paint(graphic);
        final Point pos = getPosition();

        straight.setPosition(pos);
        straight.setTransparent(true);
        straight.paint(graphic);

        curve.setPosition(pos);
        curve.setTransparent(true);
        curve.paint(graphic);
    }

    public RectangularShape getStraight() {
        return straight;
    }

    public RectangularShape getCurve() {
        return curve;
    }

    /**
     * Horizontal fork with straight line from west to east and curve from north to east..
     *
     * Schematic:
     * <pre>
     *    |
     * ____\___
     *
     *
     * </pre>
     *
     * @return
     */
    public static ForkShape newHorizontalNorthEast() {
        return new ForkShape(
                new StraightShape(StraightShape.Directions.WEST_EAST),
                new CurveShape(CurveShape.Directions.NORTH_EAST));
    }

    /**
     * Horizontal fork with straight line from west to east and curve from north to east..
     *
     * Schematic:
     * <pre>
     *    |
     * __/____
     *
     *
     * </pre>
     *
     * @return
     */
    public static ForkShape newHorizontalNorthWest() {
        return new ForkShape(
                new StraightShape(StraightShape.Directions.WEST_EAST),
                new CurveShape(CurveShape.Directions.NORTH_WEST));
    }

    /**
     * Horizontal fork with straight line from west to east and curve from north to east..
     *
     * Schematic:
     * <pre>
     *
     * _______
     *    /
     *    |
     * </pre>
     *
     * @return
     */
    public static ForkShape newHorizontalSouthEast() {
        return new ForkShape(
                new StraightShape(StraightShape.Directions.WEST_EAST),
                new CurveShape(CurveShape.Directions.SOUTH_EAST));
    }

    /**
     * Horizontal fork with straight line from west to east and curve from north to east..
     *
     * Schematic:
     * <pre>
     *
     * _______
     *   \
     *    |
     * </pre>
     *
     * @return
     */
    public static ForkShape newHorizontalSouthWest() {
        return new ForkShape(
                new StraightShape(StraightShape.Directions.WEST_EAST),
                new CurveShape(CurveShape.Directions.SOUTH_WEST));
    }

    /**
     * Vertical fork with straight line from north to south and curve from north to east..
     *
     * Schematic:
     * <pre>
     *    |
     *    |\__
     *    |
     *    |
     * </pre>
     *
     * @return
     */
    public static ForkShape newVerticalNorthEast() {
        return new ForkShape(
                new StraightShape(StraightShape.Directions.NORT_SOUTH),
                new CurveShape(CurveShape.Directions.NORTH_EAST));
    }

    /**
     * Vertical fork with straight line from north to south and curve from north to west..
     *
     * Schematic:
     * <pre>
     *    |
     * __/|
     *    |
     *    |
     * </pre>
     *
     * @return
     */
    public static ForkShape newVerticalNorthWest() {
        return new ForkShape(
                new StraightShape(StraightShape.Directions.NORT_SOUTH),
                new CurveShape(CurveShape.Directions.NORTH_WEST));
    }

    /**
     * Vertical fork with straight line from north to south and curve from south to east..
     *
     * Schematic:
     * <pre>
     *    |
     *    | __
     *    |/
     *    |
     * </pre>
     *
     * @return
     */
    public static ForkShape newVerticalSouthEast() {
        return new ForkShape(
                new StraightShape(StraightShape.Directions.NORT_SOUTH),
                new CurveShape(CurveShape.Directions.SOUTH_EAST));
    }

    /**
     * Vertical fork with straight line from north to south and curve from south to west..
     *
     * Schematic:
     * <pre>
     *    |
     * __ |
     *   \|
     *    |
     * </pre>
     *
     * @return
     */
    public static ForkShape newVerticalSouthWest() {
        return new ForkShape(
                new StraightShape(StraightShape.Directions.NORT_SOUTH),
                new CurveShape(CurveShape.Directions.SOUTH_WEST));
    }

}
