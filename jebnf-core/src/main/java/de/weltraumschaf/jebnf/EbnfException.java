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

/**
 * Represents an  error which provides an error code.
 *
 * @author Sven Strittmatter <weltraumschaf@googlemail.com>
 */
public class EbnfException extends RuntimeException {

    /**
     * Version id for serialization.
     */
    private static final long serialVersionUID = 1L;

    /**
     * The error code.
     *
     * By default this is -1.
     */
    private final int code;

    /**
     * Initializes with error message.
     *
     * Initializes code with -1.
     *
     * @param message Error message.
     */
    public EbnfException(final String message) {
        this(message, ExitCodeImpl.FATAL_ERROR);
    }

    /**
     * Initializes with error message and exit code.
     *
     * @param message Error message.
     * @param code Exit code.
     */
    public EbnfException(final String message, final ExitCodeImpl code) {
        this(message, code.getCode());
    }

    /**
     * Initializes with error message and code.
     *
     * @param message Error message.
     * @param code    Error code.
     */
    public EbnfException(final String message, final int code) {
        this(message, code, null);
    }

    /**
     * Initializes with error message and exit code.
     *
     * @param message Error message.
     * @param code Exit code.
     * @param cause Previous error.
     */
    public EbnfException(final String message, final ExitCodeImpl code, final Throwable cause) {
        this(message, code.getCode(), cause);
    }

    /**
     * Dedicated constructor.
     *
     * @param message Error message.
     * @param code    Error code.
     * @param cause   Previous error.
     */
    public EbnfException(final String message, final int code, final Throwable cause) {
        super(message, cause);
        this.code = code;
    }

    /**
     * Returns the error code.
     *
     * @return The error code.
     */
    public int getCode() {
        return code;
    }

}
