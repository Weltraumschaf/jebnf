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
import static org.junit.Assert.assertEquals;
import org.junit.Test;

/**
 *
 * @author Sven Strittmatter <weltraumschaf@googlemail.com>
 */
public class CurveNorthWestShapeTest {

    @Test public void calcArcPosition() {
        final CurveNorthWestShape curve = new CurveNorthWestShape();
        assertEquals(new Point(-16, -16), curve.calcArcPosition());
        curve.setPosition(new Point(100, 100));
        assertEquals(new Point(84, 84), curve.calcArcPosition());
        curve.setSize(new Size(50, 50));
        assertEquals(new Point(74, 74), curve.calcArcPosition());
    }

    @Test public void calcArcDimenson() {
        final CurveNorthWestShape curve = new CurveNorthWestShape();
        assertEquals(new Size(30, 31), curve.calcArcDimenson());
        curve.setSize(new Size(50, 50));
        assertEquals(new Size(49, 50), curve.calcArcDimenson());
    }

    @Test public void createArc() {
        final CurveNorthWestShape curve = new CurveNorthWestShape();
        final Arc2D arc = curve.createArc();
        assertEquals(270, (int) arc.getAngleStart());
        assertEquals(90, (int) arc.getAngleExtent());
    }
}
