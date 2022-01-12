package com.kodilla.ecommercee.domain;

import com.kodilla.ecommercee.domain.Group;
import com.kodilla.ecommercee.domain.Product;
import com.kodilla.ecommercee.repository.GroupRepository;
import com.kodilla.ecommercee.repository.ProductRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class GroupTestSuite {

    @Autowired
    private GroupRepository groupRepository;
    @Autowired
    private ProductRepository productRepository;

    @Test
    public void testGroupSave() {
        //Given
        Group group = new Group("Test group");
        //When
        groupRepository.save(group);
        Long id = group.getId();
        Optional<Group> testGroup = groupRepository.findById(id);
        //Then
        assertTrue(testGroup.isPresent());
        assertNotNull(id);
        //CleanUp
        try {
            groupRepository.deleteById(id);
        } catch (Exception e) {
            System.out.println("Unable to cleanup database");
        }
    }

    @Test
    public void testGroupSaveWithProducts_ShouldSaveProducts() {
        //Given
        Group group = new Group("Test group");
        Product product1 = new Product("Product1", "Description1", new BigDecimal("20.00"), group);
        Product product2 = new Product("Product2", "Description2", new BigDecimal("50.00"), group);
        group.getProducts().add(product1);
        group.getProducts().add(product2);
        //When
        groupRepository.save(group);
        Long groupId = group.getId();
        //Then
        assertNotNull(groupId);
        assertNotNull(product1.getId());
        assertNotNull(product2.getId());
        //CleanUp
        try {
            groupRepository.deleteById(groupId);
        } catch (Exception e) {
            System.out.println("Unable to cleanup database");
        }
    }

    @Test
    public void testGroupUpdate() {
        //Given
        Group group = new Group("Test group");
        Product product1 = new Product("Product1", "Description1", new BigDecimal("20.00"), group);
        group.getProducts().add(product1);
        groupRepository.save(group);
        Long groupId = group.getId();
        //When
        Product product2 = new Product("Product2", "Description2", new BigDecimal("50.00"), group);
        group.getProducts().add(product2);
        //Then
        assertEquals(2, group.getProducts().size());
        //CleanUp
        try {
            groupRepository.deleteById(groupId);
        } catch (Exception e) {
            System.out.println("Unable to cleanup database");
        }
    }

    @Test
    public void testGroupFindAll() {
        //Given
        Group group1 = new Group("Test group 1");
        Group group2 = new Group("Test group 2");
        groupRepository.save(group1);
        groupRepository.save(group2);
        Long group1Id = group1.getId();
        Long group2Id = group2.getId();
        //When
        List<Group> groupList = groupRepository.findAll();
        //Then
        assertEquals(2, groupList.size());
        //CleanUp
        try {
            groupRepository.deleteById(group1Id);
            groupRepository.deleteById(group2Id);
        } catch (Exception e) {
            System.out.println("Unable to cleanup database");
        }
    }

    @Test
    public void testGroupDelete_ShouldDeleteProducts() {
        //Given
        Group group = new Group("Test group");
        Product product1 = new Product("Product1", "Description1", new BigDecimal("20.00"), group);
        Product product2 = new Product("Product2", "Description2", new BigDecimal("50.00"), group);
        group.getProducts().add(product1);
        group.getProducts().add(product2);
        //When
        groupRepository.save(group);
        Long groupId = group.getId();
        Long product1Id = product1.getId();
        Long product2Id = product2.getId();
        groupRepository.deleteById(groupId);
        //Then
        assertFalse(groupRepository.existsById(groupId));
        assertFalse(productRepository.existsById(product1Id));
        assertFalse(productRepository.existsById(product2Id));
    }
}
