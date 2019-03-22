/**
 * Copyright (c) Microsoft Corporation. All rights reserved.
 * Licensed under the MIT License. See License.txt in the project root for
 * license information.
 *
 * Code generated by Microsoft (R) AutoRest Code Generator.
 */

package com.microsoft.azure.cognitiveservices.speechservices.models;

import java.util.Map;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * DatasetDefinition.
 */
public class DatasetDefinition {
    /**
     * The kind of the dataset (e.g. acoustic data, language data ...).
     * Possible values include: 'None', 'Language', 'Acoustic',
     * 'Pronunciation', 'CustomVoice', 'LanguageGeneration'.
     */
    @JsonProperty(value = "dataImportKind", required = true)
    private String dataImportKind;

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
     * The locale of the contained data.
     */
    @JsonProperty(value = "locale")
    private String locale;

    /**
     * Get the kind of the dataset (e.g. acoustic data, language data ...). Possible values include: 'None', 'Language', 'Acoustic', 'Pronunciation', 'CustomVoice', 'LanguageGeneration'.
     *
     * @return the dataImportKind value
     */
    public String dataImportKind() {
        return this.dataImportKind;
    }

    /**
     * Set the kind of the dataset (e.g. acoustic data, language data ...). Possible values include: 'None', 'Language', 'Acoustic', 'Pronunciation', 'CustomVoice', 'LanguageGeneration'.
     *
     * @param dataImportKind the dataImportKind value to set
     * @return the DatasetDefinition object itself.
     */
    public DatasetDefinition withDataImportKind(String dataImportKind) {
        this.dataImportKind = dataImportKind;
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
     * @return the DatasetDefinition object itself.
     */
    public DatasetDefinition withName(String name) {
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
     * @return the DatasetDefinition object itself.
     */
    public DatasetDefinition withDescription(String description) {
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
     * @return the DatasetDefinition object itself.
     */
    public DatasetDefinition withProperties(Map<String, String> properties) {
        this.properties = properties;
        return this;
    }

    /**
     * Get the locale of the contained data.
     *
     * @return the locale value
     */
    public String locale() {
        return this.locale;
    }

    /**
     * Set the locale of the contained data.
     *
     * @param locale the locale value to set
     * @return the DatasetDefinition object itself.
     */
    public DatasetDefinition withLocale(String locale) {
        this.locale = locale;
        return this;
    }

}
