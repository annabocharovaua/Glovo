package delivery.glovo.service;

import delivery.glovo.dto.ProductDto;
import delivery.glovo.mappers.ProductMapper;
import delivery.glovo.model.Product;
import delivery.glovo.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {

    private final ProductMapper productMapper;
    private final ProductRepository productRepository;

    @Override
    public List<ProductDto> getProducts() {
        Iterable<Product> products = productRepository.findAll();
        return productMapper.productListToProductDtoList(products);
    }

    @Override
    public ProductDto getProductById(Integer id) {
        Product product = productRepository.findById(id).orElseThrow();
        return productMapper.productToProductDto(product);
    }

    @Override
    public void saveNewProduct(ProductDto newProduct) {
        Product product = productMapper.productDtoToProduct(newProduct);
        productRepository.save(product);
    }

    @Override
    public void updateProductById(Integer id, ProductDto updatedProduct) {
        Product old = productRepository.findById(id).orElseThrow();
        Product newProduct = productMapper.updateToProduct(old, updatedProduct);
        productRepository.save(newProduct);
    }

    @Override
    public void deleteProductById(Integer id) {
        productRepository.deleteById(id);
    }

}
