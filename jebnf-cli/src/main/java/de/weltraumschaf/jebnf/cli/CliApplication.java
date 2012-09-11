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

import de.weltraumschaf.commons.IOStreams;
import de.weltraumschaf.jebnf.ExitCodeImpl;
import de.weltraumschaf.jebnf.ast.nodes.Syntax;
import de.weltraumschaf.jebnf.ast.visitor.TextSyntaxTree;
import de.weltraumschaf.jebnf.ast.visitor.Visitor;
import de.weltraumschaf.jebnf.ast.visitor.Xml;
import de.weltraumschaf.jebnf.gfx.CreatorHelper;
import de.weltraumschaf.jebnf.gfx.RailroadDiagram;
import de.weltraumschaf.jebnf.gfx.RailroadDiagramImage;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;

/**
 * Command line application.
 *
 * @author Sven Strittmatter <weltraumschaf@googlemail.com>
 */
public class CliApplication extends ApplicationAdapter {

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
    public CliApplication(final CliOptions options, final IOStreams ioStreams, final Main invoker) {
        super(options, ioStreams, invoker);
    }

    @Override
    public void execute() throws IOException {
        if (options.getOutputFormat() == OutputFormat.TREE) {
            generateTextTree(syntax);
        } else if (options.getOutputFormat() == OutputFormat.XML) {
            generateXmlTree(syntax);
        } else {
            generateRailroadImage(syntax);
        }

        invoker.exit(ExitCodeImpl.OK);
    }

    /**
     * Play around with image generation.
     *
     * XXX: Does at the moment print always the same thing for development.
     *
     * @param syntax Syntax tree to format.
     */
    private void generateRailroadImage(final Syntax syntax) {
        final File outputFile = new File(options.getOutputFile());
        final RailroadDiagramImage img = new RailroadDiagramImage(WIDTH, HEIGHT, outputFile);
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

    /**
     * Generate ASCII text tree from syntax tree.
     *
     * @param syntax Syntax tree to format.
     * @throws IOException On write error to output file.
     */
    private void generateTextTree(final Syntax syntax) throws IOException {
        final Visitor<String> visitor = new TextSyntaxTree();
        syntax.accept(visitor);
        handleOutput(visitor.getResult());
    }

    /**
     * Generate XML from the syntax tree.
     *
     * @param syntax Syntax tree to format.
     * @throws IOException On write error to output file.
     */
    private void generateXmlTree(final Syntax syntax) throws IOException {
        final Visitor<String> visitor = new Xml();
        syntax.accept(visitor);
        handleOutput(visitor.getResult());
    }

    /**
     * Determines if the output should be written to STDOUT or file.
     *
     * @param output String to write.
     * @throws IOException On write error to output file.
     */
    private void handleOutput(final String output) throws IOException {
        if (options.hasOutputFile()) {
            final File file = new File(options.getOutputFile());
            final Writer writer = new BufferedWriter(new FileWriter(file));
            writer.write(output);
            writer.close();
        } else {
            ioStreams.println(output);
        }
    }

}
