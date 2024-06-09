package com.bephathao.controller;

import com.bephathao.dto.FilterDto;
import com.bephathao.service.IProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/products")
public class ProductController {
    @Autowired
    private IProductService productService;

    @GetMapping
    public ResponseEntity<Object> getAll(@RequestParam(name = "page", defaultValue = "0") int page,
                                    @RequestParam(name = "size", defaultValue = "8") int size) {
        return ResponseEntity.ok(productService.getAll(page, size));
    }

    @GetMapping("/{productId}")
    public ResponseEntity<Object> getById(@PathVariable Long productId) {
            return ResponseEntity.ok(productService.getProductById(productId));
    }

    @GetMapping("/categories/{categoryId}")
    public ResponseEntity<Object> getAllByCategoryId(@PathVariable("categoryId") Long categoryId,
                                                @RequestParam(name = "productId", defaultValue = "0") Long productId,
                                                @RequestParam(name = "page", defaultValue = "0") int page,
                                                @RequestParam(name = "size", defaultValue = "8") int size) {
        return ResponseEntity.ok(productService.findAllByCategoryId(categoryId, productId, page, size));
    }

    @PostMapping("/filter")
    public ResponseEntity<Object> filter(@RequestBody FilterDto filterDto,
                                    @RequestParam(name = "page", defaultValue = "0") int page,
                                    @RequestParam(name = "size", defaultValue = "12") int size) {
        try {
            return ResponseEntity.ok(productService.filter(filterDto, page, size));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }
}
