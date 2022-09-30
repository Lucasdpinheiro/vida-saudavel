package br.com.saude.rest;

import javax.enterprise.context.RequestScoped;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import br.com.saude.models.Goals;
import br.com.saude.models.User;

@RequestScoped
@Path("/users")
public class UserResource {

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response createUserProfile(User user) {
        return Response.status(Response.Status.CREATED).entity(user).build();
    }

    @GET
    @Path("/{userId}")
    @Consumes(MediaType.TEXT_PLAIN)
    @Produces(MediaType.APPLICATION_JSON)
    public Response getUserProfile(final @PathParam("idConta")int userId) {
        return Response.status(Response.Status.OK).build();
    }

    @POST
    @Path("/{userId}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response setGoal(final @PathParam("idConta")int userId, Goals goal) {
        return Response.status(Response.Status.CREATED).build();
    }
}