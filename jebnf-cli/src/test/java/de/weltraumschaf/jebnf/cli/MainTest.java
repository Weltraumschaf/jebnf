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

import de.weltraumschaf.commons.system.NullExiter;
import de.weltraumschaf.jebnf.EbnfException;
import org.apache.commons.cli.ParseException;
import static org.junit.Assert.*;
import org.junit.Ignore;
import org.junit.Test;
import static org.mockito.Mockito.*;

/**
 *
 * @author Sven Strittmatter <weltraumschaf@googlemail.com>
 */
public class MainTest {

    @Test public void parseOptions() throws EbnfException, ParseException {
        final CliOptions options = mock(CliOptions.class);
        final String[] args = new String[]{"-d", "-h"};
        final Main sut = new Main(args, options);
        sut.parseOptions();
        final CliOptions parsedOptions = sut.getOptions();
        assertSame(options, parsedOptions);
        verify(options, times(1)).parse(args);
    }

    @Test public void parseOptionsWithParseError() throws ParseException {
        final CliOptions options = mock(CliOptions.class);
        final String[] args = new String[]{"foo"};
        final Main sut = new Main(args, options);
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
    public void isHelp() throws Exception {
        final Main invoker = new Main(new String[]{"-h"});
        invoker.setExiter(new NullExiter());
        invoker.execute();
    }

    @Ignore("TODO Implement test.")
    @Test public void isShowVersion() throws Exception {
        final Main invoker = new Main(new String[]{"-v"});
        invoker.setExiter(new NullExiter());
        invoker.execute();
    }

    @Ignore("TODO Implement test.")
    @Test public void isIde() throws Exception {
        final Main invoker = new Main(new String[]{"-i"});
        invoker.setExiter(new NullExiter());
        invoker.execute();
    }

}
