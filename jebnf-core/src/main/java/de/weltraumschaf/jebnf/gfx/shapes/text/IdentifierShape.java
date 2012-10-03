/*
 * LICENSE
 *
 * "THE BEER-WARE LICENSE" (Revision 43):
 * "Sven Strittmatter" <weltraumschaf(at)googlemail(dot)com> wrote this file.
 * As long as you retain this notice you can do whatever you want with
 * this stuff. If we meet some day, and you think this stuff is worth it,
 * you can buy me a non alcohol free beer in return.
 *
 * Copyright (C) 2012 "Sven Strittmatter" <weltraumschaf(at)googlemail(dot)com>
 */
package de.weltraumschaf.jebnf.gfx.shapes.text;

import de.weltraumschaf.jebnf.gfx.Point;
import de.weltraumschaf.jebnf.gfx.Size;
import de.weltraumschaf.jebnf.gfx.StringPainter;
import de.weltraumschaf.jebnf.gfx.Strokes;
import de.weltraumschaf.jebnf.gfx.shapes.Shape;
import java.awt.Color;
import java.awt.Graphics2D;

/**
 * IdentifierShape shape.
 *
 * @author Sven Strittmatter <weltraumschaf@googlemail.com>
 */
public class IdentifierShape extends AbstractTextShape implements Shape {

    /**
     * Size of the padded box around the text.
     */
    private Size boxSize;

    /**
     * Initializes the identifiers with value text and {@link StringPainter#SANSERIFIT "font"}.
     *
     * @param text IdentifierShape value text.
     */
    public IdentifierShape(final String text) {
        super(text, StringPainter.SANSERIFIT);
    }

    /**
     * Calculates size of the inner rounded box.
     *
     * @param graphic Context to calculate by.
     * @return Return dimension object.
     */
    protected Size calcBoxSize(final Graphics2D graphic) {
        final Size size = calculateTextSize(graphic);
        return size.setWidth(size.getWidth() + HORIZONTAL_PADDING * 2);
    }

    @Override
    public void adjust(final Graphics2D graphic) {
        boxSize = calcBoxSize(graphic);
        setSize(Size.DEFAULT.setWidth(calculateWidth(boxSize.getWidth())));
    }

    @Override
    public void paint(final Graphics2D graphic) {
        if (null == boxSize) {
            adjust(graphic);
        }

        final Point rectanglePosition = calculatePaddedRectanglePosition(boxSize);

        super.paint(graphic);
        backupColorAndStroke(graphic);

        graphic.setColor(Color.BLACK);
        graphic.setStroke(Strokes.createForBox());
        graphic.drawRect(rectanglePosition.getX(), rectanglePosition.getY(), boxSize.getWidth(), boxSize.getHeight());

        drawTextWithInAndOutLine(graphic, rectanglePosition, boxSize);

        resotreColorAndStroke(graphic);
    }

}
