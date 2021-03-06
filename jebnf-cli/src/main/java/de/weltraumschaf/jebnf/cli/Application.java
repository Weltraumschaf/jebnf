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
package de.weltraumschaf.jebnf.cli;

import de.weltraumschaf.jebnf.ast.nodes.SyntaxNode;
import java.io.IOException;

/**
 * Interface to describe command pattern.
 *
 * @author Sven Strittmatter <weltraumschaf@googlemail.com>
 */
interface Application {

    /**
     * Executable command method.
     *
     * @throws IOException If any I/O error happens.
     */
    void execute() throws IOException;

    /**
     * Set the parsed {@link de.weltraumschaf.jebnf.ast.nodes.SyntaxNode}.
     *
     * @param syntax The abstract syntax tree.
     */
    void setSyntax(SyntaxNode syntax);

}
