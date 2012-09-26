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

import de.weltraumschaf.jebnf.gfx.RailroadDiagram;
import java.awt.Graphics;
import java.awt.Graphics2D;
import javax.swing.JPanel;

/**
 * {@link javax.swing.JPanel "GUI component"} to display a railroad diagram.
 *
 * @author Sven Strittmatter <weltraumschaf@googlemail.com>
 */
public final class RailroadDiagramPanel extends JPanel {

    /**
     * Diagram to display.
     */
    private final RailroadDiagram diagram;

    /**
     * Initializes the panel with diagram to show.
     *
     * @param diagram Diagram to display.
     */
    public RailroadDiagramPanel(final RailroadDiagram diagram) {
        this.diagram = diagram;
    }

    @Override
    public void paintComponent(final Graphics graphic) {
        super.paintComponent(graphic);
        diagram.paint((Graphics2D) graphic);
    }

}
