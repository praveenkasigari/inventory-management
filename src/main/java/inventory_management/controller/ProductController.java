package inventory_management.controller;

import inventory_management.entity.Product;
import inventory_management.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import inventory_management.dto.ProductDTO;
import inventory_management.response.ApiResponse;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.RequestParam;
import jakarta.validation.Valid;

import java.util.List;

@RestController
@RequestMapping("/products")
public class ProductController {

    @Autowired
    private ProductService productService;

    // Add Product
    @PostMapping
    public ApiResponse<Product> addProduct(@Valid @RequestBody Product product) {
        return new ApiResponse<>(
                true,
                "Product added successfully",
                productService.saveProduct(product)
        );
    }

    // Get All Products
    @GetMapping
    public List<Product> getAllProducts() {
        return productService.getAllProducts();
    }

    // Get Product By Id
    @GetMapping("/{id}")
    public Product getProductById(@PathVariable Long id) {
        return productService.getProductById(id);
    }

    // Update Product
    @PutMapping("/{id}")
    public ApiResponse<Product> updateProduct(@PathVariable Long id,
                                              @Valid @RequestBody Product product) {

        return new ApiResponse<>(
                true,
                "Product updated successfully",
                productService.updateProduct(id, product)
        );
    }

    // Delete Product
    @DeleteMapping("/{id}")
    public String deleteProduct(@PathVariable Long id) {
        productService.deleteProduct(id);
        return "Product deleted successfully";
    }
    // Get All Products using DTO
    @GetMapping("/dto")
    public List<ProductDTO> getAllProductDTOs() {
        return productService.getAllProductDTOs();
    }
    // Get Products with Pagination
    @GetMapping("/page")
    public ApiResponse<Page<Product>> getProductsWithPagination(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "5") int size) {

        Page<Product> products = productService.getProductsWithPagination(page, size);

        return new ApiResponse<>(
                true,
                "Products fetched successfully",
                products
        );
    }
    // Get All Products with Sorting
    @GetMapping("/sort")
    public ApiResponse<List<Product>> getAllProductsSorted(
            @RequestParam(defaultValue = "id") String field) {

        List<Product> products = productService.getAllProductsSorted(field);

        return new ApiResponse<>(
                true,
                "Products sorted successfully",
                products
        );
    }
    // Pagination + Sorting
    @GetMapping("/page-sort")
    public ApiResponse<Page<Product>> getProductsWithPaginationAndSorting(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "5") int size,
            @RequestParam(defaultValue = "id") String field) {

        Page<Product> products =
                productService.getProductsWithPaginationAndSorting(page, size, field);

        return new ApiResponse<>(
                true,
                "Products fetched successfully with pagination and sorting",
                products
        );
    }
    // Search Products by Name
    @GetMapping("/search")
    public ApiResponse<List<Product>> searchProducts(
            @RequestParam String name) {

        List<Product> products = productService.searchProducts(name);

        return new ApiResponse<>(
                true,
                "Products found successfully",
                products
        );
    }
}