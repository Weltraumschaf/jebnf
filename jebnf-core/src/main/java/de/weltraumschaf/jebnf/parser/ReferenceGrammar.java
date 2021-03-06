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
package de.weltraumschaf.jebnf.parser;

import de.weltraumschaf.jebnf.ast.nodes.SyntaxNode;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Defines a immutable reference grammar.
 *
 * thus the reference grammar does not change on runtime it is
 * a shared object.
 *
 * @author Sven Strittmatter <weltraumschaf@googlemail.com>
 */
public final class ReferenceGrammar {

    /**
     * Shared instance.
     */
    private static final ReferenceGrammar INSTANCE = new ReferenceGrammar();

    /**
     * Holds the AST {@link de.weltraumschaf.jebnf.ast.nodes.SyntaxNode} node for the reference grammar for reuse.
     *
     * Is lazy computed.
     */
    private SyntaxNode syntax;

    /**
     * Not instantiated from outside, because pure static utility class.
     */
    private ReferenceGrammar() {
        super();
    }

    /**
     * Returns an immutable reference grammar instance.
     *
     * @return Return always the same instance.
     */
    public static ReferenceGrammar getInstance() {
        return INSTANCE;
    }

    /**
     * Returns the reference grammar as string.
     *
     * @return Return parsable grammar source string.
     */
    @Override
    public String toString() {
        return String.format("\"EBNF defined in itself.\" {%n"
             + "    syntax     = [ title ] \"{\" { rule } \"}\" [ comment ] .%n"
             + "    rule       = identifier ( \"=\" | \":\" | \":==\" ) expression ( \".\" | \";\" ) .%n"
             + "    expression = term { \"|\" term } .%n"
             + "    term       = factor { factor } .%n"
             + "    factor     = identifier%n"
             + "               | literal%n"
             + "               | range%n"
             + "               | \"[\" expression \"]\"%n"
             + "               | \"(\" expression \")\"%n"
             + "               | \"{\" expression \"}\" .%n"
             + "    identifier = character { character } .%n"
             + "    range      = character \"..\" character .%n"
             + "    title      = literal .%n"
             + "    comment    = literal .%n"
             + "    literal    = \"\'\" character { character } \"\'\"%n"
             + "               | \'\"\' character { character } \'\"\' .%n"
// Ranges not implemented yet.
//             + "    character  = \"a\" .. \"z\"%n"
//             + "               | \"A\" .. \"Z\"%n"
//             + "               | \"0\" .. \"9\" .%n"
             + "}");
    }

    /**
     * Returns the reference syntax as abstract syntax tree.
     *
     * @throws SyntaxException On SyntaxNode errors.
     * @return Returns the grammars {@link SyntaxNode} object.
     */
    public SyntaxNode getSyntax() throws SyntaxException {
        if (null == syntax) {
            final Parser parser = Factory.newParserFromSource(toString());

            try {
                syntax = parser.parse();
            } catch (IOException ex) {
                // This should never hapen because we read from the string above, which must be parsable.
                Logger.getLogger(ReferenceGrammar.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

        return syntax;
    }

}
