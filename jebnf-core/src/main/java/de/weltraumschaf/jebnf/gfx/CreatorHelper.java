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

import de.weltraumschaf.jebnf.gfx.shapes.GridLayout;
import de.weltraumschaf.jebnf.gfx.shapes.Loop;
import de.weltraumschaf.jebnf.gfx.shapes.Option;
import de.weltraumschaf.jebnf.gfx.shapes.Sequence;
import static de.weltraumschaf.jebnf.gfx.shapes.ShapeFactory.*;
import java.awt.Graphics;
import java.awt.Graphics2D;

/**
 *
 * @author Sven Strittmatter <weltraumschaf@googlemail.com>
 */
public class CreatorHelper {

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

    public GridLayout createObjectDiagram(final Graphics2D graphics) {
        final GridLayout object = grid();
        final Sequence innerSequence = sequence(identifier("string"),
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

    public RailroadDiagram createDiagram(final Graphics graphics) {
        return createDiagram((Graphics2D) graphics);
    }

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
