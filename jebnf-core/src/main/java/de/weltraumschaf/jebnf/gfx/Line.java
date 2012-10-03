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

import com.google.common.base.Objects;

/**
 * Represents an immutable  geometric line with a {@link Point start} and {@link Point end}.
 *
 * @author Sven Strittmatter <weltraumschaf@googlemail.com>
 */
public final class Line {

    /**
     * Start point of line.
     */
    private final Point start;

    /**
     * End point of line.
     */
    private final Point end;

    /**
     * Designated constructor.
     *
     * @param start Begin of line.
     * @param end End of line.
     */
    private Line(final Point start, final Point end) {
        super();
        this.start = start;
        this.end   = end;
    }

    /**
     *
     * @param x1 X start point of line.
     * @param y1 Y start point of line.
     * @param x2 X end point of line.
     * @param y2 Y end point of line.
     * @return
     */
    public static Line valueOf(final int x1, final int y1, final int x2, final int y2) {
        return valueOf(Point.valueOf(x1, y1), Point.valueOf(x2, y2));
    }

    /**
     *
     * @param start Begin of line.
     * @param end End of line.
     * @return
     */
    public static Line valueOf(final Point start, final Point end) {
        // TODO imlement cache.
        return new Line(start, end);
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
        if (!(obj instanceof Line)) {
            return false;
        }

        final Line other = (Line) obj;

        return Objects.equal(start, other.start)
            && Objects.equal(end, other.end);
    }

}
