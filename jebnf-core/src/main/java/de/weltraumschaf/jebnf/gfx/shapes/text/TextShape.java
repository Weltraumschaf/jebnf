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

import de.weltraumschaf.jebnf.gfx.shapes.AdjustableShape;
import de.weltraumschaf.jebnf.gfx.shapes.Shape;
import java.awt.Font;

/**
 * Defines text shape type.
 *
 * @author Sven Strittmatter <weltraumschaf@googlemail.com>
 */
public interface TextShape extends Shape, AdjustableShape {

    /**
     * Get the painted text.
     *
     * @return Return text as string.
     */
    String getText();

    /**
     * Get the painted font.
     *
     * @return Return font object.
     */
    Font getFont();

}
