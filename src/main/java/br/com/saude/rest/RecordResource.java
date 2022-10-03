package br.com.saude.rest;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import br.com.saude.models.HydrationRecord;
import br.com.saude.models.WeightLossRecord;
import br.com.saude.services.RecordService;

@RequestScoped
@Path("/records")
public class RecordResource {
    
    @Inject
    RecordService recordService;

    @GET
    @Path("/{userId}/hydration-record")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getHydrationRecord(final @PathParam("userId")int userId) {
        return Response.status(Response.Status.OK).entity(recordService.getRecord(userId, HydrationRecord.class)).build();
    }

    @GET
    @Path("/{userId}/weightloss-record")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getWeightlossRecord(final @PathParam("userId")int userId) {
        return Response.status(Response.Status.OK).entity(recordService.getRecord(userId, WeightLossRecord.class)).build();
    }

    @POST
    @Path("/{userId}/hydration-record")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response putHydrationRecord(final @PathParam("userId")int userId, HydrationRecord record) {
        recordService.postRecord(userId, record);
        return Response.status(Response.Status.OK).build();
    }

    @POST
    @Path("/{userId}/weightloss-record")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response putWeightlossRecord(final @PathParam("userId")int userId, WeightLossRecord record) {
        recordService.postRecord(userId, record);
        return Response.status(Response.Status.OK).build();
    }
}
