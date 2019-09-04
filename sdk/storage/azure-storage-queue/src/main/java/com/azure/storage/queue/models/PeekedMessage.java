// Copyright (c) Microsoft Corporation. All rights reserved.
// Licensed under the MIT License.
// Code generated by Microsoft (R) AutoRest Code Generator.

package com.azure.storage.queue.models;

import com.azure.core.implementation.DateTimeRfc1123;
import com.azure.core.annotation.Fluent;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;
import java.time.OffsetDateTime;

/**
 * The object returned in the QueueMessageList array when calling Peek Messages
 * on a Queue.
 */
@JacksonXmlRootElement(localName = "QueueMessage")
@Fluent
public final class PeekedMessage {
    /*
     * The Id of the Message.
     */
    @JsonProperty(value = "MessageId", required = true)
    private String messageId;

    /*
     * The time the Message was inserted into the Queue.
     */
    @JsonProperty(value = "InsertionTime", required = true)
    private DateTimeRfc1123 insertionTime;

    /*
     * The time that the Message will expire and be automatically deleted.
     */
    @JsonProperty(value = "ExpirationTime", required = true)
    private DateTimeRfc1123 expirationTime;

    /*
     * The number of times the message has been dequeued.
     */
    @JsonProperty(value = "DequeueCount", required = true)
    private long dequeueCount;

    /*
     * The content of the Message.
     */
    @JsonProperty(value = "MessageText", required = true)
    private String messageText;

    /**
     * Get the messageId property: The Id of the Message.
     *
     * @return the messageId value.
     */
    public String messageId() {
        return this.messageId;
    }

    /**
     * Set the messageId property: The Id of the Message.
     *
     * @param messageId the messageId value to set.
     * @return the PeekedMessage object itself.
     */
    public PeekedMessage messageId(String messageId) {
        this.messageId = messageId;
        return this;
    }

    /**
     * Get the insertionTime property: The time the Message was inserted into
     * the Queue.
     *
     * @return the insertionTime value.
     */
    public OffsetDateTime insertionTime() {
        if (this.insertionTime == null) {
            return null;
        }
        return this.insertionTime.dateTime();
    }

    /**
     * Set the insertionTime property: The time the Message was inserted into
     * the Queue.
     *
     * @param insertionTime the insertionTime value to set.
     * @return the PeekedMessage object itself.
     */
    public PeekedMessage insertionTime(OffsetDateTime insertionTime) {
        if (insertionTime == null) {
            this.insertionTime = null;
        } else {
            this.insertionTime = new DateTimeRfc1123(insertionTime);
        }
        return this;
    }

    /**
     * Get the expirationTime property: The time that the Message will expire
     * and be automatically deleted.
     *
     * @return the expirationTime value.
     */
    public OffsetDateTime expirationTime() {
        if (this.expirationTime == null) {
            return null;
        }
        return this.expirationTime.dateTime();
    }

    /**
     * Set the expirationTime property: The time that the Message will expire
     * and be automatically deleted.
     *
     * @param expirationTime the expirationTime value to set.
     * @return the PeekedMessage object itself.
     */
    public PeekedMessage expirationTime(OffsetDateTime expirationTime) {
        if (expirationTime == null) {
            this.expirationTime = null;
        } else {
            this.expirationTime = new DateTimeRfc1123(expirationTime);
        }
        return this;
    }

    /**
     * Get the dequeueCount property: The number of times the message has been
     * dequeued.
     *
     * @return the dequeueCount value.
     */
    public long dequeueCount() {
        return this.dequeueCount;
    }

    /**
     * Set the dequeueCount property: The number of times the message has been
     * dequeued.
     *
     * @param dequeueCount the dequeueCount value to set.
     * @return the PeekedMessage object itself.
     */
    public PeekedMessage dequeueCount(long dequeueCount) {
        this.dequeueCount = dequeueCount;
        return this;
    }

    /**
     * Get the messageText property: The content of the Message.
     *
     * @return the messageText value.
     */
    public String messageText() {
        return this.messageText;
    }

    /**
     * Set the messageText property: The content of the Message.
     *
     * @param messageText the messageText value to set.
     * @return the PeekedMessage object itself.
     */
    public PeekedMessage messageText(String messageText) {
        this.messageText = messageText;
        return this;
    }
}
