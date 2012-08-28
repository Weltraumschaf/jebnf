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
import de.weltraumschaf.jebnf.ast.nodes.Syntax;
import de.weltraumschaf.jebnf.ast.visitor.TextSyntaxTree;
import de.weltraumschaf.jebnf.gui.GuiApp;
import de.weltraumschaf.jebnf.parser.Factory;
import de.weltraumschaf.jebnf.parser.Parser;
import de.weltraumschaf.jebnf.parser.SyntaxException;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
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
        this.args      = args;
        this.ioStreams = ioStreams;
        this.options   = options;
    }

    /**
     * Invoked by the JVM.
     *
     * @param args Command line arguments.
     */
    public static void main(final String[] args) {
        new Invoker(args).run();
    }

    /**
     * Exits the application with given exit code.
     *
     * @param code Exit code.
     */
    private static void exit(final ExitCode code) {
        exit(code.getCode());
    }

    /**
     * Exits the application with given exit code.
     *
     * @param code Exit code.
     */
    private static void exit(final int code) {
        System.exit(code);
    }

    /**
     * Prints line to STDOUT.
     *
     * @param str String to print.
     */
    protected void println(final String str) {
        ioStreams.getStdout().println(str);
    }

    /**
     * Prints line to STDERR.
     *
     * @param str String to print.
     */
    protected void printlnErr(final String str) {
        ioStreams.getStderr().println(str);
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
    private void run() {
        try {
            parseOptions();

            if (options.isHelp()) {
                options.format(new HelpFormatter());
                exit(ExitCode.OK);
            }

            if (options.isIde()) {
                runGuiIde();
            } else {
                runCliApp();
            }
        } catch (EbnfException ex) {
            printlnErr(ex.getMessage());

            if (options.isDebug()) {
                printStackTrace(ex);
            }

            exit(ex.getCode());
        } catch (Exception ex) { // NOPMD Catch all exceptions!
            printlnErr("Fatal error!");

            if (options.isDebug()) {
                printStackTrace(ex);
            }

            exit(ExitCode.FATAL_ERROR);
        }
    }

    /**
     * Runs the command line application.
     */
    private void runCliApp() {
        if (!options.hasSyntaxFile()) {
            println("No syntax file given!");
            exit(ExitCode.NO_SYNTAX);
        }

        final String fileName = options.getSyntaxFile();

        try {
            final Parser parser  = Factory.newParserFromSource(new File(fileName), fileName);
            final Syntax ast     = parser.parse();

            if (options.isTextTree()) {
                final TextSyntaxTree visitor = new TextSyntaxTree();
                ast.accept(visitor);
                println(visitor.getText());
            }
        } catch (SyntaxException ex) {
            printlnErr("Syntax error: " + ex.getMessage());

            if (options.isDebug()) {
                ex.printStackTrace(ioStreams.getStderr());
            }

            exit(ExitCode.SYNTAX_ERROR);
        } catch (FileNotFoundException ex) {
            printlnErr(String.format("Can not read syntax file '%s'!", fileName));

            if (options.isDebug()) {
                printStackTrace(ex);
            }

            exit(ExitCode.READ_ERROR);
        } catch (IOException ex) {
            printlnErr(String.format("Can not read syntax file '%s'!", fileName));

            if (options.isDebug()) {
                printStackTrace(ex);
            }

            exit(ExitCode.READ_ERROR);
        }

        exit(ExitCode.OK);
    }

    /**
     * Runs the GUI application.
     */
    private void runGuiIde() {
        GuiApp.main(options.isDebug());
    }

    /**
     * Get the parsed command line options.
     *
     * @return Parsed and set options object.
     */
    public CliOptions getOptions() {
        return options;
    }

    /**
     * Prints exception stack trace to {@link System#err}.
     *
     * @param ex Exception to print.
     */
    private void printStackTrace(Exception ex) {
        ex.printStackTrace(ioStreams.getStderr());
    }

}
