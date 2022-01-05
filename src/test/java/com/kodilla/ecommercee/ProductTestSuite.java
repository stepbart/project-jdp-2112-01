package com.kodilla.ecommercee;

import com.kodilla.ecommercee.domain.Product;
import com.kodilla.ecommercee.repository.ProductRepository;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.math.BigDecimal;


@RunWith(SpringRunner.class)
@SpringBootTest()
public class ProductTestSuite {

    @Autowired
    private ProductRepository productRepository;

    @Test
    public void testAddProductToTable() {

        //Given
        Product product1 = new Product("Name", "Descr", new BigDecimal(5.50), null);
        Product product2 = new Product("Name", "Descr", new BigDecimal(5.50), null);

        //When
        productRepository.save(product1);
        productRepository.save(product2);

        //Then
        Assert.assertEquals(2, productRepository.findAll().size());

        //CleanUp
        productRepository.deleteAll();

    }
}
