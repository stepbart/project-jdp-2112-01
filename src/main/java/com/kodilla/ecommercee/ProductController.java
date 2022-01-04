package com.kodilla.ecommercee;

import com.kodilla.ecommercee.dto.ProductDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/products")
public class ProductController {

    @GetMapping
    public ResponseEntity<List<ProductDto>> getAllProducts() {
        List<ProductDto> temporaryList = new ArrayList<>();

        for (long i = 0; i <= 5; i++) {
            temporaryList.add(new ProductDto(
                    i,
                    "Product_" + i,
                    "price for 1 kg",
                    new BigDecimal("5.00").multiply(BigDecimal.valueOf(i))
            ));
        }
        return new ResponseEntity(
                temporaryList,
                HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProductDto> getProduct(@PathVariable("id") final Long id) {
        return new ResponseEntity(
                new ProductDto(
                        1L,
                        "Product_1",
                        "price for 1 kg",
                        new BigDecimal("25.00")
                ),
                HttpStatus.OK
        );
    }

    @PostMapping()
    public ResponseEntity<ProductDto> addNewProduct(@RequestBody final ProductDto product) {
        return new ResponseEntity(
                product,
                HttpStatus.CREATED
        );
    }

    @PutMapping()
    public ResponseEntity<ProductDto> updateProduct(@RequestBody final ProductDto product) {
        return new ResponseEntity(
                product,
                HttpStatus.OK
        );
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteProduct(@PathVariable("id") final Long id) {
        return new ResponseEntity(
                "Product by id " + id + " was deleted",
                HttpStatus.OK
        );
    }

}
