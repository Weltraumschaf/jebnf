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
package de.weltraumschaf.jebnf.gfx.shapes;

import de.weltraumschaf.jebnf.gfx.Point;
import de.weltraumschaf.jebnf.gfx.Size;
import de.weltraumschaf.jebnf.gfx.Strokes;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Stroke;
import java.util.Stack;

/**
 * Common functionality of shapes.
 *
 * @author Sven Strittmatter <weltraumschaf@googlemail.com>
 */
public abstract class AbstractShape implements Shape {

    /**
     * Stack to backup up previous settings.
     */
    private final Stack<GraphicsSetting> backup = new Stack<GraphicsSetting>();

    /**
     * Left upper corner position of shape.
     */
    private Point position;

    /**
     * Size of the shape.
     */
    private Size size;

    /**
     * Whether to paint debug lines.
     */
    private boolean debug;

    /**
     * Initializes the size with {@link de.weltraumschaf.jebnf.gfx.Size#DEFAULT_WIDTH} x
     * {@link de.weltraumschaf.jebnf.gfx.Size#DEFAULT_HEIGHT} and position with (0, 0).
     */
    public AbstractShape() {
        size     = Size.DEFAULT;
        position = Point.ZERO;
    }

    @Override
    public Point getPosition() {
        return position;
    }

    @Override
    public void setPosition(final Point position) {
        this.position = position;
    }

    @Override
    public void setDebug(final boolean onOff) {
        debug = onOff;
    }

    @Override
    public Size getSize() {
        return size;
    }

    @Override
    public void setSize(final Size size) {
        this.size = size;
    }

    /**
     * Returns if debug is enabled.
     *
     * @return Return true if enabled.
     */
    public boolean isDebug() {
        return debug;
    }

    /**
     * Return the count of backed up graphics setings.
     *
     * @return Return integer greater equal 0.
     */
    protected int countBackup() {
        return backup.size();
    }

    /**
     * Return the graphics setting backup.
     *
     * @return Return stack with backup settings.
     */
    protected Stack<GraphicsSetting> getBackup() {
        return backup;
    }

    /**
     * Back up the graphics settings.
     *
     * @param graphic Context to retrieve settings from.
     */
    protected void backupColorAndStroke(final Graphics2D graphic) {
        backup.push(new GraphicsSetting(graphic.getColor(), graphic.getStroke()));
    }

    /**
     * If there are backup settings pop one of and restore its setings on the given context.
     *
     * @param graphic Context to retrieve settings from.
     */
    protected void resotreColorAndStroke(final Graphics2D graphic) {
        if (!backup.isEmpty()) {
            final GraphicsSetting settings = backup.pop();
            graphic.setColor(settings.getColor());
            graphic.setStroke(settings.getStroke());
        }
    }

    /**
     * Paint paintable as debug debug output, if {@link #isDebug()} returns true.
     *
     * @param graphic Context to paint on..
     * @param paintable Paints the debug information.
     */
    protected void debugShape(final Graphics2D graphic, final Paintable paintable) {
        if (isDebug()) {
            backupColorAndStroke(graphic);
            graphic.setColor(Color.RED);
            graphic.setStroke(Strokes.createForDebug());
            paintable.paint(graphic);
            resotreColorAndStroke(graphic);
        }
    }

    /**
     * Get shapes centered x position.
     *
     * @return Integer greater equal 0.
     */
    protected int getCenterX() {
        return position.getX() + size.getWidth() / 2;
    }

    /**
     * Get shapes centered y position.
     *
     * @return Integer greater equal 0.
     */
    protected int getCenterY() {
        return position.getY() + size.getHeight() / 2;
    }

    /**
     * A graphic setting contains a {@link java.awt.Color} and {@link java.awt.Stroke}.
     *
     * A setting is immutable.
     */
    protected static class GraphicsSetting {

        /**
         * Settings color.
         */
        private final Color color;

        /**
         * Settings stroke.
         */
        private final Stroke stroke;

        /**
         * Initializes the setting with color and stroke.
         *
         * @param color Settings color.
         * @param stroke Settings stroke.
         */
        public GraphicsSetting(final Color color, final Stroke stroke) {
            this.color  = color;
            this.stroke = stroke;
        }

        /**
         * Get the color setting.
         *
         * @return Return color object.
         */
        public Color getColor() {
            return color;
        }

        /**
         * Get the stroke setting.
         *
         * @return Return stroke object.
         */
        public Stroke getStroke() {
            return stroke;
        }

    }

}
