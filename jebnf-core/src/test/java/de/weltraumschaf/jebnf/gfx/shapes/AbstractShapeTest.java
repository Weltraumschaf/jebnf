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

package de.weltraumschaf.jebnf.gfx.shapes;

import de.weltraumschaf.jebnf.gfx.Point;
import de.weltraumschaf.jebnf.gfx.Size;
import de.weltraumschaf.jebnf.gfx.Strokes;
import de.weltraumschaf.jebnf.gfx.shapes.AbstractShape.GraphicsSetting;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Stroke;
import static org.junit.Assert.*;
import org.junit.Test;
import static org.mockito.Mockito.*;

/**
 *
 * @author Sven Strittmatter <weltraumschaf@googlemail.com>
 */
public class AbstractShapeTest {

    static class AbstractShapeImpl extends AbstractShape {
        @Override
        public void paint(final Graphics2D graphic) {
            // Only a stub.
        }
    }

    @Test public void createInstances() {
        final Point expectedPoint1 = new Point();
        final Size expectedDim1 = new Size();
        final AbstractShape sut = new AbstractShapeImpl();
        assertEquals(expectedPoint1, sut.getPosition());
        assertEquals(expectedDim1, sut.getSize());
        assertEquals(0, sut.countBackup());
        final Point expectedPoint2 = new Point(20, 30);
        final Size expectedDim2 = new Size(100, 200);
        sut.setPosition(expectedPoint2);
        sut.setSize(expectedDim2);
        assertEquals(expectedPoint2, sut.getPosition());
        assertEquals(expectedDim2, sut.getSize());
        assertEquals(0, sut.countBackup());
    }

    @Test public void backupAndRestoreColorAndStroke() {
        final Color backupedColor = Color.RED;
        final Stroke backupedStroke = mock(Stroke.class);
        final Graphics2D graphics = mock(Graphics2D.class);
        when(graphics.getColor()).thenReturn(backupedColor);
        when(graphics.getStroke()).thenReturn(backupedStroke);
        final AbstractShape sut = new AbstractShapeImpl();
        assertEquals(0, sut.countBackup());
        sut.backupColorAndStroke(graphics);
        assertEquals(1, sut.countBackup());
        final GraphicsSetting backup = sut.getBackup().peek();
        assertEquals(backupedColor, backup.getColor());
        assertSame(backupedStroke, backup.getStroke());
        sut.resotreColorAndStroke(graphics);
        sut.resotreColorAndStroke(graphics);
        assertEquals(0, sut.countBackup());
        verify(graphics, times(1)).setColor(backup.getColor());
        verify(graphics, times(1)).setStroke(backup.getStroke());
    }

    @Test public void debugShape() {
        final Graphics2D notInvokedGfx = mock(Graphics2D.class);
        final Paintable notInvokedPaintable = mock(Paintable.class); // NOPMD
        final AbstractShape noDebug = new AbstractShapeImpl();
        assertFalse(noDebug.isDebug());
        noDebug.debugShape(notInvokedGfx, notInvokedPaintable);
        verify(notInvokedGfx, never()).setColor(Color.RED);
        verify(notInvokedGfx, never()).setStroke(Strokes.createForDebug());
        verify(notInvokedPaintable, never()).paint(notInvokedGfx);

        final Graphics2D invokedGfx = mock(Graphics2D.class);
        final Paintable invokedPaintable = mock(Paintable.class);
        final AbstractShape debug = new AbstractShapeImpl();
        debug.setDebug(true);
        assertTrue(debug.isDebug());
        debug.debugShape(invokedGfx, invokedPaintable);
        verify(invokedGfx, times(1)).setColor(Color.RED);
        verify(invokedGfx, times(1)).setStroke(Strokes.createForDebug());
        verify(invokedPaintable, times(1)).paint(invokedGfx);
    }

    @Test public void getCenterX() {
        final AbstractShape sut = new AbstractShapeImpl();
        assertEquals(15, sut.getCenterX());
        sut.setSize(new Size(100, 100));
        assertEquals(50, sut.getCenterX());
        sut.setPosition(new Point(20, 20));
        assertEquals(70, sut.getCenterX());
    }

    @Test public void getCenterY() {
        final AbstractShape sut = new AbstractShapeImpl();
        assertEquals(15, sut.getCenterY());
        sut.setSize(new Size(100, 100));
        assertEquals(50, sut.getCenterY());
        sut.setPosition(new Point(20, 20));
        assertEquals(70, sut.getCenterY());
    }


}
