package org.agoncal.quarkus.panache.repository;


import io.quarkus.test.TestTransaction;
import io.quarkus.test.junit.QuarkusTest;
import jakarta.inject.Inject;
import org.agoncal.quarkus.jpa.Customer;
import org.agoncal.quarkus.panache.model.*;
import org.junit.jupiter.api.Test;
import org.sondhi.harsh.quarkus.jdbc.Artist;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@QuarkusTest
public class PurchaseOrderRepositoryTest {

    @Inject
    CustomerRepository customerRepository;


    @Test
    @TestTransaction
    public void shouldCreateAndFindPurcahseOrder(){

        Artist artist = new Artist("artist_name","artist_bio");
        Publisher publisher =new Publisher("publisher_name");
        Book book = new Book();
        book.title ="title_of_book";
        book.nbOfPages = 500;
        book.language = Language.ENGLISH;
        book.price= new BigDecimal(10);
        book.isbn= "isbn";
        book.publisher = publisher;
        book.artist = artist;

        Book.persist(book);

        Customer customer = new Customer("first_name","last_name","email");
        customerRepository.persist(customer);

        OrderLine orderLine = new OrderLine();
        orderLine.item = book;
        orderLine.quantity = 2;

        PurchaseOrder purchaseOrder = new PurchaseOrder();
        purchaseOrder.customer = customer;
        purchaseOrder.addOrderLine(orderLine);

        PurchaseOrder.persist(purchaseOrder);
        var x=0;





    }

}
