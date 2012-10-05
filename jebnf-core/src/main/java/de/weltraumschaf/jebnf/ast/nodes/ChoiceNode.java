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

import de.weltraumschaf.jebnf.ast.BaseCompositeNode;
import de.weltraumschaf.jebnf.ast.Node;
import de.weltraumschaf.jebnf.ast.NodeType;

/**
 * ChoiceNode node.
 *
 * @author Sven Strittmatter <weltraumschaf@googlemail.com>
 */
public final class ChoiceNode extends BaseCompositeNode {

    /**
     * Initializes object with parent node.
     *
     * @param parent The parent node.
     */
    private ChoiceNode(final Node parent) {
        super(parent, NodeType.CHOICE);
    }

    /**
     * Creates a new choice node instance with a {@link NullNode} parent node.
     *
     * @return Return new instance.
     */
    public static ChoiceNode newInstance() {
        return newInstance(NullNode.getInstance());
    }

    /**
     * Creates a new choice node instance with custom parent node.
     *
     * @param parent The associated parent node.
     * @return Return new instance.
     */
    public static ChoiceNode newInstance(final Node parent) {
        return new ChoiceNode(parent);
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
