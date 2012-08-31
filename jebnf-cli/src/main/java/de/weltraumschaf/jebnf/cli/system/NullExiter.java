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

package de.weltraumschaf.jebnf.cli.system;

/**
 * Throws a {@link ExitException} instead of calling {@link System#exit(int)}.
 *
 * Useful in tests where you don't want to exit the JVM.
 *
 * @author Sven Strittmatter <weltraumschaf@googlemail.com>
 */
public class NullExiter extends BaseExiter {

    @Override
    public void exit(final int status) {
        throw new ExitException();
    }

    /**
     * Thrown on exit call to break program execution.
     */
    public static class ExitException extends RuntimeException {

        /**
         * Version id for serialization.
         */
        private static final long serialVersionUID = 1L;

    }

}
