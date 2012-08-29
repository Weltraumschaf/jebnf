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

package de.weltraumschaf.jebnf.gfx.shapes;

import de.weltraumschaf.jebnf.gfx.shapes.compound.Choice;
import de.weltraumschaf.jebnf.gfx.shapes.compound.ColumnLayout;
import de.weltraumschaf.jebnf.gfx.shapes.compound.GridLayout;
import de.weltraumschaf.jebnf.gfx.shapes.compound.Loop;
import de.weltraumschaf.jebnf.gfx.shapes.compound.Option;
import de.weltraumschaf.jebnf.gfx.shapes.compound.RowLayout;
import de.weltraumschaf.jebnf.gfx.shapes.text.Identifier;
import de.weltraumschaf.jebnf.gfx.shapes.text.Rule;
import de.weltraumschaf.jebnf.gfx.shapes.text.Terminal;
import de.weltraumschaf.jebnf.gfx.shapes.text.TextShape;

/**
 * Creates compound shapes.
 *
 * @author Sven Strittmatter <weltraumschaf@googlemail.com>
 */
public final class CompundShapeFactory {

    /**
     * Private constructor for pure static utility class.
     */
    private CompundShapeFactory() {
        super();
    }

    /**
     * Creates a grid layout.
     *
     * @return Always return new instance.
     */
    public static GridLayout grid() {
        return new GridLayout();
    }

    /**
     * Creates a column layout.
     *
     * @return Always return new instance.
     */
    public static ColumnLayout column() {
        return new ColumnLayout();
    }

    /**
     * Creates a column filled with given shapes.
     *
     * @param shapes Shapes appended to created column.
     * @return Always return new instance.
     */
    public static ColumnLayout column(final Shape... shapes) {
        final ColumnLayout column = column();
        column.append(shapes);
        return column;
    }

    /**
     * Creates a row.
     *
     * @return Always return new instance.
     */
    public static RowLayout row() {
        return new RowLayout();
    }

    /**
     * Creates a row filed with given shapes.
     *
     * @param shapes Shapes appended to created row.
     * @return Always return new instance.
     */
    public static RowLayout row(final Shape... shapes) {
        final RowLayout sequence = row();
        sequence.append(shapes);
        return sequence;
    }

    /**
     * Creates a rule shape.
     *
     * @param name Rule name.
     * @return Always return new instance.
     */
    public static TextShape rule(final String name) {
        return new Rule(name);
    }

    /**
     * Creates a terminal shape.
     *
     * @param value Terminal value.
     * @return Always return new instance.
     */
    public static TextShape terminal(final String value) {
        return new Terminal(value);
    }

    /**
     * Creates a identifier shape.
     *
     * @param value Identifier value.
     * @return Always return new instance.
     */
    public static TextShape identifier(final String value) {
        return new Identifier(value);
    }

    /**
     * Creates a choice shape.
     *
     * @return Always return new instance.
     */
    public static Choice choice() {
        return new Choice();
    }

    /**
     * Creates a choice value filed with given shapes.
     *
     * @param shapes Shapes added to created choice.
     * @return Always return new instance.
     */
    public static Choice choice(final Shape... shapes) {
        final Choice choice = choice();
        for (Shape shape : shapes) {
            choice.addChoice(shape);
        }
        return choice;
    }

    /**
     * Creates an option shape.
     *
     * @return Always return new instance.
     */
    public static Option option() {
        return new Option();
    }

    /**
     * Creates an option shape filled with option shapes.
     *
     * @param optional Optional shape..
     * @return Always return new instance.
     */
    public static Option option(final Shape optional) {
        final Option option = option();
        option.setOptional(optional);
        return option;
    }

    /**
     * Creates a loop shape.
     *
     * @return Always return new instance.
     */
    public static Loop loop() {
        return new Loop();
    }

    /**
     * Creates a loop shape filed with shapes.
     *
     * @param looped Looped shape.
     * @return Always return new instance.
     */
    public static Loop loop(final Shape looped) {
        final Loop loop = loop();
        loop.setLooped(looped);
        return loop;
    }

    /**
     * Creates a loop shape with looped shape and additional shape.
     * @param looped Looped shape.
     * @param additional Additional shape.
     * @return Always return new instance.
     */
    public static Loop loop(final Shape looped, final Shape additional) {
        final Loop loop = loop(looped);
        loop.setAdditional(additional);
        return loop;
    }

}
