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

import de.weltraumschaf.jebnf.gfx.shapes.curves.AbstractCurve;
import de.weltraumschaf.jebnf.gfx.shapes.compound.Choice;
import de.weltraumschaf.jebnf.gfx.shapes.compound.Loop;
import de.weltraumschaf.jebnf.gfx.shapes.compound.ColumnLayout;
import de.weltraumschaf.jebnf.gfx.shapes.compound.GridLayout;
import de.weltraumschaf.jebnf.gfx.shapes.compound.Option;
import de.weltraumschaf.jebnf.gfx.shapes.compound.Sequence;
import de.weltraumschaf.jebnf.gfx.shapes.text.Rule;
import de.weltraumschaf.jebnf.gfx.shapes.text.Identifier;
import de.weltraumschaf.jebnf.gfx.shapes.text.Terminal;
import de.weltraumschaf.jebnf.gfx.shapes.text.AbstractTextShape;
import de.weltraumschaf.jebnf.gfx.shapes.other.Empty;
import de.weltraumschaf.jebnf.gfx.shapes.other.End;
import de.weltraumschaf.jebnf.gfx.shapes.other.StraightNS;
import de.weltraumschaf.jebnf.gfx.shapes.other.StraightWE;
import de.weltraumschaf.jebnf.gfx.shapes.other.Start;
import de.weltraumschaf.jebnf.gfx.shapes.curves.CurveSE;
import de.weltraumschaf.jebnf.gfx.shapes.curves.CurveNW;
import de.weltraumschaf.jebnf.gfx.shapes.curves.CurveSW;
import de.weltraumschaf.jebnf.gfx.shapes.curves.CurveNE;
import de.weltraumschaf.jebnf.gfx.shapes.forkes.HForkSW;
import de.weltraumschaf.jebnf.gfx.shapes.forkes.HForkNE;
import de.weltraumschaf.jebnf.gfx.shapes.forkes.HForkSE;
import de.weltraumschaf.jebnf.gfx.shapes.forkes.HForkNW;
import de.weltraumschaf.jebnf.gfx.shapes.forkes.VForkNE;
import de.weltraumschaf.jebnf.gfx.shapes.forkes.VForkNW;
import de.weltraumschaf.jebnf.gfx.shapes.forkes.VForkSE;
import de.weltraumschaf.jebnf.gfx.shapes.forkes.VForkSW;

/**
 *
 * @author Sven Strittmatter <weltraumschaf@googlemail.com>
 */
public final class ShapeFactory {

    public enum Curves { NORTH_WEST, NORTH_EAST, SOUTH_WEST, SOUTH_EAST; }
    public enum Straights { NORT_SOUTH, WEST_EAST }

    private ShapeFactory() { }

    public static Empty empty() {
        return new Empty();
    }

    public static Empty[] empty(final int count) {
        final Empty[] empties = new Empty[count];
        for (int i = 0; i < count; ++i) {
            empties[i] = empty();
        }
        return empties;
    }

    public static Start start() {
        return new Start();
    }

    public static End end() {
        return new End();
    }

    public static AbstractCurve curve(final Curves type) {
        AbstractCurve curve;

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

    public static GridLayout grid() {
        return new GridLayout();
    }

    public static ColumnLayout column() {
        return new ColumnLayout();
    }

    public static ColumnLayout column(final Shape... shapes) {
        final ColumnLayout column = column();
        column.append(shapes);
        return column;
    }

    public static Sequence sequence() {
        return new Sequence();
    }

    public static Sequence sequence(final Shape... shapes) {
        final Sequence sequence = sequence();
        sequence.append(shapes);
        return sequence;
    }

    public static AbstractTextShape rule(final String name) {
        return new Rule(name);
    }

    public static AbstractTextShape terminal(final String value) {
        return new Terminal(value);
    }

    public static AbstractTextShape identifier(final String value) {
        return new Identifier(value);
    }

    public static Choice choice() {
        return new Choice();
    }

    public static Choice choice(final Shape... shapes) {
        final Choice choice = choice();
        for (Shape shape : shapes) {
            choice.addChoice(shape);
        }
        return choice;
    }

    public static Option option() {
        return new Option();
    }

    public static Option option(final Shape optional) {
        final Option option = option();
        option.setOptional(optional);
        return option;
    }

    public static Loop loop() {
        return new Loop();
    }

    public static Loop loop(final Shape looped) {
        final Loop loop = loop();
        loop.setLooped(looped);
        return loop;
    }

    public static Loop loop(final Shape looped, final Shape additional) {
        final Loop loop = loop(looped);
        loop.setAdditional(additional);
        return loop;
    }

}
