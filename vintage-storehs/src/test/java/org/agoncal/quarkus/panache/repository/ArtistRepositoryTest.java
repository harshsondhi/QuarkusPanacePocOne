package org.agoncal.quarkus.panache.repository;


import io.quarkus.test.TestTransaction;
import io.quarkus.test.junit.QuarkusTest;
import jakarta.inject.Inject;
import org.junit.jupiter.api.Test;
import org.sondhi.harsh.quarkus.jdbc.Artist;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@QuarkusTest
public class ArtistRepositoryTest {

    @Inject
    ArtistRepository artistRepository;

    @Test
    @TestTransaction
    public void shouldCreateAndFindAnArtist(){
        long count = artistRepository.count();
        int listAll = artistRepository.listAll().size();
        assertEquals(count, listAll);
        assertEquals(artistRepository.listAllArtistSorted().size(),listAll);

        Artist artist = new Artist();
        artist.setName("name");
        artist.setBio("bio");

        artistRepository.persist(artist);

        assertEquals(count+1, artistRepository.count());
        assertNotNull(artist.getId());
        artistRepository.deleteById(artist.getId());
        assertEquals(count, artistRepository.count());
    }
}
