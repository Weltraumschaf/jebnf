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

package de.weltraumschaf.jebnf.gfx;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

/**
 * Paints a {@link RailroadDiagram} to an image.
 *
 * @author Sven Strittmatter <weltraumschaf@googlemail.com>
 */
public class RailroadDiagramImage {

    /**
     * Type of images.
     */
    public static enum Type {

        /**
         * PNG image.
         */
        PNG("png"),

        /**
         * GIF image.
         */
        GIF("gif"),

        /**
         * JPEG image.
         */
        JPG("jpg");

        /**
         * Used as file extension.
         */
        private final String formatName;

        /**
         * Initializes type with text.
         *
         * @param formatName Text used as file extension.
         */
        private Type(final String formatName) {
            this.formatName = formatName;
        }

        /**
         * Get the type text.
         *
         * @return Return the text string.
         */
        public String getFormatName() {
            return formatName;
        }

    }

    /**
     * Diagram to paint.
     */
    private RailroadDiagram diagram;

    /**
     * Buffered image to paint on.
     */
    private BufferedImage image;

    /**
     * File where to save the buffered image.
     */
    private final File file;

    /**
     * Image type.
     */
    private final Type type;

    /**
     * Initializes {@link #type} with {@link Type#PNG}.
     *
     * @param file File to write date in.
     */
    public RailroadDiagramImage(final File file) {
        this(file, Type.PNG);
    }

    /**
     * Designated constructor.
     *
     * @param file File to write date in.
     * @param type Type of image.
     */
    public RailroadDiagramImage(final File file, final Type type) {
        super();
        this.file  = file;
        this.type  = type;
    }

    /**
     * Set the diagram to paint.
     *
     * @param diagram Diagram to paint.
     */
    public void setDiagram(final RailroadDiagram diagram) {
        this.diagram = diagram;
    }

    /**
     * Get the graphic context.
     *
     * @return Return graphics context.
     */
    public Graphics2D getGraphics() {
        return image.createGraphics();
    }

    /**
     * Paint the railroad diagram.
     *
     * Should be called before {@link #save()}.
     */
    public void paint() {
        paint(getGraphics());
    }

    /**
     * Casts the given {@link Graphics} object to {@link Graphics2D} and paints on it.
     *
     * @param graphics Graphics context to paint on.
     */
    public void paint(final Graphics graphics) {
        paint((Graphics2D) graphics);
    }

    /**
     * Paint the diagram with the given graphics context.
     *
     * @param graphics Graphics context to paint on.
     */
    public void paint(final Graphics2D graphics) {
        diagram.paint(graphics);
        image = Helper.newBufferedImage(diagram.getSize());

    }

    /**
     * Saves the painted image to {@link #file}.
     *
     * Should be called after {@link #paint()}.
     *
     * @throws IOException On file IO errors.
     */
    public void save() throws IOException {
        ImageIO.write(image, type.getFormatName(), file);
    }

}
