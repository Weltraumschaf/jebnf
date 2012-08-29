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
 * Represents an immutable  geometric line with a {@link Point start} and {@link Point end}.
 *
 * @author Sven Strittmatter <weltraumschaf@googlemail.com>
 */
public class Line {

    /**
     * Start point of line.
     */
    private final Point start;

    /**
     * End point of line.
     */
    private final Point end;

    /**
     * Initializes a line with both start and end with points to (0, 0).
     */
    public Line() {
        this(new Point(), new Point());
    }

    /**
     * Designated constructor.
     *
     * @param start Begin of line.
     * @param end End of line.
     */
    public Line(final Point start, final Point end) {
        super();
        this.start = start;
        this.end   = end;
    }

    /**
     * Get start point.
     *
     * @return Return start point.
     */
    public Point getStart() {
        return start;
    }

    /**
     * Get end point.
     *
     * @return Return end point.
     */
    public Point getEnd() {
        return end;
    }

    @Override
    public String toString() {
        return Objects.toStringHelper(this)
                      .add("start", start)
                      .add("end", end).toString();
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(start, end);
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }

        if (getClass() != obj.getClass()) {
            return false;
        }

        final Line other = (Line) obj;

        return Objects.equal(start, other.start)
            && Objects.equal(end, other.end);
    }

}
