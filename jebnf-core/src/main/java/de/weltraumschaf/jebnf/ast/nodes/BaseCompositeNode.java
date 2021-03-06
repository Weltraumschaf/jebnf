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

import com.google.common.collect.Lists;
import de.weltraumschaf.jebnf.ast.CompositeNode;
import de.weltraumschaf.jebnf.ast.Node;
import de.weltraumschaf.jebnf.ast.NodeType;
import de.weltraumschaf.jebnf.ast.Notification;
import de.weltraumschaf.jebnf.ast.visitor.Visitor;
import java.util.List;

/**
 * Abstract base class for nodes which are not leaves and have sub nodes.
 *
 * Provides interface for iterate and add child nodes.
 *
 * @author Sven Strittmatter <weltraumschaf@googlemail.com>
 */
abstract class BaseCompositeNode extends BaseNode implements CompositeNode {

    /**
     * Holds the child nodes.
     */
    private final List<Node> nodes = Lists.newArrayList();

    /**
     * Initializes object with empty child node array and parent node.
     *
     * @param parent Ancestor node.
     * @param type Type of node.
     */
    public BaseCompositeNode(final Node parent, final NodeType type) {
        super(parent, type);
    }

    @Override
    public List<Node> getChildren() {
        return nodes;
    }

    @Override
    public int countChildren() {
        return nodes.size();
    }

    @Override
    public boolean hasChildren() {
        return 0 < countChildren();
    }

    @Override
    public void addChild(final Node child) {
        nodes.add(child);
    }

    @Override
    public void accept(final Visitor<?> visitor) {
        visitor.beforeVisit(this);
        visitor.visit(this);

        if (hasChildren()) {
            for (Node subnode : getChildren()) {
                subnode.accept(visitor);
            }
        }

        visitor.afterVisit(this);
    }

    @Override
    public void probeEquivalence(final Node other, final Notification result) {
        try {
            final CompositeNode comp = (CompositeNode) other;

            if (!getClass().equals(other.getClass())) {
                result.error(
                    "Probed node types mismatch: '%s' != '%s'!",
                    getClass(),
                    other.getClass()
                );
                return;
            }

            if (countChildren() != comp.countChildren()) {
                result.error(
                    "Node %s has different child count than other: %d != %d!",
                    getNodeName(),
                    countChildren(),
                    comp.countChildren()
                );
            }

            final List<Node> subnodes      = getChildren();
            final List<Node> otherSubnodes = comp.getChildren();

            int i = 0; // NOPMD
            for (Node subnode : subnodes) {
                try {
                    subnode.probeEquivalence(otherSubnodes.get(i), result);
                } catch (IndexOutOfBoundsException ex) {
                    result.error("Other node has not the expected subnode!");
                }

                i++;
            }
        } catch (ClassCastException ex) {
            result.error(
                "Probed node is not a composite node: '%s'!",
                other.getClass()
            );
        }
    }

    @Override
    public int depth() {
        return new DepthCalculator(this).depth();
    }

}
