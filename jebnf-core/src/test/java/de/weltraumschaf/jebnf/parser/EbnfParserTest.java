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

import static de.weltraumschaf.jebnf.TestHelper.getInstance;
import de.weltraumschaf.jebnf.ast.Notification;
import static de.weltraumschaf.jebnf.ast.builder.SyntaxBuilder.syntax;
import de.weltraumschaf.jebnf.ast.nodes.SyntaxNode;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URISyntaxException;
import java.util.Arrays;
import static org.junit.Assert.*;
import org.junit.Ignore;
import org.junit.Test;

/**
 * Unit test for Parser.
 *
 * @author Sven Strittmatter <weltraumschaf@googlemail.com>
 */
public class EbnfParserTest {

    private void assertEquivalentSyntax(final SyntaxNode expected, final SyntaxNode actual) {
        Notification notification = new Notification();
        expected.probeEquivalence(actual, notification);
        assertNotificationOk(notification);
        notification = new Notification();
        actual.probeEquivalence(expected, notification);
        assertNotificationOk(notification);
    }

    private void assertNotificationOk(final Notification notification) {
        assertTrue(notification.report(), notification.isOk());
    }

    @Test public void testAssertToken() {
        final EbnfParser parser = (EbnfParser) Factory.newParserFromSource("");
        final Token token1  = new Token(TokenType.ASSIGN, ":", new Position(0, 0));
        final Token token2  = new Token(TokenType.IDENTIFIER, "foobar", new Position(0, 0));

        assertTrue(parser.assertToken(token1, TokenType.ASSIGN, ":"));
        assertFalse(parser.assertToken(token1, TokenType.IDENTIFIER, ":"));
        assertFalse(parser.assertToken(token1, TokenType.ASSIGN, ","));
        assertFalse(parser.assertToken(token1, TokenType.IDENTIFIER, ","));

        assertTrue(parser.assertToken(token2, TokenType.IDENTIFIER, "foobar"));
        assertFalse(parser.assertToken(token2, TokenType.ASSIGN, "foobar"));
        assertFalse(parser.assertToken(token2, TokenType.IDENTIFIER, "snafu"));
        assertFalse(parser.assertToken(token2, TokenType.ASSIGN, "snafu"));
    }

    @Test public void testAssertTokens() {
        final EbnfParser parser = (EbnfParser) Factory.newParserFromSource("");
        final Token token1  = new Token(TokenType.ASSIGN, ":", new Position(0, 0));
        final Token token2  = new Token(TokenType.ASSIGN, "=", new Position(0, 0));

        assertTrue(parser.assertTokens(token1, TokenType.ASSIGN, Arrays.asList("=", ":", ":==")));
        assertFalse(parser.assertTokens(token1, TokenType.ASSIGN, Arrays.asList("+", "-", "*")));
        assertTrue(parser.assertTokens(token2, TokenType.ASSIGN, Arrays.asList("=", ":", ":==")));
        assertFalse(parser.assertTokens(token2, TokenType.ASSIGN, Arrays.asList("+", "-", "*")));
    }

    @Ignore("TODO: Implement range parsing.")
    @Test public void testParseRanges() throws FileNotFoundException, URISyntaxException {
//        Parser p = new Parser(new Scanner(loadFixture("rules_with_ranges.ebnf")));
//        assertXmlStringEqualsXmlFile(
//            EBNF_TESTS_FIXTURS . DIRECTORY_SEPARATOR . "rules_with_ranges.xml",
//            p.parse().saveXML(),
//            "rules with ranges"
//        );
    }

    @Test public void testParse() throws SyntaxException, IOException, URISyntaxException { //NOPMD
        SyntaxNode ast;

        Parser parser = getInstance().createParserFromFixture("parser/rules_with_different_assignment_ops.ebnf");
        ast = syntax("Rules with different assignment operators.")
            .rule("comment1")
                .identifier("literal1")
            .end()
            .rule("comment2")
                .identifier("literal2")
            .end()
            .rule("comment3")
                .identifier("literal3")
            .end()
        .build();
        assertEquivalentSyntax(ast, parser.parse());

        parser = getInstance().createParserFromFixture("parser/rules_with_literals.ebnf");
        ast = syntax("Rules with literal.")
            .rule("literal") // NOPMD
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
        assertEquivalentSyntax(ast, parser.parse());

        parser = getInstance().createParserFromFixture("parser/rules_with_comments.ebnf");
        ast = syntax("Rules with comments.")
            .comment("(* here are rules *)")
            .rule("title")
                .identifier("literal")
            .end()
            .comment("(* Comment * at the end of line *)")
            .rule("comment") // NOPMD
                .identifier("literal")
            .end()
            .comment("(* This is a multi\n       line comment. *)")
            .rule("comment")
                .comment("(* foo *)")
                .sequence()
                    .comment("(* bar *)")
                    .identifier("literal")
                    .comment("(* baz *)")
                .end()
            .end()
        .build();
        assertEquivalentSyntax(ast, parser.parse());

        parser = getInstance().createParserFromFixture("parser/testgrammar_1.old.ebnf");
        ast = syntax("EBNF defined in itself.")
            .rule("syntax")
                .sequence()
                    .option()
                        .identifier("title")
                    .end()
                    .terminal("{")
                    .loop()
                        .identifier("rule")
                    .end()
                    .terminal("}")
                    .option()
                        .identifier("comment")
                    .end()
                .end()
            .end()
            .rule("rule")
                .sequence()
                    .identifier("identifier")
                    .choice()
                        .terminal("=")
                        .terminal(":")
                        .terminal(":==")
                    .end()
                    .identifier("expression") // NOPMD
                    .choice()
                        .terminal(".")
                        .terminal(";")
                    .end()
                .end()
            .end()
            .rule("expression")
                .sequence()
                    .identifier("term")
                    .loop()
                        .sequence()
                            .terminal("|")
                            .identifier("term")
                        .end()
                    .end()
                .end()
            .end()
            .rule("term")
                .sequence()
                    .identifier("factor")
                    .loop()
                        .identifier("factor")
                    .end()
                .end()
            .end()
            .rule("factor")
                .choice()
                    .identifier("identifier")
                    .identifier("literal")
                    .identifier("range")
                    .sequence()
                        .terminal("[")
                        .identifier("expression")
                        .terminal("]")
                    .end()
                    .sequence()
                        .terminal("(")
                        .identifier("expression")
                        .terminal(")")
                    .end()
                    .sequence()
                        .terminal("{")
                        .identifier("expression")
                        .terminal("}")
                    .end()
                .end()
            .end()
            .rule("identifier")
                .sequence()
                    .identifier("character")
                    .loop()
                        .identifier("character")
                    .end()
                .end()
            .end()
            .rule("range")
                .sequence()
                    .identifier("character")
                    .terminal("..")
                    .identifier("character")
                .end()
            .end()
            .rule("title")
                .identifier("literal")
            .end()
            .rule("comment")
                .identifier("literal")
            .end()
            .rule("literal")
                .choice()
                    .sequence()
                        .terminal("'")
                        .identifier("character")
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
            .rule("character")
                .choice()
                    .terminal("a")
                    .terminal("b")
                    .terminal("c")
                    .terminal("d")
                    .terminal("e")
                    .terminal("f")
                    .terminal("g")
                    .terminal("h")
                    .terminal("i")
                    .terminal("j")
                    .terminal("k")
                    .terminal("l")
                    .terminal("m")
                    .terminal("n")
                    .terminal("o")
                    .terminal("p")
                    .terminal("q")
                    .terminal("r")
                    .terminal("s")
                    .terminal("t")
                    .terminal("u")
                    .terminal("v")
                    .terminal("w")
                    .terminal("x")
                    .terminal("y")
                    .terminal("z")
                    .terminal("A")
                    .terminal("B")
                    .terminal("C")
                    .terminal("D")
                    .terminal("E")
                    .terminal("F")
                    .terminal("G")
                    .terminal("H")
                    .terminal("I")
                    .terminal("J")
                    .terminal("K")
                    .terminal("L")
                    .terminal("M")
                    .terminal("N")
                    .terminal("O")
                    .terminal("P")
                    .terminal("Q")
                    .terminal("R")
                    .terminal("S")
                    .terminal("T")
                    .terminal("U")
                    .terminal("V")
                    .terminal("W")
                    .terminal("X")
                    .terminal("Y")
                    .terminal("Z")
                .end()
            .end()
        .build();
        assertEquivalentSyntax(ast, parser.parse());
    }

    @Ignore("TODO: Implement test with errornous syntax fixtures.")
    @Test public void testParseErrors() { //NOPMD

    }
}
