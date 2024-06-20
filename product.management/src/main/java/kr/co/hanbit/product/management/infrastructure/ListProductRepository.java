package kr.co.hanbit.product.management.infrastructure;

import kr.co.hanbit.product.management.domian.EntityNotFoundException;
import kr.co.hanbit.product.management.domian.Product;
import kr.co.hanbit.product.management.domian.ProductRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.atomic.AtomicLong;

@Repository
public class ListProductRepository implements ProductRepository {

    private List<Product> productList = new CopyOnWriteArrayList<>();
    private AtomicLong sequence = new AtomicLong(1L);

    @Override
    public Product save(Product product) {
        product.setId(sequence.getAndAdd(1L));
        productList.add(product);

        return product;
    }

    @Override
    public Product findById(Long id) {
        return productList.stream()
                .filter(product -> product.sameId(id))
                .findFirst()
                .orElseThrow(() -> new EntityNotFoundException("Product를 찾지 못했습니다."));
    }

    @Override
    public List<Product> findAll() {
        return productList;
    }

    @Override
    public List<Product> findByNameContaining(String name) {
        return productList.stream()
                .filter(product -> product.containsName(name))
                .toList();
    }

    @Override
    public Product update(Product product) {
        Integer indexToModify = productList.indexOf(product);
        productList.set(indexToModify, product);

        return product;
    }

    @Override
    public void delete(Long id) {
        Product product = findById(id);
        productList.remove(product);
    }

}
