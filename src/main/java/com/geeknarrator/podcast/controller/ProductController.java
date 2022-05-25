package com.geeknarrator.podcast.controller;

import com.geeknarrator.podcast.model.Product;
import com.geeknarrator.podcast.service.ProductService;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/api/v1/products")
public class ProductController {
    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }


    @ApiResponses(value = {@ApiResponse(responseCode = "200",
            description = "Found product",
            content = {@Content(mediaType = "application/json", schema = @Schema(implementation = Product.class))}),
            @ApiResponse(responseCode = "400", description = "Invalid id supplied", content = @Content),
            @ApiResponse(responseCode = "404", description = "Product not found", content = @Content)})
    @GetMapping("/{id}")
    public ResponseEntity<?> getProductById(@PathVariable String id) {
        if (!id.startsWith("GN")) {
            return ResponseEntity.badRequest().body(Map.of("error", "Id has to start with GN"));
        }
        return productService.getProductById(id).map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }
}
