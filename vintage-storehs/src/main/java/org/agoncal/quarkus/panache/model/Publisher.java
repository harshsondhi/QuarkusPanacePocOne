package org.agoncal.quarkus.panache.model;

import io.quarkus.hibernate.orm.panache.PanacheEntity;
import jakarta.persistence.Entity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;

import java.time.Instant;
import java.util.List;
import java.util.Optional;


@Entity
@Table(name = "t_publishers")
public class Publisher extends PanacheEntity {

    @Column(length = 50, nullable = false)
    public String name;

    @Column(name = "created_date", nullable = false)
    public Instant createdDate = Instant.now();

    public Publisher() {
    }

    public Publisher(String name) {
        this.name = name;
    }

    public static Optional<Publisher> findByName(String name){
        return Publisher.find("name", name).firstResultOptional();
    }
    public static List<Publisher> findContainsName(String name){
        return Publisher.list("name like ?1", "%"+name+"%");
    }
}
