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
package de.weltraumschaf.jebnf.ast.builder;

import de.weltraumschaf.jebnf.ast.nodes.CommentNode;
import de.weltraumschaf.jebnf.ast.nodes.RuleNode;
import de.weltraumschaf.jebnf.ast.nodes.SyntaxNode;

/**
 * Sub builder providing methods to create rules and finaly build the syntax.
 *
 * @author Sven Strittmatter <weltraumschaf@googlemail.com>
 */
public class RuleBuilder {

    /**
     * The created syntax.
     */
    private final SyntaxNode syntax;

    /**
     * Sets the syntax to create the rules on.
     *
     * It is not intended to call this constructor from outside the package.
     *
     * @param syntax The syntax to which the all builded nodes belongs.
     */
    RuleBuilder(final SyntaxNode syntax) {
        this.syntax = syntax;
    }

    /**
     * Creates a rule with name.
     *
     * @param name The rule name.
     * @return Returns a generic builder of type {@link RuleBuilder}.
     */
    public GenericBuilder<RuleBuilder> rule(final String name) {
        final RuleNode rule = RuleNode.newInstance(syntax, name);
        syntax.addChild(rule);
        return new GenericBuilder<RuleBuilder>(this, rule);
    }

    /**
     * Creates a comment.
     *
     * @param value The comment string.
     * @return Returns this for method chaining.
     */
    public RuleBuilder comment(final String value) {
        final CommentNode comment = CommentNode.newInstance(syntax, value);
        syntax.addChild(comment);
        return this;
    }

    /**
     * Returns the created syntax.
     *
     * @return Returns the created syntax object.
     */
    public SyntaxNode build() {
        return syntax;
    }

}
