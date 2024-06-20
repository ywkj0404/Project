package kr.co.hanbit.product.management.domian;

public class Product {

    private Long id;
    private String name;
    private Integer price;
    private Integer amount;

    public void setId(Long id) {
        this.id = id;
    }

    public boolean sameId(Long id) {
        return this.id.equals(id);
    }

    public boolean containsName(String name) {
        return this.name.contains(name);
    }

}
