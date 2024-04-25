package org.agoncal.quarkus.panache.restresource;


import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.*;
import org.agoncal.quarkus.panache.repository.ArtistRepository;
import org.sondhi.harsh.quarkus.jdbc.Artist;

import java.util.List;

@Path("/api/artists")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
@Transactional(Transactional.TxType.SUPPORTS)
public class ArtistResource {

    @Inject
    ArtistRepository artistRepository;

    @GET
    @Path("{id}")
    public Artist findArtistById(@PathParam("id") Long id){
        return artistRepository.findByIdOptional(id).orElseThrow(NotFoundException::new);
    }

    @GET
    public List<Artist> findAllTheArtists(){
        return artistRepository.listAllArtistSorted();
    }

    @Transactional(Transactional.TxType.REQUIRED)
    @POST
    public Response persistArtist(Artist artist, @Context UriInfo uriInfo){
        artistRepository.persist(artist);
        UriBuilder builder = uriInfo.getAbsolutePathBuilder().path(Long.toString(artist.getId()));
        return Response.created(builder.build()).build();
    }

    @Transactional
    @DELETE
    @Path("{id}")
    public void deletArtist(@PathParam("id") Long id){
        artistRepository.deleteById(id);
    }

}
