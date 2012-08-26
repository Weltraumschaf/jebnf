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

package de.weltraumschaf.jebnf.gfx.shapes.forkes;

import de.weltraumschaf.jebnf.gfx.shapes.other.Empty;
import de.weltraumschaf.jebnf.gfx.Point;
import java.awt.Graphics2D;

/**
 * Forks are a combination of either a {@link StraightNS} or {@link StraightWE} shape
 * and a curve shape ({@link CurveNE}, {@link CurveNW}, {@link CurveSE}, or {@link CurveSW}).
 *
 * @author Sven Strittmatter <weltraumschaf@googlemail.com>
 */
public abstract class AbstractFork extends Empty {

    /**
     * The straight shape part.
     */
    final Empty straight;

    /**
     * The curve shape part.
     */
    final Empty curve;

    /**
     * Initializes {@link #straight} and {@link  #curve}.
     *
     * @param straight {@link StraightNS} or {@link StraightWE}.
     * @param curve One of {@link CurveNE}, {@link CurveNW}, {@link CurveSE}, or {@link CurveSW}.
     */
    public AbstractFork(final Empty straight, final Empty curve) {
        super();
        this.straight = straight;
        this.curve    = curve;
    }

    @Override
    public void paint(final Graphics2D graphic) {
        super.paint(graphic);
        final Point pos = getPosition();

        straight.setPosition(pos);
        straight.setTransparent(true);
        straight.paint(graphic);

        curve.setPosition(pos);
        curve.setTransparent(true);
        curve.paint(graphic);
    }

}
