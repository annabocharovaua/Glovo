package delivery.glovo.service.data;

import delivery.glovo.dto.ProductDto;

import java.util.List;

public interface ProductService {
    List<ProductDto> getProducts();
    ProductDto getProductById(Integer id);
    void saveNewProduct(ProductDto newProduct);
    void updateProductById(Integer id, ProductDto updatedProduct);
    void deleteProductById(Integer id);

}
