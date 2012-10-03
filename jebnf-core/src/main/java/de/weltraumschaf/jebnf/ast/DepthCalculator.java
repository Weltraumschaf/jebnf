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

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Encapsulates the algorithm to calculate the depth of an {@link Composite} node.
 *
 * @author Sven Strittmatter <weltraumschaf@googlemail.com>
 */
public class DepthCalculator {

    /**
     * The subject to calculate for.
     */
    private final Composite node;

    /**
     * Initializes the immutable object.
     *
     * @param node Calculation subject.
     */
    public DepthCalculator(final Composite node) {
        this.node = node;
    }

    /**
     * Calculates the depth on each call.
     *
     * It will return at least 1 if the subject node as no children.
     *
     * @return Returns positive integer greater than 0.
     */
    public int depth() {
        if (node.hasChildren()) {
            final List<Integer> depths = new ArrayList<Integer>();

            for (Node n : node.getChildren()) {
                depths.add(n.depth());
            }

            return Collections.max(depths) + 1;
        }

        return 1;
    }

}
