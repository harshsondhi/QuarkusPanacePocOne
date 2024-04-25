package org.agoncal.quarkus.panache.repository;


import io.quarkus.hibernate.orm.panache.PanacheRepository;
import io.quarkus.panache.common.Sort;
import jakarta.enterprise.context.ApplicationScoped;
import org.sondhi.harsh.quarkus.jdbc.Artist;

import java.util.List;

@ApplicationScoped
public class ArtistRepository implements PanacheRepository<Artist> {

    public List<Artist> listAllArtistSorted(){
        return listAll(Sort.ascending("name"));
    }
}
