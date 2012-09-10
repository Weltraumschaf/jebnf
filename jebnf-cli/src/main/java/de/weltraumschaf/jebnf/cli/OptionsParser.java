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

import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.CommandLineParser;
import org.apache.commons.cli.ParseException;
import org.apache.commons.cli.PosixParser;

/**
 * Parses the command line arguments.
 *
 * @author Sven Strittmatter <weltraumschaf@googlemail.com>
 */
public final class OptionsParser {

    /**
     * Short option for syntax file to read from.
     */
    public static final String OPT_SYNTAX = "s";

    /**
     * Short option for the output file to write in.
     */
    public static final String OPT_OUTPUT = "o";

    /**
     * Short option for the output format.
     */
    public static final String OPT_FORMAT = "f";

    /**
     * Short option to enable debug output (also in the generated output).
     */
    public static final String OPT_DEBUG = "d";

    /**
     * Short option for help message.
     */
    public static final String OPT_HELP = "h";

    /**
     * Short option to start the IDE.
     */
    public static final String OPT_IDE = "i";

    /**
     * Short option to show the version.
     */
    public static final String OPT_VERSION = "v";

    /**
     * Long option to start the IDE.
     */
    public static final String OPT_IDE_LONG = "ide";

    /**
     * The parsed and found options.
     */
    private final CliOptions options;

    /**
     * Initializes the parser with a new options object.
     *
     * @param options Newly created options object.
     */
    public OptionsParser(final CliOptions options) {
        this.options = options;
    }

    /**
     * PArses the given command line arguments strings.
     *
     * @param args Array of argument strings.
     * @throws ParseException On parse errors.
     */
    public void parse(final String[] args) throws ParseException {
        final CommandLineParser parser = new PosixParser();
        final CommandLine cmd = parser.parse(options.getOptions(), args);

        optSyntax(cmd);
        optFormat(cmd);
        optOutputFile(cmd);
        optDebug(cmd);
        optHelp(cmd);
        optVersion(cmd);
        optIde(cmd);
    }

    /**
     * Determines if syntax file option is set and prepares the {@link CliOptions "options"} object.
     *
     * @param cmd Parsed command line.
     */
    private void optSyntax(final CommandLine cmd) {
        if (cmd.hasOption(OPT_SYNTAX)) {
            options.setSyntaxFile(cmd.getOptionValue(OPT_SYNTAX));
        }
    }

    /**
     * Determines if format option is set and prepares the {@link CliOptions "options"} object.
     *
     * @param cmd Parsed command line.
     */
    private void optFormat(final CommandLine cmd) {
        if (cmd.hasOption(OPT_FORMAT)) {
            final String formatOption = cmd.getOptionValue(OPT_FORMAT);

            if ("tree".equalsIgnoreCase(formatOption)) {
                options.setOutputFormat(OutputFormat.TREE);
            } else if ("xml".equalsIgnoreCase(formatOption)) {
                options.setOutputFormat(OutputFormat.XML);
            } else if ("jpg".equalsIgnoreCase(formatOption)) {
                options.setOutputFormat(OutputFormat.JPG);
            } else if ("gif".equalsIgnoreCase(formatOption)) {
                options.setOutputFormat(OutputFormat.GIF);
            } else if ("png".equalsIgnoreCase(formatOption)) {
                options.setOutputFormat(OutputFormat.PNG);
            } else {
                options.setOutputFormat(OutputFormat.JPG);
            }
        }
    }

    /**
     * Determines if output file option is set and prepares the {@link CliOptions "options"} object.
     *
     * @param cmd Parsed command line.
     */
    private void optOutputFile(final CommandLine cmd) {
        if (cmd.hasOption(OPT_OUTPUT)) {
            options.setOutputFile(cmd.getOptionValue(OPT_OUTPUT));
        } else {
            options.setOutputFile("");
        }
    }

    /**
     * Determines if debug option is set and prepares the {@link CliOptions "options"} object.
     *
     * @param cmd Parsed command line.
     */
    private void optDebug(final CommandLine cmd) {
        if (cmd.hasOption(OPT_DEBUG)) {
            options.setDebug(true);
        }
    }

    /**
     * Determines if help option is set and prepares the {@link CliOptions "options"} object.
     *
     * @param cmd Parsed command line.
     */
    private void optHelp(final CommandLine cmd) {
        if (cmd.hasOption(OPT_HELP)) {
            options.setHelp(true);
        }
    }

    /**
     * Determines if version option is set and prepares the {@link CliOptions "options"} object.
     *
     * @param cmd Parsed command line.
     */
    private void optVersion(final CommandLine cmd) {
        if (cmd.hasOption(OPT_VERSION)) {
            options.setVersion(true);
        }
    }

    /**
     * Determines if GUI IDE option is set and prepares the {@link CliOptions "options"} object.
     *
     * @param cmd Parsed command line.
     */
    private void optIde(final CommandLine cmd) {
        if (cmd.hasOption(OPT_IDE) || cmd.hasOption(OPT_IDE_LONG)) {
            options.setIde(true);
        }
    }

}
