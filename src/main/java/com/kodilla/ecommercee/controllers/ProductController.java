package com.kodilla.ecommercee.controllers;

import com.kodilla.ecommercee.domain.Product;
import com.kodilla.ecommercee.dto.ProductDto;
import com.kodilla.ecommercee.exceptions.ProductNotFoundException;
import com.kodilla.ecommercee.mapper.ProductMapper;
import com.kodilla.ecommercee.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/products")
public class ProductController {

    private final ProductService productService;
    private final ProductMapper productMapper;

    @Autowired
    public ProductController(ProductService productService, ProductMapper productMapper) {
        this.productService = productService;
        this.productMapper = productMapper;
    }

    @GetMapping("/getAll")
    public List<ProductDto> getAllProducts() {
        List<Product> products = productService.getAllProducts();
        return productMapper.mapToProductListDto(products);
    }

    @GetMapping("/get/{id}")
    public ProductDto getProduct(@PathVariable("id") final Long id) throws ProductNotFoundException {
        Product product = productService.getProduct(id).orElseThrow(ProductNotFoundException::new);
        return productMapper.mapToProductDto(product);
    }

    @PostMapping(value = "/add", consumes = MediaType.APPLICATION_JSON_VALUE)
    public void addNewProduct(@RequestBody final ProductDto productDto) {
        Product product = productMapper.mapToProduct(productDto);
        productService.saveProduct(product);
    }

    @PutMapping("/modify")
    public ProductDto updateProduct(@RequestBody final ProductDto productDto) {
        Product product = productMapper.mapToProduct(productDto);
        Product savedProduct = productService.saveProduct(product);
        return productMapper.mapToProductDto(savedProduct);
    }

    @DeleteMapping("/delete/{id}")
    public void deleteProduct(@PathVariable("id") final Long id) {
        productService.removeProduct(id);
    }

}
