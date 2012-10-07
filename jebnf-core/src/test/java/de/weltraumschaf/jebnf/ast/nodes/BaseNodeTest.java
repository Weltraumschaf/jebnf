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

import de.weltraumschaf.jebnf.ast.Node;
import de.weltraumschaf.jebnf.ast.NodeType;
import de.weltraumschaf.jebnf.ast.Notification;
import static org.junit.Assert.*;
import org.junit.Test;

/**
 *
 * @author Sven Strittmatter <weltraumschaf@googlemail.com>
 */
public class BaseNodeTest {

    static class BaseNodeStub extends BaseNode {

        public BaseNodeStub(final Node parent, final NodeType type) {
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
        BaseNodeStub sut = new BaseNodeStub(null, NodeType.CHOICE);
        assertEquals(NodeType.CHOICE, sut.getType());
        assertFalse(sut.hasParent());
        assertNull(sut.getParent());

        sut = new BaseNodeStub(NullNode.getInstance(), NodeType.CHOICE);
        assertFalse(sut.hasParent());
        assertSame(NullNode.getInstance(), sut.getParent());
        final Node parent = NodeFactory.newNode(NodeType.LOOP);
        sut = new BaseNodeStub(parent, NodeType.CHOICE);
        assertTrue(sut.hasParent());
        assertSame(parent, sut.getParent());
    }

    private enum Attributes implements NodeAttribute { FOO, SNAFU; };

    @Test public void testAttributes() {
        final BaseNodeStub sut = new BaseNodeStub(null, NodeType.CHOICE);
        assertFalse(sut.hasAttributes());
        sut.setAttribute(Attributes.FOO, "bar");
        assertTrue(sut.hasAttributes());
        assertTrue(sut.hasAttribute(Attributes.FOO));
        assertEquals("bar", sut.getAttribute(Attributes.FOO));
        assertFalse(sut.hasAttribute(Attributes.SNAFU));

        try {
            sut.getAttribute(Attributes.SNAFU);
            fail("Expected exception not thrown!");
        } catch (IllegalArgumentException ex) {
            assertEquals("Does not have attribute with name 'SNAFU'!", ex.getMessage());
        }
    }
}
