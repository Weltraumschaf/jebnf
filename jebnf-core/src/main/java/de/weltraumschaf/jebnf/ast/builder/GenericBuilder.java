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

package de.weltraumschaf.jebnf.ast.builder;

import de.weltraumschaf.jebnf.ast.AbstractComposite;
import de.weltraumschaf.jebnf.ast.nodes.*;

/**
 * Generic builder provides interface to construct sub nodes for rule nodes.
 *
 * This class is parameterized with the type of the parent builder.
 *
 * @param <P> Parent builder. Either {@link RuleBuilder} or {@link GenericBuilder}.
 * @author Sven Strittmatter <weltraumschaf@googlemail.com>
 */
public class GenericBuilder<P> {

    /**
     * The parent builder which created the builder.
     */
    private final P parentBuilder;

    /**
     * The node built by the parent builder.
     */
    private final AbstractComposite parentNode;

    /**
     * Initializes the builder with its parent builder and node.
     *
     * The constructor is not intended to be called from outside the package.
     *
     * @param parentBuilder Builder which created the builder.
     * @param node          To this node all by this builder created nodes will be add.
     */
    GenericBuilder(final P parentBuilder, final AbstractComposite node) {
        this.parentBuilder = parentBuilder;
        this.parentNode = node;
    }

    /**
     * Returns to the parent builder.
     *
     * @return Parent builder of type P.
     */
    public P end() {
        return parentBuilder;
    }

    /**
     * Creates an {@link Identifier} node and returns the same builder.
     *
     * @param value The identifier value.
     * @return Returns this for method chaining.
     */
    public GenericBuilder<P> identifier(final String value) {
        parentNode.addChild(Identifier.newInstance(parentNode, value));
        return this;
    }

    /**
     * Creates a {@link Terminal} node and returns the same builder.
     *
     * @param value The terminal value.
     * @return Returns this for method chaining.
     */
    public GenericBuilder<P> terminal(final String value) {
        parentNode.addChild(Terminal.newInstance(parentNode, value));
        return this;
    }

    /**
     * Creates a {@link Comment} node and returns the same builder.
     *
     * @param value The comment value.
     * @return Returns this for method chaining.
     */
    public GenericBuilder<P> comment(final String value) {
        parentNode.addChild(Comment.newInstance(parentNode, value));
        return this;
    }

    /**
     * Creates a {@link Choice} node and returns a sub builder for adding nodes to the {@link Choice} node.
     *
     * @return Returns a new generic builder for a choice node.
     */
    public GenericBuilder<GenericBuilder<P>> choice() {
        final Choice choice = Choice.newInstance(parentNode);
        parentNode.addChild(choice);
        return new GenericBuilder<GenericBuilder<P>>(this, choice);
    }

    /**
     * Creates a {@link Loop} node and returns a sub builder for adding nodes to the {@link Loop} node.
     *
     * @return Returns a new generic builder for a loop node.
     */
    public GenericBuilder<GenericBuilder<P>> loop() {
        final Loop loop = Loop.newInstance(parentNode);
        parentNode.addChild(loop);
        return new GenericBuilder<GenericBuilder<P>>(this, loop);
    }

    /**
     * Creates a {@link Option} node and returns a sub builder for adding nodes to the {@link Option} node.
     *
     * @return Returns a new generic builder for a option node.
     */
    public GenericBuilder<GenericBuilder<P>> option() {
        final Option option = Option.newInstance(parentNode);
        parentNode.addChild(option);
        return new GenericBuilder<GenericBuilder<P>>(this, option);
    }

    /**
     * Creates a {@link Sequence} node and returns a sub builder for adding nodes to the {@link Sequence} node.
     *
     * @return Returns a new generic builder for a sequence node.
     */
    public GenericBuilder<GenericBuilder<P>> sequence() {
        final Sequence seq = Sequence.newInstance(parentNode);
        parentNode.addChild(seq);
        return new GenericBuilder<GenericBuilder<P>>(this, seq);
    }

}
