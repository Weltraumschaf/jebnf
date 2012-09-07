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

import de.weltraumschaf.commons.InvokableAdapter;
import de.weltraumschaf.commons.Version;
import de.weltraumschaf.jebnf.EbnfException;
import de.weltraumschaf.jebnf.ExitCode;
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
public final class App extends InvokableAdapter {

    /**
     * Default command line options.
     */
    private static final CliOptions DEFAULT_OPTIONS = new CliOptions();

    /**
     * Current command line options.
     */
    private final CliOptions options;

    /**
     * Version information.
     */
    private final Version version;

    /**
     * Abstracts {@link System#exit(int)}.
     *
     * By default this filed is a {@link NullExiter} to prevent NPE.
     */
    private Exitable exiter = new NullExiter();

    /**
     * Initializes object with {@link #DEFAULT_OPTIONS}.
     *
     * @param args Command line arguments.
     */
    protected App(final String[] args) {
        this(args, DEFAULT_OPTIONS);
    }

    /**
     * Dedicated constructor.
     *
     * @param args Command line arguments.
     * @param options Command line options.
     */
    protected App(final String[] args, final CliOptions options) {
        super(args);
        this.options = options;
        this.version = new Version("/de/weltraumschaf/jebnf/version.properties");
    }

    /**
     * Delegate application invocation to {@link InvokableAdapter#main(de.weltraumschaf.commons.Invokable)}.
     *
     * @param args Command line arguments.
     */
    public static void main(final String[] args) {
        InvokableAdapter.main(new App(args));
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
     * @FIXME USe super#exit().
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
            options.parse(getArgs());
        } catch (ParseException ex) {
            throw new EbnfException(ex.getMessage(), 1, ex);
        }
    }

    /**
     * Runs the application.
     *
     * Parse options, determine to show help. Then executes either {@link CliApp} or {@link GuiApp}.
     *
     * @throws Exception On parse or I/O errors.
     */
    @Override
    public void execute() throws Exception {
        try {
            parseOptions();

            if (options.isHelp()) {
                options.format(new HelpFormatter(), getIoStreams().getStdout());
                exit(ExitCode.OK);
            }

            if (options.isShowVersion()) {
                getIoStreams().println(String.format("Version: %s", version));
                exit(ExitCode.OK);
            }

            Invokeable app;

            if (options.isIde()) {
                app = new GuiApp(options, getIoStreams(), this);
            } else {
                app = new CliApp(options, getIoStreams(), this);
            }

            app.execute();
        } catch (EbnfException ex) {
            getIoStreams().printlnErr(ex.getMessage());

            if (options.isDebug()) {
                getIoStreams().printStackTraceToStdErr(ex);
            }

            exiter.exit(ex.getCode());
        } catch (Exception ex) { // NOPMD Catch all exceptions!
            getIoStreams().printlnErr("Fatal error!");

            if (options.isDebug()) {
                getIoStreams().printStackTraceToStdErr(ex);
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
