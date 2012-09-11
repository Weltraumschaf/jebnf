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

package de.weltraumschaf.jebnf.gfx.shapes.compound;

import de.weltraumschaf.jebnf.gfx.Point;
import de.weltraumschaf.jebnf.gfx.Size;
import java.awt.Graphics2D;
import org.junit.Test;
import static org.mockito.Mockito.*;

/**
 *
 * @author Sven Strittmatter <weltraumschaf@googlemail.com>
 */
public class AbstractCompundShapeTest {

    static class AbstractCompundStub extends AbstractCompundShape {
        public AbstractCompundStub(final GridLayoutShape grid) {
            super(grid);
        }
    }

    @Test public void delegatesToGridLayout() {
        final GridLayoutShape grid = mock(GridLayoutShape.class);
        final AbstractCompundStub sut = new AbstractCompundStub(grid);

        sut.getPosition();
        verify(grid, times(1)).getPosition();

        final Point pos = new Point();
        sut.setPosition(pos);
        verify(grid, times(1)).setPosition(pos);

        sut.isDebug();
        verify(grid, times(1)).isDebug();

        sut.setDebug(true);
        verify(grid, times(1)).setDebug(true);
        sut.setDebug(false);
        verify(grid, times(1)).setDebug(false);

        sut.getSize();
        verify(grid, times(1)).getSize();

        final Size size = new Size();
        sut.setSize(size);
        verify(grid, times(1)).setSize(size);

        final Graphics2D graphics = mock(Graphics2D.class);
        sut.paint(graphics);
        verify(grid, times(1)).paint(graphics);

        sut.adjust(graphics);
        verify(grid, times(1)).adjust(graphics);
    }

}
