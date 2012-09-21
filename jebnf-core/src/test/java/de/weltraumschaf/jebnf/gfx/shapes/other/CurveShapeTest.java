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
import java.awt.geom.Arc2D;
import static org.junit.Assert.*;
import org.junit.Test;

/**
 *
 * @author Sven Strittmatter <weltraumschaf@googlemail.com>
 */
public class CurveShapeTest {

    @Test public void calcArcPositionForSouthWest() {
        final CurveShape curve = new CurveShape(CurveShape.Directions.SOUTH_WEST);
        assertEquals(new Point(-16, 15), curve.calcArcPosition());
        curve.setPosition(new Point(100, 100));
        assertEquals(new Point(84, 115), curve.calcArcPosition());
        curve.setSize(new Size(50, 50));
        assertEquals(new Point(74, 125), curve.calcArcPosition());
    }

    @Test public void calcArcDimensonForSouthWest() {
        final CurveShape curve = new CurveShape(CurveShape.Directions.SOUTH_WEST);
        assertEquals(new Size(31, 32), curve.calcArcDimenson());
        curve.setSize(new Size(50, 50));
        assertEquals(new Size(50, 51), curve.calcArcDimenson());
    }

    @Test public void createArcForSouthWest() {
        final CurveShape curve = new CurveShape(CurveShape.Directions.SOUTH_WEST);
        final Arc2D arc = curve.createArc(CurveShape.SOUTH);
        assertEquals(0, (int) arc.getAngleStart());
        assertEquals(90, (int) arc.getAngleExtent());
    }

    @Test public void calcArcPositionForSouthEast() {
        final CurveShape curve = new CurveShape(CurveShape.Directions.SOUTH_EAST);
        assertEquals(new Point(15, 15), curve.calcArcPosition());
        curve.setPosition(new Point(100, 100));
        assertEquals(new Point(115, 115), curve.calcArcPosition());
        curve.setSize(new Size(50, 50));
        assertEquals(new Point(125, 125), curve.calcArcPosition());
    }

    @Test public void calcArcDimensonForSouthEast() {
        final CurveShape curve = new CurveShape(CurveShape.Directions.SOUTH_EAST);
        assertEquals(new Size(32, 32), curve.calcArcDimenson());
        curve.setSize(new Size(50, 50));
        assertEquals(new Size(51, 51), curve.calcArcDimenson());
    }

    @Test public void createArcForSouthEast() {
        final CurveShape curve = new CurveShape(CurveShape.Directions.SOUTH_EAST);
        final Arc2D arc = curve.createArc(CurveShape.EAST);
        assertEquals(90, (int) arc.getAngleStart());
        assertEquals(90, (int) arc.getAngleExtent());
    }

    @Test public void calcArcPositionForNorthWest() {
        final CurveShape curve = new CurveShape(CurveShape.Directions.NORTH_WEST);
        assertEquals(new Point(-16, -16), curve.calcArcPosition());
        curve.setPosition(new Point(100, 100));
        assertEquals(new Point(84, 84), curve.calcArcPosition());
        curve.setSize(new Size(50, 50));
        assertEquals(new Point(74, 74), curve.calcArcPosition());
    }

    @Test public void calcArcDimensonForNorthWest() {
        final CurveShape curve = new CurveShape(CurveShape.Directions.NORTH_WEST);
        assertEquals(new Size(30, 31), curve.calcArcDimenson());
        curve.setSize(new Size(50, 50));
        assertEquals(new Size(49, 50), curve.calcArcDimenson());
    }

    @Test public void createArcForNorthWest() {
        final CurveShape curve = new CurveShape(CurveShape.Directions.NORTH_WEST);
        final Arc2D arc = curve.createArc(CurveShape.WEST);
        assertEquals(270, (int) arc.getAngleStart());
        assertEquals(90, (int) arc.getAngleExtent());
    }

    @Test public void calcArcPositionForNorthEast() {
        final CurveShape curve = new CurveShape(CurveShape.Directions.NORTH_EAST);
        assertEquals(new Point(15, -16), curve.calcArcPosition());
        curve.setPosition(new Point(100, 100));
        assertEquals(new Point(115, 84), curve.calcArcPosition());
        curve.setSize(new Size(50, 50));
        assertEquals(new Point(125, 74), curve.calcArcPosition());
    }

    @Test public void calcArcDimensonForNorthEast() {
        final CurveShape curve = new CurveShape(CurveShape.Directions.NORTH_EAST);
        assertEquals(new Size(32, 31), curve.calcArcDimenson());
        curve.setSize(new Size(50, 50));
        assertEquals(new Size(51, 50), curve.calcArcDimenson());
    }

    @Test public void createArcForNorthEast() {
        final CurveShape curve = new CurveShape(CurveShape.Directions.NORTH_EAST);
        final Arc2D arc = curve.createArc(CurveShape.NORTH);
        assertEquals(180, (int) arc.getAngleStart());
        assertEquals(90, (int) arc.getAngleExtent());
    }

}