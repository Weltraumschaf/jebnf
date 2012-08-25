/*
 * LICENSE
 *
 * "THE BEER-WARE LICENSE" (Revision 42):
 * "Sven Strittmatter" <ich(at)weltraumschaf(dot)de> wrote this file.
 * As long as you retain this notice you can do whatever you want with
 * this stuff. If we meet some day, and you think this stuff is worth it,
 * you can buy me a beer in return.
 *
 */

package de.weltraumschaf.jebnf.ast.nodes;

import de.weltraumschaf.jebnf.ast.AbstractNode;
import de.weltraumschaf.jebnf.ast.Node;
import de.weltraumschaf.jebnf.ast.NodeType;
import de.weltraumschaf.jebnf.ast.Notification;

/**
 * Terminal node.
 *
 * Has no sub nodes.
 *
 * @author Sven Strittmatter <weltraumschaf@googlemail.com>
 */
public final class Terminal extends AbstractNode {

    /**
     * Key for the value attribute.
     */
    private static final String ATTR_VALUE = "value";

    /**
     * Initializes object with empty value and parent node.
     *
     * @param parent The parent node.
     * @param value The terminal value.
     */
    private Terminal(final Node parent, final String value) {
        super(parent, NodeType.TERMINAL);
        setAttribute(ATTR_VALUE, value);
    }

    /**
     * Creates an new terminal node with a {@link Null} parent node and empty value string.
     *
     * @return New instance.
     */
    public static Terminal newInstance() {
        return newInstance(Null.getInstance());
    }

    /**
     * Creates an new terminal node with a {@link Null} parent node.
     *
     * @param value The terminal value.
     * @return New instance.
     */
    public static Terminal newInstance(final String value) {
        return newInstance(Null.getInstance(), value);
    }

    /**
     * Creates an new terminal node with an empty value string.
     *
     * @param parent The parent node.
     * @return New instance.
     */
    public static Terminal newInstance(final Node parent) {
        return newInstance(parent, "");
    }

    /**
     * Creates an new terminal node.
     *
     * @param parent The parent node.
     * @param value The terminal value.
     * @return New instance.
     */
    public static Terminal newInstance(final Node parent, final String value) {
        return new Terminal(parent, value);
    }

    @Override
    public void probeEquivalence(final Node other, final Notification result) {
        try {
            final Terminal terminal = (Terminal) other;

            if (!getAttribute(ATTR_VALUE).equals(terminal.getAttribute(ATTR_VALUE))) {
                result.error("Terminal value mismatch: '%s' != '%s'!", getAttribute(ATTR_VALUE),
                                                                       terminal.getAttribute(ATTR_VALUE));
            }
        } catch (ClassCastException ex) {
            result.error(
                "Probed node types mismatch: '%s' != '%s'!",
                getClass(),
                other.getClass()
            );
        }
    }

    /**
     * Always returns 1 because a terminal has no child nodes..
     *
     * @return Returns 1.
     */
    @Override
    public int depth() {
        return 1;
    }

}
