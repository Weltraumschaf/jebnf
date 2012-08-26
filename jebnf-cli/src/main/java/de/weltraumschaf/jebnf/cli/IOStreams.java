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

import java.io.InputStream;
import java.io.PrintStream;

/**
 * Immutable aggregate object which contains STDIN, STDOUT and STDERR streams.
 *
 * @author Sven Strittmatter <weltraumschaf@googlemail.com>
 */
public final class IOStreams {

    /**
     * Standard input stream.
     */
    private final InputStream stdin;

    /**
     * Standard output stream.
     */
    private final PrintStream stdout;

    /**
     * Standard error stream.
     */
    private final PrintStream stderr;

    /**
     * Initializes the streams.
     *
     * @param stdin Input stream.
     * @param stdout Output stream.
     * @param stderr Error stream.
     */
    public IOStreams(final InputStream stdin, final PrintStream stdout, final PrintStream stderr) {
        this.stdin  = stdin;
        this.stdout = stdout;
        this.stderr = stderr;
    }

    /**
     * Get standard error output stream.
     *
     * @return Print stream object.
     */
    public PrintStream getStderr() {
        return stderr;
    }

    /**
     * Get standard input stream.
     *
     * @return Input stream object.
     */
    public InputStream getStdin() {
        return stdin;
    }

    /**
     * Get standard output stream.
     *
     * @return Print stream object.
     */
    public PrintStream getStdout() {
        return stdout;
    }

}
