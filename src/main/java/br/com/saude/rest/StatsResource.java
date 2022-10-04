package br.com.saude.rest;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import br.com.saude.services.StatisticService;

@RequestScoped
@Path("/stats")
public class StatsResource {

    @Inject
    StatisticService statService;
    
    @GET
    @Path("/{userId}/hydration-stats")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getUserGoals(final @PathParam("userId")int userId, @QueryParam(value="startDate") String startDate, @QueryParam(value="endDate") String endDate) {
        return Response.status(Response.Status.OK).entity(statService.getWaterIntakeStats(userId, startDate, endDate)).build();
    }

    @GET
    @Path("/{userId}/total-daily-energy-expenditure")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getTDEE(final @PathParam("userId")int userId) {
        return Response.status(Response.Status.OK).entity(statService.getTDEE(userId)).build();
    }

    @GET
    @Path("/{userId}/basal-metabolic-rate")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getBMR(final @PathParam("userId")int userId) {
        return Response.status(Response.Status.OK).entity(statService.getBMR(userId)).build();
    }

    @GET
    @Path("/{userId}/body-mass-index")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getBMI(final @PathParam("userId")int userId) {
        return Response.status(Response.Status.OK).entity(statService.getBMI(userId)).build();
    }

    @GET
    @Path("/{userId}/weight-lost")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getWeightLost(final @PathParam("userId")int userId) {
        return Response.status(Response.Status.OK).entity(statService.getWeightLost(userId)).build();
    }
}
