/**
 * Copyright (c) Microsoft Corporation. All rights reserved.
 * Licensed under the MIT License. See License.txt in the project root for
 * license information.
 *
 * Code generated by Microsoft (R) AutoRest Code Generator.
 */

package com.microsoft.azure.graphrbac;

import java.util.List;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Request parameters for updating a new application.
 */
public class ApplicationUpdateParameters extends ApplicationBase {
    /**
     * The display name of the application.
     */
    @JsonProperty(value = "displayName")
    private String displayName;

    /**
     * A collection of URIs for the application.
     */
    @JsonProperty(value = "identifierUris")
    private List<String> identifierUris;

    /**
     * Get the display name of the application.
     *
     * @return the displayName value
     */
    public String displayName() {
        return this.displayName;
    }

    /**
     * Set the display name of the application.
     *
     * @param displayName the displayName value to set
     * @return the ApplicationUpdateParameters object itself.
     */
    public ApplicationUpdateParameters withDisplayName(String displayName) {
        this.displayName = displayName;
        return this;
    }

    /**
     * Get a collection of URIs for the application.
     *
     * @return the identifierUris value
     */
    public List<String> identifierUris() {
        return this.identifierUris;
    }

    /**
     * Set a collection of URIs for the application.
     *
     * @param identifierUris the identifierUris value to set
     * @return the ApplicationUpdateParameters object itself.
     */
    public ApplicationUpdateParameters withIdentifierUris(List<String> identifierUris) {
        this.identifierUris = identifierUris;
        return this;
    }

}
