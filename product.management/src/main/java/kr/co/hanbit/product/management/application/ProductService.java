package kr.co.hanbit.product.management.application;

import kr.co.hanbit.product.management.domian.Product;
import kr.co.hanbit.product.management.domian.ProductRepository;
import kr.co.hanbit.product.management.presentation.ProductDto;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProductService {

    private ProductRepository productRepository;
    private ModelMapper modelMapper;

    @Autowired
    public ProductService(
            ProductRepository productRepository,
            ModelMapper modelMapper
    ) {
        this.productRepository = productRepository;
        this.modelMapper = modelMapper;
    }

    public ProductDto createProduct(ProductDto productDto) {
        Product product = modelMapper.map(productDto, Product.class);
        Product savedProduct = productRepository.save(product);
        ProductDto savedProductDto = modelMapper.map(savedProduct, ProductDto.class);

        return savedProductDto;
    }

}
