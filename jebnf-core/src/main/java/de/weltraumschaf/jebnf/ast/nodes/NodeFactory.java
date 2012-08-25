/*
 * LICENSE
 *
 * "THE BEER-WARE LICENSE" (Revision 42):
 * "Sven Strittmatter" <weltraumschaf@googlemail.com> wrote this file.
 * As long as you retain this notice you can do whatever you want with
 * this stuff. If we meet some day, and you think this stuff is worth it,
 * you can buy me a beer in return.
 *
 */

package de.weltraumschaf.jebnf.ast.nodes;

import de.weltraumschaf.jebnf.ast.Node;
import de.weltraumschaf.jebnf.ast.NodeType;

/**
 * Factory to create node obejcts.
 *
 * @author Sven Strittmatter <weltraumschaf@googlemail.com>
 */
public final class NodeFactory {

    /**
     * Private constructor for pure utility class.
     */
    private NodeFactory() {
        super();
    }

    /**
     * Creates nodes by given type and parent node.
     *
     * @param type Type of node to create.
     * @param parent Parent of the created node.
     * @return New instance.
     */
    public static Node newNode(final NodeType type, final Node parent) {
        switch (type) {
            case CHOICE:
                return Choice.newInstance(parent);
            case COMMENT:
                return Comment.newInstance(parent);
            case IDENTIFIER:
                return Identifier.newInstance(parent);
            case LOOP:
                return Loop.newInstance(parent);
            case OPTION:
                return Option.newInstance(parent);
            case RULE:
                return Rule.newInstance(parent);
            case SEQUENCE:
                return Sequence.newInstance(parent);
            case TERMINAL:
                return Terminal.newInstance(parent);
            default:
                // This may happen if someone adds new NodeType enum w/o adding them here.
                throw new IllegalArgumentException(String.format("Unsupported node type '%s'!", type));
        }
    }

    /**
     * Creates nodes w/o parent node.
     *
     * Only {@link Syntax} and {@link Null} has no parent nodes. All other nodes get a {@link Null}
     * object as parent.
     *
     * @param type Type of node to create.
     * @return New instance.
     */
    public static Node newNode(final NodeType type) {
        if (NodeType.SYNTAX == type) {
            return Syntax.newInstance();
        } else if (NodeType.NULL == type) {
            return Null.getInstance();
        } else {
            return newNode(type, Null.getInstance());
        }
    }
}
