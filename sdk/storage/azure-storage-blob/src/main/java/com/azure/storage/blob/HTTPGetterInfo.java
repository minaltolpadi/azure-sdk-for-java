// Copyright (c) Microsoft Corporation. All rights reserved.
// Licensed under the MIT License.

package com.azure.storage.blob;

import com.azure.core.util.Context;
import com.azure.storage.blob.models.BlobAccessConditions;
import com.azure.storage.common.Utility;

import java.time.Duration;

/**
 * HTTPGetterInfo is a passed to the getter function of a reliable download to specify parameters needed for the GET
 * request.
 */
public final class HTTPGetterInfo {
    private long offset = 0;

    private Long count = null;

    private String eTag = null;

    /**
     * @return the start offset used when creating the Range header. Defaults to 0.
     */
    public long getOffset() {
        return offset;
    }

    /**
     * Sets the start offset that is used when creating the Range header. If unchanged this will default to 0.
     *
     * @param offset Start offset
     * @return the updated HTTPGetterInfo object
     */
    public HTTPGetterInfo setOffset(long offset) {
        this.offset = offset;
        return this;
    }

    /**
     * @return the count of bytes used to calculate the end offset when creating the Range header. {@code} null is the
     * default and indicates that the entire rest of the blob should be retrieved.
     */
    public Long getCount() {
        return count;
    }

    /**
     * Sets the count of bytes used to calculate the end offset when creating the Range header. {@code} null is the
     * default and indicates that the entire rest of the blob should be retrieved.
     *
     * @param count Count of bytes
     * @return the updated HTTPGetterInfo object
     */
    public HTTPGetterInfo setCount(Long count) {
        if (count != null) {
            Utility.assertInBounds("count", count, 0, Long.MAX_VALUE);
        }
        this.count = count;
        return this;
    }

    /**
     * @return the eTag used when creating If-Match header. eTag is returned with any operation that modifies the
     * resource and when retrieving {@link BlobClient#getPropertiesWithResponse(BlobAccessConditions, Duration, Context) properties}.
     * Defaults to null.
     */
    public String getETag() {
        return eTag;
    }

    /**
     * Sets the eTag used when creating If-Match header. eTag is returned with any operation that modifies the
     * resource and when retrieving {@link BlobClient#getPropertiesWithResponse(BlobAccessConditions, Duration, Context) properties}.
     * Defaults to null.
     *
     * @param eTag Resource's eTag
     * @return the updated HTTPGetterInfo object
     */
    public HTTPGetterInfo setETag(String eTag) {
        this.eTag = eTag;
        return this;
    }
}
