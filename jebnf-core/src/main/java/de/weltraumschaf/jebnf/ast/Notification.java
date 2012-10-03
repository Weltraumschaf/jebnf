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
package de.weltraumschaf.jebnf.ast;

import com.google.common.collect.Lists;
import java.util.List;

/**
 * Used to notify collected AST errors.
 *
 * @author Sven Strittmatter <weltraumschaf@googlemail.com>
 */
public class Notification {

    /**
     * Collected errors.
     */
    private final List<String> errors = Lists.newArrayList();

    /**
     * Collect an line of error.
     *
     * The first parameter is a sprintf style format string.
     *
     * Example:
     * error(format, arg1, arg2 .. argN)
     *
     * @param format Format string.
     * @param args   Variable number of objects referenced in the format string.
     */
    public void error(final String format, final Object... args) {
        errors.add(String.format(format, args));
    }

    /**
     * Returns true if no error was collected.
     *
     * @return Returns true if no errors collected.
     */
    public boolean isOk() {
        return errors.isEmpty();
    }

    /**
     * Returns all errors concatenated as string.
     *
     * @return Returns empty string if {@link #isOk()} returns true, unless it returns all
     *        error messages concatenated.
     */
    public String report() {
        if (isOk()) {
            return "";
        }

        final StringBuilder report = new StringBuilder();

        for (int i = 0; i < errors.size(); ++i) {
            if (i > 0) {
                report.append(String.format("%n"));
            }

            report.append(errors.get(i));
        }

        return report.toString();
    }

}
