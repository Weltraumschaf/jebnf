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
 *
 * @author Sven Strittmatter <weltraumschaf@googlemail.com>
 */
public class NullNodeTest {

    private final NullNode sut = NullNode.getInstance();

    @Test public void getNodeName() {
        assertEquals("null", sut.getNodeName());
    }

    @Test public void accept() {
        final Visitor visitor = mock(Visitor.class);
        sut.accept(visitor);
        verify(visitor, never()).beforeVisit(sut);
        verify(visitor, never()).visit(sut);
        verify(visitor, never()).afterVisit(sut);
    }


    @Test  public void probeEquivalence() {
        Notification notification = new Notification();
        sut.probeEquivalence(sut, notification);
        assertTrue(notification.isOk());

        notification = new Notification();
        sut.probeEquivalence(SequenceNode.newInstance(), notification);
        assertFalse(notification.isOk());
    }

    @Test public void depth() {
        assertEquals(1, sut.depth());
    }

    @Test public void getType() {
        assertEquals(NodeType.NULL, sut.getType());
    }
}
