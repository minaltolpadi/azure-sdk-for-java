/**
 * Copyright (c) Microsoft Corporation. All rights reserved.
 * Licensed under the MIT License. See License.txt in the project root for
 * license information.
 *
 * Code generated by Microsoft (R) AutoRest Code Generator.
 */

package com.microsoft.azure.cognitiveservices.speechservices.models;

import java.util.UUID;
import org.joda.time.DateTime;
import java.util.List;
import java.util.Map;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Test.
 */
public class Test {
    /**
     * Information about the dataset used in the test.
     */
    @JsonProperty(value = "dataset")
    private Dataset dataset;

    /**
     * The identifier of this entity.
     */
    @JsonProperty(value = "id", required = true)
    private UUID id;

    /**
     * The word error rate of the tested model.
     */
    @JsonProperty(value = "wordErrorRate", required = true)
    private double wordErrorRate;

    /**
     * The URL that can be used to download the test results.
     * Each line in the file represents a tab separated list of filename,
     * reference transcription and decoder output.
     *
     * The URL will only be valid, if the test completed successfully.
     */
    @JsonProperty(value = "resultsUrl")
    private String resultsUrl;

    /**
     * The time-stamp when the object was created.
     */
    @JsonProperty(value = "createdDateTime", required = true)
    private DateTime createdDateTime;

    /**
     * The time-stamp when the current status was entered.
     */
    @JsonProperty(value = "lastActionDateTime", required = true)
    private DateTime lastActionDateTime;

    /**
     * The status of the object. Possible values include: 'Succeeded',
     * 'Failed', 'NotStarted', 'Running'.
     */
    @JsonProperty(value = "status", required = true)
    private String status;

    /**
     * Information about the models used for this accuracy test.
     */
    @JsonProperty(value = "models", required = true)
    private List<Model> modelsProperty;

    /**
     * The name of the object.
     */
    @JsonProperty(value = "name", required = true)
    private String name;

    /**
     * The description of the object.
     */
    @JsonProperty(value = "description")
    private String description;

    /**
     * The custom properties of this entity.
     */
    @JsonProperty(value = "properties")
    private Map<String, String> properties;

    /**
     * Get information about the dataset used in the test.
     *
     * @return the dataset value
     */
    public Dataset dataset() {
        return this.dataset;
    }

    /**
     * Set information about the dataset used in the test.
     *
     * @param dataset the dataset value to set
     * @return the Test object itself.
     */
    public Test withDataset(Dataset dataset) {
        this.dataset = dataset;
        return this;
    }

    /**
     * Get the identifier of this entity.
     *
     * @return the id value
     */
    public UUID id() {
        return this.id;
    }

    /**
     * Set the identifier of this entity.
     *
     * @param id the id value to set
     * @return the Test object itself.
     */
    public Test withId(UUID id) {
        this.id = id;
        return this;
    }

    /**
     * Get the word error rate of the tested model.
     *
     * @return the wordErrorRate value
     */
    public double wordErrorRate() {
        return this.wordErrorRate;
    }

    /**
     * Set the word error rate of the tested model.
     *
     * @param wordErrorRate the wordErrorRate value to set
     * @return the Test object itself.
     */
    public Test withWordErrorRate(double wordErrorRate) {
        this.wordErrorRate = wordErrorRate;
        return this;
    }

    /**
     * Get the URL that can be used to download the test results.
     Each line in the file represents a tab separated list of filename, reference transcription and decoder output.
     The URL will only be valid, if the test completed successfully.
     *
     * @return the resultsUrl value
     */
    public String resultsUrl() {
        return this.resultsUrl;
    }

    /**
     * Set the URL that can be used to download the test results.
     Each line in the file represents a tab separated list of filename, reference transcription and decoder output.
     The URL will only be valid, if the test completed successfully.
     *
     * @param resultsUrl the resultsUrl value to set
     * @return the Test object itself.
     */
    public Test withResultsUrl(String resultsUrl) {
        this.resultsUrl = resultsUrl;
        return this;
    }

    /**
     * Get the time-stamp when the object was created.
     *
     * @return the createdDateTime value
     */
    public DateTime createdDateTime() {
        return this.createdDateTime;
    }

    /**
     * Set the time-stamp when the object was created.
     *
     * @param createdDateTime the createdDateTime value to set
     * @return the Test object itself.
     */
    public Test withCreatedDateTime(DateTime createdDateTime) {
        this.createdDateTime = createdDateTime;
        return this;
    }

    /**
     * Get the time-stamp when the current status was entered.
     *
     * @return the lastActionDateTime value
     */
    public DateTime lastActionDateTime() {
        return this.lastActionDateTime;
    }

    /**
     * Set the time-stamp when the current status was entered.
     *
     * @param lastActionDateTime the lastActionDateTime value to set
     * @return the Test object itself.
     */
    public Test withLastActionDateTime(DateTime lastActionDateTime) {
        this.lastActionDateTime = lastActionDateTime;
        return this;
    }

    /**
     * Get the status of the object. Possible values include: 'Succeeded', 'Failed', 'NotStarted', 'Running'.
     *
     * @return the status value
     */
    public String status() {
        return this.status;
    }

    /**
     * Set the status of the object. Possible values include: 'Succeeded', 'Failed', 'NotStarted', 'Running'.
     *
     * @param status the status value to set
     * @return the Test object itself.
     */
    public Test withStatus(String status) {
        this.status = status;
        return this;
    }

    /**
     * Get information about the models used for this accuracy test.
     *
     * @return the modelsProperty value
     */
    public List<Model> modelsProperty() {
        return this.modelsProperty;
    }

    /**
     * Set information about the models used for this accuracy test.
     *
     * @param modelsProperty the modelsProperty value to set
     * @return the Test object itself.
     */
    public Test withModelsProperty(List<Model> modelsProperty) {
        this.modelsProperty = modelsProperty;
        return this;
    }

    /**
     * Get the name of the object.
     *
     * @return the name value
     */
    public String name() {
        return this.name;
    }

    /**
     * Set the name of the object.
     *
     * @param name the name value to set
     * @return the Test object itself.
     */
    public Test withName(String name) {
        this.name = name;
        return this;
    }

    /**
     * Get the description of the object.
     *
     * @return the description value
     */
    public String description() {
        return this.description;
    }

    /**
     * Set the description of the object.
     *
     * @param description the description value to set
     * @return the Test object itself.
     */
    public Test withDescription(String description) {
        this.description = description;
        return this;
    }

    /**
     * Get the custom properties of this entity.
     *
     * @return the properties value
     */
    public Map<String, String> properties() {
        return this.properties;
    }

    /**
     * Set the custom properties of this entity.
     *
     * @param properties the properties value to set
     * @return the Test object itself.
     */
    public Test withProperties(Map<String, String> properties) {
        this.properties = properties;
        return this;
    }

}
