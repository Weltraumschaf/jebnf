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

import java.util.List;

/**
 * Represents an object in the AST model which can have some child {@link Node nodes}.
 *
 * @author Sven Strittmatter <weltraumschaf@googlemail.com>
 */
public interface CompositeNode extends Node {

    /**
     * Count of direct children nodes.
     *
     * @return Returns positive integer greater equal 0.
     */
    int countChildren();

    /**
     * Whether the node has direct child nodes or not.
     *
     * @return Return true if {@link #countChildren()} is greater than 0.
     */
    boolean hasChildren();

    /**
     * Append a child {@link Node} to the list of children.
     *
     * @param child Child node to add.
     */
    void addChild(Node child);

    /**
     * Returns an iterator for the child nodes.
     *
     * @return Return a list of child nodes. List may be empty.
     */
    List<Node> getChildren();

}
