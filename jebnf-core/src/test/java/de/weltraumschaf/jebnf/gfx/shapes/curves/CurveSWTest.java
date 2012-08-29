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

import de.weltraumschaf.jebnf.gfx.shapes.curves.CurveSW;
import de.weltraumschaf.jebnf.gfx.Point;
import de.weltraumschaf.jebnf.gfx.Size;
import java.awt.geom.Arc2D;
import static org.junit.Assert.assertEquals;
import org.junit.Test;

/**
 *
 * @author Sven Strittmatter <weltraumschaf@googlemail.com>
 */
public class CurveSWTest {

    @Test public void calcArcPosition() {
        final CurveSW curve = new CurveSW();
        assertEquals(new Point(-16, 15), curve.calcArcPosition());
        curve.setPosition(new Point(100, 100));
        assertEquals(new Point(84, 115), curve.calcArcPosition());
        curve.setSize(new Size(50, 50));
        assertEquals(new Point(74, 125), curve.calcArcPosition());
    }

    @Test public void calcArcDimenson() {
        final CurveSW curve = new CurveSW();
        assertEquals(new Size(31, 32), curve.calcArcDimenson());
        curve.setSize(new Size(50, 50));
        assertEquals(new Size(50, 51), curve.calcArcDimenson());
    }

    @Test public void createArc() {
        final CurveSW curve = new CurveSW();
        final Arc2D arc = curve.createArc();
        assertEquals(0, (int) arc.getAngleStart());
        assertEquals(90, (int) arc.getAngleExtent());
    }

}
