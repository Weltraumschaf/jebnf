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
package de.weltraumschaf.jebnf;

import de.weltraumschaf.commons.system.ExitCode;

/**
 * Enumerates the exit codes with an associated error code number.
 *
 * @author Sven Strittmatter <weltraumschaf@googlemail.com>
 */
public enum ExitCodeImpl implements ExitCode {

    /**
     * Exit code for everything ok.
     */
    OK(0),
    /**
     * Exit code for read errors.
     */
    READ_ERROR(1),
    /**
     * Exit code for missing syntax file.
     */
    NO_SYNTAX(2),
    /**
     * Exit code for syntax errors.
     */
    SYNTAX_ERROR(3),
    /**
     * Exit code for all other fatal errors.
     */
    FATAL_ERROR(-1);

    /**
     * The exit codes error code number.
     */
    private final int code;

    /**
     * Initializes the enum with code.
     *
     * @param code Integer error code.
     */
    private ExitCodeImpl(final int code) {
        this.code = code;
    }

    /**
     * Returns the associated error code.
     *
     * Will be != 0 on error.
     *
     * @return The error code.
     */
    public int getCode() {
        return code;
    }

}
