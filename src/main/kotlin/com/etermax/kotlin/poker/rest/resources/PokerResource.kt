package com.etermax.kotlin.poker.rest.resources

import com.etermax.kotlin.poker.rest.representation.HandRepresentation
import javax.ws.rs.GET
import javax.ws.rs.Path
import javax.ws.rs.Produces
import javax.ws.rs.core.MediaType


@Path("/")
@Produces(MediaType.APPLICATION_JSON)
class PokerResource {

    @GET
    @Path("/showHand")
    fun showHand(): HandRepresentation {
        return HandRepresentation(arrayListOf("A", "B", "C", "D", "E"))
    }
}