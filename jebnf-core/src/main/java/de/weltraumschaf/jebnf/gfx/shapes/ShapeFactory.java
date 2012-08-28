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
import de.weltraumschaf.jebnf.gfx.shapes.forkes.*;
import de.weltraumschaf.jebnf.gfx.shapes.other.*;
import de.weltraumschaf.jebnf.gfx.shapes.text.*;

/**
 * Factory to create shape objects.
 *
 * @author Sven Strittmatter <weltraumschaf@googlemail.com>
 */
public final class ShapeFactory {

    /**
     * Type of curve railroad shapes.
     */
    public enum Curves {

        /**
         * Curve railroad from north to west.
         */
        NORTH_WEST,

        /**
         * Curve railroad from north to east.
         */
        NORTH_EAST,

        /**
         * Curve railroad from south to west.
         */
        SOUTH_WEST,

        /**
         * Curve railroad from south to east.
         */
        SOUTH_EAST;

    }

    /**
     * Type of straight railroad shapes.
     */
    public enum Straights {

        /**
         * Straight railroad from north to south.
         */
        NORT_SOUTH,

        /**
         * Straight railroad from west to east.
         */
        WEST_EAST;

    }

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
        Curve curve;

        switch (type) {
            case NORTH_EAST:
                curve = new CurveNE();
                break;
            case NORTH_WEST:
                curve = new CurveNW();
                break;
            case SOUTH_EAST:
                curve = new CurveSE();
                break;
            case SOUTH_WEST:
                curve = new CurveSW();
                break;
            default:
                throw new IllegalArgumentException(String.format("Unsupported type: %s!", type));
        }

        return curve;
    }

    /**
     * Create one of the {@link Straights "striaghts"}.
     *
     * @param type Type of straight to create.
     * @return Always return new instance.
     */
    public static Shape straight(final Straights type) {
        switch (type) {
            case NORT_SOUTH:
                return new StraightNS();
            case WEST_EAST:
                return new StraightWE();
            default:
                throw new IllegalArgumentException("Unsupported type: " + type + "!");
        }
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
        switch (orientation) {
            case NORT_SOUTH:
                return verticalFork(curve);
            case WEST_EAST:
                return horizontalFork(curve);
            default:
                throw new IllegalArgumentException("Unsupported orientation: " + orientation + "!");
        }
    }

    /**
     * Creates a fork with straight railroad from north to south.
     *
     * @param curve Type of curve.
     * @return Always return new instance.
     */
    private static Shape verticalFork(final Curves curve) {
        switch (curve) {
            case NORTH_EAST:
                return new VForkNE();
            case NORTH_WEST:
                return new VForkNW();
            case SOUTH_EAST:
                return new VForkSE();
            case SOUTH_WEST:
                return new VForkSW();
            default:
                throw new IllegalArgumentException("Unsupported curve: " + curve + "!");
        }
    }

    /**
     * Creates a fork with straight railroad from west to east.
     *
     * @param curve Type of curve.
     * @return Always return new instance.
     */
    private static Shape horizontalFork(final Curves curve) {
        switch (curve) {
            case NORTH_EAST:
                return new HForkNE();
            case NORTH_WEST:
                return new HForkNW();
            case SOUTH_EAST:
                return new HForkSE();
            case SOUTH_WEST:
                return new HForkSW();
            default:
                throw new IllegalArgumentException("Unsupported curve: " + curve + "!");
        }
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
     * Creates a sequence.
     *
     * @return Always return new instance.
     */
    public static RowLayout sequence() {
        return new RowLayout();
    }

    /**
     * Creates a sequence filed with given shapes.
     *
     * @param shapes Shapes appended to created sequence.
     * @return Always return new instance.
     */
    public static RowLayout sequence(final Shape... shapes) {
        final RowLayout sequence = sequence();
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
