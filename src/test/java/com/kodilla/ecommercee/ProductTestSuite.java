package com.kodilla.ecommercee;

import com.kodilla.ecommercee.domain.Group;
import com.kodilla.ecommercee.domain.Product;
import com.kodilla.ecommercee.repository.GroupRepository;
import com.kodilla.ecommercee.repository.ProductRepository;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.math.BigDecimal;
import java.util.Optional;


@RunWith(SpringRunner.class)
@SpringBootTest()
public class ProductTestSuite {

    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private GroupRepository groupRepository;

    @Test
    public void testProductSave() {

        //Given
        Group group = new Group("NAME");
        Product product1 = new Product("Name", "Descr", new BigDecimal(5.50), group);

        //When
        group.getProducts().add(product1);
        groupRepository.save(group);

        //Then
        long id = product1.getId();;

        Optional<Product> readProduct = productRepository.findById(id);
        Assert.assertTrue(readProduct.isPresent());


        //CleanUp
        productRepository.deleteById(id);
        System.out.println(product1.getGroup().getProducts().size());
    }

    @Test
    public void testFindAllProducts(){
        //Given
        Group group = new Group("NAME");
        Product product1 = new Product("Name", "Descr", new BigDecimal(5.50), group);
        Product product2 = new Product("Name2", "Descr2", new BigDecimal(5.50), group);
        Product product3 = new Product("Name2", "Descr2", new BigDecimal(5.50), group);

        //When
        groupRepository.save(group);
        productRepository.save(product1);
        productRepository.save(product2);
        productRepository.save(product3);
        int productAmount = productRepository.findAll().size();

        //Then
        Assert.assertEquals(3, productAmount);

    }


    @Test
    public void testDeleteProduct_ShouldSaveGroup(){
        //Given
        Group group = new Group("NAME");
        Product product1 = new Product("Name", "Descr", new BigDecimal(5.50), group);
        Product product2 = new Product("Name2", "Descr2", new BigDecimal(5.50), group);

        //When
        groupRepository.save(group);
        productRepository.save(product1);
        productRepository.save(product2);
        long product1Id = product1.getId();

        //Then
        productRepository.deleteById(product1Id);
        int productAmount = productRepository.findAll().size();

        Assert.assertEquals(1, productAmount);
        Assert.assertTrue(!groupRepository.findAll().isEmpty());

        //CleanUp
        groupRepository.deleteAll();
    }



    @Test
    public void testUpdateProduct(){
        //Given
        Group group = new Group("NAME");
        Product product1 = new Product("Name", "Descr", new BigDecimal(5.50), group);
        Product product2 = new Product("Name2", "Descr2", new BigDecimal(5.50), group);
        Product product3 = new Product("Name2", "Descr2", new BigDecimal(5.50), group);

        //When
        groupRepository.save(group);
        productRepository.save(product1);
        productRepository.save(product2);
        productRepository.save(product3);

        Product productToUpdate = productRepository.findById(product1.getId()).get();
        productToUpdate.setUnitPrice(new BigDecimal(6.00));

        //Then
        Assert.assertEquals(new BigDecimal(6.00), productToUpdate.getUnitPrice());

        //CleanUp
        groupRepository.deleteAll();
    }

}
