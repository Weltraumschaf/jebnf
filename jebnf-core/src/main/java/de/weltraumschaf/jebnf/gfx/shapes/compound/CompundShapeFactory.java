/*
 * LICENSE
 *
 * "THE BEER-WARE LICENSE" (Revision 43):
 * "Sven Strittmatter" <weltraumschaf(at)googlemail(dot)com> wrote this file.
 * As long as you retain this notice you can do whatever you want with
 * this stuff. If we meet some day, and you think this stuff is worth it,
 * you can buy me a non alcohol free beer in return.
 *
 * Copyright (C) 2012 "Sven Strittmatter" <weltraumschaf(at)googlemail(dot)com>
 */
package de.weltraumschaf.jebnf.gfx.shapes.compound;

import de.weltraumschaf.jebnf.gfx.shapes.Shape;
import de.weltraumschaf.jebnf.gfx.shapes.text.IdentifierShape;
import de.weltraumschaf.jebnf.gfx.shapes.text.RuleShape;
import de.weltraumschaf.jebnf.gfx.shapes.text.TerminalShape;
import de.weltraumschaf.jebnf.gfx.shapes.text.TextShape;

/**
 * Creates compound shapes.
 *
 * @todo Refactor to Builder Pattern.
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
    public static GridLayoutShape grid() {
        return new GridLayoutShape();
    }

    /**
     * Creates a column layout.
     *
     * @return Always return new instance.
     */
    public static ColumnLayoutShape column() {
        return new ColumnLayoutShape();
    }

    /**
     * Creates a column filled with given shapes.
     *
     * @param shapes Shapes appended to created column.
     * @return Always return new instance.
     */
    public static ColumnLayoutShape column(final Shape... shapes) {
        final ColumnLayoutShape column = column();
        column.append(shapes);
        return column;
    }

    /**
     * Creates a row.
     *
     * @return Always return new instance.
     */
    public static RowLayoutShape row() {
        return new RowLayoutShape();
    }

    /**
     * Creates a row filed with given shapes.
     *
     * @param shapes Shapes appended to created row.
     * @return Always return new instance.
     */
    public static RowLayoutShape row(final Shape... shapes) {
        final RowLayoutShape sequence = row();
        sequence.append(shapes);
        return sequence;
    }

    /**
     * Creates a rule shape.
     *
     * @param name RuleShape name.
     * @return Always return new instance.
     */
    public static RuleShape rule(final String name) {
        return new RuleShape(name);
    }

    /**
     * Creates a terminal shape.
     *
     * @param value TerminalShape value.
     * @return Always return new instance.
     */
    public static TextShape terminal(final String value) {
        return new TerminalShape(value);
    }

    /**
     * Creates a identifier shape.
     *
     * @param value IdentifierShape value.
     * @return Always return new instance.
     */
    public static TextShape identifier(final String value) {
        return new IdentifierShape(value);
    }

    /**
     * Creates a choice shape.
     *
     * @return Always return new instance.
     */
    public static ChoiceShape choice() {
        return new ChoiceShape();
    }

    /**
     * Creates a choice value filed with given shapes.
     *
     * @param shapes Shapes added to created choice.
     * @return Always return new instance.
     */
    public static ChoiceShape choice(final Shape... shapes) {
        final ChoiceShape choice = choice();
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
    public static OptionShape option() {
        return new OptionShape();
    }

    /**
     * Creates an option shape filled with option shapes.
     *
     * @param optional Optional shape..
     * @return Always return new instance.
     */
    public static OptionShape option(final Shape optional) {
        final OptionShape option = option();
        option.setOptional(optional);
        return option;
    }

    /**
     * Creates a loop shape.
     *
     * @return Always return new instance.
     */
    public static LoopShape loop() {
        return new LoopShape();
    }

    /**
     * Creates a loop shape filed with shapes.
     *
     * @param looped Looped shape.
     * @return Always return new instance.
     */
    public static LoopShape loop(final Shape looped) {
        final LoopShape loop = loop();
        loop.setLooped(looped);
        return loop;
    }

    /**
     * Creates a loop shape with looped shape and additional shape.
     * @param looped Looped shape.
     * @param additional Additional shape.
     * @return Always return new instance.
     */
    public static LoopShape loop(final Shape looped, final Shape additional) {
        final LoopShape loop = loop(looped);
        loop.setAdditional(additional);
        return loop;
    }

}
