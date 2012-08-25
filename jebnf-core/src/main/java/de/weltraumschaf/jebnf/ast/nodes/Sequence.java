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
 * Sequence node.
 *
 * @author Sven Strittmatter <weltraumschaf@googlemail.com>
 */
public final class Sequence extends AbstractComposite {

    /**
     * Initializes object with parent node.
     *
     * @param parent The parent node.
     */
    private Sequence(final Node parent) {
        super(parent, NodeType.SEQUENCE);
    }

    /**
     * Creates an new sequence node with a {@link Null} parent node.
     *
     * @return New instance.
     */
    public static Sequence newInstance() {
        return newInstance(Null.getInstance());
    }

    /**
     * Creates an new sequence node.
     *
     * @param parent The parent node.
     * @return        New instance.
     */
    public static Sequence newInstance(final Node parent) {
        return new Sequence(parent);
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
