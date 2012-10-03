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
 * Implementors can paint them selves on a {@link java.awt.Graphics2D graphics context}.
 *
 * @author Sven Strittmatter <weltraumschaf@googlemail.com>
 */
public interface Paintable {

    /**
     * Paints the implementor.
     *
     * @param graphic Context to paint on.
     */
    void paint(Graphics2D graphic);

}
