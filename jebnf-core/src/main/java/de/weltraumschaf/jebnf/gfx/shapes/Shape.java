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

/**
 * Defines the interface for railroad shapes.
 *
 * @author Sven Strittmatter <weltraumschaf@googlemail.com>
 */
public interface Shape extends Paintable {

    /**
     * Returns the position of the shape.
     *
     * @return Position object.
     */
    Point getPosition();

    /**
     * Sets the shapes position.
     *
     * @param pos The position the shape is painted.
     */
    void setPosition(Point pos);

    /**
     * Returns the size of the shape.
     *
     * @return The shapes size.
     */
    Size getSize();

    /**
     * Sets the size of the shape.
     *
     * @param size NEw size.
     */
    void setSize(Size size);

    /**
     * Enables/disable debug paintings.
     *
     * By default debugging should be off. Debug painting means that the shape paints some
     * additional helper pixels for debugging.
     *
     * @param onnOff Whether to set debug painting on or off.
     */
    void setDebug(boolean onnOff);

}
