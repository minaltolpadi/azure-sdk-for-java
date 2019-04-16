/**
 * Copyright (c) Microsoft Corporation. All rights reserved.
 * Licensed under the MIT License. See License.txt in the project root for
 * license information.
 *
 * Code generated by Microsoft (R) AutoRest Code Generator.
 */

package com.microsoft.azure.management.cdn;

import java.util.Collection;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.microsoft.rest.ExpandableStringEnum;

/**
 * Defines values for SkuName.
 */
public final class SkuName extends ExpandableStringEnum<SkuName> {
    /** Static value Standard_Verizon for SkuName. */
    public static final SkuName STANDARD_VERIZON = fromString("Standard_Verizon");

    /** Static value Premium_Verizon for SkuName. */
    public static final SkuName PREMIUM_VERIZON = fromString("Premium_Verizon");

    /** Static value Custom_Verizon for SkuName. */
    public static final SkuName CUSTOM_VERIZON = fromString("Custom_Verizon");

    /** Static value Standard_Akamai for SkuName. */
    public static final SkuName STANDARD_AKAMAI = fromString("Standard_Akamai");

    /** Static value Standard_ChinaCdn for SkuName. */
    public static final SkuName STANDARD_CHINA_CDN = fromString("Standard_ChinaCdn");

    /** Static value Premium_ChinaCdn for SkuName. */
    public static final SkuName PREMIUM_CHINA_CDN = fromString("Premium_ChinaCdn");

    /** Static value Standard_Microsoft for SkuName. */
    public static final SkuName STANDARD_MICROSOFT = fromString("Standard_Microsoft");

    /**
     * Creates or finds a SkuName from its string representation.
     * @param name a name to look for
     * @return the corresponding SkuName
     */
    @JsonCreator
    public static SkuName fromString(String name) {
        return fromString(name, SkuName.class);
    }

    /**
     * @return known SkuName values
     */
    public static Collection<SkuName> values() {
        return values(SkuName.class);
    }
}
