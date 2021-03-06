/********************************************************************************
 * Copyright (c) 2014-2018 Contributors to the Eclipse Foundation
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
package org.eclipse.winery.repository.rest.resources.servicetemplates.boundarydefinitions.reqscaps;

import org.eclipse.winery.model.tosca.TCapability;
import org.eclipse.winery.model.tosca.TCapabilityRef;
import org.eclipse.winery.model.tosca.utils.ModelUtilities;
import org.eclipse.winery.repository.rest.resources._support.IPersistable;
import org.eclipse.winery.repository.rest.resources._support.collections.CollectionsHelper;
import org.eclipse.winery.repository.rest.resources._support.collections.withoutid.EntityWithoutIdCollectionResource;
import org.eclipse.winery.repository.rest.resources.apiData.RequirementsOrCapabilityApiData;
import org.eclipse.winery.repository.rest.resources.servicetemplates.ServiceTemplateResource;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;
import java.util.List;

/**
 * This class is an adaption from {@link org.eclipse.winery.repository.resources.servicetemplates.boundarydefinitions.reqscaps.RequirementsResource}
 */
public class CapabilitiesResource extends EntityWithoutIdCollectionResource<CapabilityResource, TCapabilityRef> {

    public CapabilitiesResource(IPersistable res, List<TCapabilityRef> refs) {
        super(CapabilityResource.class, TCapabilityRef.class, refs, res);
    }

    @POST
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    public Response addNewElement(@FormParam("name") String name, @FormParam("ref") String reference) {
        // Implementation adapted from super addNewElement

        if (reference == null) {
            return Response.status(Status.BAD_REQUEST).entity("A reference has to be provided").build();
        }

        TCapabilityRef ref = new TCapabilityRef();
        ref.setName(name); // may also be null

        // The XML model fordces us to put a reference to the object and not just the string
        ServiceTemplateResource rs = (ServiceTemplateResource) this.res;
        TCapability resolved = ModelUtilities.resolveCapability(rs.getServiceTemplate(), reference);
        // In case nothing was found: report back to the user
        if (resolved == null) {
            return Response.status(Status.BAD_REQUEST).entity("Reference could not be resolved").build();
        }

        ref.setRef(resolved);

        // "this.alreadyContains(ref)" cannot be called as this leads to a mappable exception: The data does not contain an id where the given ref attribute may point to

        this.list.add(ref);
        return CollectionsHelper.persist(this.res, this, ref, true);
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public Response addNewElementJSON(RequirementsOrCapabilityApiData reqOrCap) {
        // Implementation adapted from super addNewElement

        if (reqOrCap.ref == null) {
            return Response.status(Status.BAD_REQUEST).entity("A reference has to be provided").build();
        }

        TCapabilityRef ref = new TCapabilityRef();
        ref.setName(reqOrCap.name); // may also be null

        // The XML model fordces us to put a reference to the object and not just the string
        ServiceTemplateResource rs = (ServiceTemplateResource) this.res;
        TCapability resolved = ModelUtilities.resolveCapability(rs.getServiceTemplate(), reqOrCap.ref);
        // In case nothing was found: report back to the user
        if (resolved == null) {
            return Response.status(Status.BAD_REQUEST).entity("Reference could not be resolved").build();
        }

        ref.setRef(resolved);

        // "this.alreadyContains(ref)" cannot be called as this leads to a mappable exception: The data does not contain an id where the given ref attribute may point to

        this.list.add(ref);
        return CollectionsHelper.persist(this.res, this, ref, true);
    }

    @Override
    @Path("{id}/")
    public CapabilityResource getEntityResource(@PathParam("id") String id) {
        return this.getEntityResourceFromEncodedId(id);
    }
}
