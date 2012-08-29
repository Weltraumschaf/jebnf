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
package de.weltraumschaf.jebnf.gfx;

import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics2D;

/**
 * Paints a centered string into a rectangular area.
 *
 * Also provides some helpers for measuring text and calculate positions.
 *
 * The algorithm for position the text as centered is from:
 * http://stackoverflow.com/questions/5378052/positioning-string-in-graphic-java
 *
 * @author Sven Strittmatter <weltraumschaf@googlemail.com>
 */
public class StringPainter {

    /**
     * Default font for terminal shapes.
     */
    public static final Font MONOSPACED = Fonts.MONOSPACED.create();
    /**
     * Default font for rule shapes.
     */
    public static final Font SANSERIF   = Fonts.SANSERIF.create();
    /**
     * Default font for identifier shapes.
     */
    public static final Font SANSERIFIT = Fonts.SANSERIFIT.create();

    /**
     * Default graphics object.
     */
    private static final Graphics2D DEFAULT_GRAPHIC = Helper.newGraphics();

    /**
     * used graphics object.
     */
    private final Graphics2D graphic;

    /**
     * Used font object.
     */
    private final Font font;

    /**
     * Initializes the {@link #graphic} with {@link #DEFAULT_GRAPHIC} and {@link #font}
     * with {@link #MONOSPACED}..
     */
    public StringPainter() {
        this(DEFAULT_GRAPHIC);
    }

    /**
     * Initializes the {@link #graphic} with {@link #DEFAULT_GRAPHIC}.
     *
     * @param font Font to paint the string.
     */
    public StringPainter(final Font font) {
        this(DEFAULT_GRAPHIC, font);
    }

    /**
     * Initializes the {@link #font} with {@link #MONOSPACED}.
     *
     * @param graphic Graphic object to paint on.
     */
    public StringPainter(final Graphics2D graphic) {
        this(graphic, MONOSPACED);
    }

    /**
     * Designated constructor.
     *
     * @param graphic Graphic object to paint on.
     * @param font Font to paint the string.
     */
    public StringPainter(final Graphics2D graphic, final Font font) {
        super();
        this.graphic  = graphic;
        this.font     = font;
    }

    /**
     * Draws a string centered inside the given size.
     *
     * @param str  String to center.
     * @param size Size of the rectangle to center inside.
     */
    public void drawCenteredString(final String str, final Size size) {
        drawCenteredString(str, new Point(), size);
    }

    /**
     * Draws a string centered inside the given size with an x and y offset..
     *
     * @param str    String to center.
     * @param offset X and y offset.
     * @param size   Size of the rectangle to center inside.
     */
    public void drawCenteredString(final String str, final Point offset, final Size size) {
        final Font backup = graphic.getFont();
        graphic.setFont(font);
        final FontMetrics metrics = graphic.getFontMetrics();
        final int xPosition = calcXPosition(metrics.stringWidth(str), offset.getX(), size.getWidth());
        final int yPosition = calcYPosition(offset.getY(), size.getHeight(),
                                            metrics.getAscent(), metrics.getDescent());
        graphic.drawString(str, xPosition, yPosition);
        graphic.setFont(backup);
    }

    /**
     * Calculates the string x position to draw.
     *
     * @param stringWidth Width of string in pixel.
     * @param offsetX Space before and after the string.
     * @param width Width of drawing area.
     * @return Return x position where to start painting of the string.
     */
    public int calcXPosition(final int stringWidth, final int offsetX, final int width) {
        final int checkedOffsetX = offsetX < 0
                                 ? 0
                                 : offsetX;
        return (width - stringWidth) / 2 + checkedOffsetX;
    }

    /**
     * Calculates the string y position to draw.
     *
     * @param offsetY Space above and below string.
     * @param height Height of drawing area.
     * @param ascent Ascent of font.
     * @param descent Descent of font.
     * @return Return y position where to start painting of the string.
     */
    public int calcYPosition(final int offsetY, final int height, final int ascent, final int descent) {
        return (ascent + (height - (ascent + descent)) / 2) + offsetY;
    }

}
