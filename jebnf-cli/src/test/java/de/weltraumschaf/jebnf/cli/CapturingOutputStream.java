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

import java.io.IOException;
import java.io.OutputStream;

/**
 * Captures the written bytes into a string buffer.
 *
 * @author Sven Strittmatter <weltraumschaf@googlemail.com>
 */
public class CapturingOutputStream extends OutputStream {

    /**
     * Buffers the output.
     */
    private final StringBuilder capturedOutput = new StringBuilder();

    @Override
    public void write(int b) throws IOException {
        final String character = String.valueOf((char) b);
        capturedOutput.append(character);
    }

    /**
     * Get the captured output as string.
     *
     * @return Return the captured string.
     */
    public String getCapturedOutput() {
        return capturedOutput.toString();
    }

}
