// Copyright (c) Microsoft Corporation. All rights reserved.
// Licensed under the MIT License.

package com.azure.core.amqp.implementation;

/**
 * Holds information about the SDK levering the AMPQ library.
 */
public class SdkInfo {
    private final String productName;
    private final String version;

    public SdkInfo(String productName, String version) {
        this.productName = productName;
        this.version = version;
    }

    public String getVersion() {
        return version;
    }

    public String getProductName() {
        return productName;
    }
}
