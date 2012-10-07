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
package de.weltraumschaf.jebnf.ast;

import static de.weltraumschaf.jebnf.ast.builder.SyntaxBuilder.syntax;
import de.weltraumschaf.jebnf.ast.nodes.RuleNode;
import de.weltraumschaf.jebnf.ast.nodes.SyntaxNode;
import java.util.List;
import static org.junit.Assert.*;
import org.junit.Test;

/**
 * Tests the integration of all AST nodes in a whole tree.
 *
 * @author Sven Strittmatter <weltraumschaf@googlemail.com>
 */
public class IntegrationTest {

    @Test public void testIntegration() {
        final SyntaxNode syntax = SyntaxNode.newInstance("foo", "bar"); // NOPMD
        List<Node> children = syntax.getChildren();
        assertEquals(0, children.size());

        final RuleNode rule1 = RuleNode.newInstance(syntax);
        assertSame(syntax, rule1.getParent());
        rule1.setAttribute(RuleNode.Attributes.NAME, "first");
        syntax.addChild(rule1);

        children = syntax.getChildren();
        assertEquals(1, children.size());
        assertSame(rule1, children.get(0));

        final RuleNode rule2 = RuleNode.newInstance(syntax);
        rule2.setAttribute(RuleNode.Attributes.NAME, "second");
        assertSame(syntax, rule1.getParent());
        syntax.addChild(rule2);

        children = syntax.getChildren();
        assertEquals(2, children.size());
        assertSame(rule1, children.get(0));
        assertSame(rule2, children.get(1));
    }

    @Test public void testProbeEquivalenceSyntax() {
        final SyntaxNode syntax1 = SyntaxNode.newInstance("foo", "bar");
        syntax1.setAttribute(SyntaxNode.Attributes.TITLE, "foo");
        syntax1.setAttribute(SyntaxNode.Attributes.META, "bar");
        final SyntaxNode syntax2 = SyntaxNode.newInstance("foo", "bar");
        syntax2.setAttribute(SyntaxNode.Attributes.TITLE, "foo");
        syntax2.setAttribute(SyntaxNode.Attributes.META, "bar");
        final SyntaxNode syntax3 = SyntaxNode.newInstance("bla", "blub");
        syntax3.setAttribute(SyntaxNode.Attributes.TITLE, "bla");
        syntax3.setAttribute(SyntaxNode.Attributes.META, "blub");

        Notification notification = new Notification();
        syntax1.probeEquivalence(syntax1, notification);
        assertTrue(notification.report(), notification.isOk());
        assertEquals("", notification.report());
        notification = new Notification();
        syntax1.probeEquivalence(syntax2, notification);
        assertTrue(notification.report(), notification.isOk());
        assertEquals("", notification.report());

        notification = new Notification();
        syntax2.probeEquivalence(syntax2, notification);
        assertTrue(notification.report(), notification.isOk());
        assertEquals("", notification.report());
        notification = new Notification();
        syntax2.probeEquivalence(syntax1, notification);
        assertTrue(notification.report(), notification.isOk());
        assertEquals("", notification.report());

        notification = new Notification();
        syntax3.probeEquivalence(syntax3, notification);
        assertTrue(notification.report(), notification.isOk());
        assertEquals("", notification.report());

        StringBuilder errors = new StringBuilder();
        errors.append("Titles of syntx differs: 'foo' != 'bla'!\n");
        errors.append("Meta of syntx differs: 'bar' != 'blub'!");
        notification = new Notification();
        syntax1.probeEquivalence(syntax3, notification);
        assertFalse(notification.isOk());
        assertEquals(errors.toString(), notification.report());
        notification = new Notification();
        syntax2.probeEquivalence(syntax3, notification);
        assertFalse(notification.isOk());
        assertEquals(errors.toString(), notification.report());

        errors = new StringBuilder();
        errors.append("Titles of syntx differs: 'bla' != 'foo'!\n");
        errors.append("Meta of syntx differs: 'blub' != 'bar'!");
        notification = new Notification();
        syntax3.probeEquivalence(syntax1, notification);
        assertFalse(notification.isOk());
        assertEquals(errors.toString(), notification.report());
        notification = new Notification();
        syntax3.probeEquivalence(syntax2, notification);
        assertFalse(notification.isOk());
        assertEquals(errors.toString(), notification.report());

        errors = new StringBuilder();
        errors.append("Probed node types mismatch: 'class de.weltraumschaf.jebnf.ast.nodes.SyntaxNode'"
                    + " != 'class de.weltraumschaf.jebnf.ast.nodes.RuleNode'!");
        final SyntaxNode stub = SyntaxNode.newInstance();
        final Node mock = RuleNode.newInstance(stub);
        notification = new Notification();
        stub.probeEquivalence(mock, notification);
        assertFalse(notification.isOk());
        assertEquals(errors.toString(), notification.report());
    }

    @Test public void testProbeEquivalenceSyntaxWithRules() {
        final SyntaxNode syntax1 = SyntaxNode.newInstance("foo", "bar");
        final SyntaxNode syntax2 = SyntaxNode.newInstance("foo", "bar");
        final SyntaxNode syntax3 = SyntaxNode.newInstance("foo", "bar");
        final RuleNode rule1 = RuleNode.newInstance();
        rule1.setAttribute(RuleNode.Attributes.NAME, "rule1");
        syntax1.addChild(rule1);
        syntax2.addChild(rule1);

        Notification notification = new Notification();
        syntax1.probeEquivalence(syntax2, notification);
        assertTrue(notification.report(), notification.isOk());
        assertEquals("", notification.report());

        notification = new Notification();
        syntax2.probeEquivalence(syntax1, notification);
        assertTrue(notification.report(), notification.isOk());
        assertEquals("", notification.report());

        final RuleNode rule2 = RuleNode.newInstance();
        rule2.setAttribute(RuleNode.Attributes.NAME, "rule2");
        syntax1.addChild(rule2);
        StringBuilder error = new StringBuilder();
        error.append("Node syntax has different child count than other: 2 != 1!\n");
        error.append("Other node has not the expected subnode!");
        notification = new Notification();
        syntax1.probeEquivalence(syntax2, notification);
        assertFalse(notification.isOk());
        assertEquals(error.toString(), notification.report());

        error = new StringBuilder();
        error.append("Node syntax has different child count than other: 1 != 2!");
        notification = new Notification();
        syntax2.probeEquivalence(syntax1, notification);
        assertFalse(notification.isOk());
        assertEquals(error.toString(), notification.report());

        syntax2.addChild(rule2);
        notification = new Notification();
        syntax1.probeEquivalence(syntax2, notification);
        assertTrue(notification.report(), notification.isOk());
        assertEquals("", notification.report());

        notification = new Notification();
        syntax2.probeEquivalence(syntax1, notification);
        assertTrue(notification.report(), notification.isOk());
        assertEquals("", notification.report());

        final RuleNode rule3 = RuleNode.newInstance();
        rule3.setAttribute(RuleNode.Attributes.NAME, "rule3");
        syntax3.addChild(rule1);
        syntax3.addChild(rule3);
        notification = new Notification();
        syntax1.probeEquivalence(syntax3, notification);
        assertFalse(notification.isOk());
        assertEquals("Names of rule differs: 'rule2' != 'rule3'!", notification.report());
    }

    @Test public void testProbeEquivalenceSyntaxWithRulesAndSubnodes() { //NOPMD
        SyntaxNode syntax1;
        SyntaxNode syntax2;

        syntax1 = syntax("foo", "bar")
            .rule("syntax") // NOPMD
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
        .build();
        syntax2 = syntax("foo", "bar")
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
        .build();

        Notification notification = new Notification();
        syntax1.probeEquivalence(syntax2, notification);
        assertTrue(notification.report(), notification.isOk());
        assertEquals("", notification.report());

        notification = new Notification();
        syntax2.probeEquivalence(syntax1, notification);
        assertTrue(notification.report(), notification.isOk());
        assertEquals("", notification.report());

        syntax1 = syntax("foo", "bar")
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
        .build();

        syntax2 = syntax("snafu", "bar")
            .rule("syntax")
                .sequence()
                    .option()
                        .identifier("bla")
                    .end()
                    .terminal("{")
                    .loop()
                        .identifier("snafu")
                    .end()
                    .terminal("}")
                    .option()
                        .identifier("blub")
                    .end()
                .end()
            .end()
        .build();

        notification = new Notification();
        syntax1.probeEquivalence(syntax2, notification);
        assertFalse(notification.isOk());
        assertEquals("Titles of syntx differs: 'foo' != 'snafu'!\n"
                   + "Identifier value mismatch: 'title' != 'bla'!\n"
                   + "Identifier value mismatch: 'rule' != 'snafu'!\n"
                   + "Identifier value mismatch: 'comment' != 'blub'!",
            notification.report()
        );

        notification = new Notification();
        syntax2.probeEquivalence(syntax1, notification);
        assertFalse(notification.isOk());
        assertEquals("Titles of syntx differs: 'snafu' != 'foo'!\n"
                   + "Identifier value mismatch: 'bla' != 'title'!\n"
                   + "Identifier value mismatch: 'snafu' != 'rule'!\n"
                   + "Identifier value mismatch: 'blub' != 'comment'!",
            notification.report()
        );

    }

}
