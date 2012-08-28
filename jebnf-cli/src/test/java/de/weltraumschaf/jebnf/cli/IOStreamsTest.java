/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package de.weltraumschaf.jebnf.cli;

import java.io.InputStream;
import java.io.PrintStream;
import static org.junit.Assert.*;
import org.junit.Test;
import static org.mockito.Mockito.*;

/**
 *
 * @author sxs
 */
public class IOStreamsTest {

    private final IOStreams defaultStreams = IOStreams.newDefault();
    private final IOStreams mockedStreams = new IOStreams(mock(InputStream.class), mock(PrintStream.class), mock(PrintStream.class));

    @Test public void testGetStderrOnDefault() {
        assertSame(System.err, defaultStreams.getStderr());
    }

    @Test public void testGetStdinOnDefault() {
        assertSame(System.in, defaultStreams.getStdin());
    }

    @Test public void testGetStdoutOnDefault() {
        assertSame(System.out, defaultStreams.getStdout());
    }

    @Test public void println() {
        final String msg = "some text";
        mockedStreams.println(msg);
        verify(mockedStreams.getStdout(), times(1)).println(msg);
    }

    @Test public void printlnErr() {
        final String msg = "some text";
        mockedStreams.printlnErr(msg);
        verify(mockedStreams.getStderr(), times(1)).println(msg);
    }

}
