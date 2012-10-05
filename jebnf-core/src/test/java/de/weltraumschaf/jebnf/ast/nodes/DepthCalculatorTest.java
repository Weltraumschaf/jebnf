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
import static org.junit.Assert.assertEquals;
import org.junit.Test;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

/**
 * Unit test for DepthCalculator.
 *
 * @author Sven Strittmatter <weltraumschaf@googlemail.com>
 */
public class DepthCalculatorTest {

    static class AbstractCompositeImpl extends BaseCompositeNode {

        public AbstractCompositeImpl() {
            super(NullNode.getInstance(), null);
        }


    }

    private Node createNode() {
        return createNode(1);
    }

    /**
     * @param int depth
     * @return Node
     */
    private Node createNode(final int depth) {
        final Node node = mock(Node.class);
        when(node.depth()).thenReturn(depth);
        return node;
    }

    @Test public void testDepth() {
        BaseCompositeNode subject = new AbstractCompositeImpl();

        DepthCalculator calc = new DepthCalculator(subject);
        assertEquals(0, subject.countChildren());
        assertEquals(1, calc.depth());

        subject.addChild(createNode());
        assertEquals(1, subject.countChildren());
        calc = new DepthCalculator(subject);
        assertEquals(2, calc.depth());

        subject.addChild(createNode());
        assertEquals(2, subject.countChildren());
        calc = new DepthCalculator(subject);
        assertEquals(2, calc.depth());

        subject.addChild(createNode());
        assertEquals(3, subject.countChildren());
        calc = new DepthCalculator(subject);
        assertEquals(2, calc.depth());

        subject = new AbstractCompositeImpl();
        subject.addChild(createNode(2));
        calc = new DepthCalculator(subject);
        assertEquals(3, calc.depth());

        subject.addChild(createNode(5));
        calc = new DepthCalculator(subject);
        assertEquals(6, calc.depth());

        subject.addChild(createNode(1));
        calc = new DepthCalculator(subject);
        assertEquals(6, calc.depth());

        subject.addChild(createNode(8));
        calc = new DepthCalculator(subject);
        assertEquals(9, calc.depth());
    }
}
