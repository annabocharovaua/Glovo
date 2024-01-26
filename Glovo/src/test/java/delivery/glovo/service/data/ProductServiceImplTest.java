//package delivery.glovo.service.data;
//
//import delivery.glovo.dto.ProductDto;
//import delivery.glovo.mappers.ProductMapper;
//import delivery.glovo.model.Product;
//import delivery.glovo.service.ProductServiceImpl;
//import delivery.glovo.repository.ProductRepository;
//import org.mockito.junit.jupiter.MockitoExtension;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//import org.junit.jupiter.api.extension.ExtendWith;
//import org.mockito.InjectMocks;
//import org.mockito.Mock;
//
//import java.util.ArrayList;
//import java.util.List;
//import java.util.NoSuchElementException;
//import java.util.Optional;
//
//import static org.junit.jupiter.api.Assertions.*;
//import static org.mockito.ArgumentMatchers.any;
//import static org.mockito.Mockito.verify;
//import static org.mockito.Mockito.when;
//
//
//@ExtendWith(MockitoExtension.class)
//public class ProductServiceImplTest {
//    private static final int PRODUCT_ID = 10;
//    @InjectMocks
//    private ProductServiceImpl testInstance;
//    @Mock
//    private ProductMapper productMapper;
//    @Mock
//    private ProductRepository productRepository;
//    @Mock
//    private List<Product> products = new ArrayList<>();
//    @Mock
//    private Product product;
//    private ProductDto productDto;
//    private List<ProductDto> productDtoList;
//
//    @BeforeEach
//    public void init() {
//        productDto = ProductDto.builder().
//                id(PRODUCT_ID).
//                name("product").
//                cost(15.5).
//                build();
//
//        productDtoList = new ArrayList<>();
//        productDtoList.add(productDto);
//    }
//
//    @Test
//    void shouldReturnProducts() {
//        when(productRepository.findAll()).thenReturn(products);
//        when(productMapper.productListToProductDtoList(products)).thenReturn(productDtoList);
//
//        List<ProductDto> result = testInstance.getProducts();
//
//        verify(productRepository).findAll();
//        verify(productMapper).productListToProductDtoList(products);
//        assertNotNull(result);
//        assertEquals(1, productDtoList.size());
//        assertEquals(productDtoList, result);
//    }
//
//    @Test
//    void shouldReturnProductById() {
//        when(productRepository.findById(any())).thenReturn(Optional.of(product));
//        when(productMapper.productToProductDto(product)).thenReturn(productDto);
//
//        ProductDto result = testInstance.getProductById(PRODUCT_ID);
//
//        verify(productRepository).findById(PRODUCT_ID);
//        verify(productMapper).productToProductDto((product));
//        assertNotNull(result);
//        assertEquals(PRODUCT_ID, result.getId());
//    }
//
//    @Test
//    void shouldNotReturnProductBuId() {
//        assertThrows(NoSuchElementException.class, () ->
//                testInstance.getProductById(PRODUCT_ID));
//    }
//
//    @Test
//    void shouldSaveProduct() {
//        when(productMapper.productDtoToProduct(productDto)).thenReturn(product);
//
//        testInstance.saveNewProduct(productDto);
//
//        verify(productMapper).productDtoToProduct(productDto);
//        verify(productRepository).save(product);
//    }
//
//    @Test
//    void shouldUpdateProduct() {
//        when(productRepository.findById(any())).thenReturn(Optional.of(product));
//        when(productMapper.updateToProduct(product, productDto)).thenReturn(new Product());
//
//        testInstance.updateProductById(PRODUCT_ID, productDto);
//
//        verify(productRepository).findById(PRODUCT_ID);
//        verify(productMapper).updateToProduct(product, productDto);
//        verify(productRepository).save(new Product());
//    }
//
//    @Test
//    void shouldDeleteProduct() {
//        testInstance.deleteProductById(PRODUCT_ID);
//
//        verify(productRepository).deleteById(PRODUCT_ID);
//    }
//
//}