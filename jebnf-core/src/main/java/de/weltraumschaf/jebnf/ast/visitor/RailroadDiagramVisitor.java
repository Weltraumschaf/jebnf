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

package de.weltraumschaf.jebnf.ast.visitor;

import de.weltraumschaf.jebnf.ast.Node;
import de.weltraumschaf.jebnf.ast.Visitable;
import de.weltraumschaf.jebnf.ast.nodes.RuleNode;
import de.weltraumschaf.jebnf.ast.nodes.SyntaxNode;
import de.weltraumschaf.jebnf.gfx.RailroadDiagram;
import static de.weltraumschaf.jebnf.gfx.shapes.ShapeFactory.*;
import de.weltraumschaf.jebnf.gfx.shapes.text.RuleShape;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Visits the given {@link de.weltraumschaf.jebnf.ast.nodes.SyntaxNode} and generates
 * a {@link de.weltraumschaf.jebnf.gfx.RailroadDiagram} as result.
 *
 * @author Sven Strittmatter <weltraumschaf@googlemail.com>
 */
public class RailroadDiagramVisitor implements Visitor<RailroadDiagram> {

    private static final Logger LOGGER = Logger.getLogger(RailroadDiagramVisitor.class.getName());

    /**
     * Holds the resulting diagram.
     */
    private final RailroadDiagram diagram = new RailroadDiagram();
    private RuleShape currentRule;

    @Override
    public void beforeVisit(final Visitable visitable) {
        // Nothing to do here.
    }

    @Override
    public void visit(final Visitable visitable) {
        final Node node = (Node) visitable;

        switch (node.getType()) {
            case SYNTAX: {
                diagram.setTitle(node.getAttribute(SyntaxNode.ATTRIBUTE_TITLE));
                diagram.setMeta(node.getAttribute(SyntaxNode.ATTRIBUTE_META));
                break;
            }
            case RULE: {
                currentRule = rule(node.getAttribute(RuleNode.ATTRIBUTE_NAME));
                diagram.add(currentRule);
                break;
            }
            case CHOICE: {
                // TODO Implement.
                break;
            }
            case COMMENT: {
                // TODO Implement.
                break;
            }
            case LOOP: {
                // TODO Implement.
                break;
            }
            case OPTION: {
                // TODO Implement.
                break;
            }
            case SEQUENCE: {
                // TODO Implement.
                break;
            }
            case IDENTIFIER: {
                // TODO Implement.
                break;
            }
            case TERMINAL: {
                // TODO Implement.
                break;
            }
            case NULL: {
                // Ignore nul nodes.
                break;
            }
            default:
                LOGGER.log(Level.WARNING, String.format("Unsupported node type: '%s'!", node.getType()));
        }
    }

    @Override
    public void afterVisit(final Visitable visitable) {
        // Nothing to do here.
    }

    @Override
    public RailroadDiagram getResult() {
        return diagram;
    }

}
