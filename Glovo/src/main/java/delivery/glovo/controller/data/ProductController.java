package delivery.glovo.controller.data;

import delivery.glovo.controller.response.ApiResponse;
import delivery.glovo.dto.ProductDto;
import delivery.glovo.service.data.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Objects;
import java.util.stream.Stream;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/products")
public class ProductController {
    private final ProductService productService;

    @GetMapping("/{id}")
    public ApiResponse<ProductDto> getById(@PathVariable("id") int id) {
        ApiResponse<ProductDto> apiResponse = new ApiResponse<>();
        ProductDto productDto = productService.getProductById(id);
        if (productDto != null) {
            apiResponse.setSuccess(true);
            apiResponse.setData(productDto);
            apiResponse.setMessages(Stream.of(HttpStatus.OK.toString()).toList());
        }
        return apiResponse;
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
    public ApiResponse<ProductDto> save(@RequestBody ProductDto productDto) {
        ApiResponse<ProductDto> apiResponse = new ApiResponse<>();
        productService.saveNewProduct(productDto);
        ProductDto newProduct = productService.getProductById(productDto.getId());
        if (newProduct != null && newProduct.getName().equals(productDto.getName())
                && Objects.equals(newProduct.getCost(), newProduct.getCost())) {
            apiResponse.setSuccess(true);
            apiResponse.setData(newProduct);
            apiResponse.setMessages(Stream.of(HttpStatus.OK.toString()).toList());
        }
        return apiResponse;
    }

    @PutMapping("/{id}")
    public ApiResponse<ProductDto> updateById(@PathVariable("id") int id, @RequestBody ProductDto productDto) {
        ApiResponse<ProductDto> apiResponse = new ApiResponse<>();
        productService.updateProductById(id, productDto);
        ProductDto updatedProduct = productService.getProductById(id);
        if (updatedProduct != null && updatedProduct.getName().equals(productDto.getName())
                && Objects.equals(updatedProduct.getCost(), productDto.getCost())) {
            apiResponse.setSuccess(true);
            apiResponse.setData(updatedProduct);
            apiResponse.setMessages(Stream.of(HttpStatus.OK.toString()).toList());
        }
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
