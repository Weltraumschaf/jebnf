package de.weltraumschaf.jebnf.ast.nodes;

import de.weltraumschaf.jebnf.ast.*;

/**
 *
 * @author Sven Strittmatter <weltraumschaf@googlemail.com>
 */
public final class Null extends AbstractNode {

    private static final Null INSTANCE = new Null();

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
    public void accept(final Visitor visitor) {
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
        return 0;
    }

}
