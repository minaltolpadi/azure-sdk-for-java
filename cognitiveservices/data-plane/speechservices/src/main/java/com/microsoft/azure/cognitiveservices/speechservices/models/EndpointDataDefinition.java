/**
 * Copyright (c) Microsoft Corporation. All rights reserved.
 * Licensed under the MIT License. See License.txt in the project root for
 * license information.
 *
 * Code generated by Microsoft (R) AutoRest Code Generator.
 */

package com.microsoft.azure.cognitiveservices.speechservices.models;

import org.joda.time.DateTime;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * EndpointDataDefinition.
 */
public class EndpointDataDefinition {
    /**
     * The start date of the demplyment data export.
     */
    @JsonProperty(value = "startDate", required = true)
    private DateTime startDate;

    /**
     * The end date of the demplyment data export.
     */
    @JsonProperty(value = "endDate", required = true)
    private DateTime endDate;

    /**
     * Get the start date of the demplyment data export.
     *
     * @return the startDate value
     */
    public DateTime startDate() {
        return this.startDate;
    }

    /**
     * Set the start date of the demplyment data export.
     *
     * @param startDate the startDate value to set
     * @return the EndpointDataDefinition object itself.
     */
    public EndpointDataDefinition withStartDate(DateTime startDate) {
        this.startDate = startDate;
        return this;
    }

    /**
     * Get the end date of the demplyment data export.
     *
     * @return the endDate value
     */
    public DateTime endDate() {
        return this.endDate;
    }

    /**
     * Set the end date of the demplyment data export.
     *
     * @param endDate the endDate value to set
     * @return the EndpointDataDefinition object itself.
     */
    public EndpointDataDefinition withEndDate(DateTime endDate) {
        this.endDate = endDate;
        return this;
    }

}
