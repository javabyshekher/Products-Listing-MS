package in.ashokit.service;

import in.ashokit.dto.ProductDto;
import in.ashokit.entity.ProductEntity;
import in.ashokit.mapper.ProductMapper;
import in.ashokit.repository.ProductCategoryRepository;
import in.ashokit.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.List;
import java.util.UUID;

@Service
public class ProductService {

    @Autowired
    private ProductRepository repository;

    @Autowired
    private ProductCategoryRepository categoryRepository;


    public List<ProductDto> getAllProductsByCategoryId(Integer categoryId) {
        return repository.findByCategoryCategoryId(categoryId).stream().map(ProductMapper::toDto).toList();
    }

    public ProductDto getProductById(Integer productId) {
        ProductEntity productEntity = repository.findById(productId).orElseThrow(() -> new RuntimeException("Product not found with id: " + productId));
        return ProductMapper.toDto(productEntity);
    }

    public List<ProductDto> getProductsByName(String productName) {
        return repository.findByNameContainingIgnoreCase(productName).stream().map(ProductMapper::toDto).toList();
    }

}
