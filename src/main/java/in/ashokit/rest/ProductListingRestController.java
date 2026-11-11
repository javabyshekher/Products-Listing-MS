package in.ashokit.rest;

import in.ashokit.dto.ApiResponse;
import in.ashokit.dto.ProductCategoryDto;
import in.ashokit.dto.ProductDto;
import in.ashokit.service.ProductCategoryService;
import in.ashokit.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/products")
@CrossOrigin
public class ProductListingRestController {

    @Autowired
    private ProductService productService;

    @Autowired
    private ProductCategoryService productCategoryService;

    @GetMapping("/categories")
    public ResponseEntity<ApiResponse<List<ProductCategoryDto>>> getAllProductCategories(){

        List<ProductCategoryDto> allProductCategories = productCategoryService.getAllProductCategories();

        ApiResponse<List<ProductCategoryDto>> response = new ApiResponse<>();

        response.setStatusCode(200);
        response.setMessage("Product Categories Retrieved Successfully");
        response.setData(allProductCategories);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/category/{categoryId}")
    public ResponseEntity<ApiResponse<List<ProductDto>>> getProductsByCategoryId(@PathVariable Integer categoryId){

        List<ProductDto> allProductsByCategoryId = productService.getAllProductsByCategoryId(categoryId);
        ApiResponse<List<ProductDto>> response = new ApiResponse<>();

        if (!allProductsByCategoryId.isEmpty()) {
            response.setStatusCode(200);
            response.setMessage("Products fetched successfully");
            response.setData(allProductsByCategoryId);
            return ResponseEntity.status(200).body(response);
        } else {
            response.setStatusCode(400);
            response.setMessage("Failed to fetch products");
            response.setData(null);
            return ResponseEntity.status(400).body(response);
        }
    }

    @GetMapping("/{productId}")
    public ResponseEntity<ApiResponse<ProductDto>> getProductById(@PathVariable Integer productId){

        ProductDto productById = productService.getProductById(productId);

        ApiResponse<ProductDto> response = new ApiResponse<>();

        if (productById!=null) {
            response.setStatusCode(200);
            response.setMessage("Product fetched successfully");
            response.setData(productById);
            return ResponseEntity.status(200).body(response);
        } else {
            response.setStatusCode(400);
            response.setMessage("Failed to fetch product");
            response.setData(null);
            return ResponseEntity.status(400).body(response);
        }
    }

    @GetMapping("/by-name/{name}")
    public ResponseEntity<ApiResponse<List<ProductDto>>> getProductsByName(@PathVariable String name){

        List<ProductDto> productsByName = productService.getProductsByName(name);

        ApiResponse<List<ProductDto>> response = new ApiResponse<>();

        if (!productsByName.isEmpty()) {
            response.setStatusCode(200);
            response.setMessage("Products fetched successfully");
            response.setData(productsByName);
            return ResponseEntity.status(200).body(response);
        } else {
            response.setStatusCode(400);
            response.setMessage("Failed to fetch products");
            response.setData(null);
            return ResponseEntity.status(400).body(response);
        }
    }
}
