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

import de.weltraumschaf.jebnf.gfx.shapes.compound.*;
import de.weltraumschaf.jebnf.gfx.shapes.curves.*;
import de.weltraumschaf.jebnf.gfx.shapes.other.*;
import de.weltraumschaf.jebnf.gfx.shapes.text.*;

/**
 * Factory to create shape objects.
 *
 * @author Sven Strittmatter <weltraumschaf@googlemail.com>
 */
public final class ShapeFactory {

    /**
     * Private constructor for pure static utility class.
     */
    private ShapeFactory() {
        super();
    }

    /**
     * Create empty shape.
     *
     * @return Always return new instance.
     */
    public static Empty empty() {
        return new Empty();
    }

    /**
     * Return array of empty shapes.
     *
     * @param count How many shapes to create.
     * @return Always return new instances.
     */
    public static Empty[] empty(final int count) {
        final Empty[] empties = new Empty[count];
        for (int i = 0; i < count; ++i) {
            empties[i] = empty();
        }
        return empties;
    }

    /**
     * Create railroad start shape.
     *
     * @return Always return new instance.
     */
    public static Start start() {
        return new Start();
    }

    /**
     * Create railroad end shape.
     *
     * @return Always return new instance.
     */
    public static End end() {
        return new End();
    }

    /**
     * Creates one of the {@link Curves "curves"}.
     *
     * @param type Type of curve to create.
     * @return Always return new instance.
     */
    public static Curve curve(final Curves type) {
        return StraightAndCurveShapeFactory.curve(type);
    }

    /**
     * Create one of the {@link Straights "striaghts"}.
     *
     * @param type Type of straight to create.
     * @return Always return new instance.
     */
    public static Shape straight(final Straights type) {
        return StraightAndCurveShapeFactory.straight(type);
    }

    /**
     * Creates a fork railroad shape.
     *
     * A fork is a combination of a straight and curve.
     *
     * @param orientation Type of straight.
     * @param curve Type of curve.
     * @return Always return new instance.
     */
    public static Shape fork(final Straights orientation, final Curves curve) {
        return ForkShapeFactory.fork(orientation, curve);
    }

    /**
     * Creates a grid layout.
     *
     * @return Always return new instance.
     */
    public static GridLayout grid() {
        return CompundShapeFactory.grid();
    }

    /**
     * Creates a column layout.
     *
     * @return Always return new instance.
     */
    public static ColumnLayout column() {
        return CompundShapeFactory.column();
    }

    /**
     * Creates a column filled with given shapes.
     *
     * @param shapes Shapes appended to created column.
     * @return Always return new instance.
     */
    public static ColumnLayout column(final Shape... shapes) {
        return CompundShapeFactory.column(shapes);
    }

    /**
     * Creates a row.
     *
     * @return Always return new instance.
     */
    public static RowLayout row() {
        return CompundShapeFactory.row();
    }

    /**
     * Creates a row filed with given shapes.
     *
     * @param shapes Shapes appended to created row.
     * @return Always return new instance.
     */
    public static RowLayout row(final Shape... shapes) {
        return CompundShapeFactory.row(shapes);
    }

    /**
     * Creates a rule shape.
     *
     * @param name Rule name.
     * @return Always return new instance.
     */
    public static TextShape rule(final String name) {
        return CompundShapeFactory.rule(name);
    }

    /**
     * Creates a terminal shape.
     *
     * @param value Terminal value.
     * @return Always return new instance.
     */
    public static TextShape terminal(final String value) {
        return CompundShapeFactory.terminal(value);
    }

    /**
     * Creates a identifier shape.
     *
     * @param value Identifier value.
     * @return Always return new instance.
     */
    public static TextShape identifier(final String value) {
        return CompundShapeFactory.identifier(value);
    }

    /**
     * Creates a choice shape.
     *
     * @return Always return new instance.
     */
    public static Choice choice() {
        return CompundShapeFactory.choice();
    }

    /**
     * Creates a choice value filed with given shapes.
     *
     * @param shapes Shapes added to created choice.
     * @return Always return new instance.
     */
    public static Choice choice(final Shape... shapes) {
        return CompundShapeFactory.choice(shapes);
    }

    /**
     * Creates an option shape.
     *
     * @return Always return new instance.
     */
    public static Option option() {
        return CompundShapeFactory.option();
    }

    /**
     * Creates an option shape filled with option shapes.
     *
     * @param optional Optional shape..
     * @return Always return new instance.
     */
    public static Option option(final Shape optional) {
        return CompundShapeFactory.option(optional);
    }

    /**
     * Creates a loop shape.
     *
     * @return Always return new instance.
     */
    public static Loop loop() {
        return CompundShapeFactory.loop();
    }

    /**
     * Creates a loop shape filed with shapes.
     *
     * @param looped Looped shape.
     * @return Always return new instance.
     */
    public static Loop loop(final Shape looped) {
        return CompundShapeFactory.loop(looped);
    }

    /**
     * Creates a loop shape with looped shape and additional shape.
     * @param looped Looped shape.
     * @param additional Additional shape.
     * @return Always return new instance.
     */
    public static Loop loop(final Shape looped, final Shape additional) {
        return CompundShapeFactory.loop(looped, additional);
    }

}
