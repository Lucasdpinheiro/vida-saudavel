package br.com.saude.rest;

import java.math.BigDecimal;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.PATCH;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import br.com.saude.services.GoalService;

@RequestScoped
@Path("/goals")
public class GoalsResource {

    @Inject
    GoalService goalService;

    @GET
    @Path("/{userId}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getUserGoals(final @PathParam("userId")int userId) {
        return Response.status(Response.Status.OK).entity(goalService.getUserGoals(userId)).build();
    }

    @PATCH
    @Path("/{userId}/hydration")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response patchHydrationGoals(final @PathParam("userId")int userId, int hydration) {
        goalService.patchHydrationGoals(userId, hydration);
        return Response.status(Response.Status.OK).build();
    }

    @PATCH
    @Path("/{userId}/weightloss")
    @Produces(MediaType.APPLICATION_JSON)
    public Response patchWeightLossGoals(final @PathParam("userId")int userId, BigDecimal weightloss) {
        goalService.patchWeightLossGoals(userId, weightloss);
        return Response.status(Response.Status.OK).build();
    }

}
