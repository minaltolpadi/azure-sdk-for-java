// Copyright (c) Microsoft Corporation. All rights reserved.
// Licensed under the MIT License.

package com.azure.core.amqp.implementation;

import com.azure.core.amqp.RetryOptions;
import com.azure.core.amqp.TransportType;
import com.azure.core.amqp.models.ProxyConfiguration;
import com.azure.core.credentials.TokenCredential;
import reactor.core.scheduler.Scheduler;

import java.util.Objects;

/**
 * A wrapper class that contains all parameters that are needed to establish a connection to an Event Hub.
 */
public class ConnectionOptions {
    private final TokenCredential tokenCredential;
    private final TransportType transport;
    private final RetryOptions retryOptions;
    private final ProxyConfiguration proxyConfiguration;
    private final Scheduler scheduler;
    private final String host;
    private final String eventHubName;
    private final CBSAuthorizationType authorizationType;

    public ConnectionOptions(String host, String eventHubName, TokenCredential tokenCredential,
                             CBSAuthorizationType authorizationType, TransportType transport, RetryOptions retryOptions,
                             ProxyConfiguration proxyConfiguration, Scheduler scheduler) {
        this.host = Objects.requireNonNull(host, "'host' cannot be null.");
        this.eventHubName = Objects.requireNonNull(eventHubName, "'eventHubName' cannot be null.");
        this.tokenCredential = Objects.requireNonNull(tokenCredential, "'tokenCredential' cannot be null.");
        this.authorizationType = Objects.requireNonNull(authorizationType, "'authorizationType' cannot be null.");
        this.transport = Objects.requireNonNull(transport, "'transport' cannot be null.");
        this.retryOptions = Objects.requireNonNull(retryOptions, "'retryOptions' cannot be null.");
        this.proxyConfiguration = Objects.requireNonNull(proxyConfiguration, "'proxyConfiguration' cannot be null.");
        this.scheduler = Objects.requireNonNull(scheduler, "'scheduler' cannot be null.");
    }

    public String host() {
        return host;
    }

    public String eventHubName() {
        return eventHubName;
    }

    public TokenCredential tokenCredential() {
        return tokenCredential;
    }

    public CBSAuthorizationType authorizationType() {
        return authorizationType;
    }

    public TransportType transportType() {
        return transport;
    }

    public RetryOptions retry() {
        return retryOptions;
    }

    public ProxyConfiguration proxyConfiguration() {
        return proxyConfiguration;
    }

    public Scheduler scheduler() {
        return scheduler;
    }
}
