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
 * Provides an AST builder.
 *
 * <p>This package is a part of the open-source
 * <a href="https://github.com/Weltraumschaf/jebnf">JEBNF library</a></p>
 *
 * <p>This package provides a builder factory implemented as an internal DSL to generate an
 * Abstract Syntax Tree for an EBNF syntax.</p>
 *
 * <h2>Example</h2>
 * <code>
 * import static de.weltraumschaf.ebnf.ast.builder.SyntaxBuilder.syntax;
 * ...
 * Syntax syntax = syntax("EBNF defined in itself.")
 *     .rule("syntax")
 *         .sequence()
 *             .option()
 *                 .identifier("title")
 *             .end()
 *             .terminal("{")
 *             .loop()
 *                 .identifier("rule")
 *             .end()
 *             .terminal("}")
 *             .option()
 *                 .identifier("comment")
 *             .end()
 *         .end()
 *     .end()
 * .build();
 * ...
 * </code>
 */
package de.weltraumschaf.jebnf.ast.builder;
