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

package de.weltraumschaf.jebnf.gfx.shapes.forks;

import de.weltraumschaf.jebnf.gfx.shapes.forkes.ForkShape;
import de.weltraumschaf.jebnf.gfx.shapes.other.RectangularShape;
import de.weltraumschaf.jebnf.gfx.Point;
import de.weltraumschaf.jebnf.gfx.shapes.forkes.ForkShape;
import de.weltraumschaf.jebnf.gfx.shapes.forkes.HForkNorthEastShape;
import de.weltraumschaf.jebnf.gfx.shapes.forkes.HForkNorthWestShape;
import de.weltraumschaf.jebnf.gfx.shapes.forkes.HForkSouthEastShape;
import de.weltraumschaf.jebnf.gfx.shapes.forkes.HForkSouthWestShape;
import de.weltraumschaf.jebnf.gfx.shapes.forkes.VForkNorthEastShape;
import de.weltraumschaf.jebnf.gfx.shapes.forkes.VForkNorthWestShape;
import de.weltraumschaf.jebnf.gfx.shapes.forkes.VForkSouthEastShape;
import de.weltraumschaf.jebnf.gfx.shapes.forkes.VForkSouthWestShape;
import java.awt.Graphics2D;
import org.junit.Test;
import static org.mockito.Mockito.*;

/**
 *
 * @author Sven Strittmatter <weltraumschaf@googlemail.com>
 */
public class ForkTest {

    @Test public void paint() {
        final Point pos = new Point(1, 2);
        final Graphics2D graphics = mock(Graphics2D.class);

        final RectangularShape straight   = mock(RectangularShape.class);
        final RectangularShape curve      = mock(RectangularShape.class);

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

    @Test public void constructAndPaintConcreteForks() {
        // Test creation and paint w/o any errors.
        final VForkNorthEastShape fork1 = new VForkNorthEastShape();
        fork1.paint(mock(Graphics2D.class));

        final VForkNorthWestShape fork2 = new VForkNorthWestShape();
        fork2.paint(mock(Graphics2D.class));

        final VForkSouthEastShape fork3 = new VForkSouthEastShape();
        fork3.paint(mock(Graphics2D.class));

        final VForkSouthWestShape fork4 = new VForkSouthWestShape();
        fork4.paint(mock(Graphics2D.class));

        // Test creation and paint w/o any errors.
        final HForkNorthEastShape fork5 = new HForkNorthEastShape();
        fork5.paint(mock(Graphics2D.class));

        final HForkNorthWestShape fork6 = new HForkNorthWestShape();
        fork6.paint(mock(Graphics2D.class));

        final HForkSouthEastShape fork7 = new HForkSouthEastShape();
        fork7.paint(mock(Graphics2D.class));

        final HForkSouthWestShape fork8 = new HForkSouthWestShape();
        fork8.paint(mock(Graphics2D.class));
    }
}
