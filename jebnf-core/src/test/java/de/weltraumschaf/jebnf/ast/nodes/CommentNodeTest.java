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
import de.weltraumschaf.jebnf.ast.visitor.Visitor;
import static org.junit.Assert.*;
import org.junit.Test;
import static org.mockito.Mockito.*;

/**
 * Unit test for CommentNode.
 *
 * @author Sven Strittmatter <weltraumschaf@googlemail.com>
 */
public class CommentNodeTest {

    @Test public void testProbeEquivalence() {
        Notification notification;
        final CommentNode comment1 = CommentNode.newInstance();
        comment1.setAttribute(CommentNode.Attributes.VALUE, "a");
        notification = new Notification();
        comment1.probeEquivalence(comment1, notification);
        assertTrue(notification.isOk());

        final CommentNode comment2 = CommentNode.newInstance();
        comment2.setAttribute(CommentNode.Attributes.VALUE, "b");
        notification = new Notification();
        comment2.probeEquivalence(comment2, notification);
        assertTrue(notification.isOk());

        notification = new Notification();
        comment1.probeEquivalence(IdentifierNode.newInstance(), notification);
        assertFalse(notification.isOk());
        assertEquals("Probed node types mismatch: 'class de.weltraumschaf.jebnf.ast.nodes.CommentNode' "
                   + "!= 'class de.weltraumschaf.jebnf.ast.nodes.IdentifierNode'!",
            notification.report()
        );

        notification = new Notification();
        comment1.probeEquivalence(comment2, notification);
        assertFalse(notification.isOk());
        assertEquals("Comment value mismatch: 'a' != 'b'!", notification.report());

        notification = new Notification();
        comment2.probeEquivalence(comment1, notification);
        assertFalse(notification.isOk());
        assertEquals("Comment value mismatch: 'b' != 'a'!", notification.report());
    }

    @Test public void testDepth() {
        final CommentNode comment = CommentNode.newInstance();
        assertEquals(1, comment.depth());
    }

    @Test public void accept() {
        final Visitor<?> visitor = mock(Visitor.class);
        final CommentNode comment = CommentNode.newInstance();
        comment.accept(visitor);
        verify(visitor, times(1)).beforeVisit(comment);
        verify(visitor, times(1)).visit(comment);
        verify(visitor, times(1)).afterVisit(comment);
    }

    @Test public void testToString() {
        final CommentNode comment = CommentNode.newInstance("foo");
        assertEquals("<COMMENT VALUE=foo>", comment.toString());
    }

    @Test public void getType() {
        assertEquals(NodeType.COMMENT, CommentNode.newInstance().getType());
    }
}
