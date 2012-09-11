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

import de.weltraumschaf.jebnf.gfx.shapes.forkes.HForkNE;
import de.weltraumschaf.jebnf.gfx.shapes.forkes.VForkNW;
import de.weltraumschaf.jebnf.gfx.shapes.forkes.VForkSW;
import de.weltraumschaf.jebnf.gfx.shapes.forkes.HForkSE;
import de.weltraumschaf.jebnf.gfx.shapes.forkes.HForkSW;
import de.weltraumschaf.jebnf.gfx.shapes.forkes.VForkNE;
import de.weltraumschaf.jebnf.gfx.shapes.forkes.HForkNW;
import de.weltraumschaf.jebnf.gfx.shapes.forkes.VForkSE;
import java.awt.Graphics2D;
import org.junit.Test;
import static org.mockito.Mockito.mock;

/**
 *
 * @author Sven Strittmatter <weltraumschaf@googlemail.com>
 */
public class ForkTest {

    @Test public void constructAndPaint() {
        // Test creation and paint w/o any errors.
        final VForkNE fork1 = new VForkNE();
        fork1.paint(mock(Graphics2D.class));

        final VForkNW fork2 = new VForkNW();
        fork2.paint(mock(Graphics2D.class));

        final VForkSE fork3 = new VForkSE();
        fork3.paint(mock(Graphics2D.class));

        final VForkSW fork4 = new VForkSW();
        fork4.paint(mock(Graphics2D.class));

        // Test creation and paint w/o any errors.
        final HForkNE fork5 = new HForkNE();
        fork5.paint(mock(Graphics2D.class));

        final HForkNW fork6 = new HForkNW();
        fork6.paint(mock(Graphics2D.class));

        final HForkSE fork7 = new HForkSE();
        fork7.paint(mock(Graphics2D.class));

        final HForkSW fork8 = new HForkSW();
        fork8.paint(mock(Graphics2D.class));
    }
}