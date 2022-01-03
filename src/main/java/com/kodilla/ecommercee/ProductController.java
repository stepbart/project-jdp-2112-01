package com.kodilla.ecommercee;

import com.kodilla.ecommercee.dto.ProductDto;
import org.springframework.web.bind.annotation.*;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/products")
public class ProductController {

    @GetMapping(value="/getProducts")
    public List<ProductDto> getProducts() {
        List<ProductDto> productDtoList = new ArrayList<>();
        productDtoList.add(new ProductDto(1L, "cheese", "price for 1 kg",
                new BigDecimal("11.99")));
        productDtoList.add(new ProductDto(2L, "apple", "price per item",
                new BigDecimal("1.99")));
        productDtoList.add(new ProductDto(3L, "potatoes", "price for 1 kg",
                new BigDecimal("4.99")));
        return productDtoList;
    }

    @GetMapping(value="getProduct")
    public ProductDto getProduct(@RequestParam final Long id) {
        return new ProductDto(1L, "cheese", "price for 1 kg",
                new BigDecimal("11.99"));
    }

    @PostMapping(value="createProduct")
    public ProductDto addProduct(@RequestBody final ProductDto product) {
        return product;
    }

    @PutMapping(value="updateProduct")
    public ProductDto updateProduct(@RequestBody final ProductDto product) {
        return product;
    }

    @DeleteMapping(value="deleteProduct")
    public String deleteProduct(@RequestParam final Long id) {
        return "The product with id " + id + " has been deleted";
    }

}
