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

package de.weltraumschaf.jebnf.parser;

import java.io.*;
import java.net.URI;

/**
 * Factory to create {@link Scanner scanners} and {@link Parser parsers}.
 *
 * @author Sven Strittmatter <weltraumschaf@googlemail.com>
 */
public final class Factory {

    /**
     * Private constructor for pure static utility class.
     */
    private Factory() {
        super();
    }

    /**
     * Creates new scanner from given reader.
     *
     * Initializes the {@link de.weltraumschaf.jebnf.parser.Scanner#fileName} of the scanner with an
     * empty string.
     *
     * @param reader Input to scan.
     * @return Return new scanner instance.
     */
    public static Scanner newScanner(final Reader reader) {
        return newScanner(reader, "");
    }

    /**
     * Creates new scanner from given reader and file name.
     *
     * The parameter fileName is not used for file IO. It is only for information to know from
     * "where" you're reading. If you read from something else as an file you may set fileName string
     * to any informative string you want or leave empty.
     *
     * @param reader Input to scan.
     * @param fileName Name of scanned file. this may be any string if not reading from file.
     * @return Return new scanner instance.
     */
    public static Scanner newScanner(final Reader reader, final String fileName) {
        final String checkedFileName = null != fileName // NOPMD This ternary is well readable.
                                     ? fileName
                                     : "";
        return new EbnfScanner(reader, checkedFileName);
    }

    /**
     * Creates new parser from given scanner.
     *
     * @param scanner Scanner to obtain tokens from.
     * @return Return new instance.
     */
    public static Parser newParser(final Scanner scanner) {
        return new EbnfParser(scanner);
    }

    /**
     * Return new parser from resource URI.
     *
     * @param uri Where to read source.
     * @return Return new instance.
     * @throws FileNotFoundException If no file found at the given URI.
     */
    public static Parser newParserFromSource(final URI uri) throws FileNotFoundException {
        return newParserFromSource(uri, uri.toString());
    }

    /**
     * Return new parser from resource URI.
     *
     * @param uri Where to read source.
     * @param fileName Name of scanned file. this may be any string if not reading from file.
     * @return Return new instance.
     * @throws FileNotFoundException If no file found at the given URI.
     */
    public static Parser newParserFromSource(final URI uri, final String fileName) throws FileNotFoundException {
        return newParserFromSource(new File(uri), fileName);
    }

    /**
     * Return new parser from resource file.
     *
     * @param file Where to read source.
     * @return Return new instance.
     * @throws FileNotFoundException If no file found at the given file.
     */
    public static Parser newParserFromSource(final File file) throws FileNotFoundException {
        return newParserFromSource(file, file.getAbsolutePath());
    }

    /**
     * Return new parser from resource file.
     *
     * @param file Where to read source.
     * @param fileName Name of scanned file. this may be any string if not reading from file.
     * @return Return new instance.
     * @throws FileNotFoundException If no file found at the given file.
     */
    public static Parser newParserFromSource(final File file, final String fileName) throws FileNotFoundException {
        return newParserFromSource(new FileReader(file), file.getAbsolutePath());
    }

    /**
     * Return new parser from resource string.
     *
     * @param src Source to read from.
     * @return Return new instance.
     */
    public static Parser newParserFromSource(final String src) {
        return newParserFromSource(src, "");
    }

    /**
     * Return new parser from resource string.
     *
     * @param src Source to read from.
     * @param fileName Name of scanned file. this may be any string if not reading from file.
     * @return Return new instance.
     */
    public static Parser newParserFromSource(final String src, final String fileName) {
        return newParserFromSource(new StringReader(src), fileName);
    }

    /**
     * Return new parser from resource reader.
     * @param reader Reader to read from.
     * @return Return new instance.
     */
    public static Parser newParserFromSource(final Reader reader) {
        return newParserFromSource(reader, "");
    }

    /**
     * Return new parser from resource reader.
     *
     * @param reader Reader to read from.
     * @param fileName Name of scanned file. this may be any string if not reading from file.
     * @return Return new instance.
     */
    public static Parser newParserFromSource(final Reader reader, final String fileName) {
        final Scanner scanner = newScanner(new BufferedReader(reader), fileName);
        return newParser(scanner);
    }

}
