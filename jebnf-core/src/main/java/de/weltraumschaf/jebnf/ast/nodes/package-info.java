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
/**
 * All available AST nodes.
 *
 * <p>
 * This package is a part of the open-source
 * <a href="https://github.com/Weltraumschaf/jebnf">JEBNF library</a>
 *
 * <p>
 * This package contains all available nodes an EBNF AST may have.
 *
 * <p>
 * The nodes are dived in three types:
 *
 * <ol>
 *  <li>Leafs: The nodes {@link de.weltraumschaf.jebnf.ast.nodes.IdentifierNode},
 *      {@link de.weltraumschaf.jebnf.ast.nodes.TerminalNode}, and
 *      {@link de.weltraumschaf.jebnf.ast.nodes.NullNode} are leafs. They can not contain other nodes
 *      as children.
 *  <li>Composites: All other nodes are composite nodes and may have one or more child nodes.
 *  <li>Special: The leaf node {@link de.weltraumschaf.jebnf.ast.nodes.NullNode} is a special
 *      purpose node mostly used in the unit tests or to prevent NullPointerExceptions.
 *      It is used as default parent node for all composite nodes.
 * </ol>
 */
package de.weltraumschaf.jebnf.ast.nodes;
