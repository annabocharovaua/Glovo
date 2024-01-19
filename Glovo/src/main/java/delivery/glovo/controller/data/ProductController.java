package delivery.glovo.controller.data;

import delivery.glovo.controller.response.ApiResponse;
import delivery.glovo.dto.ProductDto;
import delivery.glovo.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Stream;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/products")
public class ProductController {
    private final ProductService productService;

    @GetMapping("/{id}")
    public ResponseEntity<ProductDto> getProductById(@PathVariable("id") Integer id) {
        ProductDto product = productService.getProductById(id);
        if (product != null) {
            return ResponseEntity.ok(product);
        }
        return (ResponseEntity<ProductDto>) ResponseEntity.notFound();
    }

    @GetMapping()
    public ApiResponse<List<ProductDto>> getProducts() {
        ApiResponse<List<ProductDto>> apiResponse = new ApiResponse<>();
        List<ProductDto> productDtoList = productService.getProducts();
        if (!CollectionUtils.isEmpty(productDtoList)) {
            apiResponse.setSuccess(true);
            apiResponse.setData(productDtoList);
            apiResponse.setMessages(Stream.of(HttpStatus.OK.toString()).toList());
        }

        return apiResponse;
    }

    @PostMapping()
    @ResponseStatus(HttpStatus.CREATED)
    public void save(@RequestBody ProductDto productDto) {
        productService.saveNewProduct(productDto);
    }

    @PutMapping("/{id}")
    public ApiResponse<ProductDto> updateById(@PathVariable("id") int id, @RequestBody ProductDto productDto) {
        ApiResponse<ProductDto> apiResponse = new ApiResponse<>();
        productService.updateProductById(id, productDto);
        ProductDto updatedProduct = productService.getProductById(id);
        apiResponse.setSuccess(true);
        apiResponse.setData(updatedProduct);
        apiResponse.setMessages(Stream.of(HttpStatus.OK.toString()).toList());

        return apiResponse;
    }

    @DeleteMapping("/{id}")
    public ApiResponse<ProductDto> deleteById(@PathVariable("id") int id) {
        ApiResponse<ProductDto> apiResponse = new ApiResponse<>();
        productService.deleteProductById(id);
        try {
            productService.getProductById(id);
        } catch (NoSuchElementException e) {
            apiResponse.setSuccess(true);
            apiResponse.setMessages(Stream.of(HttpStatus.OK.toString()).toList());
        }
        return apiResponse;
    }

}
