package org.sondhi.harsh.quarkus.jdbc;


import io.quarkus.test.junit.QuarkusTest;

import org.junit.jupiter.api.Test;

import jakarta.inject.Inject;

import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@QuarkusTest
public class ArtistRepositoryTest {

    @Inject
    ArtistRepositoty artistRepositoty;

    @Test
    public void should_create_find_artist() throws SQLException {
        Artist artist = new Artist("name", "bio");

        artistRepositoty.persist(artist);
        assertNotNull(artist.getId());

        artist = artistRepositoty.findById(artist.getId());
        assertEquals("name", artist.getName());
    }
}
