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

import java.awt.Graphics2D;

/**
 * Implementors can adjust their size and/or position.
 *
 * @author Sven Strittmatter <weltraumschaf@googlemail.com>
 */
public interface AdjustableShape {

    /**
     * Recalculates the dimension and position of the implementor.
     *
     * Compound adjustable which contains other adjustable may need to invoke
     * this method recursively.
     *
     * @param graphic Graphic context used for font measuring.
     */
    void adjust(Graphics2D graphic);

}
