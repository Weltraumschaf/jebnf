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

import de.weltraumschaf.jebnf.gfx.shapes.forkes.AbstractForkShape;
import de.weltraumschaf.jebnf.gfx.shapes.other.EmptyShape;
import de.weltraumschaf.jebnf.gfx.Point;
import de.weltraumschaf.jebnf.gfx.shapes.forkes.AbstractForkShape;
import java.awt.Graphics2D;
import org.junit.Test;
import static org.mockito.Mockito.*;

/**
 *
 * @author Sven Strittmatter <weltraumschaf@googlemail.com>
 */
public class AbstractForkTest {

    static class AbstractForkStub extends AbstractForkShape {
        public AbstractForkStub(final EmptyShape straight, final EmptyShape curve) {
            super(straight, curve);
        }
    }

    @Test public void paint() {
        final Point pos = new Point(1, 2);
        final Graphics2D graphics = mock(Graphics2D.class);

        final EmptyShape straight   = mock(EmptyShape.class);
        final EmptyShape curve      = mock(EmptyShape.class);

        final AbstractForkShape sut = new AbstractForkStub(straight, curve);
        sut.setPosition(pos);
        sut.paint(graphics);

        verify(straight, times(1)).setPosition(pos);
        verify(straight, times(1)).setTransparent(true);
        verify(straight, times(1)).paint(graphics);

        verify(curve, times(1)).setPosition(pos);
        verify(curve, times(1)).setTransparent(true);
        verify(curve, times(1)).paint(graphics);
    }
}
