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

package de.weltraumschaf.jebnf.cli;

import de.weltraumschaf.jebnf.ast.nodes.Syntax;
import java.io.IOException;

/**
 * Interface to describe command pattern.
 *
 * XXX: Good candidate for de.weltraumschaf.commons.
 *
 * @author Sven Strittmatter <weltraumschaf@googlemail.com>
 */
public interface Application {

    /**
     * Executable command method.
     */
    void execute() throws IOException ;

    void setSyntax(Syntax ast);

}
