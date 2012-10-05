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

/**
 * SequenceNode node.
 *
 * @author Sven Strittmatter <weltraumschaf@googlemail.com>
 */
public final class SequenceNode extends BaseCompositeNode {

    /**
     * Initializes object with parent node.
     *
     * @param parent The parent node.
     */
    private SequenceNode(final Node parent) {
        super(parent, NodeType.SEQUENCE);
    }

    /**
     * Creates an new sequence node with a {@link NullNode} parent node.
     *
     * @return New instance.
     */
    public static SequenceNode newInstance() {
        return newInstance(NullNode.getInstance());
    }

    /**
     * Creates an new sequence node.
     *
     * @param parent The parent node.
     * @return        New instance.
     */
    public static SequenceNode newInstance(final Node parent) {
        return new SequenceNode(parent);
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
