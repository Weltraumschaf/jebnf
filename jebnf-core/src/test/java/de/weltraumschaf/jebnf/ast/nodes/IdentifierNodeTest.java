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
package de.weltraumschaf.jebnf.ast.nodes;

import de.weltraumschaf.jebnf.ast.NodeType;
import de.weltraumschaf.jebnf.ast.Notification;
import static org.junit.Assert.*;
import org.junit.Test;

/**
 * Unit test for IdentifierNode.
 *
 * @author Sven Strittmatter <weltraumschaf@googlemail.com>
 */
public class IdentifierNodeTest {

    @Test public void testProbeEquivalence() {
        Notification notifiaction;

        final IdentifierNode ident1 = IdentifierNode.newInstance();
        ident1.setAttribute(IdentifierNode.Attributes.VALUE, "a");
        notifiaction = new Notification();
        ident1.probeEquivalence(ident1, notifiaction);
        assertTrue(notifiaction.isOk());

        final IdentifierNode ident2 = IdentifierNode.newInstance();
        ident2.setAttribute(IdentifierNode.Attributes.VALUE, "b");
        notifiaction = new Notification();
        ident2.probeEquivalence(ident2, notifiaction);
        assertTrue(notifiaction.isOk());

        ident1.probeEquivalence(TerminalNode.newInstance(), notifiaction);
        assertFalse(notifiaction.isOk());
        assertEquals("Probed node types mismatch: "
            + "'class de.weltraumschaf.jebnf.ast.nodes.IdentifierNode' != "
            + "'class de.weltraumschaf.jebnf.ast.nodes.TerminalNode'!",
            notifiaction.report()
        );

        notifiaction = new Notification();
        ident1.probeEquivalence(ident2, notifiaction);
        assertFalse(notifiaction.isOk());
        assertEquals("Identifier value mismatch: 'a' != 'b'!", notifiaction.report());

        notifiaction = new Notification();
        ident2.probeEquivalence(ident1, notifiaction);
        assertFalse(notifiaction.isOk());
        assertEquals("Identifier value mismatch: 'b' != 'a'!", notifiaction.report());
    }

    @Test public void testDepth() {
        final IdentifierNode ident = IdentifierNode.newInstance();
        assertEquals(1, ident.depth());
    }

    @Test public void testToString() {
        final IdentifierNode ident = IdentifierNode.newInstance();
        assertEquals("<IDENTIFIER VALUE=>", ident.toString());
        ident.setAttribute(IdentifierNode.Attributes.VALUE, "foo");
        assertEquals("<IDENTIFIER VALUE=foo>", ident.toString());
    }

    @Test public void getType() {
        assertEquals(NodeType.IDENTIFIER, IdentifierNode.newInstance().getType());
    }
}
