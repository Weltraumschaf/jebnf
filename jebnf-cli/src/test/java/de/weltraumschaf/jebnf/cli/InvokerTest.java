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

import de.weltraumschaf.jebnf.EbnfException;
import java.io.InputStream;
import java.io.PrintStream;
import org.apache.commons.cli.ParseException;
import static org.junit.Assert.*;
import org.junit.Ignore;
import org.junit.Test;
import static org.mockito.Mockito.*;

/**
 *
 * @author Sven Strittmatter <weltraumschaf@googlemail.com>
 */
public class InvokerTest {

    private final IOStreams ioStreams = new IOStreams(mock(InputStream.class), mock(PrintStream.class), mock(PrintStream.class));

    @Test public void parseOptions() throws EbnfException, ParseException {
        final CliOptions options = mock(CliOptions.class);
        final String[] args = new String[]{"-d", "-h"};
        final Invoker sut = new Invoker(args, ioStreams, options);
        sut.parseOptions();
        final CliOptions parsedOptions = sut.getOptions();
        assertSame(options, parsedOptions);
        verify(options, times(1)).parse(args);
    }

    @Test public void parseOptionsWithPArseError() throws ParseException {
        final CliOptions options = mock(CliOptions.class);
        final String[] args = new String[]{"foo"};
        final Invoker sut = new Invoker(args, ioStreams, options);
        final ParseException throwed = new ParseException("foobar");
        doThrow(throwed).when(options).parse(args);

        try {
            sut.parseOptions();
            fail("Expected exeption not thrown!");
        } catch (EbnfException ex) {
            assertEquals(throwed.getMessage(), ex.getMessage());
            assertSame(throwed, ex.getCause());
        }

        verify(options, times(1)).parse(args);
    }

    @Ignore("TODO Implement test.")
    @Test public void isHelp() {

    }

    @Ignore("TODO Implement test.")
    @Test public void isShowVersion() {

    }

    @Ignore("TODO Implement test.")
    @Test public void isIde() {
        
    }
}
