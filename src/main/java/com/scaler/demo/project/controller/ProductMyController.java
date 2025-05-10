package com.scaler.demo.project.controller;

import com.scaler.demo.project.dto.ProductDTO;
import com.scaler.demo.project.exceptions.ResourceNotFoundException;
import com.scaler.demo.project.model.ApiResponse;
import com.scaler.demo.project.model.Product;
import com.scaler.demo.project.service.ProductService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.List;

import static org.springframework.http.HttpStatus.*;
import static org.springframework.http.HttpStatus.NOT_FOUND;

@RestController
@RequestMapping("/api/v1")
public class ProductMyController {

    private ProductService productService;

    private RestTemplate restTemplate;

    public ProductMyController(ProductService productService){
        this.productService = productService;
    }

    @PostMapping
    public Product addNewProduct(@RequestBody Product product)
    {

        return productService.addNewproduct(product);
    }

    @GetMapping("/user/{uid}/product/{pid}")
    public Product getProductDetails(@PathVariable Long uid, @PathVariable Long pid){
        Product product =  productService.getProductDetails(uid, pid);

        return product;
    }

    @GetMapping("/all")
    public ResponseEntity<ApiResponse> getAllProducts() {
        List<Product> products = productService.getAllProducts();
      //  List<ProductDto> convertedProducts = productService.getConvertedProducts(products);

        return  ResponseEntity.ok(new ApiResponse("success", products));
    }

    @GetMapping("product/{productId}/product")
    public ResponseEntity<ApiResponse> getProductById(@PathVariable Long productId) {
        try {
            Product product = productService.findByProductId(productId);
            //ProductDto productDto = productService.convertToDto(product);
            return  ResponseEntity.ok(new ApiResponse("success", product));
        } catch (ResourceNotFoundException e) {
            return ResponseEntity.status(NOT_FOUND).body(new ApiResponse(e.getMessage(), null));
        }
    }

   // @PreAuthorize("hasRole('ROLE_ADMIN')")
//    @PostMapping("/add")
//    public ResponseEntity<ApiResponse> addProduct(@RequestBody Product product) {
//        try {
//            Product theProduct = productService.addProduct(product);
//            ProductDto productDto = productService.convertToDto(theProduct);
//            return ResponseEntity.ok(new ApiResponse("Add product success!", productDto));
//        } catch (AlreadyExistsException e) {
//            return ResponseEntity.status(CONFLICT).body(new ApiResponse(e.getMessage(), null));
//        }
//    }
//
//    @PreAuthorize("hasRole('ROLE_ADMIN')")
//    @PutMapping("/product/{productId}/update")
//    public  ResponseEntity<ApiResponse> updateProduct(@RequestBody ProductUpdateRequest request, @PathVariable Long productId) {
//        try {
//            Product theProduct = productService.updateProduct(request, productId);
//            ProductDto productDto = productService.convertToDto(theProduct);
//            return ResponseEntity.ok(new ApiResponse("Update product success!", productDto));
//        } catch (ResourceNotFoundException e) {
//            return ResponseEntity.status(NOT_FOUND).body(new ApiResponse(e.getMessage(), null));
//        }
//    }

//    @PreAuthorize("hasRole('ROLE_ADMIN')")
//    @DeleteMapping("/product/{productId}/delete")
//    public ResponseEntity<ApiResponse> deleteProduct(@PathVariable Long productId) {
//        try {
//            productService.deleteProductById(productId);
//            return ResponseEntity.ok(new ApiResponse("Delete product success!", productId));
//        } catch (ResourceNotFoundException e) {
//            return ResponseEntity.status(NOT_FOUND).body(new ApiResponse(e.getMessage(), null));
//        }
//    }

    @GetMapping("/products/by/brand-and-name")
    public ResponseEntity<ApiResponse> getProductByBrandAndName(@RequestParam String brandName, @RequestParam String productName) {
        try {
            List<Product> products = productService.getProductsByBrandAndName(brandName, productName);
            if (products.isEmpty()) {
                return ResponseEntity.status(NOT_FOUND).body(new ApiResponse("No products found ", null));
            }
          //  List<ProductDto> convertedProducts = productService.getConvertedProducts(products);
            return  ResponseEntity.ok(new ApiResponse("success", products));
        } catch (Exception e) {
            return ResponseEntity.status(INTERNAL_SERVER_ERROR).body(new ApiResponse(e.getMessage(), null));
        }
    }

    @GetMapping("/products/by/category-and-brand")
    public ResponseEntity<ApiResponse> getProductByCategoryAndBrand(@RequestParam String category, @RequestParam String brand){
        try {
            List<Product> products = productService.getProductsByCategoryAndBrand(category, brand);
            if (products.isEmpty()) {
                return ResponseEntity.status(NOT_FOUND).body(new ApiResponse("No products found ", null));
            }
          //  List<ProductDto> convertedProducts = productService.getConvertedProducts(products);
            return  ResponseEntity.ok(new ApiResponse("success", products));
        } catch (Exception e) {
            return ResponseEntity.status(INTERNAL_SERVER_ERROR).body(new ApiResponse("error", e.getMessage()));
        }
    }

    @GetMapping("/products/{name}/products")
    public ResponseEntity<ApiResponse> getProductByName(@PathVariable String name){
        try {
            List<Product> products = productService.getProductsByName(name);
            if (products.isEmpty()) {
                return ResponseEntity.status(NOT_FOUND).body(new ApiResponse("No products found ", null));
            }
          //  List<ProductDto> convertedProducts = productService.getConvertedProducts(products);
            return  ResponseEntity.ok(new ApiResponse("success", products));
        } catch (Exception e) {
            return ResponseEntity.status(INTERNAL_SERVER_ERROR).body(new ApiResponse("error", e.getMessage()));
        }
    }

    @GetMapping("/product/by-brand")
    public ResponseEntity<ApiResponse> findProductByBrand(@RequestParam String brand) {
        try {
            List<Product> products = productService.getProductsByBrand(brand);
            if (products.isEmpty()) {
                return ResponseEntity.status(NOT_FOUND).body(new ApiResponse("No products found ", null));
            }
            return  ResponseEntity.ok(new ApiResponse("success", products));
        } catch (Exception e) {
            return ResponseEntity.ok(new ApiResponse(e.getMessage(), null));
        }
    }

    @GetMapping("/product/{category}/all/products")
    public ResponseEntity<ApiResponse> findProductByCategory(@PathVariable String category) {
        try {
            List<Product> products = productService.getProductsByCategory(category);
            if (products.isEmpty()) {
                return ResponseEntity.status(NOT_FOUND).body(new ApiResponse("No products found ", null));
            }
            return  ResponseEntity.ok(new ApiResponse("success", products));
        } catch (Exception e) {
            return ResponseEntity.ok(new ApiResponse(e.getMessage(), null));
        }
    }

    @GetMapping("/product/count/by-brand/and-name")
    public ResponseEntity<ApiResponse> countProductsByBrandAndName(@RequestParam String brand, @RequestParam String name) {
        try {
            var productCount = productService.countProductsByBrandAndName(brand, name);
            return ResponseEntity.ok(new ApiResponse("Product count!", productCount));
        } catch (Exception e) {
            return ResponseEntity.ok(new ApiResponse(e.getMessage(), null));
        }
    }

}
