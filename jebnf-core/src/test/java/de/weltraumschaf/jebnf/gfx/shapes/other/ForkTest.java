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
import java.awt.Graphics2D;
import static org.junit.Assert.*;
import org.junit.Test;
import static org.mockito.Mockito.*;

/**
 *
 * @author Sven Strittmatter <weltraumschaf@googlemail.com>
 */
public class ForkTest {

    @Test public void paint() {
        final Point pos = Point.valueOf(1, 2);
        final Graphics2D graphics = mock(Graphics2D.class);

        final StraightShape straight = mock(StraightShape.class);
        final CurveShape curve       = mock(CurveShape.class);

        final ForkShape sut = new ForkShape(straight, curve);
        sut.setPosition(pos);
        sut.paint(graphics);

        verify(straight, times(1)).setPosition(pos);
        verify(straight, times(1)).setTransparent(true);
        verify(straight, times(1)).paint(graphics);

        verify(curve, times(1)).setPosition(pos);
        verify(curve, times(1)).setTransparent(true);
        verify(curve, times(1)).paint(graphics);
    }

    /**
     * Test creation and paint w/o any errors.
     */
    @Test public void constructAndPaintConcreteForks() {
        ForkShape sut;

        sut = ForkShape.newVerticalNorthEast();
        assertEquals(StraightShape.Directions.NORT_SOUTH, sut.getStraight().getDirection());
        assertEquals(CurveShape.Directions.NORTH_EAST, sut.getCurve().getDirection());
        sut.paint(mock(Graphics2D.class));

        sut = ForkShape.newVerticalNorthWest();
        assertEquals(StraightShape.Directions.NORT_SOUTH, sut.getStraight().getDirection());
        assertEquals(CurveShape.Directions.NORTH_WEST, sut.getCurve().getDirection());
        sut.paint(mock(Graphics2D.class));

        sut = ForkShape.newVerticalSouthEast();
        assertEquals(StraightShape.Directions.NORT_SOUTH, sut.getStraight().getDirection());
        assertEquals(CurveShape.Directions.SOUTH_EAST, sut.getCurve().getDirection());
        sut.paint(mock(Graphics2D.class));

        sut = ForkShape.newVerticalSouthWest();
        assertEquals(StraightShape.Directions.NORT_SOUTH, sut.getStraight().getDirection());
        assertEquals(CurveShape.Directions.SOUTH_WEST, sut.getCurve().getDirection());
        sut.paint(mock(Graphics2D.class));

        sut = ForkShape.newHorizontalNorthEast();
        assertEquals(StraightShape.Directions.WEST_EAST, sut.getStraight().getDirection());
        assertEquals(CurveShape.Directions.NORTH_EAST, sut.getCurve().getDirection());
        sut.paint(mock(Graphics2D.class));

        sut = ForkShape.newHorizontalNorthWest();
        assertEquals(StraightShape.Directions.WEST_EAST, sut.getStraight().getDirection());
        assertEquals(CurveShape.Directions.NORTH_WEST, sut.getCurve().getDirection());
        sut.paint(mock(Graphics2D.class));

        sut = ForkShape.newHorizontalSouthEast();
        assertEquals(StraightShape.Directions.WEST_EAST, sut.getStraight().getDirection());
        assertEquals(CurveShape.Directions.SOUTH_EAST, sut.getCurve().getDirection());
        sut.paint(mock(Graphics2D.class));

        sut = ForkShape.newHorizontalSouthWest();
        assertEquals(StraightShape.Directions.WEST_EAST, sut.getStraight().getDirection());
        assertEquals(CurveShape.Directions.SOUTH_WEST, sut.getCurve().getDirection());
        sut.paint(mock(Graphics2D.class));
    }

}
