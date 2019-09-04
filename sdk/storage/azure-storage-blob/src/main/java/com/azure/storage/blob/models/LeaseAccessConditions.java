// Copyright (c) Microsoft Corporation. All rights reserved.
// Licensed under the MIT License.
// Code generated by Microsoft (R) AutoRest Code Generator.

package com.azure.storage.blob.models;

import com.azure.core.annotation.Fluent;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;

/**
 * Additional parameters for a set of operations.
 */
@JacksonXmlRootElement(localName = "lease-access-conditions")
@Fluent
public final class LeaseAccessConditions {
    /*
     * If specified, the operation only succeeds if the resource's lease is
     * active and matches this ID.
     */
    @JsonProperty(value = "leaseId")
    private String leaseId;

    /**
     * Get the leaseId property: If specified, the operation only succeeds if
     * the resource's lease is active and matches this ID.
     *
     * @return the leaseId value.
     */
    public String leaseId() {
        return this.leaseId;
    }

    /**
     * Set the leaseId property: If specified, the operation only succeeds if
     * the resource's lease is active and matches this ID.
     *
     * @param leaseId the leaseId value to set.
     * @return the LeaseAccessConditions object itself.
     */
    public LeaseAccessConditions leaseId(String leaseId) {
        this.leaseId = leaseId;
        return this;
    }
}
