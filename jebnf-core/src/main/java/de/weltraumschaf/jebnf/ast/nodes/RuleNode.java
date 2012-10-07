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

import de.weltraumschaf.jebnf.ast.Node;
import de.weltraumschaf.jebnf.ast.NodeType;
import de.weltraumschaf.jebnf.ast.Notification;

/**
 * RuleNode node.
 *
 * @author Sven Strittmatter <weltraumschaf@googlemail.com>
 */
public final class RuleNode extends BaseCompositeNode {

    /**
     * Attribute keys of the rule node.
     */
    public static enum Attributes implements NodeAttribute {

        /**
         * Name attribute.
         */
        NAME;

    }

    /**
     * Initializes object with empty value and parent node.
     *
     * @param parent The parent node.
     * @param name The rule name.
     */
    private RuleNode(final Node parent, final String name) {
        super(parent, NodeType.RULE);
        setAttribute(Attributes.NAME, name);
    }

    /**
     * Creates an new rule node with a {@link NullNode} parent node and empty name string.
     *
     * @return New instance.
     */
    public static RuleNode newInstance() {
        return newInstance(NullNode.getInstance());
    }

    /**
     * Creates an new rule node with a {@link NullNode} parent node.
     *
     * @param name The rule name.
     * @return      New instance.
     */
    public static RuleNode newInstance(final String name) {
        return newInstance(NullNode.getInstance(), name);
    }

    /**
     * Creates an new rule node with an empty name string.
     *
     * @param parent The parent node.
     * @return        New instance.
     */
    public static RuleNode newInstance(final Node parent) {
        return newInstance(parent, "");
    }

    /**
     * Creates an new rule node.
     *
     * @param parent The parent node.
     * @param name   The rule name.
     * @return        New instance.
     */
    public static RuleNode newInstance(final Node parent, final String name) {
        return new RuleNode(parent, name);
    }

    @Override
    public void probeEquivalence(final Node other, final Notification result) {
        super.probeEquivalence(other, result);

        final RuleNode rule = (RuleNode) other;

        if (!getAttribute(Attributes.NAME).equals(rule.getAttribute(Attributes.NAME))) {
            result.error("Names of rule differs: '%s' != '%s'!",
                         getAttribute(Attributes.NAME),
                         rule.getAttribute(Attributes.NAME));
        }
    }

    @Override
    public String toString() {
        final StringBuilder str = new StringBuilder(super.toString());

        if (hasChildren()) {
            for (Node child : getChildren()) {
                str.append('\n').append(child.toString());
            }
        }

        return str.toString();
    }

}
