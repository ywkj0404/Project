package kr.co.hanbit.product.management.infrastructure;

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

}
