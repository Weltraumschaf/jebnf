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
package de.weltraumschaf.jebnf.ast.visitor;

import com.google.common.collect.Lists;
import de.weltraumschaf.jebnf.ast.Node;
import de.weltraumschaf.jebnf.ast.NodeType;
import de.weltraumschaf.jebnf.ast.Visitable;
import de.weltraumschaf.jebnf.ast.nodes.CommentNode;
import de.weltraumschaf.jebnf.ast.nodes.IdentifierNode;
import de.weltraumschaf.jebnf.ast.nodes.RuleNode;
import de.weltraumschaf.jebnf.ast.nodes.TerminalNode;
import java.util.List;
import org.apache.commons.lang3.StringUtils;

/**
 * Generates an ASCII formatted tree of the visited AST {@link de.weltraumschaf.jebnf.ast.nodes.SyntaxNode} node.
 *
 * Example:
 *
 * The file <kbd>rules_with_literals.ebnf</kbd> will produce
 *
 * <pre>
 * [syntax]
 *  +--[rule='literal']
 *      +--[choice]
 *          +--[sequence]
 *          |   +--[terminal=''']
 *          |   +--[identifier='character']
 *          |   +--[loop]
 *          |   |   +--[identifier='character']
 *          |   +--[terminal=''']
 *          +--[sequence]
 *              +--[terminal='"']
 *              +--[identifier='character']
 *              +--[loop]
 *              |   +--[identifier='character']
 *              +--[terminal='"']
 * </pre>
 *
 * For generating the tree lines a two dimensional array is used as
 * "render" matrix. The text is lazy computed by computing the array field
 * column by column and row by row.
 *
 * @author Sven Strittmatter <weltraumschaf@googlemail.com>
 */
public class TextSyntaxTreeVisitor implements Visitor<String> {

    /**
     * ASCII pattern for branch.
     */
    private static final String BRANCH = " +--";

    /**
     * ASCII pattern for line down.
     */
    private static final String PIPE   = " |  ";

    /**
     * ASCII pattern horizontal alignment.
     */
    private static final String BLANK  = "    ";

    /**
     * Values longer than this constant will be truncated.
     */
    private static final int MAX_VALUE_LENGTH = 20;

    /**
     * The formatted ASCII text.
     *
     * Lazy computed.
     */
    private String text;

    /**
     * Depth of the visited tree.
     *
     * Asked in {@link de.weltraumschaf.jebnf.ast.visitor.Visitor#beforeVisit} node.
     */
    private int depth;

    /**
     * The indention level in the matrix.
     */
    private int level;

    /**
     * The matrix.
     *
     * Two dimensional array. Initialized on visiting a {@link de.weltraumschaf.jebnf.ast.nodes.SyntaxNode}
     * node. So it is important that the syntax node is the root node of the tree. The matrix grows
     * row by row by visiting each child node. A child node represents a row.
     */
    private final List<List<String>> matrix = Lists.newArrayList();

    /**
     * Returns the two dimensional matrix.
     *
     * @return array
     */
    public List<List<String>> getMatrix() {
        return matrix;
    }

    /**
     * Returns the depth of the visited tree.
     *
     * @return Return an integer greater than 0.
     */
    public int getDepth() {
        return depth;
    }

    /**
     * Formats nodes two strings.
     *
     * {@link de.weltraumschaf.jebnf.ast.nodes.RuleNode}, {@link de.weltraumschaf.jebnf.ast.nodes.TerminalNode}
     * and {@link de.weltraumschaf.jebnf.ast.nodes.IdentifierNode} nodes will be rendered with their
     * attributes name or value.
     *
     * @param node Node to format.
     * @return      Formatted node.
     */
    public static String formatNode(final Node node) {
        final StringBuilder text = new StringBuilder();
        text.append('[').append(node.getNodeName());
        String value = "";

        if (node.isType(NodeType.RULE)) {
            value = node.getAttribute(RuleNode.Attributes.NAME);
        } else if (node.isType(NodeType.TERMINAL)) {
            value = node.getAttribute(TerminalNode.Attributes.VALUE);
        } else if (node.isType(NodeType.IDENTIFIER)) {
            value = node.getAttribute(IdentifierNode.Attributes.VALUE);
        } else if (node.isType(NodeType.COMMENT)) {
            value = node.getAttribute(CommentNode.Attributes.VALUE);

            if (value.length() > MAX_VALUE_LENGTH) {
                value = value.substring(0, MAX_VALUE_LENGTH) + "...";
            }
        }

        if (!value.isEmpty()) {
            text.append("='").append(value).append('\'');
        }

        text.append(']');
        return text.toString();
    }

    /**
     * Returns an list of colCount empty strings as elements.
     *
     * @param colCount Count of columns with empty strings. Throws exception if less than 0.
     * @return Return string list.
     */
    public static List<String> createRow(final int colCount) {
        if (colCount < 0) {
            throw new IllegalArgumentException(
                String.format("Coll count msut be greater equal 0! Given value '%d'.", colCount));
        }

        final List<String> row = Lists.newArrayListWithCapacity(colCount);

        for (int i = 0; i < colCount; i++) {
            row.add("");
        }

        return row;
    }

    /**
     * If as {@link de.weltraumschaf.jebnf.ast.nodes.SyntaxNode} node comes around the visitor will be initializes.
     *
     * Which means that the depth property is read, the matrix and level properties will be
     * initialized. All other {@link de.weltraumschaf.jebnf.ast.Node} types increment the level
     * property.
     *
     * @param visitable Visited node.
     */
    @Override
    public void beforeVisit(final Visitable visitable) {
        final Node node = (Node) visitable;
        if (node.isType(NodeType.SYNTAX)) {
            depth  = node.depth();
            level  = 0;
            matrix.clear();
        } else {
            level++;
        }

        // While we're visiting the output will change anyway.
        text = null;
    }

    /**
     * Generates the string contents  in the row of the visited node.
     *
     * @param visitable Visited node.
     */
    @Override
    public void visit(final Visitable visitable) {
        final List<String> row = createRow(depth);

        if (level > 0) {
            for (int i = 0; i < level - 1; ++i) {
                row.set(i, BLANK);
            }

            row.set(level - 1, BRANCH);
            row.set(level, formatNode((Node) visitable));
        }

        row.set(level, formatNode((Node) visitable));
        matrix.add(row);
    }

    /**
     * Also "climbs" all rows in the current level and sets a "|" to parent nodes
     * id appropriate.
     *
     * Decrements the level until it reaches 0.
     *
     * @param visitable visited node.
     */
    @Override
    public void afterVisit(final Visitable visitable) {
        final int rowCnt = matrix.size();

        for (int i = rowCnt - 1; i > -1; i--) {
            if ((matrix.get(i).get(level).equals(BRANCH) || matrix.get(i).get(level).equals(PIPE))
                && (matrix.get(i - 1).get(level).equals(BLANK))) {
                matrix.get(i - 1).set(level, PIPE);
            }
        }

        level = level - 1;

        if (level < 0) {
            level = 0;
        }
    }

    /**
     * Concatenates the matrix columns and rows and returns the ASCII formatted text.
     *
     * After all visiting is done this method only generates the string once and memorizes
     * the result. Revisiting the tree will cause text regeneration on invoking this method.
     *
     * @return Return formated text.
     */
    @Override
    public String getResult() {
        if (null == text) {
            final StringBuilder buffer = new StringBuilder();

            for (List<String> row : matrix) {
                buffer.append(StringUtils.join(row, ""))
                      .append(System.getProperty("line.separator"));
            }

            text = buffer.toString();
        }

        return text;
    }

}
