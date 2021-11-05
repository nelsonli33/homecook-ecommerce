
package com.homecook.tappay;

/**
 * Configuration Interface for the library.
 */
public interface Configuration
{
    String getApiKey();

    /**
     * Current API environment.
     *
     * @return a copy of environment
     */
    Environment getEnvironment();

    /**
     * Get base URI by current environment.
     *
     * @return Processed base URI
     */
    String getBaseUri();
}
