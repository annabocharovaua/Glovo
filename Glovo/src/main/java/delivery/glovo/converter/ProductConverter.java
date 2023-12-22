package delivery.glovo.converter;

import delivery.glovo.dto.ProductDto;
import delivery.glovo.model.data.Product;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class ProductConverter {
    public ProductDto fromModel(Product product) {
        return ProductDto.builder().
                id(product.getId()).
                name(product.getName()).
                cost(product.getCost()).
                build();
    }
    public Product toModel(ProductDto productDto) {
        return Product.builder().
                id(productDto.getId()).
                name(productDto.getName()).
                cost(productDto.getCost()).
                build();
    }
    public List<ProductDto> fromModel(Iterable<Product> products) {
        List<ProductDto> productDtoList = new ArrayList<>();
        for (Product product : products) {
            productDtoList.add(fromModel(product));
        }
        return productDtoList;
    }
    public Product toModel(Product product, ProductDto productDto) {
        product.setName(productDto.getName());
        product.setCost(productDto.getCost());
        return product;
    }
}
