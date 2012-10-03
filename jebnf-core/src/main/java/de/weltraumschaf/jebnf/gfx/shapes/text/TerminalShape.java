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
 * TerminalShape shape.
 *
 * @author Sven Strittmatter <weltraumschaf@googlemail.com>
 */
public class TerminalShape extends AbstractTextShape implements Shape {

    /**
     * Width of the rounded rectangle arcs.
     */
    private static final int ARC_WIDTH = 25;

    /**
     * Additional padding added to text width and height.
     */
    private static final int PADDING = 4;

    /**
     * Padded box size of the text.
     */
    private Size boxSize;

    /**
     * Initializes shape with terminal text and {@link StringPainter#MONOSPACED "monospaced font"}.
     *
     * @param text TerminalShape value text.
     */
    public TerminalShape(final String text) {
        super(text, StringPainter.MONOSPACED);
    }

    /**
     * Calculates the box size around the text.
     *
     * @param graphic Used to obtain string size information depending on the used font.
     * @return Returns dimension object.
     */
    protected Size calcBoxSize(final Graphics2D graphic) {
        final Size size = calculateTextSize(graphic);
        return Size.valueOf(size.getWidth() + HORIZONTAL_PADDING * PADDING, size.getHeight() + PADDING);
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
        graphic.drawRoundRect(rectanglePosition.getX(), rectanglePosition.getY(),
                              boxSize.getWidth(), boxSize.getHeight(),
                              ARC_WIDTH, ARC_WIDTH);

        drawTextWithInAndOutLine(graphic, rectanglePosition, boxSize);

        resotreColorAndStroke(graphic);
    }

}
