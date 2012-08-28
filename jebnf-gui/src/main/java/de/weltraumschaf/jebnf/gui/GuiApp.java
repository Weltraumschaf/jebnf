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

package de.weltraumschaf.jebnf.gui;

import de.weltraumschaf.jebnf.gfx.CreatorHelper;
import de.weltraumschaf.jebnf.gfx.RailroadDiagram;
import java.awt.Dimension;
import javax.swing.JFrame;
import javax.swing.SwingUtilities;

/**
 * Runs the GUI IDE.
 *
 * @author Sven Strittmatter <weltraumschaf@googlemail.com>
 */
public class GuiApp implements Runnable {

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
     * Whether to draw debug output in diagrams.
     */
    private final boolean debug;

    /**
     * Initializes app with debug option.
     *
     * @param debug Whether to draw debug output in diagrams.
     */
    public GuiApp(final boolean debug) {
        this.debug = debug;
    }

    /**
     * Creates a {@link GuiApp} object and {@link SwingUtilities#invokeLater(java.lang.Runnable) "invokes it later"}.
     *
     * @param debug Whether to draw debug output in diagrams.
     */
    public static void main(final boolean debug) {
        final GuiApp app = new GuiApp(debug);
        SwingUtilities.invokeLater(app);
    }

    @Override
    public void run() {
        frame.setTitle("Railroad");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(new Dimension(WIDTH, HEIGHT));
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
        final RailroadDiagram diagram = helper.createDiagram(frame.getGraphics());
        diagram.setDebug(debug);
        final RailroadDiagramPanel panel = new RailroadDiagramPanel(diagram);
        frame.add(panel);
        frame.validate();
    }

}
