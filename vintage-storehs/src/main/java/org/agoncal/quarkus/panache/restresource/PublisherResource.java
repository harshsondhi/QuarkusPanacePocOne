package org.agoncal.quarkus.panache.restresource;

import jakarta.transaction.Transactional;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.*;
import org.agoncal.quarkus.panache.model.Publisher;
import org.sondhi.harsh.quarkus.jdbc.Artist;

import java.util.List;

@Path("/api/publishers")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class PublisherResource {


    @GET
    @Path("{id}")
    public Publisher findPublisherById(@PathParam("id") Long id){
        return (Publisher) Publisher.findByIdOptional(id).orElseThrow(NotFoundException::new);
    }

    @GET
    public List<Publisher> findAllThePublishers(){
        return Publisher.listAll();
    }

    @Transactional(Transactional.TxType.REQUIRED)
    @POST
    public Response persistPublishert(Publisher publisher, @Context UriInfo uriInfo){
        Publisher.persist(publisher);
        UriBuilder builder = uriInfo.getAbsolutePathBuilder().path(Long.toString(publisher.id));
        return Response.created(builder.build()).build();
    }

    @Transactional
    @DELETE
    @Path("{id}")
    public void deletPubliher(@PathParam("id") Long id){
        Publisher.deleteById(id);
    }


}
