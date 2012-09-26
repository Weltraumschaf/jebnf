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

import com.google.common.base.Objects;

/**
 * Represents an immutable geometric size with width and height.
 *
 * @author Sven Strittmatter <weltraumschaf@googlemail.com>
 */
public final class Size {

    /**
     * Default width for shapes.
     */
    public static final int DEFAULT_WIDTH = 31;

    /**
     * Default height for shapes.
     */
    public static final int DEFAULT_HEIGHT = 31;

    /**
     * Immutable x coordinate.
     */
    private final int width;

    /**
     * Immutable x coordinate.
     */
    private final int height;

    /**
     * Initializes the size with zero width and height.
     */
    public Size() {
        this(DEFAULT_WIDTH, DEFAULT_HEIGHT);
    }

    /**
     * Dedicated constructor.
     *
     * @param width Width in pixel.
     * @param height HEight in pixel.
     */
    public Size(int width, int height) {
        this.width  = width;
        this.height = height;
    }

    /**
     * Get the width.
     *
     * @return Width in pixel.
     */
    public int getWidth() {
        return width;
    }

    /**
     * Returns new instance width changed width.
     *
     * @param newWidth Width in pixel.
     * @return Return new instance.
     */
    public Size setWidth(final int newWidth) {
        return new Size(newWidth, height);
    }

    /**
     * Get the height.
     *
     * @return Height in pixel.
     */
    public int getHeight() {
        return height;
    }

    /**
     * Returns new instance width changed width.
     *
     * @param newHEight Height in pixel.
     * @return Return new instance.
     */
    public Size setHeight(final int newHEight) {
        return new Size(width, newHEight);
    }

    @Override
    public String toString() {
        return Objects.toStringHelper(this)
                      .add("width", width)
                      .add("height", height)
                      .toString();
    }

    @Override
    public boolean equals(final Object obj) {
        if (!(obj instanceof Size)) {
            return false;
        }

        final Size other = (Size) obj;

        return Objects.equal(width, other.width)
               && Objects.equal(height, other.height);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(width, height);
    }

}
