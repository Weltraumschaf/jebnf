/*
 * LICENSE
 *
 * "THE BEER-WARE LICENSE" (Revision 42):
 * "Sven Strittmatter" <weltraumschaf@googlemail.com> wrote this file.
 * As long as you retain this notice you can do whatever you want with
 * this stuff. If we meet some day, and you think this stuff is worth it,
 * you can buy me a beer in return.
 *
 */

package de.weltraumschaf.jebnf.ast;

import de.weltraumschaf.jebnf.ast.nodes.LoopNode;
import de.weltraumschaf.jebnf.ast.nodes.CommentNode;
import de.weltraumschaf.jebnf.ast.nodes.SequenceNode;
import de.weltraumschaf.jebnf.ast.nodes.NullNode;
import de.weltraumschaf.jebnf.ast.nodes.Option;
import de.weltraumschaf.jebnf.ast.nodes.IdentifierNode;
import de.weltraumschaf.jebnf.ast.nodes.SyntaxNode;
import de.weltraumschaf.jebnf.ast.nodes.ChoiceNode;
import de.weltraumschaf.jebnf.ast.nodes.TerminalNode;
import de.weltraumschaf.jebnf.ast.nodes.NodeFactory;
import de.weltraumschaf.jebnf.ast.nodes.RuleNode;
import de.weltraumschaf.jebnf.ast.NodeType;
import static org.junit.Assert.assertTrue;
import org.junit.Test;

/**
 *
 * @author Sven Strittmatter <weltraumschaf@googlemail.com>
 */
public class NodeFactoryTest {

    @Test public void newNode() {
        assertTrue(NodeFactory.newNode(NodeType.SYNTAX) instanceof SyntaxNode);
        assertTrue(NodeFactory.newNode(NodeType.NULL) instanceof NullNode);
        assertTrue(NodeFactory.newNode(NodeType.CHOICE) instanceof ChoiceNode);
        assertTrue(NodeFactory.newNode(NodeType.COMMENT) instanceof CommentNode);
        assertTrue(NodeFactory.newNode(NodeType.IDENTIFIER) instanceof IdentifierNode);
        assertTrue(NodeFactory.newNode(NodeType.LOOP) instanceof LoopNode);
        assertTrue(NodeFactory.newNode(NodeType.OPTION) instanceof Option);
        assertTrue(NodeFactory.newNode(NodeType.RULE) instanceof RuleNode);
        assertTrue(NodeFactory.newNode(NodeType.SEQUENCE) instanceof SequenceNode);
        assertTrue(NodeFactory.newNode(NodeType.TERMINAL) instanceof TerminalNode);
    }
}
