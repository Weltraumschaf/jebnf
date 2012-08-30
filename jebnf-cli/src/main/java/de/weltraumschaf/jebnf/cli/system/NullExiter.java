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
 *
 * @author Sven Strittmatter <weltraumschaf@googlemail.com>
 */
public class NullExiter implements Exitable {

    @Override
    public void exit(int status) {
        // Ignore it.
    }

}
