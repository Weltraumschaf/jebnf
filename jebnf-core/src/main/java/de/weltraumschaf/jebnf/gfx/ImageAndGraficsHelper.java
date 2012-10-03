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

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

/**
 * ImageAndGraficsHelper to create various AWT stuff.
 *
 * @author Sven Strittmatter <weltraumschaf@googlemail.com>
 */
public final class ImageAndGraficsHelper {

    /**
     * Pure factory.
     */
    private ImageAndGraficsHelper() { }

    /**
     * Creates a Buffered image of size 1 x 1.
     *
     * @return Return new instance.
     */
    public static BufferedImage newBufferedImage() {
        return newBufferedImage(1, 1);
    }

    /**
     * Creates a buffered image with custom size.
     *
     * @param width  Image width.
     * @param height Image height.
     * @return Return new instance.
     */
    public static BufferedImage newBufferedImage(final int width, final int height) {
        return new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
    }

    /**
     * Creates a buffered image with custom size.
     *
     * @param size Image size.
     * @return Return new instance.
     */
    public static BufferedImage newBufferedImage(final Size size) {
        return newBufferedImage(size.getWidth(), size.getHeight());
    }

    /**
     * Creates a graphics object from a {@link #newBufferedImage()  default buffered} image.
     *
     * @return Return new instance.
     */
    public static Graphics2D newGraphics() {
        return newBufferedImage().createGraphics();
    }

}
