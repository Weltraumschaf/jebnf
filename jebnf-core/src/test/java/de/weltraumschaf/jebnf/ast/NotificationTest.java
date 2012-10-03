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
package de.weltraumschaf.jebnf.ast;

import static org.junit.Assert.*;
import org.junit.Test;

/**
 * Unit test for Notification.
 *
 * @author Sven Strittmatter <weltraumschaf@googlemail.com>
 */
public class NotificationTest {

    @Test public void errorCollectingAndReporting() {
        final Notification notification = new Notification();
        assertTrue(notification.isOk());
        assertEquals("", notification.report());
        notification.error("An error!");
        assertFalse(notification.isOk());
        notification.error("Some %s occured at %s!", "FOO", "BAR");
        assertFalse(notification.isOk());
        notification.error("Error: %s at line %d occued because %s!", "SNAFU", 5, "FOOBAR");
        assertEquals("An error!\n"
                   + "Some FOO occured at BAR!\n"
                   + "Error: SNAFU at line 5 occued because FOOBAR!", notification.report());
    }

}
