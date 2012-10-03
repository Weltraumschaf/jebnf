/*
 * LICENSE
 *
 * "THE BEER-WARE LICENSE" (Revision 43):
 * "Sven Strittmatter" <weltraumschaf(at)googlemail(dot)com> wrote this file.
 * As long as you retain this notice you can do whatever you want with
 * this stuff. If we meet some day, and you think this stuff is worth it,
 * you can buy me a non alcohol free beer in return.
 *
 * Copyright (C) 2012 "Sven Strittmatter" <weltraumschaf(at)googlemail(dot)com>
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
public class SouthWestCurveShapeTest {

    private static final double DELTA = 0.01;

    @Test public void createArc_onDefaultPositionWithDefaultSize() {
        final CurveShape curve = new CurveShape(CurveShape.Directions.SOUTH_WEST);
        final Arc2D arc = curve.createArc();
        assertEquals(0.0, arc.getAngleStart(), DELTA);
        assertEquals(90.0, arc.getAngleExtent(), DELTA);
        assertEquals(-16.0, arc.getX(), DELTA);
        assertEquals(15.0, arc.getY(), DELTA);
        assertEquals(31.0, arc.getWidth(), DELTA);
        assertEquals(32.0, arc.getHeight(), DELTA);
    }

    @Test public void createArc_onPosition50x50WithDefaultSize() {
        final CurveShape curve = new CurveShape(CurveShape.Directions.SOUTH_WEST);
        curve.setPosition(Point.valueOf(50, 50));
        final Arc2D arc = curve.createArc();
        assertEquals(0.0, arc.getAngleStart(), DELTA);
        assertEquals(90.0, arc.getAngleExtent(), DELTA);
        assertEquals(34.0, arc.getX(), DELTA);
        assertEquals(65.0, arc.getY(), DELTA);
        assertEquals(31.0, arc.getWidth(), DELTA);
        assertEquals(32.0, arc.getHeight(), DELTA);
    }

    @Test public void createArc_onPosition100x100WithDefaultSize() {
        final CurveShape curve = new CurveShape(CurveShape.Directions.SOUTH_WEST);
        curve.setPosition(Point.valueOf(100, 100));
        final Arc2D arc = curve.createArc();
        assertEquals(0.0, arc.getAngleStart(), DELTA);
        assertEquals(90.0, arc.getAngleExtent(), DELTA);
        assertEquals(84.0, arc.getX(), DELTA);
        assertEquals(115.0, arc.getY(), DELTA);
        assertEquals(31.0, arc.getWidth(), DELTA);
        assertEquals(32.0, arc.getHeight(), DELTA);
    }

    @Test public void createArc_onDefaultPositionWithSize50x50() {
        final CurveShape curve = new CurveShape(CurveShape.Directions.SOUTH_WEST);
        curve.setSize(Size.valueOf(50, 50));
        final Arc2D arc = curve.createArc();
        assertEquals(0.0, arc.getAngleStart(), DELTA);
        assertEquals(90.0, arc.getAngleExtent(), DELTA);
        assertEquals(-26.0, arc.getX(), DELTA);
        assertEquals(25.0, arc.getY(), DELTA);
        assertEquals(50.0, arc.getWidth(), DELTA);
        assertEquals(51.0, arc.getHeight(), DELTA);
    }

    @Test public void createArc_onPosition50x50WithSize50x50() {
        final CurveShape curve = new CurveShape(CurveShape.Directions.SOUTH_WEST);
        curve.setPosition(Point.valueOf(50, 50));
        curve.setSize(Size.valueOf(50, 50));
        final Arc2D arc = curve.createArc();
        assertEquals(0.0, arc.getAngleStart(), DELTA);
        assertEquals(90.0, arc.getAngleExtent(), DELTA);
        assertEquals(24.0, arc.getX(), DELTA);
        assertEquals(75.0, arc.getY(), DELTA);
        assertEquals(50.0, arc.getWidth(), DELTA);
        assertEquals(51.0, arc.getHeight(), DELTA);
    }

    @Test public void createArc_onPosition100x100WithSize50x50() {
        final CurveShape curve = new CurveShape(CurveShape.Directions.SOUTH_WEST);
        curve.setPosition(Point.valueOf(100, 100));
        curve.setSize(Size.valueOf(50, 50));
        final Arc2D arc = curve.createArc();
        assertEquals(0.0, arc.getAngleStart(), DELTA);
        assertEquals(90.0, arc.getAngleExtent(), DELTA);
        assertEquals(74.0, arc.getX(), DELTA);
        assertEquals(125.0, arc.getY(), DELTA);
        assertEquals(50.0, arc.getWidth(), DELTA);
        assertEquals(51.0, arc.getHeight(), DELTA);
    }

}
