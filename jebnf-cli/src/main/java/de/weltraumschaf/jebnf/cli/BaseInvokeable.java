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

package de.weltraumschaf.jebnf.cli;

/**
 * Common functionality for base invokable apps.
 *
 * @author Sven Strittmatter <weltraumschaf@googlemail.com>
 */
public class BaseInvokeable {

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
    protected final Invoker invoker;

    /**
     * Initializes app with options and IO streams.
     *
     * @param options Command line options
     * @param ioStreams IO streams.
     * @param invoker Invoked the invokable.
     */
    public BaseInvokeable(final CliOptions options, final IOStreams ioStreams, final Invoker invoker) {
        this.ioStreams = ioStreams;
        this.options = options;
        this.invoker = invoker;
    }

}