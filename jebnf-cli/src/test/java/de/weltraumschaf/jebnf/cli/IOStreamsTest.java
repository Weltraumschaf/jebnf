/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package de.weltraumschaf.jebnf.cli;

import java.io.InputStream;
import java.io.PrintStream;
import org.junit.Test;
import static org.junit.Assert.*;

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

}
