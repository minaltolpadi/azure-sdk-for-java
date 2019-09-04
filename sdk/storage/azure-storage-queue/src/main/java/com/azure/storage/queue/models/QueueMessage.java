// Copyright (c) Microsoft Corporation. All rights reserved.
// Licensed under the MIT License.
// Code generated by Microsoft (R) AutoRest Code Generator.

package com.azure.storage.queue.models;

import com.azure.core.annotation.Fluent;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;

/**
 * A Message object which can be stored in a Queue.
 */
@JacksonXmlRootElement(localName = "QueueMessage")
@Fluent
public final class QueueMessage {
    /*
     * The content of the message
     */
    @JsonProperty(value = "MessageText", required = true)
    private String messageText;

    /**
     * Get the messageText property: The content of the message.
     *
     * @return the messageText value.
     */
    public String messageText() {
        return this.messageText;
    }

    /**
     * Set the messageText property: The content of the message.
     *
     * @param messageText the messageText value to set.
     * @return the QueueMessage object itself.
     */
    public QueueMessage messageText(String messageText) {
        this.messageText = messageText;
        return this;
    }
}
