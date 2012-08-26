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

/**
 * Supported output formats.
 *
 * TODO Add PNG.
 *
 * @author Sven Strittmatter <weltraumschaf@googlemail.com>
 */
public enum OutputFormat {

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
    GIF;

}
