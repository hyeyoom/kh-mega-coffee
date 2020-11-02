package kr.or.iei.repositories;

import kr.or.iei.entities.Product;
import kr.or.iei.entities.ProductRepository;
import kr.or.iei.entities.ProductType;

import java.util.ArrayList;
import java.util.List;

public class InMemoryProductRepository implements ProductRepository {

    private static final InMemoryProductRepository INSTANCE = new InMemoryProductRepository();

    private final List<Product> list = new ArrayList<>();

    private InMemoryProductRepository() {
        list.add(new Product(1, ProductType.COFFEE, "아메리카노", 3500));
        list.add(new Product(2, ProductType.COFFEE, "카페모카", 4000));
        list.add(new Product(3, ProductType.COFFEE, "마끼아또", 4500));
        list.add(new Product(4, ProductType.SMOOTHIE, "요거트 스무디", 5500));
        list.add(new Product(5, ProductType.SMOOTHIE, "딸기 스무디", 5500));
        list.add(new Product(6, ProductType.CAKE, "치즈 케이크", 15500));
        list.add(new Product(7, ProductType.CAKE, "딸기 케이크", 16500));
    }

    public static ProductRepository getInstance() {
        return INSTANCE;
    }

    @Override
    public List<Product> getProductListByType(ProductType productType) {
        final List<Product> products = new ArrayList<>();
        for (Product product : list) {
            if (product.getProductType().equals(productType)) {
                products.add(product);
            }
        }
        return products;
    }

    @Override
    public Product getProductById(int productId) {
        for (Product product : list) {
            if(product.getId() == productId) {
                return product;
            }
        }
        return null;
    }
}
