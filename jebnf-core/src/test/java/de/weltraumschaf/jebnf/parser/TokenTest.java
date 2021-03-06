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

import com.google.common.collect.Lists;
import java.util.Arrays;
import java.util.List;
import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;
import org.junit.Test;

/**
 * Unit test for Token.
 *
 * @author Sven Strittmatter <weltraumschaf@googlemail.com>
 */
public class TokenTest {

    private static final String[] CHAR_SET_1 = {"a", "b", "c"};
    private static final String[] CHAR_SET_2 = {"x", "y", "z"};

    @Test public void testGetTypeAsString() {
        final Position pos = new Position(5, 10);
        Token token;
        token = new Token(TokenType.IDENTIFIER, "", pos);
        assertEquals("IDENTIFIER", token.getType().toString());
        token = new Token(TokenType.COMMENT, "", pos);
        assertEquals("COMMENT", token.getType().toString());
        token = new Token(TokenType.LITERAL, "", pos);
        assertEquals("LITERAL", token.getType().toString());
        token = new Token(TokenType.EOF, "", pos);
        assertEquals("EOF", token.getType().toString());
        token = new Token(TokenType.L_BRACK, "", pos);
        assertEquals("L_BRACK", token.getType().toString());
    }

    @Test public void testToString() {
        Position pos = new Position(5, 10);
        Token token;
        token = new Token(TokenType.IDENTIFIER, "ident", pos);
        assertEquals("<'ident', IDENTIFIER, (5, 10)>", token.toString());

        pos = new Position(5, 10, "/foo/bar.ebnf");
        token = new Token(TokenType.IDENTIFIER, "ident", pos);
        assertEquals("<'ident', IDENTIFIER, /foo/bar.ebnf (5, 10)>", token.toString());

        pos = new Position(5, 10, "/foo/bar.ebnf");
        token = new Token(TokenType.IDENTIFIER, "", pos);
        assertEquals("<IDENTIFIER, /foo/bar.ebnf (5, 10)>", token.toString());

        pos = new Position(5, 10, "/foo/bar.ebnf");
        token = new Token(TokenType.IDENTIFIER, "a-very-very-very-very-long-identifier", pos);
        assertEquals("<'a-very-very-ver...', IDENTIFIER, /foo/bar.ebnf (5, 10)>", token.toString());
    }

    @Test public void testIsType() {
        final Position pos = new Position(5, 10);
        Token token;

        token = new Token(TokenType.L_BRACK, "", pos);
        assertTrue(token.isType(TokenType.L_BRACK));
        assertFalse(token.isType(TokenType.IDENTIFIER));
        assertFalse(token.isType(TokenType.COMMENT));
        assertFalse(token.isType(TokenType.LITERAL));
        assertFalse(token.isType(TokenType.EOF));

        token = new Token(TokenType.IDENTIFIER, "", pos);
        assertTrue(token.isType(TokenType.IDENTIFIER));
        assertFalse(token.isType(TokenType.L_BRACK));
        assertFalse(token.isType(TokenType.COMMENT));
        assertFalse(token.isType(TokenType.LITERAL));
        assertFalse(token.isType(TokenType.EOF));

        token = new Token(TokenType.COMMENT, "", pos);
        assertTrue(token.isType(TokenType.COMMENT));
        assertFalse(token.isType(TokenType.IDENTIFIER));
        assertFalse(token.isType(TokenType.L_BRACK));
        assertFalse(token.isType(TokenType.LITERAL));
        assertFalse(token.isType(TokenType.EOF));

        token = new Token(TokenType.LITERAL, "", pos);
        assertTrue(token.isType(TokenType.LITERAL));
        assertFalse(token.isType(TokenType.IDENTIFIER));
        assertFalse(token.isType(TokenType.COMMENT));
        assertFalse(token.isType(TokenType.L_BRACK));
        assertFalse(token.isType(TokenType.EOF));

        token = new Token(TokenType.EOF, "", pos);
        assertTrue(token.isType(TokenType.EOF));
        assertFalse(token.isType(TokenType.IDENTIFIER));
        assertFalse(token.isType(TokenType.COMMENT));
        assertFalse(token.isType(TokenType.LITERAL));
        assertFalse(token.isType(TokenType.L_BRACK));
    }

    @Test public void testIsEqual() {
        final Position pos = new Position(5, 10);
        final Token token = new Token(TokenType.LITERAL, "foo", pos);
        assertTrue(token.isEqual("foo"));
        assertFalse(token.isEqual("bar"));

        assertFalse(token.isNotEqual("foo"));
        assertTrue(token.isNotEqual("bar"));
    }

    @Test public void testIsEquals() {
        final List<Token> tokens = Arrays.asList(
            new Token(TokenType.IDENTIFIER, "a", new Position(0, 0)),
            new Token(TokenType.IDENTIFIER, "b", new Position(0, 0)),
            new Token(TokenType.IDENTIFIER, "c", new Position(0, 0))
        );

        for (Token token : tokens) {
            assertTrue(token.isEquals(CHAR_SET_1));
            assertFalse(token.isEquals(CHAR_SET_2));
            assertFalse(token.isNotEquals(CHAR_SET_1));
            assertTrue(token.isNotEquals(CHAR_SET_2));
        }
    }

    @Test  public void testUnquoteString() {
        final Token token = new Token(TokenType.COMMENT,
                                      "\"a \"test\" string\"",
                                      new Position(0, 0));
        assertEquals("\"a \"test\" string\"", token.getValue());
        assertEquals("a \"test\" string", token.getValue(true));
    }

    @Test public void testGetPostion() {
        final Token token = new Token(TokenType.IDENTIFIER, "abc", new Position(1, 5));
        final Position end = token.getPosition(true);
        assertEquals(1, end.getLine());
        assertEquals(8, end.getColumn());
    }

    @Test public void testIsOperator() {
        List<TokenType> types;

        types = Arrays.asList(
            TokenType.ASSIGN, TokenType.CHOICE, TokenType.END_OF_RULE, TokenType.RANGE,
            TokenType.L_BRACE, TokenType.L_BRACK, TokenType.L_PAREN,
            TokenType.R_BRACE, TokenType.R_BRACK, TokenType.R_PAREN
        );

        final Position pos = new Position(0, 0);
        for (TokenType type : types) {
            final Token token = new Token(type, "", pos); // NOPMD
            assertTrue(token.isOperator());
        }

        types = Arrays.asList(
            TokenType.COMMENT, TokenType.EOF, TokenType.IDENTIFIER, TokenType.LITERAL
        );

        for (TokenType type : types) {
            Token token = new Token(type, "", pos); // NOPMD
            assertFalse(token.isOperator());
        }
    }

    @Test public void isOfType() {
        Token token = new Token(TokenType.ASSIGN, null, null);
        assertThat(token.isOfType(Lists.newArrayList(TokenType.EOF)), is(false));
        assertThat(token.isOfType(Lists.newArrayList(TokenType.EOF, TokenType.COMMENT)), is(false));
        assertThat(token.isOfType(Lists.newArrayList(TokenType.EOF, TokenType.COMMENT, TokenType.ASSIGN)), is(true));

        token = new Token(TokenType.IDENTIFIER, null, null);
        assertThat(token.isOfType(Lists.newArrayList(TokenType.EOF)), is(false));
        assertThat(token.isOfType(Lists.newArrayList(TokenType.EOF, TokenType.IDENTIFIER)), is(true));
        assertThat(token.isOfType(Lists.newArrayList(TokenType.EOF, TokenType.IDENTIFIER, TokenType.L_BRACK)), is(true));
    }

}
