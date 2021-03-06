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
package de.weltraumschaf.jebnf.cli;

/**
 * Supported output formats.
 *
 * @author Sven Strittmatter <weltraumschaf@googlemail.com>
 */
enum OutputFormat {

    /**
     * Prints the parsed syntax tree as ASCII text tree.
     */
    TREE,

    /**
     * Prints the parsed syntax tree as XML.
     */
    XML,

    /**
     * Prints the railroad diagram as Jpeg.
     */
    JPG,

    /**
     * Prints the railroad diagram as gif.
     */
    GIF,

    /**
     * Prints the railroad diagram as png.
     */
    PNG;

}
