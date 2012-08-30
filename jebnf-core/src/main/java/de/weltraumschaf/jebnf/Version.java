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

/**
 * Holds the current version.
 *
 * @author Sven Strittmatter <weltraumschaf@googlemail.com>
 */
public enum Version {

    /**
     * There is only one version.
     *
     * TODO Maven should set this string.
     */
    VERSION("1.2.4");

    /**
     * Version string.
     */
    private final String version;

    /**
     * Initializes the version string.
     *
     * @param version The version string.
     */
    Version(final String version) {
        this.version = version;
    }

    @Override
    public String toString() {
        return version;
    }

}
