package inventory_management.service;

import inventory_management.dto.ProductDTO;
import inventory_management.entity.Product;
import inventory_management.exception.ResourceNotFoundException;
import inventory_management.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProductService {

    @Autowired
    private ProductRepository productRepository;

    // Save Product
    public Product saveProduct(Product product) {
        return productRepository.save(product);
    }

    // Get All Products
    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    // Get Product By Id
    public Product getProductById(Long id) {
        return productRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Product not found with id: " + id));
    }

    // Update Product
    public Product updateProduct(Long id, Product product) {
        Product existingProduct = productRepository.findById(id).orElse(null);

        if (existingProduct != null) {
            existingProduct.setProductName(product.getProductName());
            existingProduct.setCategory(product.getCategory());
            existingProduct.setPrice(product.getPrice());
            existingProduct.setQuantity(product.getQuantity());

            return productRepository.save(existingProduct);
        }

        return null;
    }

    // Delete Product
    public void deleteProduct(Long id) {
        productRepository.deleteById(id);
    }

    // Get All Products as DTO
    public List<ProductDTO> getAllProductDTOs() {
        return productRepository.findAll().stream()
                .map(product -> new ProductDTO(
                        product.getProductName(),
                        product.getCategory(),
                        product.getPrice(),
                        product.getQuantity()
                ))
                .collect(Collectors.toList());
    }
    // Get Products with Pagination
    public Page<Product> getProductsWithPagination(int page, int size) {

        Pageable pageable = PageRequest.of(page, size);

        return productRepository.findAll(pageable);
    }
    // Get All Products with Sorting
    public List<Product> getAllProductsSorted(String field) {
        return productRepository.findAll(Sort.by(Sort.Direction.ASC, field));
    }
    // Pagination + Sorting
    public Page<Product> getProductsWithPaginationAndSorting(int page, int size, String field) {

        Pageable pageable = PageRequest.of(page, size, Sort.by(field));

        return productRepository.findAll(pageable);
    }
    // Search Products by Name
    public List<Product> searchProducts(String productName) {
        return productRepository.findByProductNameContainingIgnoreCase(productName);
    }
}