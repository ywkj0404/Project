package kr.co.hanbit.product.management.application;

import kr.co.hanbit.product.management.domian.Product;
import kr.co.hanbit.product.management.domian.ProductRepository;
import kr.co.hanbit.product.management.presentation.ProductDto;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

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

    public ProductDto findById(Long id) {
        Product product = productRepository.findById(id);
        ProductDto productDto = modelMapper.map(product, ProductDto.class);

        return productDto;
    }

    public List<ProductDto> findAll() {
        List<Product> productList = productRepository.findAll();
        List<ProductDto> productDtoList = productList.stream()
                .map(product -> modelMapper.map(product, ProductDto.class))
                .toList();

        return productDtoList;
    }

    public List<ProductDto> findByNameContaining(String name) {
        List<Product> productList = productRepository.findByNameContaining(name);
        List<ProductDto> productDtoList = productList.stream()
                .map(product -> modelMapper.map(product, ProductDto.class))
                .toList();

        return productDtoList;
    }

}
