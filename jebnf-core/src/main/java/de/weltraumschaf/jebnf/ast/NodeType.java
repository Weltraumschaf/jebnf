/*
 * LICENSE
 *
 * "THE BEER-WARE LICENSE" (Revision 42):
 * "Sven Strittmatter" <ich(at)weltraumschaf(dot)de> wrote this file.
 * As long as you retain this notice you can do whatever you want with
 * this stuff. If we meet some day, and you think this stuff is worth it,
 * you can buy me a beer in return.
 *
 */

package de.weltraumschaf.jebnf.ast;

/**
 * Represents the type of an EBNF node type of the syntax tree.
 *
 * @author Sven Strittmatter <weltraumschaf@googlemail.com>
 */
public enum NodeType {

    /**
     * This node type describes an alternation node.
     *
     * Alternations are recognized by '|' character in the syntax.
     */
    CHOICE,

    /**
     * This node type describes a comment node.
     */
    COMMENT,

    /**
     * This node type describes an identifier node.
     */
    IDENTIFIER,

    /**
     * This node type describes an repetition node.
     *
     * Repetitions are recognized by '{' and '}' characters in the syntax.
     */
    LOOP,

    /**
     * This node type describes an option node.
     *
     * Options are recognized by '[' and ']' characters in the syntax.
     */
    OPTION,

    /**
     * This node type describes a rule node.
     */
    RULE,

    /**
     * This node type describes a sequence node.
     */
    SEQUENCE,

    /**
     * This node type describes a syntax node.
     *
     * This is the root node of the syntax tree.
     */
    SYNTAX,

    /**
     * This node type describes a terminal node.
     */
    TERMINAL,

    /**
     * This node type describes a null node.
     *
     * Null type nodes are used for testing and preventing NPE.
     */
    NULL;

    @Override
    public String toString() {
        return name().toLowerCase();
    }

}
