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

import de.weltraumschaf.jebnf.gfx.shapes.Shape;
import static de.weltraumschaf.jebnf.gfx.shapes.ShapeFactory.*;
import de.weltraumschaf.jebnf.gfx.shapes.StraightShapes;
import de.weltraumschaf.jebnf.gfx.shapes.compound.GridLayoutShape;
import de.weltraumschaf.jebnf.gfx.shapes.compound.LoopShape;
import de.weltraumschaf.jebnf.gfx.shapes.compound.OptionShape;
import de.weltraumschaf.jebnf.gfx.shapes.compound.RowLayoutShape;
import java.awt.Graphics;
import java.awt.Graphics2D;

/**
 * Helper to create some diagrams for debugging and development.
 *
 * TODO Remove this.
 *
 * @deprecated Will be removed. Only used for development.
 * @author Sven Strittmatter <weltraumschaf@googlemail.com>
 */
@Deprecated
public class CreatorHelper {

    private static final int START_X = 20;

    private static final int START_Y = 20;

    public GridLayoutShape createStraights(final Graphics2D graphics) {
        final GridLayoutShape straights = grid();
        straights.append(
            column().append(
                row().append(
                    empty(),
                    straight(StraightShapes.WEST_EAST),
                    straight(StraightShapes.WEST_EAST),
                    empty(),
                    straight(StraightShapes.NORT_SOUTH),
                    empty()
                )
            )
        );

        straights.adjust(graphics);
        return straights;
    }

    /**
     * Creates a diagram for the "value syntax" of JavaScript. See json.org.
     *
     * @param graphics Graphics context.
     * @return Grid layout object.
     */
    public GridLayoutShape createValueDiagram(final Graphics2D graphics) {
        final GridLayoutShape value = grid();
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
    public GridLayoutShape createObjectDiagram(final Graphics2D graphics) {
        final GridLayoutShape object = grid();
        final RowLayoutShape innerSequence = row(identifier("string"),
                                                terminal(":"),
                                                identifier("value"));
        innerSequence.adjust(graphics);
        final LoopShape loop = loop(innerSequence, terminal(","));
        loop.adjust(graphics);
        final OptionShape option = option(loop);

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
        Point offset = new Point(START_X, START_Y);
        final RailroadDiagram diagram = new RailroadDiagram(true);

        final Shape straights = createStraights(graphics);
        straights.setPosition(offset);
        diagram.add(straights);

        offset = offset.moveY(straights.getSize().getHeight());
        final Shape value = createValueDiagram(graphics);
        value.setPosition(offset);
        diagram.add(value);

        offset = offset.moveY(value.getSize().getHeight());
        final Shape object = createObjectDiagram(graphics);
        object.setPosition(offset);
        diagram.add(object);

        return diagram;
    }

}
