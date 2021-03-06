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
import java.io.IOException;
import java.io.StringReader;
import java.util.Arrays;
import java.util.List;
import static org.junit.Assert.*;
import org.junit.Ignore;
import org.junit.Test;
import org.mockito.Mockito;
import static org.mockito.Mockito.*;

/**
 * Unit test for Scanner.
 *
 * @author Sven Strittmatter <weltraumschaf@googlemail.com>
 */
public class EbnfScannerTest {

    static class Expectation {

        private final String value;
        private final TokenType type;
        private final int line;
        private final int col;

        public Expectation(final String value, final TokenType type, final int line, final int col) {
            this.value = value;
            this.type  = type;
            this.line  = line;
            this.col   = col;
        }

        public int getCol() {
            return col;
        }

        public int getLine() {
            return line;
        }

        public TokenType getType() {
            return type;
        }

        public String getValue() {
            return value;
        }
    }

    private void assertTokens(final Scanner scanner, final List<Expectation> expectations, final String msg)
            throws SyntaxException, IOException {
        int count = 0;

        while (scanner.hasNextToken()) {
            scanner.nextToken();
            final Token token = scanner.getCurrentToken();
            assertNotNull(token);
            final Expectation expectation = expectations.get(count);
            assertEquals(String.format("%s %d type: %s", msg, count, token.getValue()),
                         expectation.getType(), token.getType());
            assertEquals(String.format("%s %d value: %s", msg, count, token.getValue()),
                         expectation.getValue(), token.getValue());

            final Position position = token.getPosition();
            assertEquals("", position.getFile());
            assertEquals(String.format("%s %d line: %s", msg, count, token.getValue()),
                         expectation.getLine(), position.getLine());
            assertEquals(String.format("%s %d col: %s", msg, count, token.getValue()),
                         expectation.getCol(), position.getColumn());
            ++count;
        }

        scanner.nextToken();
        final Token lastToken = scanner.getCurrentToken();
        assertEquals(TokenType.EOF, lastToken.getType());

        assertEquals("Not enough tokens!", expectations.size(), count);

        final int[] backtracks = {1, 3, 20, 200000};
        int index = expectations.size() - 1;

        for (int i = 0; i < backtracks.length; ++i) {
            final int backtrack = backtracks[i];
            index -= backtrack;

            if (index < 0) {
                try {
                    scanner.backtrackToken(backtrack);
                    fail("Expected excpetion not thrown!");
                } catch (IllegalArgumentException ex) { // NOPMD
                    // Exception is expected here.
                }
            } else {
                scanner.backtrackToken(backtrack);
                final Token token = scanner.getCurrentToken();
                final Expectation expectation = expectations.get(index);
                assertEquals(String.format("%s %d type: %s", msg, count, token.getValue()),
                             expectation.getType(), token.getType());
                assertEquals(String.format("%s %d value: %s", msg, count, token.getValue()),
                             expectation.getValue(), token.getValue());
                final Position position = token.getPosition();
                assertEquals("", position.getFile());
                assertEquals(String.format("%s %d line: %s", msg, count, token.getValue()),
                             expectation.getLine(), position.getLine());
                assertEquals(String.format("%s %d col: %s", msg, count, token.getValue()),
                             expectation.getCol(), position.getColumn());
            }
        }

        scanner.close();
    }

    @Test public void nullableFile() {
        Scanner scanner = new EbnfScanner(null, "foo");
        assertEquals("foo", scanner.getFile());
        scanner = new EbnfScanner(null);
        assertNull(scanner.getFile());
    }

    @Test public void testNextOnemptySource() throws Exception {
        Scanner scanner = Factory.newScanner(new StringReader(""));
        assertNull(scanner.getCurrentToken());
        assertNull(scanner.getCurrentToken());
    }

    @Test public void testNextOnRulesWithRanges() throws Exception {
        assertTokens(getInstance().createScannerFromFixture("parser/rules_with_ranges.ebnf"), Arrays.asList(
                new Expectation("\"Rules with ranges.\"", TokenType.LITERAL, 1, 1),
                new Expectation("{", TokenType.L_BRACE, 1, 22),
                new Expectation("lower", TokenType.IDENTIFIER, 2, 5),
                new Expectation("=", TokenType.ASSIGN, 2, 15),
                new Expectation("\"a\"", TokenType.LITERAL, 2, 17),
                new Expectation("..", TokenType.RANGE, 2, 21),
                new Expectation("\"z\"", TokenType.LITERAL, 2, 24),
                new Expectation(";", TokenType.END_OF_RULE, 2, 28),
                new Expectation("upper", TokenType.IDENTIFIER, 3, 5),
                new Expectation("=", TokenType.ASSIGN, 3, 15),
                new Expectation("\"A\"", TokenType.LITERAL, 3, 17),
                new Expectation("..", TokenType.RANGE, 3, 21),
                new Expectation("\"Z\"", TokenType.LITERAL, 3, 24),
                new Expectation(";", TokenType.END_OF_RULE, 3, 28),
                new Expectation("number", TokenType.IDENTIFIER, 4, 5),
                new Expectation("=", TokenType.ASSIGN, 4, 15),
                new Expectation("\"0\"", TokenType.LITERAL, 4, 17),
                new Expectation("..", TokenType.RANGE, 4, 21),
                new Expectation("\"9\"", TokenType.LITERAL, 4, 24),
                new Expectation(";", TokenType.END_OF_RULE, 4, 28),
                new Expectation("alpha-num", TokenType.IDENTIFIER, 5, 5),
                new Expectation("=", TokenType.ASSIGN, 5, 15),
                new Expectation("\"a\"", TokenType.LITERAL, 5, 17),
                new Expectation("..", TokenType.RANGE, 5, 21),
                new Expectation("\"z\"", TokenType.LITERAL, 5, 24),
                new Expectation("|", TokenType.CHOICE, 5, 28),
                new Expectation("\"0\"", TokenType.LITERAL, 5, 30),
                new Expectation("..", TokenType.RANGE, 5, 34),
                new Expectation("\"9\"", TokenType.LITERAL, 5, 37),
                new Expectation(";", TokenType.END_OF_RULE, 5, 41),
                new Expectation("}", TokenType.R_BRACE, 6, 1),
                new Expectation("", TokenType.EOF, 6, 2)), "Rules with range.");
    }

    @Test public void testNextOnRulesWithComments() throws Exception {
        assertTokens(getInstance().createScannerFromFixture("parser/rules_with_comments.ebnf"), Arrays.asList(
            new Expectation("\"Rules with comments.\"", TokenType.LITERAL,    1, 1),
            new Expectation("{",       TokenType.L_BRACE,   1, 24),

            new Expectation("(* here are rules *)", TokenType.COMMENT, 2, 5),

            new Expectation("title",   TokenType.IDENTIFIER,  3, 5),
            new Expectation("=",       TokenType.ASSIGN,       3, 16),
            new Expectation("literal", TokenType.IDENTIFIER,  3, 18), // NOPMD
            new Expectation(".",       TokenType.END_OF_RULE, 3, 26),
            new Expectation("(* Comment * at the end of line *)",
                                        TokenType.COMMENT,    3, 28),

            new Expectation("comment", TokenType.IDENTIFIER,  4, 5), // NOPMD
            new Expectation("=",       TokenType.ASSIGN,       4, 16),
            new Expectation("literal", TokenType.IDENTIFIER,  4, 18),
            new Expectation(".",       TokenType.END_OF_RULE, 4, 26),

            new Expectation("(* This is a multi\n       line comment. *)", TokenType.COMMENT, 5, 5),

            new Expectation("comment",   TokenType.IDENTIFIER,  7, 5),
            new Expectation("(* foo *)", TokenType.COMMENT,     7, 13),
            new Expectation("=",         TokenType.ASSIGN,       7, 23),
            new Expectation("(* bar *)", TokenType.COMMENT,     7, 25),
            new Expectation("literal",   TokenType.IDENTIFIER,  7, 35),
            new Expectation("(* baz *)", TokenType.COMMENT,     7, 43),
            new Expectation(".",         TokenType.END_OF_RULE, 7, 53),

            new Expectation("}",       TokenType.R_BRACE, 8, 1),
            new Expectation("",      TokenType.EOF,     8, 2)
        ), "Rule with comment.");
    }

    @Test public void testNextOnRulesWithDifferentAssignemnts() throws Exception {
        assertTokens(getInstance().createScannerFromFixture("parser/rules_with_different_assignment_ops.ebnf"),
            Arrays.asList(
                new Expectation("\"Rules with different assignment operators.\"",
                                            TokenType.LITERAL,    1, 1),
                new Expectation("{",        TokenType.L_BRACE,   1, 46),

                new Expectation("comment1", TokenType.IDENTIFIER, 2, 5),
                new Expectation("=",        TokenType.ASSIGN,   2, 14),
                new Expectation("literal1", TokenType.IDENTIFIER, 2, 18),
                new Expectation(".",        TokenType.END_OF_RULE,   2, 27),

                new Expectation("comment2", TokenType.IDENTIFIER, 3, 5),
                new Expectation(":",        TokenType.ASSIGN,   3, 14),
                new Expectation("literal2", TokenType.IDENTIFIER, 3, 18),
                new Expectation(".",        TokenType.END_OF_RULE,   3, 27),

                new Expectation("comment3", TokenType.IDENTIFIER, 4, 5),
                new Expectation(":==",      TokenType.ASSIGN,   4, 14),
                new Expectation("literal3", TokenType.IDENTIFIER, 4, 18),
                new Expectation(".",        TokenType.END_OF_RULE,   4, 27),

                new Expectation("}",        TokenType.R_BRACE,   5, 1),
                new Expectation("",       TokenType.EOF,        5, 2)
            ),
        "Assignemnt operators.");
    }

    @Test public void testNextOnRulesWithLiterals() throws Exception {
        assertTokens(getInstance().createScannerFromFixture("parser/rules_with_literals.ebnf"),
            Arrays.asList(
                new Expectation("\"Rules with literal.\"", TokenType.LITERAL,    1, 1),
                new Expectation("{",                     TokenType.L_BRACE,   1, 23),

                new Expectation("literal",   TokenType.IDENTIFIER, 2, 5),
                new Expectation("=",         TokenType.ASSIGN,   2, 13),
                new Expectation("\"\'\"",      TokenType.LITERAL,    2, 15), // NOPMD
                new Expectation("character", TokenType.IDENTIFIER, 2, 19), // NOPMD
                new Expectation("{",         TokenType.L_BRACE,   2, 29),
                new Expectation("character", TokenType.IDENTIFIER, 2, 31),
                new Expectation("}",         TokenType.R_BRACE,   2, 41),
                new Expectation("\"\'\"",      TokenType.LITERAL,    2, 43),

                new Expectation("|",         TokenType.CHOICE,   3, 13),
                new Expectation("'\"'",      TokenType.LITERAL,    3, 15), // NOPMD
                new Expectation("character", TokenType.IDENTIFIER, 3, 19),
                new Expectation("{",         TokenType.L_BRACE,   3, 29),
                new Expectation("character", TokenType.IDENTIFIER, 3, 31),
                new Expectation("}",         TokenType.R_BRACE,   3, 41),
                new Expectation("'\"'",      TokenType.LITERAL,    3, 43),
                new Expectation(".",         TokenType.END_OF_RULE,   3, 47),

                new Expectation("}",       TokenType.R_BRACE,   4, 1),
                new Expectation("",        TokenType.EOF,        4, 2)
            ),
        "Rules with literal.");
    }

    @Test public void testNextOnTestgrammar_1() throws Exception {
        assertTokens(getInstance().createScannerFromFixture("parser/testgrammar_1.ebnf"), Arrays.asList(
            new Expectation("\"EBNF defined in itself.\"",   TokenType.LITERAL, 1, 1),
            new Expectation("{",          TokenType.L_BRACE,   1,  27),

            new Expectation("syntax",     TokenType.IDENTIFIER, 2,  3),
            new Expectation("=",          TokenType.ASSIGN,   2,  14),
            new Expectation("[",          TokenType.L_BRACK,   2,  16),
            new Expectation("title",      TokenType.IDENTIFIER, 2,  18),
            new Expectation("]",          TokenType.R_BRACK,   2,  24),
            new Expectation("\"{\"",        TokenType.LITERAL,    2,  26),
            new Expectation("{",          TokenType.L_BRACE,   2,  30),
            new Expectation("rule",       TokenType.IDENTIFIER, 2,  32),
            new Expectation("}",          TokenType.R_BRACE,   2,  37),
            new Expectation("\"}\"",        TokenType.LITERAL,    2,  39),
            new Expectation("[",          TokenType.L_BRACK,   2,  43),
            new Expectation("comment",    TokenType.IDENTIFIER, 2,  45),
            new Expectation("]",          TokenType.R_BRACK,   2,  53),
            new Expectation(".",          TokenType.END_OF_RULE,   2,  55),

            new Expectation("rule",       TokenType.IDENTIFIER, 3,  3),
            new Expectation("=",          TokenType.ASSIGN,   3,  14),
            new Expectation("identifier", TokenType.IDENTIFIER, 3,  16),
            new Expectation("(",          TokenType.L_PAREN,   3,  27),
            new Expectation("\"=\"",        TokenType.LITERAL,    3,  29),
            new Expectation("|",          TokenType.CHOICE,   3,  33),
            new Expectation("\":\"",        TokenType.LITERAL,    3,  35),
            new Expectation("|",          TokenType.CHOICE,   3,  39),
            new Expectation("\":==\"",      TokenType.LITERAL,    3,  41),
            new Expectation(")",          TokenType.R_PAREN,   3,  47),
            new Expectation("expression", TokenType.IDENTIFIER, 3,  49), // NOPMD
            new Expectation("(",          TokenType.L_PAREN,   3,  60),
            new Expectation("\".\"",        TokenType.LITERAL,    3,  62),
            new Expectation("|",          TokenType.CHOICE,   3,  66),
            new Expectation("\";\"",        TokenType.LITERAL,    3,  68),
            new Expectation(")",          TokenType.R_PAREN,   3,  72),
            new Expectation(".",          TokenType.END_OF_RULE,   3,  74),

            new Expectation("expression", TokenType.IDENTIFIER, 4,  3),
            new Expectation("=",          TokenType.ASSIGN,   4,  14),
            new Expectation("term",       TokenType.IDENTIFIER, 4,  16),
            new Expectation("{",          TokenType.L_BRACE,   4,  21),
            new Expectation("\"|\"",        TokenType.LITERAL,    4,  23),
            new Expectation("term",       TokenType.IDENTIFIER, 4,  27),
            new Expectation("}",          TokenType.R_BRACE,   4,  32),
            new Expectation(".",          TokenType.END_OF_RULE,   4,  34),

            new Expectation("term",       TokenType.IDENTIFIER, 5,  3),
            new Expectation("=",          TokenType.ASSIGN,   5,  14),
            new Expectation("factor",     TokenType.IDENTIFIER, 5,  16),
            new Expectation("{",          TokenType.L_BRACE,   5,  23),
            new Expectation("factor",     TokenType.IDENTIFIER, 5,  25),
            new Expectation("}",          TokenType.R_BRACE,   5,  32),
            new Expectation(".",          TokenType.END_OF_RULE,   5,  34),

            new Expectation("factor",     TokenType.IDENTIFIER, 6,  3),
            new Expectation("=",          TokenType.ASSIGN,   6,  14),
            new Expectation("identifier", TokenType.IDENTIFIER, 6,  16),
            new Expectation("|",          TokenType.CHOICE,   7,  14),
            new Expectation("literal",    TokenType.IDENTIFIER, 7,  16),
            new Expectation("|",          TokenType.CHOICE,   8,  14),
            new Expectation("range",      TokenType.IDENTIFIER, 8,  16),
            new Expectation("|",          TokenType.CHOICE,   9,  14),
            new Expectation("\"[\"",        TokenType.LITERAL,    9,  16),
            new Expectation("expression", TokenType.IDENTIFIER, 9,  20),
            new Expectation("\"]\"",        TokenType.LITERAL,    9,  31),
            new Expectation("|",          TokenType.CHOICE,   10, 14),
            new Expectation("\"(\"",        TokenType.LITERAL,    10, 16),
            new Expectation("expression", TokenType.IDENTIFIER, 10, 20),
            new Expectation("\")\"",        TokenType.LITERAL,    10, 31),
            new Expectation("|",          TokenType.CHOICE,   11, 14),
            new Expectation("\"{\"",        TokenType.LITERAL,    11, 16),
            new Expectation("expression", TokenType.IDENTIFIER, 11, 20),
            new Expectation("\"}\"",        TokenType.LITERAL,    11, 31),
            new Expectation(".",          TokenType.END_OF_RULE,   11, 35),

            new Expectation("identifier", TokenType.IDENTIFIER, 12, 3),
            new Expectation("=",          TokenType.ASSIGN,   12, 14),
            new Expectation("character",  TokenType.IDENTIFIER, 12, 16),
            new Expectation("{",          TokenType.L_BRACE,   12, 26),
            new Expectation("character",  TokenType.IDENTIFIER, 12, 28),
            new Expectation("}",          TokenType.R_BRACE,   12, 38),
            new Expectation(".",          TokenType.END_OF_RULE,   12, 40),

            new Expectation("range",      TokenType.IDENTIFIER, 13, 3),
            new Expectation("=",          TokenType.ASSIGN,   13, 14),
            new Expectation("character",  TokenType.IDENTIFIER, 13, 16),
            new Expectation("\"..\"",       TokenType.LITERAL,    13, 26),
            new Expectation("character",  TokenType.IDENTIFIER, 13, 31),
            new Expectation(".",          TokenType.END_OF_RULE,   13, 41),

            new Expectation("title",      TokenType.IDENTIFIER, 14, 3),
            new Expectation("=",          TokenType.ASSIGN,   14, 14),
            new Expectation("literal",    TokenType.IDENTIFIER, 14, 16),
            new Expectation(".",          TokenType.END_OF_RULE,   14, 24),

            new Expectation("comment",    TokenType.IDENTIFIER, 15, 3),
            new Expectation("=",          TokenType.ASSIGN,   15, 14),
            new Expectation("literal",    TokenType.IDENTIFIER, 15, 16),
            new Expectation(".",          TokenType.END_OF_RULE,   15, 24),

            new Expectation("literal",    TokenType.IDENTIFIER, 16, 3),
            new Expectation("=",          TokenType.ASSIGN,   16, 14),
            new Expectation("\"\'\"",       TokenType.LITERAL,    16, 16),
            new Expectation("character",  TokenType.IDENTIFIER, 16, 20),
            new Expectation("{",          TokenType.L_BRACE,   16, 30),
            new Expectation("character",  TokenType.IDENTIFIER, 16, 32),
            new Expectation("}",          TokenType.R_BRACE,   16, 42),
            new Expectation("\"\'\"",       TokenType.LITERAL,    16, 44),
            new Expectation("|",          TokenType.CHOICE,   17, 14),
            new Expectation("'\"'",       TokenType.LITERAL,    17, 16),
            new Expectation("character",  TokenType.IDENTIFIER, 17, 20),
            new Expectation("{",          TokenType.L_BRACE,   17, 30),
            new Expectation("character",  TokenType.IDENTIFIER, 17, 32),
            new Expectation("}",          TokenType.R_BRACE,   17, 42),
            new Expectation("'\"'",       TokenType.LITERAL,    17, 44),
            new Expectation(".",          TokenType.END_OF_RULE,   17, 48),

            new Expectation("character",  TokenType.IDENTIFIER, 18, 3),
            new Expectation("=",          TokenType.ASSIGN,   18, 14),
            new Expectation("\"a\"",        TokenType.LITERAL,    18, 16),
            new Expectation("..",         TokenType.RANGE,   18, 20),
            new Expectation("\"z\"",        TokenType.LITERAL,    18, 23),
            new Expectation("|",          TokenType.CHOICE,   19, 14),
            new Expectation("\"A\"",        TokenType.LITERAL,    19, 16),
            new Expectation("..",         TokenType.RANGE,   19, 20),
            new Expectation("\"Z\"",        TokenType.LITERAL,    19, 23),
            new Expectation("|",          TokenType.CHOICE,   20, 14),
            new Expectation("\"0\"",        TokenType.LITERAL,    20, 16),
            new Expectation("..",         TokenType.RANGE,   20, 20),
            new Expectation("\"9\"",        TokenType.LITERAL,    20, 23),
            new Expectation(".",          TokenType.END_OF_RULE,   20, 27),
            new Expectation("}",          TokenType.R_BRACE,   21, 1),
            new Expectation("",           TokenType.EOF,        21, 2)
        ), "testgrammar_1.ebnf");
    }

    @Test public void testThrowSyntaxExceptionOnBadAssignmentOperator() throws Exception {
        final Scanner scanner = Factory.newScanner(new StringReader("\ncomment := literal .\n"));

        try {
            while (scanner.hasNextToken()) {
                scanner.nextToken();
            }

            fail("Expected exception not thrown!");
        } catch (SyntaxException ex) {
            assertEquals("Expecting '=' but seen ' '", ex.getMessage());
            final Position pos = ex.getPosition();
            assertEquals(2, pos.getLine());
            assertEquals(11, pos.getColumn());
        }

        scanner.close();
    }

    @Ignore("Throw excpetion on invalid characters")
    @Test public void testRaiseErrorOnInvalidCharacter() { //NOPMD

    }

    @Test public void testRaiseError() {
        final Position position = new Position(5, 3);
        final EbnfScanner scanner = mock(EbnfScanner.class, Mockito.CALLS_REAL_METHODS);
        when(scanner.createPosition()).thenReturn(position);
        final String msg = "an error";

        try {
            scanner.raiseError(msg);
            fail("Expected exception not thrown!");
        } catch (SyntaxException e) {
            verify(scanner, times(1)).createPosition();
            assertSame(e.getPosition(), position);
            assertEquals(String.format("Syntax error: %s at %s!", msg, position), e.
                    toString());
        }
    }

    @Test public void testPeekToken() throws SyntaxException, IOException {
        final Scanner scanner = Factory.newScanner(new StringReader("comment :== literal ."));
        Token token;

        token = scanner.peekToken();
        assertEquals("comment", token.getValue());

        token = scanner.getCurrentToken();
        assertEquals("comment", token.getValue());

        token = scanner.peekToken();
        assertEquals(":==", token.getValue());

        scanner.nextToken();
        token = scanner.getCurrentToken();
        assertEquals(":==", token.getValue());

        token = scanner.peekToken();
        assertEquals("literal", token.getValue());

        scanner.nextToken();
        token = scanner.getCurrentToken();
        assertEquals("literal", token.getValue());

        scanner.nextToken();
        token = scanner.getCurrentToken();
        assertEquals(".", token.getValue());

        scanner.nextToken();
        token = scanner.getCurrentToken();
        assertEquals(TokenType.EOF, token.getType());
    }

    @Test public void peekCharacter() throws IOException {
        final Scanner scanner = Factory.newScanner(new StringReader("comment :== literal ."));
        scanner.nextCharacter();
        assertEquals('c', scanner.getCurrentCharacter());
        assertEquals('o', scanner.peekCharacter());
        assertEquals('c', scanner.getCurrentCharacter());

        scanner.nextCharacter();
        assertEquals('o', scanner.getCurrentCharacter());
        scanner.nextCharacter();
        assertEquals('m', scanner.getCurrentCharacter());
        scanner.nextCharacter();
        assertEquals('m', scanner.getCurrentCharacter());

        assertEquals('e', scanner.peekCharacter());
        assertEquals('m', scanner.getCurrentCharacter());
        assertEquals('e', scanner.peekCharacter());
        assertEquals('m', scanner.getCurrentCharacter());
    }
}
