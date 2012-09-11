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
 * IdentifierNode node.
 *
 * Has no sub nodes.
 *
 * @author Sven Strittmatter <weltraumschaf@googlemail.com>
 */
public final class IdentifierNode extends AbstractNode {

    /**
     * Key for the value attribute.
     */
    private static final String ATTR_VALUE = "value";

    /**
     * Initializes object with value and parent node.
     *
     * @param parent The parent node.
     * @param value The identifier value.
     */
    private IdentifierNode(final Node parent, final String value) {
        super(parent, NodeType.IDENTIFIER);
        setAttribute(ATTR_VALUE, value);
    }

    /**
     * Creates an new identifier node with a {@link NullNode} parent node and empty value string.
     *
     * @return New instance.
     */
    public static IdentifierNode newInstance() {
        return newInstance(NullNode.getInstance());
    }

    /**
     * Creates an new identifier node with a {@link NullNode} parent node.
     *
     * @param value The identifier name.
     * @return New instance.
     */
    public static IdentifierNode newInstance(final String value) {
        return newInstance(NullNode.getInstance(), value);
    }

    /**
     * Creates an new identifier node with an empty value string.
     *
     * @param parent The parent node.
     * @return New instance.
     */
    public static IdentifierNode newInstance(final Node parent) {
        return newInstance(parent, "");
    }

    /**
     * Creates an new identifier node.
     *
     * @param parent The parent node.
     * @param value The identifier name.
     * @return New instance.
     */
    public static IdentifierNode newInstance(final Node parent, final String value) {
        return new IdentifierNode(parent, value);
    }

    @Override
    public void probeEquivalence(final Node other, final Notification result) {
        try {
            final IdentifierNode ident = (IdentifierNode) other;

            if (!getAttribute(ATTR_VALUE).equals(ident.getAttribute(ATTR_VALUE))) {
                result.error("Identifier value mismatch: '%s' != '%s'!", getAttribute(ATTR_VALUE),
                                                                         ident.getAttribute(ATTR_VALUE));
            }
        } catch (ClassCastException ex) {
            result.error("Probed node types mismatch: '%s' != '%s'!", getClass(), other.getClass());
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
