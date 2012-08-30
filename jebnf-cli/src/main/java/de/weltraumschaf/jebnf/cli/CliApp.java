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

import de.weltraumschaf.jebnf.ExitCode;
import de.weltraumschaf.jebnf.ast.nodes.Syntax;
import de.weltraumschaf.jebnf.ast.visitor.TextSyntaxTree;
import de.weltraumschaf.jebnf.gfx.CreatorHelper;
import de.weltraumschaf.jebnf.gfx.RailroadDiagram;
import de.weltraumschaf.jebnf.gfx.RailroadDiagramImage;
import de.weltraumschaf.jebnf.parser.Factory;
import de.weltraumschaf.jebnf.parser.Parser;
import de.weltraumschaf.jebnf.parser.SyntaxException;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

/**
 * Command line application.
 *
 * @author Sven Strittmatter <weltraumschaf@googlemail.com>
 */
public class CliApp extends BaseInvokeable implements Invokeable {

    /**
     * Default width in pixel.
     */
    private static final int WIDTH = 800;

    /**
     * Default height in pixel.
     */
    private static final int HEIGHT = 600;

    /**
     * Helper to create diagrams.
     */
    private final CreatorHelper helper = new CreatorHelper();

    /**
     * Initializes app with options and IO streams.
     *
     * @param options Command line options
     * @param ioStreams IO streams.
     * @param invoker Invoked the invokable.
     */
    public CliApp(final CliOptions options, final IOStreams ioStreams, final Invoker invoker) {
        super(options, ioStreams, invoker);
    }

    @Override
    public void execute() {
        if (!options.hasSyntaxFile()) {
            ioStreams.printlnErr("No syntax file given!");
            invoker.exit(ExitCode.NO_SYNTAX);
        }

        final String fileName = options.getSyntaxFile();

        try {
            if (options.isTextTree()) {
                final Parser parser  = Factory.newParserFromSource(new File(fileName), fileName);
                final Syntax ast     = parser.parse();
                final TextSyntaxTree visitor = new TextSyntaxTree();
                ast.accept(visitor);
                ioStreams.println(visitor.getText());
            } else {
                foo();
            }
        } catch (SyntaxException ex) {
            ioStreams.printlnErr("Syntax error: " + ex.getMessage());

            if (options.isDebug()) {
                ioStreams.printStackTraceToStdErr(ex);
            }

            invoker.exit(ExitCode.SYNTAX_ERROR);
        } catch (FileNotFoundException ex) {
            ioStreams.printlnErr(String.format("Can not read syntax file '%s'!", fileName));

            if (options.isDebug()) {
                ioStreams.printStackTraceToStdErr(ex);
            }

            invoker.exit(ExitCode.READ_ERROR);
        } catch (IOException ex) {
            ioStreams.printlnErr(String.format("Can not read syntax file '%s'!", fileName));

            if (options.isDebug()) {
                ioStreams.printStackTraceToStdErr(ex);
            }

            invoker.exit(ExitCode.READ_ERROR);
        }

        invoker.exit(ExitCode.OK);
    }

    /**
     * Play around with image generation.
     *
     * @deprecated Will be removed.
     */
    @Deprecated private void foo() {
        final RailroadDiagramImage img = new RailroadDiagramImage(WIDTH, HEIGHT, new File("./test.png"));
        final RailroadDiagram diagram = helper.createDiagram(img.getGraphics());
        diagram.setDebug(options.isDebug());
        img.setDiagram(diagram);
        img.paint();

        try {
            img.save();
        } catch (IOException ex) {
            ioStreams.getStderr().println("Can't write file!");
        }
    }

}
