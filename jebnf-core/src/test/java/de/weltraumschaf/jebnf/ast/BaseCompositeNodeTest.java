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

import de.weltraumschaf.jebnf.ast.nodes.*;
import java.util.List;
import static org.junit.Assert.*;
import org.junit.Test;
import static org.mockito.Mockito.mock;

/**
 * Unit test for BaseCompositeNode.
 *
 * @author Sven Strittmatter <weltraumschaf@googlemail.com>
 */
public class BaseCompositeNodeTest {

    static class CompositeImpl extends BaseCompositeNode {

        public CompositeImpl() {
            super(NullNode.getInstance(), null);
        }

    }

    @Test public void testAddHasAndGetChildren() {
        final BaseCompositeNode composite = new CompositeImpl();

        assertFalse(composite.hasChildren());
        assertEquals(0, composite.countChildren());
        List<Node> childs = composite.getChildren();
        assertEquals(0, childs.size());

        final Node nodeOne = mock(Node.class);
        composite.addChild(nodeOne);
        assertTrue(composite.hasChildren());
        assertEquals(1, composite.countChildren());
        childs = composite.getChildren();
        assertEquals(1, childs.size());
        assertSame(nodeOne, childs.get(0));

        final Node nodeTwo = mock(Node.class);
        composite.addChild(nodeTwo);
        assertTrue(composite.hasChildren());
        assertEquals(2, composite.countChildren());
        childs = composite.getChildren();
        assertEquals(2, childs.size());
        assertSame(nodeOne, childs.get(0));
        assertSame(nodeTwo, childs.get(1));
    }

    @Test public void probeEquivalenceInternal() {
        final BaseCompositeNode comp = new CompositeImpl();
        Notification notification = new Notification();
        comp.probeEquivalence(TerminalNode.newInstance(), notification);
        assertFalse(notification.isOk());
        assertEquals("Probed node is not a composite node: "
                   + "'class de.weltraumschaf.jebnf.ast.nodes.TerminalNode'!",
            notification.report()
        );

        notification = new Notification();
        comp.probeEquivalence(RuleNode.newInstance(), notification);
        assertFalse(notification.isOk());
        assertEquals("Probed node types mismatch: "
            + "'class de.weltraumschaf.jebnf.ast.BaseCompositeNodeTest$CompositeImpl' "
            + "!= 'class de.weltraumschaf.jebnf.ast.nodes.RuleNode'!",
            notification.report()
        );
    }

    @Test public void depth() {
        final SyntaxNode syntax = SyntaxNode.newInstance();
        assertEquals(1, syntax.depth());

        final RuleNode rule = RuleNode.newInstance();
        assertEquals(1, rule.depth());
        syntax.addChild(rule);
        assertEquals(2, syntax.depth());

        final SequenceNode seq = SequenceNode.newInstance();
        assertEquals(1, seq.depth());
        rule.addChild(seq);
        assertEquals(2, rule.depth());
        assertEquals(3, syntax.depth());

        final IdentifierNode ident = IdentifierNode.newInstance();
        assertEquals(1, ident.depth());
        seq.addChild(ident);
        assertEquals(2, seq.depth());
        assertEquals(3, rule.depth());
        assertEquals(4, syntax.depth());

        final LoopNode loop = LoopNode.newInstance();
        assertEquals(1, loop.depth());
        seq.addChild(loop);
        assertEquals(2, seq.depth());
        assertEquals(3, rule.depth());
        assertEquals(4, syntax.depth());

        final TerminalNode term = TerminalNode.newInstance();
        assertEquals(1, term.depth());
        loop.addChild(term);
        assertEquals(2, loop.depth());
        assertEquals(3, seq.depth());
        assertEquals(4, rule.depth());
        assertEquals(5, syntax.depth());
    }
}
