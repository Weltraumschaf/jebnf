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

import de.weltraumschaf.jebnf.ast.nodes.NodeFactory;
import de.weltraumschaf.jebnf.ast.nodes.NullNode;
import static org.junit.Assert.*;
import org.junit.Test;

/**
 *
 * @author Sven Strittmatter <weltraumschaf@googlemail.com>
 */
public class BaseNodeTest {

    static class AbstractNodeStub extends BaseNode {

        public AbstractNodeStub(final Node parent, final NodeType type) {
            super(parent, type);
        }

        @Override
        public void probeEquivalence(final Node other, final Notification result) {
            throw new UnsupportedOperationException("Not supported yet.");
        }

        @Override
        public int depth() {
            throw new UnsupportedOperationException("Not supported yet.");
        }

    }

    @Test public void hasParent() {
        AbstractNodeStub sut = new AbstractNodeStub(null, NodeType.CHOICE);
        assertEquals(NodeType.CHOICE, sut.getType());
        assertFalse(sut.hasParent());
        assertNull(sut.getParent());

        sut = new AbstractNodeStub(NullNode.getInstance(), NodeType.CHOICE);
        assertFalse(sut.hasParent());
        assertSame(NullNode.getInstance(), sut.getParent());
        final Node parent = NodeFactory.newNode(NodeType.LOOP);
        sut = new AbstractNodeStub(parent, NodeType.CHOICE);
        assertTrue(sut.hasParent());
        assertSame(parent, sut.getParent());
    }

    @Test public void testAttributes() {
        final AbstractNodeStub sut = new AbstractNodeStub(null, NodeType.CHOICE);
        assertFalse(sut.hasAttributes());
        sut.setAttribute("foo", "bar");
        assertTrue(sut.hasAttributes());
        assertTrue(sut.hasAttribute("foo"));
        assertEquals("bar", sut.getAttribute("foo"));
        assertFalse(sut.hasAttribute("snafu"));

        try {
            sut.getAttribute("snafu");
            fail("Expected exception not thrown!");
        } catch (IllegalArgumentException ex) {
            assertEquals("Does not have attribute with name 'snafu'!", ex.getMessage());
        }
    }
}
