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
import de.weltraumschaf.jebnf.gfx.shapes.AdjustableShape;
import de.weltraumschaf.jebnf.gfx.shapes.Shape;
import java.awt.Graphics2D;
import java.util.List;

/**
 * A grid layout organizes multiple {@link ColumnLayoutShape "column layouts"} to a two dimensional grid.
 *
 * @author Sven Strittmatter <weltraumschaf@googlemail.com>
 */
public class GridLayoutShape extends AbstractLayoutShape implements AdjustableShape {

    /**
     * List of column layouts.
     */
    private final List<ColumnLayoutShape> columns = Lists.newArrayList();

    /**
     * Count the containing columns of the grid.
     *
     * @return Return the number of columns.
     */
    public int countCols() {
        return columns.size();
    }

    /**
     * Count the rows of the grid.
     *
     * @return Return the number of rows.
     */
    public int counRows() {
        return countCols() == 0
               ? 0
               : columns.get(0).countShapes();
    }

    /**
     * Get the column at given index.
     *
     * @param columnIndex Index beginning with 0.
     * @return Return the column.
     */
    public ColumnLayoutShape get(final int columnIndex) {
        ColumnLayoutShape col;

        try {
            col = columns.get(columnIndex);
        } catch (IndexOutOfBoundsException ex) {
            col = null;
        }

        if (null == col) {
            throw new IllegalArgumentException(
                    String.format("The column at columnIndex %d is not present!", columnIndex));
        }

        return col;
    }

    /**
     * Get shape at given column and row index.
     *
     * @param columnIndex Index beginning with 0.
     * @param rowIndex Index beginning with 0.
     * @return Return the shape.
     */
    public Shape get(final int columnIndex, final int rowIndex) {
        Shape shape;

        try {
            shape = get(columnIndex).get(rowIndex);
        } catch (IndexOutOfBoundsException ex) {
            shape = null;
        }

        if (null == shape) {
            throw new IllegalArgumentException(
                    String.format("The shape at columnIndex %d and rowIndex %d is not present!",
                                  columnIndex, rowIndex));
        }

        return shape;
    }

    /**
     * Set shape at given column and row index.
     *
     * @param columnIndex Index beginning with 0.
     * @param rowIndex Index beginning with 0.
     * @param shape Shape to set.
     * @return Return itself for method chaining.
     */
    public GridLayoutShape set(final int columnIndex, final int rowIndex, final Shape shape) {
        final int colCount = countCols();

        if (columnIndex >= colCount) {
            for (int i = colCount; i < columnIndex + 1; ++i) {
                columns.add(ColumnLayoutShape.newColumnLayout());
            }
        }

        final ColumnLayoutShape col = columns.get(columnIndex);
        col.set(rowIndex, shape);
        return this;
    }

    /**
     * Append columns to the grid.
     *
     * @param cols Columns to add.
     * @return Return itself for method chaining.
     */
    public GridLayoutShape append(final ColumnLayoutShape... cols) {
        for (ColumnLayoutShape cl : cols) {
            append(cl);
        }

        return this;
    }

    /**
     * Append column to the grid.
     *
     * @param col Column to add.
     * @return Return itself for method chaining.
     */
    public GridLayoutShape append(final ColumnLayoutShape col) {
        columns.add(col);
        return this;
    }

    @Override
    public void paint(final Graphics2D graphic) {
        final Point pos = getPosition();
        int currentX = pos.getX();

        for (ColumnLayoutShape col : columns) {
            col.setPosition(pos.setX(currentX));
            col.setDebug(isDebug());
            col.paint(graphic);
            currentX += col.getSize().getWidth();
        }
    }

    @Override
    public void adjust(final Graphics2D graphic) {
        int width = 0;
        int height = 0;

        for (ColumnLayoutShape col : columns) {
            col.adjust(graphic);
            final  Size colSize = col.getSize();
            width  += colSize.getWidth();
            height = Math.max(height, colSize.getHeight());
        }

        setSize(new Size(width, height));
    }

}
