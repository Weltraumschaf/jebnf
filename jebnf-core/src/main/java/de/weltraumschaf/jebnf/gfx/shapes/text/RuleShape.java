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

import de.weltraumschaf.jebnf.gfx.Point;
import de.weltraumschaf.jebnf.gfx.Size;
import de.weltraumschaf.jebnf.gfx.StringPainter;
import de.weltraumschaf.jebnf.gfx.shapes.Shape;
import java.awt.Color;
import java.awt.Graphics2D;

/**
 * A syntax rule shape.
 *
 * @author Sven Strittmatter <weltraumschaf@googlemail.com>
 */
public class RuleShape extends AbstractTextShape implements Shape {

    /**
     * Initializes the object with text and {@link de.weltraumschaf.jebnf.gfx.StringPainter#SANSERIF "font"}.
     *
     * @param text RuleShape name.
     */
    public RuleShape(final String text) {
        super(text, StringPainter.SANSERIF);
    }

    @Override
    public void adjust(final Graphics2D graphic) {
        textSize = calculateTextSize(graphic);
        setSize(Size.DEFAULT.setWidth(calculateWidth(textSize.getWidth() + HORIZONTAL_PADDING * 2)));
    }

    @Override
    public void paint(final Graphics2D graphic) {
        if (null == textSize) {
            adjust(graphic);
        }

        final Point pos = getPosition();
        final Size size = getSize();
        final Point textPosition = new Point(pos.getX() + HORIZONTAL_PADDING,
                                             pos.getY() + (size.getHeight() - textSize.getHeight()) / 2);

        super.paint(graphic);
        backupColorAndStroke(graphic);
        graphic.setColor(Color.BLACK);
        final StringPainter textPainter = createStringPainter(graphic);
        textPainter.drawCenteredString(getText(), textPosition, textSize);
        resotreColorAndStroke(graphic);
    }

}
