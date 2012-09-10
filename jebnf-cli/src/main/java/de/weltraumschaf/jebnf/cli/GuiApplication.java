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

import de.weltraumschaf.jebnf.gfx.CreatorHelper;
import de.weltraumschaf.jebnf.gfx.RailroadDiagram;
import de.weltraumschaf.jebnf.gui.RailroadDiagramPanel;
import java.awt.Dimension;
import javax.swing.JFrame;
import javax.swing.SwingUtilities;
import de.weltraumschaf.commons.IOStreams;
import java.io.IOException;

/**
 * Runs the GUI IDE.
 *
 * @author Sven Strittmatter <weltraumschaf@googlemail.com>
 */
public class GuiApplication extends ApplicationAdapter implements Runnable {

    /**
     * Default diagram width.
     */
    private static final int WIDTH = 800;

    /**
     * Default diagram height.
     */
    private static final int HEIGHT = 600;

    /**
     * Main desktop window.
     */
    private final JFrame frame = new JFrame();

    /**
     * USed to generated a diagram dor testing/development.
     */
    private final CreatorHelper helper = new CreatorHelper();

    /**
     * Initializes app with options and IO streams.
     *
     * @param options Command line options
     * @param ioStreams IO streams.
     * @param invoker Invoked the invokable.
     */
    public GuiApplication(final CliOptions options, final IOStreams ioStreams, final Main invoker) {
        super(options, ioStreams, invoker);
    }

    /**
     * {@link SwingUtilities#invokeLater(java.lang.Runnable) "Invokes itself later"}.
     */
    @Override
    public void execute() throws IOException {
        SwingUtilities.invokeLater(this);
    }

    @Override
    public void run() {
        frame.setTitle("Railroad");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(new Dimension(WIDTH, HEIGHT));
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
        final RailroadDiagram diagram = helper.createDiagram(frame.getGraphics());
        diagram.setDebug(options.isDebug());
        final RailroadDiagramPanel panel = new RailroadDiagramPanel(diagram);
        frame.add(panel);
        frame.validate();
    }

}
