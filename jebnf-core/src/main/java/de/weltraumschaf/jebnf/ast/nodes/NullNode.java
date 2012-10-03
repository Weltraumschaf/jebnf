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

import de.weltraumschaf.jebnf.ast.*;
import de.weltraumschaf.jebnf.ast.visitor.Visitor;

/**
 * NullNode node.
 *
 * This node type is used for testing and as default node. E.g. parent nodes (of factory
 * created nodes) have a NullNode node by default to prevent null pointer exceptions.
 *
 * @author Sven Strittmatter <weltraumschaf@googlemail.com>
 */
public final class NullNode extends AbstractNode {

    /**
     * Single shared instance.
     */
    private static final NullNode INSTANCE = new NullNode();

    /**
     * Initializes the parent node with null.
     */
    private NullNode() {
        super(null, NodeType.NULL);
    }

    /**
     * Returns always the same null instance.
     *
     * @return Shared instance.
     */
    public static NullNode getInstance() {
        return INSTANCE;
    }

    @Override
    public void accept(final Visitor<?> visitor) {
        // Do nothing here.
    }

    @Override
    public void probeEquivalence(final Node other, final Notification result) {
        if (!(other instanceof NullNode)) {
            result.error("Other node is not of type Null!");
        }
    }

    @Override
    public int depth() {
        return 1;
    }

}
