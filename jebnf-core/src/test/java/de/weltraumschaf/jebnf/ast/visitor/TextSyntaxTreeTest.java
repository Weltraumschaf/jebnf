/*
 * LICENSE
 *
 * "THE BEER-WARE LICENSE" (Revision 42):
 * "Sven Strittmatter" <weltraumschaf@googlemail.com> wrote this file.
 * As long as you retain this notice you can do whatever you want with
 * this stuff. If we meet some day, and you think this stuff is worth it,
 * you can buy me a beer in return.
 *
 */

package de.weltraumschaf.jebnf.ast.visitor;

import de.weltraumschaf.jebnf.ast.visitor.TextSyntaxTree;
import de.weltraumschaf.jebnf.ast.nodes.Loop;
import de.weltraumschaf.jebnf.ast.nodes.Comment;
import de.weltraumschaf.jebnf.ast.nodes.Sequence;
import de.weltraumschaf.jebnf.ast.nodes.Option;
import de.weltraumschaf.jebnf.ast.nodes.Identifier;
import de.weltraumschaf.jebnf.ast.nodes.Syntax;
import de.weltraumschaf.jebnf.ast.nodes.Choice;
import de.weltraumschaf.jebnf.ast.nodes.Terminal;
import de.weltraumschaf.jebnf.ast.nodes.Rule;
import static de.weltraumschaf.jebnf.TestHelper.getInstance;
import static de.weltraumschaf.jebnf.ast.builder.SyntaxBuilder.syntax;
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
public class TextSyntaxTreeTest {

    @Test public void testCreateRow() {
        try {
            TextSyntaxTree.createRow(-3);
            fail("Expected excpetion not thrown!");
        } catch (IllegalArgumentException ex) {
            assertEquals("Coll count msut be greater equal 0! Given value '-3'.", ex.getMessage());
        }

        assertEquals(new ArrayList<String>(), TextSyntaxTree.createRow(0));
        assertEquals(Arrays.asList(""), TextSyntaxTree.createRow(1));
        assertEquals(Arrays.asList("", "", ""), TextSyntaxTree.createRow(3));
        assertEquals(Arrays.asList("", "", "", "", ""), TextSyntaxTree.createRow(5));
    }

    @Test public void testGenerateMatrix() {
        Syntax ast = syntax("fobar")
            .rule("one")
            .end()
            .rule("two")
            .end()
            .rule("three")
            .end()
        .build();

        assertEquals(2, ast.depth());
        final TextSyntaxTree visitor = new TextSyntaxTree();
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
        TextSyntaxTree visitor = new TextSyntaxTree();
        Syntax ast = syntax("foo").build();
        ast.accept(visitor);
        assertEquals("[syntax]\n", visitor.getText()); // NOPMD

        ast = syntax("foo")
            .rule("rule-1")
            .end()
        .build();
        ast.accept(visitor);
        assertEquals("[syntax]\n"
                   + " +--[rule='rule-1']\n",
            visitor.getText()
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
            visitor.getText()
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
            visitor.getText()
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
            visitor.getText()
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
            visitor.getText()
        );

        final String expected = getInstance().createStringFromFixture("ast/visitor/rules_with_literals_tree_output");
        final Parser parser = getInstance().createParserFromFixture("parser/rules_with_literals.ebnf");
        ast     = parser.parse();
        visitor = new TextSyntaxTree();
        ast.accept(visitor);
        assertEquals(
            expected,
            visitor.getText()
        );
    }

    @Test public void testFormatNode() {
        assertEquals(
            "[choice]",
            TextSyntaxTree.formatNode(Choice.newInstance()));
        assertEquals(
            "[identifier]",
            TextSyntaxTree.formatNode(Identifier.newInstance()));
        assertEquals(
            "[identifier='foobar']",
            TextSyntaxTree.formatNode(Identifier.newInstance("foobar")));
        assertEquals(
            "[loop]",
            TextSyntaxTree.formatNode(Loop.newInstance()));
        assertEquals(
            "[option]",
            TextSyntaxTree.formatNode(Option.newInstance()));
        assertEquals(
            "[rule]",
            TextSyntaxTree.formatNode(Rule.newInstance()));
        assertEquals(
            "[rule='snafu']",
            TextSyntaxTree.formatNode(Rule.newInstance("snafu")));
        assertEquals(
            "[sequence]",
            TextSyntaxTree.formatNode(Sequence.newInstance()));
        assertEquals(
            "[syntax]",
            TextSyntaxTree.formatNode(Syntax.newInstance()));
        assertEquals(
            "[terminal]",
            TextSyntaxTree.formatNode(Terminal.newInstance()));
        assertEquals(
            "[terminal='foobar']",
            TextSyntaxTree.formatNode(Terminal.newInstance("foobar")));
        assertEquals(
            "[comment]",
            TextSyntaxTree.formatNode(Comment.newInstance()));
        assertEquals(
            "[comment='foobar']",
            TextSyntaxTree.formatNode(Comment.newInstance("foobar")));
        assertEquals(
            "[comment='foobar very very ver...']",
            TextSyntaxTree.formatNode(
                Comment.newInstance("foobar very very very long comment string")));
    }

}
