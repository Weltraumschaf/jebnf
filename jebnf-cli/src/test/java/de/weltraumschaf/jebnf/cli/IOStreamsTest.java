/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package de.weltraumschaf.jebnf.cli;

import static org.junit.Assert.*;
import org.junit.Test;
import static org.mockito.Mockito.*;

/**
 *
 * @author sxs
 */
public class IOStreamsTest {

    private final IOStreams streams = IOStreams.newDefault();

    @Test
    public void testGetStderr() {
        assertSame(System.err, streams.getStderr());
    }

    @Test
    public void testGetStdin() {
        assertSame(System.in, streams.getStdin());
    }

    @Test
    public void testGetStdout() {
        assertSame(System.out, streams.getStdout());
    }

    @Test public void println() {
        final String msg = "some text";
        streams.println(msg);
        verify(streams.getStdout(), times(1)).println(msg);
    }

    @Test public void printlnErr() {
        final String msg = "some text";
        streams.printlnErr(msg);
        verify(streams.getStderr(), times(1)).println(msg);
    }

}
