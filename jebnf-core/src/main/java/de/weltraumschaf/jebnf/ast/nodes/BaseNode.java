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

import com.google.common.collect.Maps;
import de.weltraumschaf.jebnf.ast.Node;
import de.weltraumschaf.jebnf.ast.NodeType;
import de.weltraumschaf.jebnf.ast.visitor.Visitor;
import java.util.Iterator;
import java.util.Map;
import java.util.TreeMap;

/**
 * Abstract representation of AST nodes which are not the {@link nodes.SyntaxNode "root node"}
 * but any other kind of {@link Node}.
 *
 * @author Sven Strittmatter <weltraumschaf@googlemail.com>
 */
abstract class BaseNode implements Node {

    /**
     * The direct ancestor in the AST tree.
     */
    private final Node parent;

    /**
     * Type to differentiate homogenous nodes.
     */
    private final NodeType type;

    /**
     * Map of node attributes.
     *
     * @todo Use enum as key.
     */
    private final Map<NodeAttribute, String> attributes = new TreeMap<NodeAttribute, String>();

    /**
     * Initializes the node with it's parent. This is immutable.
     *
     * May the node itself change, it is not possible to set the
     * reference to the parent node.
     *
     * @param parent Ancestor node.
     * @param type Type of node.
     */
    protected BaseNode(final Node parent, final NodeType type) {
        this.parent = parent;
        this.type   = type;
    }

    @Override
    public boolean hasParent() {
        return parent != null && parent.getType() != NodeType.NULL;
    }

    @Override
    public Node getParent() {
        return parent;
    }

    @Override
    public NodeType getType() {
        return type;
    }

    @Override
    public String getNodeName() {
        return getType().toString();
    }

    @Override
    public void accept(final Visitor<?> visitor) {
        visitor.beforeVisit(this);
        visitor.visit(this);
        visitor.afterVisit(this);
    }

    @Override
    public boolean hasAttributes() {
        return !attributes.isEmpty();
    }

    @Override
    public Map<NodeAttribute, String> getAttributes() {
        return Maps.newHashMap(attributes);
    }

    @Override
    public boolean hasAttribute(final NodeAttribute attribute) {
        return attributes.containsKey(attribute);
    }

    @Override
    public String getAttribute(final NodeAttribute attribute) {
        if (!hasAttribute(attribute)) {
            throw new IllegalArgumentException(String.format("Does not have attribute with name '%s'!", attribute));
        }

        return attributes.get(attribute);
    }

    @Override
    public void setAttribute(final NodeAttribute attribute, final String value) {
        attributes.put(attribute, value);
    }

    @Override
    public String toString() {
        final StringBuilder str = new StringBuilder(String.format("<%s", getNodeName().toUpperCase()));

        if (hasAttributes()) {
            final Iterator<Map.Entry<NodeAttribute, String>> iterator = attributes.entrySet().iterator();

            while (iterator.hasNext()) {
                final Map.Entry<NodeAttribute, String> pairs = iterator.next();
                str.append(String.format(" %s=%s", pairs.getKey(), pairs.getValue()));
            }
        }

        return str.append('>').toString();
    }

    @Override
    public boolean isType(final NodeType checked) {
        return type == checked; // NOPMD Enums may be treated like integers.
    }

}
