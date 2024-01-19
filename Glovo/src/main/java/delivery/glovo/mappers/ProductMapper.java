package delivery.glovo.mappers;

import delivery.glovo.dto.ProductDto;
import delivery.glovo.model.Product;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

import java.util.List;

@Mapper
public interface ProductMapper {
    List<Product> productDtoListToProductList(Iterable<ProductDto> list);
    Product productDtoToProduct(ProductDto productDto);
    ProductDto productToProductDto(Product product);
    List<ProductDto> productListToProductDtoList(Iterable<Product> list);
    @Mappings({
            @Mapping(target = "id", source = "product.id"),
            @Mapping(target = "name", source = "productDto.name"),
            @Mapping(target = "cost", source = "productDto.cost")
    })
    Product updateToProduct(Product product, ProductDto productDto);
}
