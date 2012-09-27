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

import com.google.common.collect.Lists;
import de.weltraumschaf.jebnf.gfx.Point;
import de.weltraumschaf.jebnf.gfx.Size;
import de.weltraumschaf.jebnf.gfx.shapes.Shape;
import static de.weltraumschaf.jebnf.gfx.shapes.ShapeFactory.*;
import java.awt.Graphics2D;
import java.util.List;

/**
 * Organizes shapes in a vertical layout.
 *
 * <pre>
 * +-------------------+
 * | ColumnLayoutShape |
 * |                   |
 * |   +-----------+   |
 * |   |   Shape   |   |
 * |   +-----------+   |
 * |                   |
 * |   +-----------+   |
 * |   |   Shape   |   |
 * |   +-----------+   |
 * |                   |
 * |   +-----------+   |
 * |   |   Shape   |   |
 * |   +-----------+   |
 * |        ...        |
 * +-------------------+
 * </pre>
 *
 * @author Sven Strittmatter <weltraumschaf@googlemail.com>
 */
public class ColumnLayoutShape extends AbstractLayoutShape implements SequenceShape {

    /**
     * Containing shapes.
     */
    private final List<Shape> col = Lists.newArrayList();

    /**
     * Creates a new empty column layout.
     *
     * @return Return always new instance.
     */
    public static ColumnLayoutShape newColumnLayout() {
        return new ColumnLayoutShape();
    }

    @Override
    public Shape get(final int index) {
        return col.get(index);
    }

    @Override
    public ColumnLayoutShape set(final int index, final Shape shape) {
        final int count = countShapes();

        if (index >= count) {
            append(empty(index - count));
            append(shape);
        } else {
            col.set(index, shape);
        }

        return this;
    }

    @Override
    public ColumnLayoutShape append(final Shape... shapes) {
        for (Shape shape : shapes) {
            append(shape);
        }

        return this;
    }

    @Override
    public ColumnLayoutShape append(final Shape shape) {
        col.add(shape);
        return this;
    }

    @Override
    public int countShapes() {
        return col.size();
    }

    @Override
    public void paint(final Graphics2D graphic) {
        if (countShapes() == 0) {
            return;
        }

        final Point pos = getPosition();
        int currentY = pos.getY();

        for (Shape shape : col) {
            shape.setPosition(pos.setY(currentY));
            shape.setDebug(isDebug());
            shape.paint(graphic);
            currentY += shape.getSize().getHeight();
        }
    }

    @Override
    public void adjust(final Graphics2D graphics) {
        int width = 0;
        int height = 0;

        for (Shape shape : col) {
            adjustShape(shape, graphics);
            final Size shapeSize = shape.getSize();
            height += shapeSize.getHeight();
            width = Math.max(width, shapeSize.getWidth());
        }

        for (final Shape shape : col) {
            shape.setSize(shape.getSize().setWidth(width));
        }

        setSize(Size.valueOf(width, height));
    }

}
