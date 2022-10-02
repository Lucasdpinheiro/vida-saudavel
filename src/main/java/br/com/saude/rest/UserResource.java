package br.com.saude.rest;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import br.com.saude.models.User;
import br.com.saude.services.UserService;

@RequestScoped
@Path("/users")
public class UserResource {

    @Inject
    UserService userService;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getUserProfileList() {
        return Response.status(Response.Status.OK).entity(userService.getUserProfileList()).build();
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response createUserProfile(User user) {
        userService.postUserProfile(user);
        return Response.status(Response.Status.CREATED).build();
    }

    @GET
    @Path("/{userId}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getUserProfile(final @PathParam("userId")int userId) {
        return Response.status(Response.Status.OK).entity(userService.getUserProfile(userId)).build();
    }

    @DELETE
    @Path("/{userId}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response setGoal(final @PathParam("userId")int userId) {
        userService.deleteUserProfile(userId);
        return Response.status(Response.Status.OK).build();
    }
}