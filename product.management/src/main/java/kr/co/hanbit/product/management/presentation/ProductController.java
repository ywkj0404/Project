package kr.co.hanbit.product.management.presentation;

import jakarta.validation.Valid;
import jakarta.websocket.server.PathParam;
import kr.co.hanbit.product.management.application.ProductService;
import kr.co.hanbit.product.management.domian.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class ProductController {

    private ProductService productService;

    @Autowired
    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @RequestMapping(value = "/products", method = RequestMethod.POST)
    public ProductDto createProduct(@Valid @RequestBody ProductDto productDto) {
        ProductDto createdProductDto = productService.createProduct(productDto);

        return createdProductDto;
    }

    @RequestMapping(value = "/products/{id}", method = RequestMethod.GET)
    public ProductDto findById(
            @PathVariable Long id
    ) {
        return productService.findById(id);
    }

    @RequestMapping(value = "/products", method = RequestMethod.GET)
    public List<ProductDto> findProduct(
            @RequestParam(required = false) String name
    ) {
        if(name == null) return productService.findAll();

        return productService.findByNameContaining(name);
    }

    @RequestMapping(value = "/products/{id}", method = RequestMethod.PUT)
    public ProductDto updateProduct(
            @PathVariable Long id,
            @RequestBody ProductDto productDto
    ) {
        productDto.setId(id);

        return productService.updateProduct(productDto);
    }

    @RequestMapping(value = "/products/{id}", method = RequestMethod.DELETE)
    public void deleteProduct(
            @PathVariable Long id
    ) {
        productService.deleteProduct(id);
    }
}
