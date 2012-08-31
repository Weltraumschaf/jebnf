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

import de.weltraumschaf.jebnf.EbnfException;
import de.weltraumschaf.jebnf.ExitCode;
import de.weltraumschaf.jebnf.Version;
import de.weltraumschaf.jebnf.cli.system.DefaultExiter;
import de.weltraumschaf.jebnf.cli.system.Exitable;
import de.weltraumschaf.jebnf.cli.system.NullExiter;
import org.apache.commons.cli.HelpFormatter;
import org.apache.commons.cli.ParseException;

/**
 * Main class.
 *
 * Invokes {@link Invokeable "applications"}.
 *
 * @author Sven Strittmatter <weltraumschaf@googlemail.com>
 */
public final class Invoker {

    /**
     * Default IO streams.
     */
    private static final IOStreams DEFAULT_IO = IOStreams.newDefault();

    /**
     * Default command line options.
     */
    private static final CliOptions DEFAULT_OPTIONS = new CliOptions();

    /**
     * Holds the IO streams (stdin, stdout, stderr).
     */
    private final IOStreams ioStreams;

    /**
     * Command line arguments from the JVM.
     */
    private final String[] args;

    /**
     * Current command line options.
     */
    private final CliOptions options;

    /**
     * Abstracts {@link System#exit(int)}.
     *
     * By default this filed is a {@link NullExiter} to prevent NPE.
     */
    private Exitable exiter = new NullExiter();

    /**
     * Initializes object with {@link #DEFAULT_IO}.
     *
     * @param args Command line arguments.
     */
    protected Invoker(final String[] args) {
        this(args, DEFAULT_IO);
    }

    /**
     * Initializes object with {@link #DEFAULT_OPTIONS}.
     *
     * @param args Command line arguments.
     * @param ioStreams IO streams.
     */
    protected Invoker(final String[] args, IOStreams ioStreams) {
        this(args, ioStreams, DEFAULT_OPTIONS);
    }

    /**
     * Dedicated constructor.
     *
     * @param args Command line arguments.
     * @param ioStreams IO streams.
     * @param options Command line options.
     */
    protected Invoker(final String[] args, final IOStreams ioStreams, final CliOptions options) {
        super();
        this.args      = args.clone();
        this.ioStreams = ioStreams;
        this.options   = options;
    }

    /**
     * Invoked by the JVM.
     *
     * @param args Command line arguments.
     */
    public static void main(final String[] args) {
        final Invoker invoker = new Invoker(args);
        invoker.setExiter(new DefaultExiter());
        invoker.run();
    }

    /**
     * Set the exiter.
     *
     * @param exiter The used exiter.
     */
    public void setExiter(final Exitable exiter) {
        this.exiter = exiter;
    }

    /**
     * Exits the application with given exit code.
     *
     * @param code Exit code.
     */
    void exit(final ExitCode code) {
        exiter.exit(code.getCode());
    }

    /**
     * Parse the command line options.
     *
     * @throws EbnfException On command line parse errors.
     */
    protected void parseOptions() throws EbnfException {
        try {
            options.parse(args);
        } catch (ParseException ex) {
            throw new EbnfException(ex.getMessage(), 1, ex);
        }
    }

    /**
     * Runs the application.
     *
     * Parse options, determine to show help. Then executes either {@link CliApp} or {@link GuiApp}.
     */
    public void run() {
        try {
            parseOptions();

            if (options.isHelp()) {
                options.format(new HelpFormatter(), ioStreams.getStdout());
                exit(ExitCode.OK);
            }

            if (options.isShowVersion()) {
                ioStreams.println(String.format("Version: %s", Version.getInstance()));
                exit(ExitCode.OK);
            }

            Invokeable app;

            if (options.isIde()) {
                app = new GuiApp(options, ioStreams, this);
            } else {
                app = new CliApp(options, ioStreams, this);
            }

            app.execute();
        } catch (EbnfException ex) {
            ioStreams.printlnErr(ex.getMessage());

            if (options.isDebug()) {
                ioStreams.printStackTraceToStdErr(ex);
            }

            exiter.exit(ex.getCode());
        } catch (Exception ex) { // NOPMD Catch all exceptions!
            ioStreams.printlnErr("Fatal error!");

            if (options.isDebug()) {
                ioStreams.printStackTraceToStdErr(ex);
            }

            exit(ExitCode.FATAL_ERROR);
        }
    }

    /**
     * Get the parsed command line options.
     *
     * @return Parsed and set options object.
     */
    public CliOptions getOptions() {
        return options;
    }

}
