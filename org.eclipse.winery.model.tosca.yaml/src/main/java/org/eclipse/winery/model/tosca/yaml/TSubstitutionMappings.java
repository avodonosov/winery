/********************************************************************************
 * Copyright (c) 2017-2020 Contributors to the Eclipse Foundation
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
package org.eclipse.winery.model.tosca.yaml;

import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Objects;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlType;
import javax.xml.namespace.QName;

import org.eclipse.winery.model.tosca.yaml.support.TListString;
import org.eclipse.winery.model.tosca.yaml.visitor.AbstractParameter;
import org.eclipse.winery.model.tosca.yaml.visitor.AbstractResult;
import org.eclipse.winery.model.tosca.yaml.visitor.IVisitor;
import org.eclipse.winery.model.tosca.yaml.visitor.VisitorNode;

import org.eclipse.jdt.annotation.NonNull;
import org.eclipse.jdt.annotation.Nullable;

/**
 * Used in Topology Template Definition
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "tSubstitutionMapping", namespace = " http://docs.oasis-open.org/tosca/ns/simple/yaml/1.3", propOrder = {
    "nodeType",
    "capabilities",
    "requirements"
})
public class TSubstitutionMappings implements VisitorNode {
    @XmlAttribute(name = "node_type")
    private QName nodeType;
    private Map<String, TListString> capabilities;
    private Map<String, TListString> requirements;

    public TSubstitutionMappings() {
    }

    public TSubstitutionMappings(Builder builder) {
        this.setNodeType(builder.nodeType);
        this.setCapabilities(builder.capabilities);
        this.setRequirements(builder.requirements);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof TSubstitutionMappings)) return false;
        TSubstitutionMappings that = (TSubstitutionMappings) o;
        return Objects.equals(getNodeType(), that.getNodeType()) &&
            Objects.equals(getCapabilities(), that.getCapabilities()) &&
            Objects.equals(getRequirements(), that.getRequirements());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getNodeType(), getCapabilities(), getRequirements());
    }

    @Override
    public String toString() {
        return "TSubstitutionMappings{" +
            "nodeType=" + getNodeType() +
            ", capabilities=" + getCapabilities() +
            ", requirements=" + getRequirements() +
            '}';
    }

    @Nullable
    public QName getNodeType() {
        return nodeType;
    }

    public void setNodeType(QName nodeType) {
        this.nodeType = nodeType;
    }

    @NonNull
    public Map<String, TListString> getCapabilities() {
        if (this.capabilities == null) {
            this.capabilities = new LinkedHashMap<>();
        }

        return capabilities;
    }

    public void setCapabilities(Map<String, TListString> capabilities) {
        this.capabilities = capabilities;
    }

    @NonNull
    public Map<String, TListString> getRequirements() {
        if (this.requirements == null) {
            this.requirements = new LinkedHashMap<>();
        }

        return requirements;
    }

    public void setRequirements(Map<String, TListString> requirements) {
        this.requirements = requirements;
    }

    public <R extends AbstractResult<R>, P extends AbstractParameter<P>> R accept(IVisitor<R, P> visitor, P parameter) {
        return visitor.visit(this, parameter);
    }

    public static class Builder {
        private QName nodeType;
        private Map<String, TListString> capabilities;
        private Map<String, TListString> requirements;

        public Builder setNodeType(QName nodeType) {
            this.nodeType = nodeType;
            return this;
        }

        public Builder setCapabilities(Map<String, TListString> capabilities) {
            this.capabilities = capabilities;
            return this;
        }

        public Builder setRequirements(Map<String, TListString> requirements) {
            this.requirements = requirements;
            return this;
        }

        public Builder addCapabilities(Map<String, TListString> capabilities) {
            if (capabilities == null || capabilities.isEmpty()) {
                return this;
            }

            if (this.capabilities == null) {
                this.capabilities = new LinkedHashMap<>(capabilities);
            } else {
                this.capabilities.putAll(capabilities);
            }

            return this;
        }

        public Builder addCapabilities(String name, TListString capability) {
            if (name == null || name.isEmpty()) {
                return this;
            }

            return addCapabilities(Collections.singletonMap(name, capability));
        }

        public Builder addRequirements(Map<String, TListString> requirements) {
            if (requirements == null || requirements.isEmpty()) {
                return this;
            }

            if (this.requirements == null) {
                this.requirements = new LinkedHashMap<>(requirements);
            } else {
                this.requirements.putAll(requirements);
            }

            return this;
        }

        public Builder addRequirements(String name, TListString requirement) {
            if (name == null || name.isEmpty()) {
                return this;
            }

            return addRequirements(Collections.singletonMap(name, requirement));
        }

        public TSubstitutionMappings build() {
            return new TSubstitutionMappings(this);
        }
    }
}
