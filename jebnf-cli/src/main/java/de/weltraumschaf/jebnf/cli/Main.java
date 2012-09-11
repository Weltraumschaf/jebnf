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
import de.weltraumschaf.commons.system.NullExiter;
import de.weltraumschaf.jebnf.EbnfException;
import de.weltraumschaf.jebnf.ExitCodeImpl;
import de.weltraumschaf.jebnf.ast.nodes.SyntaxNode;
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
 * Invokes {@link Application "applications"}.
 *
 * @author Sven Strittmatter <weltraumschaf@googlemail.com>
 */
public final class Main extends InvokableAdapter {

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
     * Initializes object with {@link #DEFAULT_OPTIONS}.
     *
     * @param args Command line arguments.
     */
    public Main(final String[] args) {
        this(args, DEFAULT_OPTIONS);
    }

    /**
     * Dedicated constructor.
     *
     * @param args Command line arguments.
     * @param options Command line options.
     */
    public Main(final String[] args, final CliOptions options) {
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
        InvokableAdapter.main(new Main(args));
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
     * Parse options, determine to show help. Then executes either {@link CliApplication} or {@link GuiApplication}.
     *
     * @throws Exception On parse or I/O errors.
     */
    @Override
    public void execute() throws Exception {
        try {
            parseOptions();

            if (options.isHelp()) {
                options.format(new HelpFormatter(), getIoStreams().getStdout());
                exit(ExitCodeImpl.OK);
            }

            if (options.isShowVersion()) {
                getIoStreams().println(String.format("Version: %s", version));
                exit(ExitCodeImpl.OK);
            }

            if (!options.hasSyntaxFile()) {
                getIoStreams().printlnErr("No syntax file given!");
                exit(ExitCodeImpl.NO_SYNTAX);
            }

            final String syntaxFileName = options.getSyntaxFile();
            SyntaxNode ast = null;

            try {
                final File syntaxFile = new File(syntaxFileName);
                final Parser parser   = Factory.newParserFromSource(syntaxFile);
                ast = parser.parse();
            } catch (SyntaxException ex) {
                getIoStreams().printlnErr("Syntax error: " + ex.getMessage());

                if (options.isDebug()) {
                    getIoStreams().printStackTraceToStdErr(ex);
                }

                exit(ExitCodeImpl.SYNTAX_ERROR);
            } catch (FileNotFoundException ex) {
                getIoStreams().printlnErr(String.format("Can not read syntax file '%s'!", syntaxFileName));

                if (options.isDebug()) {
                    getIoStreams().printStackTraceToStdErr(ex);
                }

                exit(ExitCodeImpl.READ_ERROR);
            } catch (IOException ex) {
                getIoStreams().printlnErr(String.format("Can not read syntax file '%s'!", syntaxFileName));

                if (options.isDebug()) {
                    getIoStreams().printStackTraceToStdErr(ex);
                }

                exit(ExitCodeImpl.READ_ERROR);
            }

            Application app;

            if (options.isIde()) {
                app = new GuiApplication(options, getIoStreams(), this);
                setExiter(new NullExiter()); // Do not exit on return.
            } else {
                app = new CliApplication(options, getIoStreams(), this);
            }

            app.setSyntax(ast);
            app.execute();
        } catch (EbnfException ex) {
            getIoStreams().printlnErr(ex.getMessage());

            if (options.isDebug()) {
                getIoStreams().printStackTraceToStdErr(ex);
            }

            exit(ex.getCode());
        } catch (Exception ex) { // NOPMD Catch all exceptions!
            getIoStreams().printlnErr("Fatal error!");

            if (options.isDebug()) {
                getIoStreams().printStackTraceToStdErr(ex);
            }

            exit(ExitCodeImpl.FATAL_ERROR);
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
