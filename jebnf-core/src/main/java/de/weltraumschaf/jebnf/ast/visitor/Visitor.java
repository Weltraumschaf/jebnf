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

package de.weltraumschaf.jebnf.ast.visitor;

import de.weltraumschaf.jebnf.ast.Visitable;

/**
 * Defines interface for an AST tree visitor.
 *
 * Interface for <a href="http://en.wikipedia.org/wiki/Visitor_pattern">Visitor Pattern</a>.
 *
 * @author Sven Strittmatter <weltraumschaf@googlemail.com>
 */
public interface Visitor {

    /**
     * Template method to hook in before specific node visitor method
     * will be invoked.
     *
     * @param visitable Visited {@link Visitable}.
     */
    void beforeVisit(Visitable visitable);

    /**
     * Generic visitor method called by a visited {@link Node}.
     *
     * @param visitable Visited {@link Visitable}.
     */
    void visit(Visitable visitable);

    /**
     * Template method to hook in after specific node visitor method
     * will be invoked.
     *
     * @param visitable Visited {@link Visitable}.
     */
    void afterVisit(Visitable visitable);

}
