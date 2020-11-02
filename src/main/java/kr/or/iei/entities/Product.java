package kr.or.iei.entities;

public class Product {

    private long id;
    private ProductType productType;
    private String name;
    private long price;

    public Product(long id, ProductType productType, String name, long price) {
        this.id = id;
        this.productType = productType;
        this.name = name;
        this.price = price;
    }

    public ProductType getProductType() {
        return productType;
    }

    public long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public long getPrice() {
        return price;
    }

    @Override
    public String toString() {
        return "Product{" +
                "id=" + id +
                ", productType=" + productType +
                ", name='" + name + '\'' +
                ", price=" + price +
                '}';
    }
}
