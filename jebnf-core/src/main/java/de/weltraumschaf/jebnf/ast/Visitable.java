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

import de.weltraumschaf.jebnf.ast.visitor.Visitor;

/**
 * Defines interface for a visitable AST tree node.
 *
 * Interface for <a href="http://en.wikipedia.org/wiki/Visitor_pattern">Visitor Pattern</a>.
 *
 * @author Sven Strittmatter <weltraumschaf@googlemail.com>
 */
public interface Visitable {

    /**
     * Defines method to accept {@link Visitor}.
     *
     * @param visitor Object which visits the visitable.
     */
    void accept(Visitor<?> visitor);

}
