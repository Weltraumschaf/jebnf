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

package de.weltraumschaf.jebnf.gfx;

import static de.weltraumschaf.jebnf.gfx.shapes.ShapeFactory.*;
import de.weltraumschaf.jebnf.gfx.shapes.compound.GridLayout;
import de.weltraumschaf.jebnf.gfx.shapes.compound.Loop;
import de.weltraumschaf.jebnf.gfx.shapes.compound.Option;
import de.weltraumschaf.jebnf.gfx.shapes.compound.RowLayout;
import java.awt.Graphics;
import java.awt.Graphics2D;

/**
 * Helper to create some diagrams for debugging and development.
 *
 * @deprecated Will be removed. Only used for development.
 * @author Sven Strittmatter <weltraumschaf@googlemail.com>
 */
@Deprecated
public class CreatorHelper {

    /**
     * Creates a diagram for the "value syntax" of JavaScript. See json.org.
     *
     * @param graphics Graphics context.
     * @return Grid layout object.
     */
    public GridLayout createValueDiagram(final Graphics2D graphics) {
        final GridLayout value = grid();
        value.append(
            column().append(
                rule("value"),
                start(),
                empty(),
                empty(),
                empty(),
                empty()
            )
        );
        value.set(1, 1, choice(
            identifier("string"),
            identifier("number"),
            identifier("object"),
            terminal("true"),
            terminal("false")
        ));
        value.append(
            column().append(
                empty(),
                end(),
                empty(),
                empty(),
                empty(),
                empty()
            )
        );
        value.adjust(graphics);
        return value;
    }

    /**
     * Creates a diagram for the "object syntax" of JavaScript. See json.org.
     *
     * @param graphics Graphics context.
     * @return Grid layout object.
     */
    public GridLayout createObjectDiagram(final Graphics2D graphics) {
        final GridLayout object = grid();
        final RowLayout innerSequence = sequence(identifier("string"),
                                                terminal(":"),
                                                identifier("value"));
        innerSequence.adjust(graphics);
        final Loop loop = loop(innerSequence, terminal(","));
        loop.adjust(graphics);
        final Option option = option(loop);

        object.append(
            column().append(
                rule("object"),
                start(),
                empty(),
                empty()
            )
        );
        object.append(
            column().append(
                empty(),
                terminal("{"),
                empty(),
                empty()
            )
        );
        object.append(
            column().append(
                empty(),
                option
            )
        );
        object.append(
            column().append(
                empty(),
                terminal("}"),
                empty(),
                empty()
            )
        );
        object.append(
            column().append(
                empty(),
                end(),
                empty(),
                empty()
            )
        );
        object.adjust(graphics);
        return object;
    }

    /**
     * Casts the given graphics object to {@link Graphics2D} and invokes {@link #createDiagram(java.awt.Graphics2D)}.
     *
     * @param graphics Graphics context.
     * @return New instance.
     */
    public RailroadDiagram createDiagram(final Graphics graphics) {
        return createDiagram((Graphics2D) graphics);
    }

    /**
     * Creates a diagram with {@link #createValueDiagram(java.awt.Graphics2D)}.
     *
     * @param graphics Graphics context.
     * @return New instance.
     */
    public RailroadDiagram createDiagram(final Graphics2D graphics) {
        final Point offset = new Point(20, 20);
        final RailroadDiagram diagram = new RailroadDiagram();
        final GridLayout value = createValueDiagram(graphics);
        value.setPosition(offset);
        diagram.add(value);

        final GridLayout object = createObjectDiagram(graphics);
        object.setPosition(new Point(offset.x, offset.y + value.getSize().height));
        diagram.add(object);

        return diagram;
    }

}
