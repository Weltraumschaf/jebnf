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

import java.awt.BasicStroke;
import java.awt.Stroke;

/**
 * Utility class to create various strokes to paint graphics.
 *
 * See http://docs.oracle.com/javase/tutorial/2d/geometry/strokeandfill.html
 *
 * @author Sven Strittmatter <weltraumschaf@googlemail.com>
 */
public final class Strokes {

    /**
     * Thickness of rail lines.
     */
    private static final int RAIL_LINE = 5;

    /**
     * Thickness of box lines.
     */
    private static final int BOX_LINE = 3;

    /**
     * Thickness of debug lines.
     */
    private static final int DEBUG_LINE = 1;

    /**
     * Stroke for debugging lines..
     */
    private static final Stroke DEBUG = new BasicStroke(DEBUG_LINE,
                                                        BasicStroke.CAP_BUTT,
                                                        BasicStroke.JOIN_MITER);

    /**
     * Stroke for rail road.
     */
    private static final Stroke RAIL = new BasicStroke(RAIL_LINE,
                                                       BasicStroke.CAP_BUTT,
                                                       BasicStroke.JOIN_MITER);

    /**
     * Stroke for rounded rail road.
     */
    private static final Stroke RAIL_ROUNDED = new BasicStroke(RAIL_LINE,
                                                               BasicStroke.CAP_ROUND,
                                                               BasicStroke.JOIN_ROUND);

    /**
     * Stroke for boxes.
     */
    private static final Stroke BOX = new BasicStroke(BOX_LINE,
                                                      BasicStroke.CAP_BUTT,
                                                      BasicStroke.JOIN_MITER);

    /**
     * Private constructor for pure utility class.
     */
    private Strokes() {
        super();
    }

    /**
     * Creates stroke for debugging.
     *
     * @return Return always the same instance.
     */
    public static Stroke createForDebug() {
        return DEBUG;
    }

    /**
     * Creates stroke for rails.
     *
     * @return Return always the same instance.
     */
    public static Stroke createForRail() {
        return createForRail(false);
    }

    /**
     * Creates stroke for rails.
     *
     * @param rounded If the rails should have rounded corners or not.
     * @return Return always the same instance.
     */
    public static Stroke createForRail(final boolean rounded) {
        return rounded
               ? RAIL_ROUNDED
               : RAIL;
    }

    /**
     * Creates stroke for boxes.
     *
     * @return Return always the same instance.
     */
    public static Stroke createForBox() {
        return BOX;
    }

}
