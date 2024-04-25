package org.agoncal.quarkus.panache.repository;


import io.quarkus.test.TestTransaction;
import io.quarkus.test.junit.QuarkusTest;
import jakarta.persistence.EntityNotFoundException;
import org.agoncal.quarkus.panache.model.Publisher;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

@QuarkusTest
public class PublisherRepositoryTest {

    @Test
    @TestTransaction
    public void shouldCreateAndFindAPublisher() {

        long count = Publisher.count();
        int listAll = Publisher.listAll().size();
        assertEquals(count, listAll);
        Publisher publisher = new Publisher("name");

        Publisher.persist(publisher);
        assertNotNull(publisher.id);

        assertEquals(count +1, Publisher.count());
        publisher = Publisher.findByName(publisher.name).orElseThrow(EntityNotFoundException::new);

        publisher = Publisher.findById(publisher.id);
        assertEquals("name", publisher.name);
        assertFalse(Publisher.findContainsName("name").isEmpty());
        Publisher.findById(publisher.id);

    }


}
