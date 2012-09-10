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

import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.RenderingHints.Key;

/**
 * Helper class to turn on and off antialiasing of AWT rendering.
 *
 * This class is final because it's a pure static utility class.
 *
 * @author Sven Strittmatter <weltraumschaf@googlemail.com>
 */
public final class Antialiaser {

    /**
     * Hint key for antialiasing.
     */
    static final Key HINT_KEY = RenderingHints.KEY_ANTIALIASING;

    /**
     * Private constructor for pure static utility class.
     */
    private Antialiaser() {
        super();
    }

    /**
     * Turns on antialiasing for given {@link Graphics2D "graphics context"}.
     *
     * @param graphic Context to turn on antialiasing.
     */
    public static Object turnOn(final Graphics2D graphic) {
        final Object oldValue = graphic.getRenderingHint(HINT_KEY);
        setHintOnGraphics(graphic, RenderingHints.VALUE_ANTIALIAS_ON);
        return oldValue;
    }

    /**
     * Turns off antialiasing for given {@link Graphics2D "graphics context"}.
     *
     * @param graphic Context to turn off antialiasing.
     */
    public static Object turnOff(final Graphics2D graphic) {
        final Object oldValue = graphic.getRenderingHint(HINT_KEY);
        setHintOnGraphics(graphic, RenderingHints.VALUE_ANTIALIAS_OFF);
        return oldValue;
    }

    /**
     * Sets a hint on the given graphics context.
     *
     * @param graphic Context to set hint.
     * @param hint Hint to set.
     */
    public static void setHintOnGraphics(final Graphics2D graphic, final Object hint) {
        graphic.setRenderingHint(HINT_KEY, hint);
    }

}
