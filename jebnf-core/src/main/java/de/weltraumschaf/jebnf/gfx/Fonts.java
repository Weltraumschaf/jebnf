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
package de.weltraumschaf.jebnf.gfx;

import java.awt.Font;

/**
 * Collection of used fonts with default size.
 *
 * @author Sven Strittmatter <weltraumschaf@googlemail.com>
 */
public enum Fonts {

    /**
     * Used for terminals.
     */
    MONOSPACED("Monospaced", Font.PLAIN),

    /**
     * Used for rule names.
     */
    SANSERIF("Sanserif", Font.PLAIN),

    /**
     * Used for identifiers.
     */
    SANSERIFIT("Sanserif", Font.ITALIC);

    /**
     * Default font size.
     */
    public static final int DEFAULT_SIZE = 14;

    /**
     * Font name.
     */
    private final String name;

    /**
     * Font style.
     */
    private final int style;

    /**
     * Font size.
     */
    private int size;

    /**
     * Caches font object for particular font size.
     */
    private Font font;

    /**
     * Font with default size.
     *
     * @param name  Font name.
     * @param style Font style.
     */
    Fonts(final String name, final int style) {
        this(name, style, DEFAULT_SIZE);
    }

    /**
     * Font with custom size.
     *
     * @param name  Font name.
     * @param style Font style.
     * @param size  Font size.
     */
    Fonts(final String name, final int style, final int size) {
        this.name  = name;
        this.style = style;
        this.size  = size;
    }

    /**
     * Creates an AWT font object.
     *
     * Returns always same object until a different size is creates.
     *
     * @return Returns same reusable instances, until size changes
     */
    public Font create() {
        if (null == font) {
            font = new Font(name, style, size);
        }

        return font;
    }

    /**
     * Creates a font object with a new size.
     *
     * @param newSize New font size.
     * @return Return newly created font for the new size.
     */
    public Font create(final int newSize) {
        this.size = newSize;
        font = null;
        return create();
    }

}
