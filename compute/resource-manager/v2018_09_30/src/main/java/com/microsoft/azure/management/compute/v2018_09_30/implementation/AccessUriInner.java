/**
 * Copyright (c) Microsoft Corporation. All rights reserved.
 * Licensed under the MIT License. See License.txt in the project root for
 * license information.
 *
 * Code generated by Microsoft (R) AutoRest Code Generator.
 */

package com.microsoft.azure.management.compute.v2018_09_30.implementation;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * A disk access SAS uri.
 */
public class AccessUriInner {
    /**
     * A SAS uri for accessing a disk.
     */
    @JsonProperty(value = "accessSAS", access = JsonProperty.Access.WRITE_ONLY)
    private String accessSAS;

    /**
     * Get a SAS uri for accessing a disk.
     *
     * @return the accessSAS value
     */
    public String accessSAS() {
        return this.accessSAS;
    }

}
