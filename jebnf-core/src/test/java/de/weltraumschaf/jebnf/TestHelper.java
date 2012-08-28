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

package de.weltraumschaf.jebnf;

import com.google.common.io.Files;
import de.weltraumschaf.jebnf.parser.Factory;
import de.weltraumschaf.jebnf.parser.Parser;
import de.weltraumschaf.jebnf.parser.Scanner;
import java.io.*;
import java.net.URI;
import java.net.URISyntaxException;
import java.nio.charset.Charset;

/**
 * Helper class to read fixture files.
 *
 * @author Sven Strittmatter <weltraumschaf@googlemail.com>
 */
public final class TestHelper {

    /**
     * Package root directory for fixture files.
     */
    public static final String FIXTURE_DIR = "/de/weltraumschaf/jebnf";

    /**
     * Reusable instance.
     */
    private static final TestHelper INSTANCE = new TestHelper();

    /**
     * Private constructor for singleton class.
     */
    private TestHelper() {
        super();
    }

    /**
     * Return instance.
     *
     * @return Always return same instance.
     */
    public static TestHelper getInstance() {
        return INSTANCE;
    }

    /**
     * Create reader from given fixture file name relative to {@link #FIXTURE_DIR}.
     *
     * @param fixtureFile Filename relative to {@link #FIXTURE_DIR}.
     * @return Return reader object.
     * @throws FileNotFoundException If file not found.
     * @throws URISyntaxException If file name is bad.
     */
    private Reader createFileReaderFromFixture(final String fixtureFile) throws  FileNotFoundException,
                                                                             URISyntaxException {
        return new FileReader(new File(createResourceFromFixture(fixtureFile)));
    }

    /**
     * Create resource from given fixture file name relative to {@link #FIXTURE_DIR}.
     *
     * @param fixtureFile Filename relative to {@link #FIXTURE_DIR}.
     * @return Return resource identifier object.
     * @throws URISyntaxException If file name is bad.
     */
    private URI createResourceFromFixture(final String fixtureFile) throws URISyntaxException {
        return getClass().getResource(FIXTURE_DIR + '/' + fixtureFile).toURI();
    }

    /**
     * Reads th string content from fixture file.
     *
     * @param fixtureFile Filename relative to {@link #FIXTURE_DIR}.
     * @return Return file content.
     * @throws URISyntaxException If file name is bad.
     * @throws IOException On IO errors.
     */
    public String createStringFromFixture(final String fixtureFile) throws URISyntaxException, IOException {
        return Files.toString(new File(createResourceFromFixture(fixtureFile)), Charset.defaultCharset());
    }

    /**
     * Creates scanner initialized with syntax from fixture file.
     *
     * @param fixtureFile Filename relative to {@link #FIXTURE_DIR}.
     * @return Return scanner object.
     * @throws FileNotFoundException If file not found.
     * @throws URISyntaxException If file name is bad.
     */
    public Scanner createScannerFromFixture(final String fixtureFile) throws FileNotFoundException, URISyntaxException {
        return Factory.newScanner(createFileReaderFromFixture(fixtureFile));
    }

    /**
     * Creates parser initialized with syntax from fixture file.
     *
     * @param fixtureFile Filename relative to {@link #FIXTURE_DIR}.
     * @return Return parser object.
     * @throws FileNotFoundException If file not found.
     * @throws URISyntaxException If file name is bad.
     */
    public Parser createParserFromFixture(final String fixtureFile) throws FileNotFoundException, URISyntaxException {
        return Factory.newParserFromSource(createResourceFromFixture(fixtureFile));
    }
}
