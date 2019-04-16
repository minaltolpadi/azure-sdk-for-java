/**
 * Copyright (c) Microsoft Corporation. All rights reserved.
 * Licensed under the MIT License. See License.txt in the project root for
 * license information.
 *
 * Code generated by Microsoft (R) AutoRest Code Generator.
 */

package com.microsoft.azure.management.cdn;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.fasterxml.jackson.annotation.JsonTypeName;

/**
 * Defines the certificate source parameters using CDN managed certificate for
 * enabling SSL.
 */
@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, include = JsonTypeInfo.As.PROPERTY, property = "certificateSource")
@JsonTypeName("Cdn")
public class CdnManagedHttpsParameters extends CustomDomainHttpsParameters {
    /**
     * Defines the certificate source parameters using CDN managed certificate
     * for enabling SSL.
     */
    @JsonProperty(value = "certificateSourceParameters", required = true)
    private CdnCertificateSourceParameters certificateSourceParameters;

    /**
     * Get defines the certificate source parameters using CDN managed certificate for enabling SSL.
     *
     * @return the certificateSourceParameters value
     */
    public CdnCertificateSourceParameters certificateSourceParameters() {
        return this.certificateSourceParameters;
    }

    /**
     * Set defines the certificate source parameters using CDN managed certificate for enabling SSL.
     *
     * @param certificateSourceParameters the certificateSourceParameters value to set
     * @return the CdnManagedHttpsParameters object itself.
     */
    public CdnManagedHttpsParameters withCertificateSourceParameters(CdnCertificateSourceParameters certificateSourceParameters) {
        this.certificateSourceParameters = certificateSourceParameters;
        return this;
    }

}
