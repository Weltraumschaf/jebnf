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
package de.weltraumschaf.jebnf.gfx.shapes.compound;

import com.google.common.collect.Lists;
import de.weltraumschaf.jebnf.gfx.Point;
import de.weltraumschaf.jebnf.gfx.Size;
import de.weltraumschaf.jebnf.gfx.shapes.Shape;
import static de.weltraumschaf.jebnf.gfx.shapes.ShapeFactory.empty;
import java.awt.Graphics2D;
import java.util.List;

/**
 * Organizes shapes in a horizontal layout.
 *
 * <pre>
 * +--------------------------------------------+
 * | RowLayoutShape                                   |
 * | +----------+ +----------+ +----------+     |
 * | |   Shape  | |   Shape  | |   Shape  | ... |
 * | +----------+ +----------+ +----------+     |
 * +--------------------------------------------+
 * </pre>
 *
 * @author Sven Strittmatter <weltraumschaf@googlemail.com>
 */
public class RowLayoutShape extends AbstractLayoutShape implements SequenceShape {

    /**
     * Containing shapes.
     */
    private final List<Shape> row = Lists.newArrayList();

    @Override
    public Shape get(final int index) {
        return row.get(index);
    }

    @Override
    public RowLayoutShape set(final int index, final Shape shape) {
        final int count = countShapes();

        if (index >= count) {
            append(empty(count - 1));
            append(shape);
        } else {
            row.set(index, shape);
        }

        return this;
    }

    @Override
    public RowLayoutShape append(final Shape... shapes) {
        for (Shape shape : shapes) {
            append(shape);
        }

        return this;
    }

    @Override
    public RowLayoutShape append(final Shape shape) {
        row.add(shape);
        return this;
    }

    @Override
    public int countShapes() {
        return row.size();
    }

    @Override
    public void paint(final Graphics2D graphic) {
        if (countShapes() == 0) {
            return;
        }

        final Point pos = getPosition();
        int currentX = pos.getX();

        for (Shape shape : row) {
            shape.setPosition(pos.setX(currentX));
            shape.setDebug(isDebug());
            shape.paint(graphic);
            currentX += shape.getSize().getWidth();
        }
    }

    @Override
    public void adjust(final Graphics2D graphics) {
        int width = 0;
        int height = 0;

        for (Shape shape : row) {
            adjustShape(shape, graphics);
            final Size shapeSize = shape.getSize();
            width += shapeSize.getWidth();
            height = Math.max(height, shapeSize.getHeight());
        }

        for (Shape shape : row) {
            shape.setSize(shape.getSize().setHeight(height));
        }

        setSize(Size.valueOf(width, height));
    }

}
