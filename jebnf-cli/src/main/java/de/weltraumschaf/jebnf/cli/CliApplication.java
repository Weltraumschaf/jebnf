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
import de.weltraumschaf.jebnf.ast.visitor.RailroadDiagramVisitor;
import de.weltraumschaf.jebnf.ast.visitor.TextSyntaxTreeVisitor;
import de.weltraumschaf.jebnf.ast.visitor.Visitor;
import de.weltraumschaf.jebnf.ast.visitor.XmlVisitor;
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
            generateTextTree();
        } else if (options.getOutputFormat() == OutputFormat.XML) {
            generateXmlTree();
        } else {
            generateRailroadImage();
        }

        invoker.exit(ExitCodeImpl.OK);
    }

    /**
     * Play around with image generation.
     */
    private void generateRailroadImage() {
        final Visitor<RailroadDiagram> visitor = new RailroadDiagramVisitor();
        syntax.accept(visitor);

        final RailroadDiagram diagram = visitor.getResult();
        diagram.setDebug(options.isDebug());

        final RailroadDiagramImage img = new RailroadDiagramImage(WIDTH, HEIGHT, new File(options.getOutputFile()));
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
     * @throws IOException On write error to output file.
     */
    private void generateTextTree() throws IOException {
        final Visitor<String> visitor = new TextSyntaxTreeVisitor();
        syntax.accept(visitor);
        handleOutput(visitor.getResult());
    }

    /**
     * Generate XML from the syntax tree.
     *
     * @throws IOException On write error to output file.
     */
    private void generateXmlTree() throws IOException {
        final Visitor<String> visitor = new XmlVisitor();
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
