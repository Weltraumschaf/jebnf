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
import de.weltraumschaf.jebnf.gfx.Strokes;
import static de.weltraumschaf.jebnf.gfx.shapes.ShapeFactory.start;
import java.awt.Color;
import java.awt.Graphics2D;
import org.junit.Test;
import static org.mockito.Mockito.*;

/**
 *
 * @author Sven Strittmatter <weltraumschaf@googlemail.com>
 */
public class StartTest {

    @Test public void paint() {
        final Graphics2D graphics = mock(Graphics2D.class);
        final Start start = start();

        start.setPosition(new Point(0, 0));
        start.setSize(new Size());
        start.paint(graphics);

        verify(graphics, atLeast(1)).setColor(Color.BLACK);
        verify(graphics, times(1)).setStroke(Strokes.createForRail());
        verify(graphics, times(1)).drawLine(15, 15, 31, 15);
    }

}
