/*******************************************************************************
 * Copyright (c) 2013-2017 Contributors to the Eclipse Foundation
 *
 * See the NOTICE file(s) distributed with this work for additional
 * information regarding copyright ownership.
 *
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License 2.0 which is available at
 * http://www.eclipse.org/legal/epl-2.0, or the Apache Software License 2.0
 * which is available at https://www.apache.org/licenses/LICENSE-2.0.
 *
 * SPDX-License-Identifier: EPL-2.0 OR Apache-2.0
 *******************************************************************************/

package org.eclipse.winery.model.tosca;

import org.eclipse.jdt.annotation.NonNull;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * <p>Java class for tTags complex type.
 * <p>
 * <p>The following schema fragment specifies the expected content contained within this class.
 * <p>
 * <pre>
 * &lt;complexType name="tTags">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="Tag" type="{http://docs.oasis-open.org/tosca/ns/2011/12}tTag" maxOccurs="unbounded"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "tTags", propOrder = {
    "tag"
})
public class TTags implements Serializable {

    @XmlElement(name = "Tag", required = true)
    protected List<TTag> tag;

    public TTags() {
    }

    public TTags(Builder builder) {
        this.tag = builder.tag;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof TTags)) return false;
        TTags tTags = (TTags) o;
        return Objects.equals(tag, tTags.tag);
    }

    @Override
    public int hashCode() {
        return Objects.hash(tag);
    }

    /**
     * Gets the value of the tag property.
     * <p>
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the tag property.
     * <p>
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getTag().add(newItem);
     * </pre>
     * <p>
     * <p>
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link TTag }
     */
    @NonNull
    public List<TTag> getTag() {
        if (tag == null) {
            tag = new ArrayList<TTag>();
        }
        return this.tag;
    }

    public static class Builder {
        private List<TTag> tag;

        public Builder() {

        }

        public Builder setTag(List<TTag> tag) {
            this.tag = tag;
            return this;
        }

        public Builder addTag(List<TTag> tag) {
            if (tag == null) {
                return this;
            }

            if (this.tag == null) {
                this.tag = tag;
            } else {
                this.tag.addAll(tag);
            }
            return this;
        }

        public Builder addTag(TTag tag) {
            if (tag == null) {
                return this;
            }

            List<TTag> tmp = new ArrayList<>();
            tmp.add(tag);
            return addTag(tmp);
        }

        public Builder addTag(String name, String value) {
            if (name == null || name.isEmpty()) {
                return this;
            }

            TTag tag = new TTag();
            tag.setName(name);
            tag.setValue(value);
            return addTag(tag);
        }

        public TTags build() {
            return new TTags(this);
        }
    }
}
