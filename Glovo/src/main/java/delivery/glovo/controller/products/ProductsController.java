package delivery.glovo.controller.products;

import delivery.glovo.controller.response.ApiResponse;
import delivery.glovo.dto.ProductDto;
import delivery.glovo.service.GlovoService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.dao.EmptyResultDataAccessException;

import java.util.List;
import java.util.stream.Stream;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/products")
public class ProductsController {
    private final GlovoService glovoService;

    @GetMapping("/{id}")
    public ApiResponse<ProductDto> getById(@PathVariable("id") int id) {
        ApiResponse<ProductDto> apiResponse = new ApiResponse<>();
        ProductDto productDto = glovoService.getProduct(id);
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
        List<ProductDto> productDtoList = glovoService.getAllProducts();
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
        glovoService.addProduct(productDto);
        ProductDto newProduct = glovoService.getProduct(productDto.getId());
        if (newProduct != null && newProduct.getName().equals(productDto.getName())
                && newProduct.getCost() == newProduct.getCost()) {
            apiResponse.setSuccess(true);
            apiResponse.setData(newProduct);
            apiResponse.setMessages(Stream.of(HttpStatus.OK.toString()).toList());
        }
        return apiResponse;
    }

    @PutMapping("/{id}")
    public ApiResponse<ProductDto> updateById(@PathVariable("id") int id, @RequestBody ProductDto productDto) {
        ApiResponse<ProductDto> apiResponse = new ApiResponse<>();
        glovoService.updateProduct(id, productDto);
        ProductDto updatedProduct = glovoService.getProduct(id);
        if (updatedProduct != null && updatedProduct.getName().equals(productDto.getName())
                && updatedProduct.getCost() == productDto.getCost()) {
            apiResponse.setSuccess(true);
            apiResponse.setData(updatedProduct);
            apiResponse.setMessages(Stream.of(HttpStatus.OK.toString()).toList());
        }
        return apiResponse;
    }

    @DeleteMapping("/{id}")
    public ApiResponse<ProductDto> deleteById(@PathVariable("id") int id) {
        ApiResponse<ProductDto> apiResponse = new ApiResponse<>();
        glovoService.deleteProduct(id);
        try {
            glovoService.getProduct(id);
        } catch (EmptyResultDataAccessException e) {
            apiResponse.setSuccess(true);
            apiResponse.setMessages(Stream.of(HttpStatus.OK.toString()).toList());
        }
        return apiResponse;
    }

}
