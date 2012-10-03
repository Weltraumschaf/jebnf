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
package de.weltraumschaf.jebnf.ast.visitor;

import static de.weltraumschaf.jebnf.TestHelper.getInstance;
import static de.weltraumschaf.jebnf.ast.builder.SyntaxBuilder.syntax;
import de.weltraumschaf.jebnf.ast.nodes.ChoiceNode;
import de.weltraumschaf.jebnf.ast.nodes.CommentNode;
import de.weltraumschaf.jebnf.ast.nodes.IdentifierNode;
import de.weltraumschaf.jebnf.ast.nodes.LoopNode;
import de.weltraumschaf.jebnf.ast.nodes.Option;
import de.weltraumschaf.jebnf.ast.nodes.RuleNode;
import de.weltraumschaf.jebnf.ast.nodes.SequenceNode;
import de.weltraumschaf.jebnf.ast.nodes.SyntaxNode;
import de.weltraumschaf.jebnf.ast.nodes.TerminalNode;
import de.weltraumschaf.jebnf.parser.Parser;
import de.weltraumschaf.jebnf.parser.SyntaxException;
import java.io.IOException;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import static org.junit.Assert.*;
import org.junit.Test;

/**
 *
 * @author Sven Strittmatter <weltraumschaf@googlemail.com>
 */
public class TextSyntaxTreeVisitorTest {

    @Test public void testCreateRow() {
        try {
            TextSyntaxTreeVisitor.createRow(-3);
            fail("Expected excpetion not thrown!");
        } catch (IllegalArgumentException ex) {
            assertEquals("Coll count msut be greater equal 0! Given value '-3'.", ex.getMessage());
        }

        assertEquals(new ArrayList<String>(), TextSyntaxTreeVisitor.createRow(0));
        assertEquals(Arrays.asList(""), TextSyntaxTreeVisitor.createRow(1));
        assertEquals(Arrays.asList("", "", ""), TextSyntaxTreeVisitor.createRow(3));
        assertEquals(Arrays.asList("", "", "", "", ""), TextSyntaxTreeVisitor.createRow(5));
    }

    @Test public void testGenerateMatrix() {
        SyntaxNode ast = syntax("fobar")
            .rule("one")
            .end()
            .rule("two")
            .end()
            .rule("three")
            .end()
        .build();

        assertEquals(2, ast.depth());
        final TextSyntaxTreeVisitor visitor = new TextSyntaxTreeVisitor();
        assertEquals(new ArrayList<ArrayList<String>>(), visitor.getMatrix());
        assertEquals(0, visitor.getDepth());
        ast.accept(visitor);

        assertEquals(2, visitor.getDepth());
        final List<List<String>> matrix = visitor.getMatrix();
        assertEquals(4, matrix.size());
        assertEquals("[syntax]",  matrix.get(0).get(0));
        assertEquals("",          matrix.get(0).get(1));
        assertEquals(" +--",       matrix.get(1).get(0)); // NOPMD
        assertEquals("[rule='one']", matrix.get(1).get(1));
        assertEquals(" +--",       matrix.get(2).get(0));
        assertEquals("[rule='two']", matrix.get(2).get(1));
        assertEquals(" +--",       matrix.get(3).get(0));
        assertEquals("[rule='three']", matrix.get(3).get(1));

        ast = syntax("foo") // NOPMD
            .rule("literal")
                .choice()
                    .sequence()
                        .terminal("'")
                        .identifier("character") // NOPMD
                        .loop()
                            .identifier("character")
                        .end()
                        .terminal("'")
                    .end()
                    .sequence()
                        .terminal("\"")
                        .identifier("character")
                        .loop()
                            .identifier("character")
                        .end()
                        .terminal("\"")
                    .end()
                .end()
            .end()
            .build();

        ast.accept(visitor);
        assertEquals(6, visitor.getDepth());
        @SuppressWarnings("unchecked")
        final List<List<String>> expected = Arrays.asList(
            Arrays.asList("[syntax]", "", "", "", "", ""),
            Arrays.asList(" +--",     "[rule='literal']", "", "", "", ""),
            Arrays.asList("    ",     " +--", "[choice]", "", "", ""), // NOPMD
            Arrays.asList("    ",     "    ", " +--", "[sequence]", "", ""),
            Arrays.asList("    ",     "    ", " |  ", " +--",       "[terminal=''']", ""), // NOPMD
            Arrays.asList("    ",     "    ", " |  ", " +--",       "[identifier='character']", ""), // NOPMD
            Arrays.asList("    ",     "    ", " |  ", " +--",       "[loop]", ""),
            Arrays.asList("    ",     "    ", " |  ", " |  ",       " +--", "[identifier='character']"),
            Arrays.asList("    ",     "    ", " |  ", " +--",       "[terminal=''']", ""),
            Arrays.asList("    ",     "    ", " +--", "[sequence]", "", ""),
            Arrays.asList("    ",     "    ", "    ", " +--",       "[terminal='\"']", ""),
            Arrays.asList("    ",     "    ", "    ", " +--",       "[identifier='character']", ""),
            Arrays.asList("    ",     "    ", "    ", " +--",       "[loop]", ""),
            Arrays.asList("    ",     "    ", "    ", " |  ",       " +--", "[identifier='character']"),
            Arrays.asList("    ",     "    ", "    ", " +--",       "[terminal='\"']", "")
        );
        assertEquals(expected, visitor.getMatrix());
    }

    @Test public void testGenerateText() throws URISyntaxException, IOException, SyntaxException {
        TextSyntaxTreeVisitor visitor = new TextSyntaxTreeVisitor();
        SyntaxNode ast = syntax("foo").build();
        ast.accept(visitor);
        assertEquals("[syntax]\n", visitor.getResult()); // NOPMD

        ast = syntax("foo")
            .rule("rule-1")
            .end()
        .build();
        ast.accept(visitor);
        assertEquals("[syntax]\n"
                   + " +--[rule='rule-1']\n",
            visitor.getResult()
        );

        ast = syntax("foo")
            .rule("rule-1")
            .end()
            .rule("rule-2")
            .end()
        .build();
        ast.accept(visitor);
        assertEquals("[syntax]\n"
                   + " +--[rule='rule-1']\n"
                   + " +--[rule='rule-2']\n",
            visitor.getResult()
        );

        ast = syntax("foo")
            .rule("name")
                .choice()
                .end()
            .end()
        .build();
        ast.accept(visitor);
        assertEquals("[syntax]\n"
                   + " +--[rule='name']\n"
                   + "     +--[choice]\n",
            visitor.getResult()
        );

        ast = syntax("foo")
            .rule("name")
                .choice()
                    .identifier("ident")
                    .terminal("term")
                .end()
            .end()
        .build();
        ast.accept(visitor);
        assertEquals("[syntax]\n"
                   + " +--[rule='name']\n"
                   + "     +--[choice]\n"
                   + "         +--[identifier='ident']\n"
                   + "         +--[terminal='term']\n",
            visitor.getResult()
        );

        ast = syntax("foobar") // NOPMD
            .rule("one")
                .choice()
                .end()
            .end()
            .rule("two")
            .end()
        .build();
        ast.accept(visitor);
        assertEquals("[syntax]\n"
                   + " +--[rule='one']\n"
                   + " |   +--[choice]\n"
                   + " +--[rule='two']\n",
            visitor.getResult()
        );

        final String expected = getInstance().createStringFromFixture("ast/visitor/rules_with_literals_tree_output");
        final Parser parser = getInstance().createParserFromFixture("parser/rules_with_literals.ebnf");
        ast     = parser.parse();
        visitor = new TextSyntaxTreeVisitor();
        ast.accept(visitor);
        assertEquals(
            expected,
            visitor.getResult()
        );
    }

    @Test public void testFormatNode() {
        assertEquals(
            "[choice]",
            TextSyntaxTreeVisitor.formatNode(ChoiceNode.newInstance()));
        assertEquals(
            "[identifier]",
            TextSyntaxTreeVisitor.formatNode(IdentifierNode.newInstance()));
        assertEquals(
            "[identifier='foobar']",
            TextSyntaxTreeVisitor.formatNode(IdentifierNode.newInstance("foobar")));
        assertEquals(
            "[loop]",
            TextSyntaxTreeVisitor.formatNode(LoopNode.newInstance()));
        assertEquals(
            "[option]",
            TextSyntaxTreeVisitor.formatNode(Option.newInstance()));
        assertEquals(
            "[rule]",
            TextSyntaxTreeVisitor.formatNode(RuleNode.newInstance()));
        assertEquals(
            "[rule='snafu']",
            TextSyntaxTreeVisitor.formatNode(RuleNode.newInstance("snafu")));
        assertEquals(
            "[sequence]",
            TextSyntaxTreeVisitor.formatNode(SequenceNode.newInstance()));
        assertEquals(
            "[syntax]",
            TextSyntaxTreeVisitor.formatNode(SyntaxNode.newInstance()));
        assertEquals(
            "[terminal]",
            TextSyntaxTreeVisitor.formatNode(TerminalNode.newInstance()));
        assertEquals(
            "[terminal='foobar']",
            TextSyntaxTreeVisitor.formatNode(TerminalNode.newInstance("foobar")));
        assertEquals(
            "[comment]",
            TextSyntaxTreeVisitor.formatNode(CommentNode.newInstance()));
        assertEquals(
            "[comment='foobar']",
            TextSyntaxTreeVisitor.formatNode(CommentNode.newInstance("foobar")));
        assertEquals(
            "[comment='foobar very very ver...']",
            TextSyntaxTreeVisitor.formatNode(
                CommentNode.newInstance("foobar very very very long comment string")));
    }

}
