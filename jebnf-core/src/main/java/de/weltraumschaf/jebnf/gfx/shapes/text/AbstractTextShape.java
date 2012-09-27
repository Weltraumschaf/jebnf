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

package de.weltraumschaf.jebnf.gfx.shapes.text;

import de.weltraumschaf.jebnf.gfx.Line;
import de.weltraumschaf.jebnf.gfx.Point;
import de.weltraumschaf.jebnf.gfx.Size;
import de.weltraumschaf.jebnf.gfx.StringPainter;
import de.weltraumschaf.jebnf.gfx.Strokes;
import de.weltraumschaf.jebnf.gfx.shapes.other.RectangularShape;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.geom.Rectangle2D;

/**
 * Common functionality for all shapes with text.
 *
 * A text shape has a text, an in and out line and some optional graphics
 * (like rounded box for terminals or rectangular box for identifiers.).
 *
 * Schema of an identifier:
 * <pre>
 *                         inner box
 *                        /
 *    in line +--------------+   out line
 *   /        |              |  /
 * -----------+  Identifier  +-----------
 *            |              |
 *            +--------------+
 * </pre>
 *
 * @author Sven Strittmatter <weltraumschaf@googlemail.com>
 */
public abstract class AbstractTextShape extends RectangularShape implements TextShape {

    /**
     * Horizontal padding.
     */
    protected static final int HORIZONTAL_PADDING = 5;

    /**
     * Default font.
     */
    private static final Font DEFUALT_FONT = StringPainter.SANSERIF;

    /**
     * Size of the shape.
     */
    protected Size textSize;

    /**
     * Text to paint on shape.
     */
    private final String text;

    /**
     * Actual font to paint text on shape.
     */
    private final Font font;

    /**
     * Utility object to paint text.
     */
    private StringPainter textPainter;

    /**
     * Last used context to paint on.
     */
    private Graphics2D lastContext;

    /**
     * Initializes shape with {@link #DEFUALT_FONT}.
     *
     * @param text Text to paint on shape.
     */
    public AbstractTextShape(final String text) {
        this(text, DEFUALT_FONT);
    }

    /**
     * Designated constructor.
     *
     * @param text Text to paint on shape.
     * @param font Font to paint text on shape.
     */
    public AbstractTextShape(final String text, final Font font) {
        super();
        this.text = text;
        this.font = font;
    }

    @Override
    public String getText() {
        return text;
    }

    @Override
    public Font getFont() {
        return font;
    }

    /**
     * Calculates the shapes width depending on the containing text box width.
     *
     * @param boxWidth Width of the containing text bos.
     * @return Returns width in pixel.
     */
    protected static int calculateWidth(final int boxWidth) {
        if (boxWidth < 0) {
            throw new IllegalArgumentException("box width need to be greater or equal zero!");
        }

        final int minWidth = (boxWidth + 2 * HORIZONTAL_PADDING);
        final int emtpyShapeCount = minWidth / Size.DEFAULT_WIDTH + 1;
        return Size.DEFAULT_WIDTH * emtpyShapeCount;
    }

    /**
     * Creates a string painter.
     *
     * @param graphics Context to paint on.
     * @return Returns the same object, unless the graphics object hasn't changed.
     */
    protected StringPainter createStringPainter(final Graphics2D graphics) {
        if (null == textPainter || !lastContext.equals(graphics)) {
            textPainter = new StringPainter(graphics, font);
            lastContext = graphics;
        }
        return textPainter;
    }

    /**
     * Calculates the text size.
     *
     * @param graphic Context used to calculate size.
     * @return Returns size of text.
     */
    protected Size calculateTextSize(final Graphics2D graphic) {
        if (null == textSize) {
            final Rectangle2D textBounds = font.getStringBounds(getText(),
                                                                graphic.getFontRenderContext());
            textSize = new Size((int) Math.ceil(textBounds.getWidth()),
                                (int) Math.ceil(textBounds.getHeight()));
        }
        return textSize;
    }

    /**
     * Calculates the railroad line into the shape.
     *
     * @param boxWidth Inner box width.
     * @return Returns line object with start and end {@link Point "points"}.
     */
    protected Line calculateInLine(final int boxWidth) {
        final Point pos      = getPosition();
        final int vCenter    = getCenterY();
        final Point start    = Point.valueOf(pos.getX() - 1, vCenter);
        final Point end      = Point.valueOf(pos.getX() + calculateHorizontalPadding(boxWidth), vCenter);
        return Line.valueOf(start, end);
    }

    /**
     * Calculates the railroad line out of the shape.
     *
     * @param boxWidth Inner box width.
     * @return Returns line object with start and end {@link Point "points"}.
     */
    protected Line calculateOutLine(final int boxWidth) {
        final Point pos      = getPosition();
        final int vCenter    = getCenterY();
        final Point start    = Point.valueOf(pos.getX() + calculateHorizontalPadding(boxWidth) + boxWidth,
                                             vCenter);
        final Point end      = Point.valueOf(pos.getX() + getSize().getWidth(), vCenter);
        return Line.valueOf(start, end);
    }

    /**
     * Calculates horizontal padding of the inner box.
     *
     * @param boxWidth Inner box.
     * @return Padding in pixel.
     */
    protected int calculateHorizontalPadding(final int boxWidth) {
        return (getSize().getWidth() - boxWidth) / 2;
    }

    /**
     * Calculates vertical padding of the inner box.
     *
     * @param boxHeight Inner box.
     * @return Padding in pixel.
     */
    protected int calculateVerticalPadding(final int boxHeight) {
        return (getSize().getHeight() - boxHeight) / 2;
    }

    /**
     * Calculated the shapes position with padding.
     *
     * @param size Size to add padding to.
     * @return Return start point.
     */
    protected Point calculatePaddedRectanglePosition(final Size size) {
        final Point pos = getPosition();
        return Point.valueOf(pos.getX() + calculateHorizontalPadding(size.getWidth()),
                             pos.getY() + calculateVerticalPadding(size.getHeight()));
    }

    /**
     * Draws a given line.
     *
     * @param graphic Context to draw on.
     * @param line Line to draw.
     */
    protected void drawLine(final Graphics2D graphic, final Line line) {
        drawLine(graphic, line.getStart(), line.getEnd());
    }

    /**
     * Draws a line for given start and end point.
     *
     * @param graphic Context to draw on.
     * @param start Start of line.
     * @param end End of line.
     */
    protected void drawLine(final Graphics2D graphic, final Point start, final Point end) {
        graphic.drawLine(start.getX(), start.getY(), end.getX(), end.getY());
    }

    /**
     * Draws the shape text string centered.
     *
     * @param graphic Context to draw on.
     * @param pos Position to start drawing.
     * @param size Dimension of available space to draw text.
     */
    protected void drawText(final Graphics2D graphic, final Point pos, final Size size) {
        final StringPainter painter = createStringPainter(graphic);
        painter.drawCenteredString(getText(), pos, size);
    }

    /**
     * Draws the text and the in and out line of the shape.
     *
     * @param graphic Context to draw on.
     * @param pos Position to start drawing.
     * @param size Size to draw.
     */
    protected void drawTextWithInAndOutLine(final Graphics2D graphic, final Point pos, final Size size) {
        graphic.setStroke(Strokes.createForRail());
        drawLine(graphic, calculateInLine(size.getWidth()));
        drawLine(graphic, calculateOutLine(size.getWidth()));
        drawText(graphic, pos, size);
    }

}
