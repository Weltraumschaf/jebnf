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

import com.google.common.collect.Lists;
import de.weltraumschaf.jebnf.ast.*;
import java.util.List;

/**
 * Syntax node.
 *
 * The root of the AST.
 *
 * @author Sven Strittmatter <weltraumschaf@googlemail.com>
 */
public final class Syntax extends AbstractNode implements Composite {

    /**
     * Default meta string.
     */
    public static final String DEFAULT_META = "xis/ebnf v2.0 http://wiki.karmin.ch/ebnf/ gpl3";

    /**
     * Key for the title attribute.
     */
    private static final String ATTR_TITLE = "title";

    /**
     * Key for the meta attribute.
     */
    private static final String ATTR_META = "meta";

    /**
     * Type of the node.
     */
    private static final NodeType TYPE = NodeType.SYNTAX;

    /**
     * Holds the child nodes.
     */
    private final List<Node> nodes = Lists.newArrayList();

    /**
     * Initializes the object with title and meta.
     *
     * @param title Syntax title.
     * @param meta Syntax meta.
     */
    private Syntax(final String title, final String meta) {
        super(Null.getInstance(), NodeType.SYNTAX);
        setAttribute(ATTR_TITLE, title);
        setAttribute(ATTR_META, meta);
    }

    /**
     * Creates a new syntax node with default meta and empty title.
     *
     * @return New instance.
     */
    public static Syntax newInstance() {
        return newInstance("");
    }

    /**
     * Creates a new syntax node with default meta.
     *
     * @param title Title of the syntax.
     * @return New instance.
     */
    public static Syntax newInstance(final String title) {
        return newInstance(title, DEFAULT_META);
    }

    /**
     * Creates a new syntax node.
     *
     * @param title Title of the syntax.
     * @param meta  Meta of the syntax.
     * @return New instance.
     */
    public static Syntax newInstance(final String title, final String meta) {
        return new Syntax(title, meta);
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
    public List<Node> getChildren() {
        return nodes;
    }

    @Override
    public void probeEquivalence(final Node other, final Notification result) {
        try {
            final Syntax syntax = (Syntax) other;

            if (!getAttribute(ATTR_TITLE).equals(syntax.getAttribute(ATTR_TITLE))) {
                result.error("Titles of syntx differs: '%s' != '%s'!", getAttribute(ATTR_TITLE),
                                                                       syntax.getAttribute(ATTR_TITLE));
            }

            if (!getAttribute(ATTR_META).equals(syntax.getAttribute(ATTR_META))) {
                result.error("Meta of syntx differs: '%s' != '%s'!", getAttribute(ATTR_META),
                                                                     syntax.getAttribute(ATTR_META));
            }

            if (countChildren() != syntax.countChildren()) {
                result.error(
                    "Node %s has different child count than other: %d != %d!",
                    getNodeName(),
                    countChildren(),
                    syntax.countChildren()
                );
            }

            final List<Node> subnodes      = getChildren();
            final List<Node> otherSubnodes = syntax.getChildren();
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
                "Probed node types mismatch: '%s' != '%s'!",
                getClass(),
                other.getClass()
            );
        }
    }

    @Override
    public void accept(final Visitor visitor) {
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
    public int depth() {
        return new DepthCalculator(this).depth();
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

    @Override
    public String getNodeName() {
        return TYPE.toString();
    }

    @Override
    public NodeType getType() {
        return TYPE;
    }

}
