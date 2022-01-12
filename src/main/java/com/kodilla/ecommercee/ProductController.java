package com.kodilla.ecommercee;

import com.kodilla.ecommercee.domain.Product;
import com.kodilla.ecommercee.dto.ProductDto;
import com.kodilla.ecommercee.exceptions.ProductNotFoundException;
import com.kodilla.ecommercee.mapper.ProductMapper;
import com.kodilla.ecommercee.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/products")
public class ProductController {

    private ProductService productService;
    private ProductMapper productMapper;

    @GetMapping
    public List<ProductDto> getAllProducts() {
        List<Product> products = productService.getAllProducts();
        return productMapper.mapToProductListDto(products);
    }

    @GetMapping("/{id}")
    public ProductDto getProduct(@PathVariable("id") final Long id) throws ProductNotFoundException {
        Product product = productService.getProduct(id).orElseThrow(ProductNotFoundException::new);
        return productMapper.mapToProductDto(product);
    }

    @PostMapping()
    public void addNewProduct(@RequestBody final ProductDto productDto) {
        Product product = productMapper.mapToProduct(productDto);
        productService.saveProduct(product);
    }

    @PutMapping()
    public ProductDto updateProduct(@RequestBody final ProductDto productDto) {
        Product product = productMapper.mapToProduct(productDto);
        Product savedProduct = productService.saveProduct(product);
        return productMapper.mapToProductDto(savedProduct);
    }

    @DeleteMapping("/{id}")
    public void deleteProduct(@PathVariable("id") final Long id) {
        productService.removeProduct(id);
    }

}
