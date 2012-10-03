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
import static org.junit.Assert.assertSame;
import org.junit.Test;

/**
 *
 * @author Sven Strittmatter <weltraumschaf@googlemail.com>
 */
public class ReferenceGrammarTest {

    @Test public void getInstanceAndSyntax() throws SyntaxException {
        final ReferenceGrammar grammar = ReferenceGrammar.getInstance();
        assertSame(grammar, ReferenceGrammar.getInstance());
        final SyntaxNode syntax = grammar.getSyntax();
        assertSame(syntax, grammar.getSyntax());
    }
}
