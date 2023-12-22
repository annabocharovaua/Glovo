package delivery.glovo.service.data;

import delivery.glovo.converter.ProductConverter;
import delivery.glovo.dto.ProductDto;
import delivery.glovo.model.data.Product;
import delivery.glovo.repository.data.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {
    private final ProductConverter productConverter;
    private final ProductRepository productRepository;

    @Override
    public List<ProductDto> getProducts() {
        Iterable<Product> products = productRepository.findAll();
        return productConverter.fromModel(products);
    }

    @Override
    public ProductDto getProductById(Integer id) {
        Product product = productRepository.findById(id).orElseThrow();
        return productConverter.fromModel(product);
    }

    @Override
    public void saveNewProduct(ProductDto newProduct) {
        Product product = productConverter.toModel(newProduct);
        productRepository.save(product);
    }

    @Override
    public void updateProductById(Integer id, ProductDto updatedProduct) {
        Product old = productRepository.findById(id).orElseThrow();
        Product newProduct = productConverter.toModel(old, updatedProduct);
        productRepository.save(newProduct);
    }

    @Override
    public void deleteProductById(Integer id) {
        productRepository.deleteById(id);
    }
}
