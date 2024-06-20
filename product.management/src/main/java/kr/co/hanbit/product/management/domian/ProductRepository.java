package kr.co.hanbit.product.management.domian;

import java.util.List;

public interface ProductRepository {

    Product save(Product product);

    Product findById(Long id);

    List<Product> findAll();

    List<Product> findByNameContaining(String name);

}
