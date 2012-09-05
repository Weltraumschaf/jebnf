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

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * Holds the current version.
 *
 * Class is implemented as singleton.
 *
 * XXX: Good candidate for de.weltraumschaf.commons.
 * 
 * @author Sven Strittmatter <weltraumschaf@googlemail.com>
 */
public final class Version {

    /**
     * Available properties in the file.
     */
    enum PropertyNames {

        /**
         * Version property.
         */
        VERSION("jebnf.version");

        /**
         * Property name.
         */
        private final String name;

        /**
         * Initializes the enum with the property name.
         *
         * @param name The property name.
         */
        PropertyNames(final String name) {
            this.name = name;
        }

        /**
         * Returns the property name.
         *
         * @return String containing the property name.
         */
        @Override
        public String toString() {
            return name;
        }

    }

    /**
     * Location of property file.
     */
    private static final String FILE_NAME = "/de/weltraumschaf/jebnf/version.properties";

    /**
     * Thread save singleton instance.
     */
    private static final Version INSTANCE = new Version();

    /**
     * Indicates whether the properties are already loaded or not.
     */
    private boolean propertiesLoaded;

    /**
     * Properties.
     */
    private final Properties properties = new Properties();

    /**
     * Private constructor for singleton.
     */
    private Version() {
        super();
    }

    /**
     * Return the singleton instance.
     *
     * @return Always the same object.
     * @throws IOException On IO errors of the property file.
     */
    public static Version getInstance() throws IOException {
        if (!INSTANCE.arePropertiesLoaded()) {
            INSTANCE.loadProperties();
        }

        return INSTANCE;
    }

    /**
     * Indicates if properties were load.
     *
     * @return True if properties are loaded, unless false.
     */
    private boolean arePropertiesLoaded() {
        return propertiesLoaded;
    }

    /**
     * Opens the properties file and loads it.
     *
     * @throws IOException On IO errors of the property file.
     */
    private void loadProperties() throws IOException {
        final InputStream in = getClass().getResourceAsStream(FILE_NAME);
        properties.load(in);
        in.close();
        propertiesLoaded = true;
    }

    /**
     * Get the version string.
     *
     * @return The version string.
     */
    public String getVersion() {
        return properties.getProperty(PropertyNames.VERSION.toString(), "Not available!");
    }

    /**
     * Returns the version string.
     *
     * @return Same as {@link #getVersion()}.
     */
    @Override
    public String toString() {
        return getVersion();
    }

}
