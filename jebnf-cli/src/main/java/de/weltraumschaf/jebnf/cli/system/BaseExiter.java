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

import de.weltraumschaf.jebnf.ExitCode;

/**
 * Common shared functionality for exiters.
 *
 * @author Sven Strittmatter <weltraumschaf@googlemail.com>
 */
public abstract class BaseExiter implements Exitable {

    @Override
    public void exit(ExitCode status) {
        exit(status.getCode());
    }

}
