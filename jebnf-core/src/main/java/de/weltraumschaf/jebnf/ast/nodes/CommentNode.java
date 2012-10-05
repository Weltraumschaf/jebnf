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
 * CommentNode node.
 *
 * @author Sven Strittmatter <weltraumschaf@googlemail.com>
 */
public final class CommentNode extends BaseNode {

    /**
     * Key for the value attribute.
     */
    private static final String ATTR_VALUE = "value";

    /**
     * Initializes object with empty value and parent node.
     *
     * @param parent The parent node.
     * @param value CommentNode value.
     */
    private CommentNode(final Node parent, final String value) {
        super(parent, NodeType.COMMENT);
        setAttribute(ATTR_VALUE, value);
    }

    /**
     * Creates new comment node with {@link NullNode} parent node and empty string value.
     *
     * @return New instance.
     */
    public static CommentNode newInstance() {
        return newInstance(NullNode.getInstance());
    }

    /**
     * Creates new comment node with {@link NullNode} parent node.
     *
     * @param value The comment string.
     * @return       New instance.
     */
    public static CommentNode newInstance(final String value) {
        return newInstance(NullNode.getInstance(), value);
    }

    /**
     * Creates new comment node with empty string value.
     *
     * @param parent The node's parent.
     * @return        New instance.
     */
    public static CommentNode newInstance(final Node parent) {
        return newInstance(parent, "");
    }

    /**
     * Creates new comment node.
     *
     * @param parent The node's parent.
     * @param value  The comment string.
     * @return        New instance.
     */
    public static CommentNode newInstance(final Node parent, final String value) {
        return new CommentNode(parent, value);
    }

    @Override
    public void probeEquivalence(final Node other, final Notification result) {
        try {
            final CommentNode terminal = (CommentNode) other;

            if (!getAttribute(ATTR_VALUE).equals(terminal.getAttribute(ATTR_VALUE))) {
                result.error("Comment value mismatch: '%s' != '%s'!", getAttribute(ATTR_VALUE),
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
