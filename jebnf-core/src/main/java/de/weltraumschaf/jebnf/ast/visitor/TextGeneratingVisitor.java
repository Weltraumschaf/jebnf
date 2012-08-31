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

package de.weltraumschaf.jebnf.ast.visitor;

/**
 * Visitors which generates a textual representation of the visited tree.
 *
 * @author Sven Strittmatter <weltraumschaf@googlemail.com>
 */
public interface TextGeneratingVisitor extends Visitor {


    /**
     * Returns the text string the visitor generates.
     *
     * @return Returns XML string.
     */
    String getText();

}
