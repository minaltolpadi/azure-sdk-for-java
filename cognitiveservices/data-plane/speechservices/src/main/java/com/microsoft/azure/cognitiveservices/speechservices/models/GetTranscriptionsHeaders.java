/**
 * Copyright (c) Microsoft Corporation. All rights reserved.
 * Licensed under the MIT License. See License.txt in the project root for
 * license information.
 *
 * Code generated by Microsoft (R) AutoRest Code Generator.
 */

package com.microsoft.azure.cognitiveservices.speechservices.models;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Defines headers for GetTranscriptions operation.
 */
public class GetTranscriptionsHeaders {
    /**
     * The limit of requests for the resource.
     */
    @JsonProperty(value = "X-RateLimit-Limit")
    private Integer xRateLimitLimit;

    /**
     * The remaining number of requests until the rate limit is reached.
     */
    @JsonProperty(value = "X-RateLimit-Remaining")
    private Integer xRateLimitRemaining;

    /**
     * The timestamp when the rate limit will be reset formatted as ISO 8601
     * combined date and time in UTC.
     */
    @JsonProperty(value = "X-RateLimit-Reset")
    private String xRateLimitReset;

    /**
     * The minimum number of seconds to wait before not getting this response
     * anymore.
     */
    @JsonProperty(value = "Retry-After")
    private Integer retryAfter;

    /**
     * Get the limit of requests for the resource.
     *
     * @return the xRateLimitLimit value
     */
    public Integer xRateLimitLimit() {
        return this.xRateLimitLimit;
    }

    /**
     * Set the limit of requests for the resource.
     *
     * @param xRateLimitLimit the xRateLimitLimit value to set
     * @return the GetTranscriptionsHeaders object itself.
     */
    public GetTranscriptionsHeaders withXRateLimitLimit(Integer xRateLimitLimit) {
        this.xRateLimitLimit = xRateLimitLimit;
        return this;
    }

    /**
     * Get the remaining number of requests until the rate limit is reached.
     *
     * @return the xRateLimitRemaining value
     */
    public Integer xRateLimitRemaining() {
        return this.xRateLimitRemaining;
    }

    /**
     * Set the remaining number of requests until the rate limit is reached.
     *
     * @param xRateLimitRemaining the xRateLimitRemaining value to set
     * @return the GetTranscriptionsHeaders object itself.
     */
    public GetTranscriptionsHeaders withXRateLimitRemaining(Integer xRateLimitRemaining) {
        this.xRateLimitRemaining = xRateLimitRemaining;
        return this;
    }

    /**
     * Get the timestamp when the rate limit will be reset formatted as ISO 8601 combined date and time in UTC.
     *
     * @return the xRateLimitReset value
     */
    public String xRateLimitReset() {
        return this.xRateLimitReset;
    }

    /**
     * Set the timestamp when the rate limit will be reset formatted as ISO 8601 combined date and time in UTC.
     *
     * @param xRateLimitReset the xRateLimitReset value to set
     * @return the GetTranscriptionsHeaders object itself.
     */
    public GetTranscriptionsHeaders withXRateLimitReset(String xRateLimitReset) {
        this.xRateLimitReset = xRateLimitReset;
        return this;
    }

    /**
     * Get the minimum number of seconds to wait before not getting this response anymore.
     *
     * @return the retryAfter value
     */
    public Integer retryAfter() {
        return this.retryAfter;
    }

    /**
     * Set the minimum number of seconds to wait before not getting this response anymore.
     *
     * @param retryAfter the retryAfter value to set
     * @return the GetTranscriptionsHeaders object itself.
     */
    public GetTranscriptionsHeaders withRetryAfter(Integer retryAfter) {
        this.retryAfter = retryAfter;
        return this;
    }

}
