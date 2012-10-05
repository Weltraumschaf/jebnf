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
package de.weltraumschaf.jebnf.ast.nodes;

import de.weltraumschaf.jebnf.ast.BaseNode;
import de.weltraumschaf.jebnf.ast.Node;
import de.weltraumschaf.jebnf.ast.NodeType;
import de.weltraumschaf.jebnf.ast.Notification;

/**
 * TerminalNode node.
 *
 * Has no sub nodes.
 *
 * @author Sven Strittmatter <weltraumschaf@googlemail.com>
 */
public final class TerminalNode extends BaseNode {

    /**
     * Key for the value attribute.
     */
    public static final String ATTR_IBUTE_VALUE = "value";

    /**
     * Initializes object with empty value and parent node.
     *
     * @param parent The parent node.
     * @param value The terminal value.
     */
    private TerminalNode(final Node parent, final String value) {
        super(parent, NodeType.TERMINAL);
        setAttribute(ATTR_IBUTE_VALUE, value);
    }

    /**
     * Creates an new terminal node with a {@link NullNode} parent node and empty value string.
     *
     * @return New instance.
     */
    public static TerminalNode newInstance() {
        return newInstance(NullNode.getInstance());
    }

    /**
     * Creates an new terminal node with a {@link NullNode} parent node.
     *
     * @param value The terminal value.
     * @return New instance.
     */
    public static TerminalNode newInstance(final String value) {
        return newInstance(NullNode.getInstance(), value);
    }

    /**
     * Creates an new terminal node with an empty value string.
     *
     * @param parent The parent node.
     * @return New instance.
     */
    public static TerminalNode newInstance(final Node parent) {
        return newInstance(parent, "");
    }

    /**
     * Creates an new terminal node.
     *
     * @param parent The parent node.
     * @param value The terminal value.
     * @return New instance.
     */
    public static TerminalNode newInstance(final Node parent, final String value) {
        return new TerminalNode(parent, value);
    }

    @Override
    public void probeEquivalence(final Node other, final Notification result) {
        try {
            final TerminalNode terminal = (TerminalNode) other;

            if (!getAttribute(ATTR_IBUTE_VALUE).equals(terminal.getAttribute(ATTR_IBUTE_VALUE))) {
                result.error("Terminal value mismatch: '%s' != '%s'!", getAttribute(ATTR_IBUTE_VALUE),
                                                                       terminal.getAttribute(ATTR_IBUTE_VALUE));
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
