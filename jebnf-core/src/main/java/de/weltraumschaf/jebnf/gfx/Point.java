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
 * Represents a immutable point with a x and y coordinate.
 *
 * @author Sven Strittmatter <weltraumschaf@googlemail.com>
 */
public class Point {

    /**
     * Immutable x coordinate.
     */
    public final int x; // NOPMD Ignore short name warning.

    /**
     * Immutable x coordinate.
     */
    public final int y; //NOPMD Ignore short name warning.

    /**
     * Initializes a point with coordinates (0, 0).
     */
    public Point() {
        this(0, 0);
    }

    /**
     * Initializes a point with given coordinates.
     *
     * @param x X-coordinate.
     * @param y Y-Coordinate.
     */
    public Point(final int x, final int y) {
        this.x = x;
        this.y = y;
    }

    /**
     * Returns a new point object with changed x-coordinate.
     *
     * @param newX The new x-coordinate.
     * @return Returns a new object.
     */
    public Point setX(final int newX) {
        return new Point(newX, y);
    }

    /**
     * Returns a new point object with changed y-coordinate.
     *
     * @param newY The new y-coordinate.
     * @return Returns a new object.
     */
    public Point setY(final int newY) {
        return new Point(x, newY);
    }

    @Override
    public String toString() {
        return Objects.toStringHelper(this)
                      .add("x", x)
                      .add("y", y)
                      .toString();
    }

    @Override
    public boolean equals(final Object obj) {
        if (obj == null) {
            return false;
        }

        if (getClass() != obj.getClass()) {
            return false;
        }

        final Point other = (Point) obj;

        if (Objects.equal(x, other.x) && Objects.equal(y, other.y)) {
            return true;
        }

        return false;
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(x, y);
    }

}
