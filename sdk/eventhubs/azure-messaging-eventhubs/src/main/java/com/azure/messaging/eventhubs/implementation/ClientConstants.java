// Copyright (c) Microsoft Corporation. All rights reserved.
// Licensed under the MIT License.

package com.azure.messaging.eventhubs.implementation;

import java.time.Duration;

public final class ClientConstants {
    public static final Duration OPERATION_TIMEOUT = Duration.ofSeconds(60);
    public static final String NOT_APPLICABLE = "n/a";
    public static final int MAX_EVENTHUB_AMQP_HEADER_SIZE_BYTES = 512;
    public static final Duration TOKEN_VALIDITY = Duration.ofMinutes(20);
    public static final int SERVER_BUSY_BASE_SLEEP_TIME_IN_SECS = 4;

    public static final String PRODUCT_NAME = "azsdk-java-eventhubs";
    public static final String CURRENT_JAVA_CLIENT_VERSION = "5.0.0-preview.4";

    private ClientConstants() {
    }
}
