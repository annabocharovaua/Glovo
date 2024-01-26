//package delivery.glovo.controller.data;
//
//import delivery.glovo.controller.response.ApiResponse;
//import delivery.glovo.dto.ProductDto;
//import delivery.glovo.model.Product;
//import delivery.glovo.repository.ProductRepository;
//import org.junit.jupiter.api.Assertions;
//import org.junit.jupiter.api.Test;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.boot.test.web.client.TestRestTemplate;
//import org.springframework.core.ParameterizedTypeReference;
//import org.springframework.http.HttpEntity;
//import org.springframework.http.HttpMethod;
//import org.springframework.test.context.ActiveProfiles;
//
//import java.util.List;
//import java.util.Optional;
//
//@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
//@ActiveProfiles("dev")
//class ProductControllerTest {
//
//    @Value("${local.server.port}")
//    private int port;
//
//    @Autowired
//    private TestRestTemplate testRestTemplate;
//
//    @Autowired
//    private ProductRepository productRepository;
//
//    @Test
//    void shouldGetById() {
//        Product product = Product.builder().
//                name("product").
//                cost(10.1).
//                build();
//        Product savedProduct = productRepository.save(product);
//
//        ProductDto result = testRestTemplate.getForObject("http://localhost:" + port + "/api/v1/products/" + savedProduct.getId(), ProductDto.class);
//
//        Assertions.assertNotNull(result);
//        Assertions.assertEquals(savedProduct.getId(), result.getId());
//    }
//
//    @Test
//    void shouldCreateProduct() {
//        List<Product> initialProducts = productRepository.findAll();
//        int initialSize = initialProducts.size();
//        Product product = Product.builder().
//                name("product2").
//                cost(25.1).
//                build();
//        Product savedProduct = productRepository.save(product);
//
//        testRestTemplate.postForEntity("http://localhost:" + port + "/api/v1/products/" + savedProduct.getId(), product, Product.class);
//        List<Product> updatedProducts = productRepository.findAll();
//        int updatedSize = updatedProducts.size();
//
//        Assertions.assertEquals(initialSize + 1, updatedSize);
//    }
//
//    @Test
//    void shouldUpdateProductById() {
//        Product product = Product.builder().
//                name("product3").
//                cost(100.0).
//                build();
//        Product savedProduct = productRepository.save(product);
//        ProductDto productDto = ProductDto.builder().name("newName").cost(75.25).build();
//
//        testRestTemplate.exchange("http://localhost:" + port + "/api/v1/products/" + savedProduct.getId(), HttpMethod.PUT,
//                new HttpEntity<>(productDto),
//                new ParameterizedTypeReference<ApiResponse<ProductDto>>() {
//                });
//        Optional<Product> updatedProduct = productRepository.findById(savedProduct.getId());
//
//        Assertions.assertEquals("newName", updatedProduct.get().getName());
//    }
//
//    @Test
//    void shouldDeleteProductById(){
//       Product product = Product.builder().
//                name("product3").
//                cost(100.0).
//                build();
//        Product savedProduct = productRepository.save(product);
//        List<Product> initialProducts = productRepository.findAll();
//        int initialSize = initialProducts.size();
//
//        testRestTemplate.delete("http://localhost:" + port + "/api/v1/products/" + savedProduct.getId());
//        List<Product> updatedProducts = productRepository.findAll();
//        int updatedSize = updatedProducts.size();
//        Optional<Product> deletedProduct = productRepository.findById(savedProduct.getId());
//
//        Assertions.assertTrue(deletedProduct.isEmpty());
//        Assertions.assertEquals(initialSize-1, updatedSize);
//
//    }
//
//
//}