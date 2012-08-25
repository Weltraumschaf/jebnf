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

import de.weltraumschaf.jebnf.ast.nodes.Comment;
import de.weltraumschaf.jebnf.ast.nodes.Rule;
import de.weltraumschaf.jebnf.ast.nodes.Syntax;

/**
 * Sub builder providing methods to create rules and finaly build the syntax.
 *
 * @author Sven Strittmatter <weltraumschaf@googlemail.com>
 */
public class RuleBuilder {

    /**
     * The created syntax.
     */
    private final Syntax syntax;

    /**
     * Sets the syntax to create the rules on.
     *
     * It is not intended to call this constructor from outside the package.
     *
     * @param syntax The syntax to which the all builded nodes belongs.
     */
    RuleBuilder(final Syntax syntax) {
        this.syntax = syntax;
    }

    /**
     * Creates a rule with name.
     *
     * @param name The rule name.
     * @return Returns a generic builder of type {@link RuleBuilder}.
     */
    public GenericBuilder<RuleBuilder> rule(final String name) {
        final Rule rule = Rule.newInstance(syntax, name);
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
        final Comment comment = Comment.newInstance(syntax, value);
        syntax.addChild(comment);
        return this;
    }

    /**
     * Returns the created syntax.
     *
     * @return Returns the created syntax object.
     */
    public Syntax build() {
        return syntax;
    }

}
