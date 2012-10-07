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
 * Unit test for TerminalNode.
 *
 * @author Sven Strittmatter <weltraumschaf@googlemail.com>
 */
public class TerminalNodeTest {

    @Test public void testProbeEquivalence() {
        Notification notification;
        final TerminalNode term1 = TerminalNode.newInstance();
        term1.setAttribute(TerminalNode.Attributes.VALUE, "a");
        notification = new Notification();
        term1.probeEquivalence(term1, notification);
        assertTrue(notification.isOk());

        final TerminalNode term2 = TerminalNode.newInstance();
        term2.setAttribute(TerminalNode.Attributes.VALUE, "b");
        notification = new Notification();
        term2.probeEquivalence(term2, notification);
        assertTrue(notification.isOk());

        term1.probeEquivalence(IdentifierNode.newInstance(), notification);
        assertFalse(notification.isOk());
        assertEquals("Probed node types mismatch: 'class de.weltraumschaf.jebnf.ast.nodes.TerminalNode' "
                   + "!= 'class de.weltraumschaf.jebnf.ast.nodes.IdentifierNode'!",
            notification.report()
        );

        notification = new Notification();
        term1.probeEquivalence(term2, notification);
        assertFalse(notification.isOk());
        assertEquals("Terminal value mismatch: 'a' != 'b'!", notification.report());

        notification = new Notification();
        term2.probeEquivalence(term1, notification);
        assertFalse(notification.isOk());
        assertEquals("Terminal value mismatch: 'b' != 'a'!", notification.report());
    }

    @Test public void testDepth() {
        final TerminalNode term1 = TerminalNode.newInstance();
        assertEquals(1, term1.depth());
    }

    @Test public void testToString() {
        final TerminalNode term = TerminalNode.newInstance();
        assertEquals("<TERMINAL VALUE=>", term.toString());
        term.setAttribute(TerminalNode.Attributes.VALUE, "foo");
        assertEquals("<TERMINAL VALUE=foo>", term.toString());
    }

    @Test public void getType() {
        assertEquals(NodeType.TERMINAL, TerminalNode.newInstance().getType());
    }

}
