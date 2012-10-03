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

import de.weltraumschaf.commons.IOStreams;
import de.weltraumschaf.jebnf.ast.nodes.SyntaxNode;

/**
 * Common functionality for base invokable apps.
 *
 * @author Sven Strittmatter <weltraumschaf@googlemail.com>
 */
abstract class ApplicationAdapter implements Application {

    /**
     * IO Streams.
     */
    protected final IOStreams ioStreams;

    /**
     * Command line options.
     */
    protected final CliOptions options;

    /**
     * Invoked the invokable.
     */
    protected final Main invoker;

    /**
     * The abstract syntax tree the application works with.
     */
    protected SyntaxNode syntax;

    /**
     * Initializes app with options and IO streams.
     *
     * @param options Command line options
     * @param ioStreams IO streams.
     * @param invoker Invoked the invokable.
     */
    public ApplicationAdapter(final CliOptions options, final IOStreams ioStreams, final Main invoker) {
        this.ioStreams = ioStreams;
        this.options = options;
        this.invoker = invoker;
    }

    @Override
    public void setSyntax(final SyntaxNode syntax) {
        this.syntax = syntax;
    }
}
