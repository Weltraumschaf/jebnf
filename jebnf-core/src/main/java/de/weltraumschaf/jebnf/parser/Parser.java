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
package de.weltraumschaf.jebnf.parser;

import de.weltraumschaf.jebnf.ast.nodes.SyntaxNode;
import java.io.IOException;

/**
 * Interface for parser.
 *
 * @author Sven Strittmatter <weltraumschaf@googlemail.com>
 */
public interface Parser {

    /**
     * Parse the token stream.
     *
     * @return Parsed syntax tree.
     * @throws SyntaxException On parse or scan errors.
     * @throws IOException On source input io errors.
     */
    SyntaxNode parse() throws SyntaxException, IOException;

}
