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

import de.weltraumschaf.jebnf.ast.AbstractComposite;
import de.weltraumschaf.jebnf.ast.Node;
import de.weltraumschaf.jebnf.ast.NodeType;

/**
 * Lop node.
 *
 * @author Sven Strittmatter <weltraumschaf@googlemail.com>
 */
public final class LoopNode extends AbstractComposite {

    /**
     * Initializes object with parent node.
     *
     * @param parent The parent node.
     */
    private LoopNode(final Node parent) {
        super(parent, NodeType.LOOP);
    }

    /**
     * Creates new loop node with a {@link NullNode} as parent.
     *
     * @return New instance.
     */
    public static LoopNode newInstance() {
        return newInstance(NullNode.getInstance());
    }

    /**
     * Creates new loop node.
     *
     * @param parent The parent node.
     * @return        New instance.
     */
    public static LoopNode newInstance(final Node parent) {
        return new LoopNode(parent);
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
