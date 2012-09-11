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

import de.weltraumschaf.jebnf.ast.*;
import de.weltraumschaf.jebnf.ast.visitor.Visitor;

/**
 * Null node.
 *
 * This node type is used for testing and as default node. E.g. parent nodes (of factory
 * created nodes) have a Null node by default to prevent null pointer exceptions.
 *
 * @author Sven Strittmatter <weltraumschaf@googlemail.com>
 */
public final class Null extends AbstractNode {

    /**
     * Single shared instance.
     */
    private static final Null INSTANCE = new Null();

    /**
     * Initializes the parent node with null.
     */
    private Null() {
        super(null, NodeType.NULL);
    }

    /**
     * Returns always the same null instance.
     *
     * @return Shared instance.
     */
    public static Null getInstance() {
        return INSTANCE;
    }

    @Override
    public void accept(final Visitor<?> visitor) {
        // Do nothing here.
    }

    @Override
    public void probeEquivalence(final Node other, final Notification result) {
        if (!(other instanceof Null)) {
            result.error("Other node is not of type Null!");
        }
    }

    @Override
    public int depth() {
        return 1;
    }

}
