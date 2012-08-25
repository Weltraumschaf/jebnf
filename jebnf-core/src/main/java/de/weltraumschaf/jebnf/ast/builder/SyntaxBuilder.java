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

import de.weltraumschaf.jebnf.ast.nodes.Syntax;

/**
 * Entry point to build an AST.
 *
 * This builder only provides two static methods to start the generation
 * of an syntax AST.
 *
 * You may import the methods statically for convenience:
 * <code>
 * import static de.weltraumschaf.ebnf.ast.builder.SyntaxBuilder.syntax;
 * ...
 * Syntax syntax = syntax("EBNF defined in itself.")
 * ...
 * </code>
 *
 * @author Sven Strittmatter <weltraumschaf@googlemail.com>
 */
public final class SyntaxBuilder {

    /**
     * It is not intended to create an object from outside.
     *
     * Use the syntax builder method.
     */
    private SyntaxBuilder() { }

    /**
     * Creates the syntax with default meta string.
     *
     * @param title Title of the syntax.
     * @return Return a rule builder object.
     */
    public static RuleBuilder syntax(final String title) {
        return syntax(title, Syntax.DEFAULT_META);
    }

    /**
     * Creates the syntax with title and meta.
     *
     * @param title Title of the syntax.
     * @param meta  Meta information of the syntax.
     * @return Return a rule builder object.
     */
    public static RuleBuilder syntax(final String title, final String meta) {
        return new RuleBuilder(Syntax.newInstance(title, meta));
    }

}
