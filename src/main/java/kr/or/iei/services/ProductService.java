package kr.or.iei.services;

import kr.or.iei.entities.Product;
import kr.or.iei.entities.ProductRepository;
import kr.or.iei.entities.ProductType;
import kr.or.iei.repositories.DBProductRepository;

import java.util.List;

public class ProductService {

    private final ProductRepository repository = DBProductRepository.getInstance();

    public List<Product> getProductListByType(ProductType productType) {
        return repository.getProductListByType(productType);
    }

    public Product getProductById(int productId) {
        return repository.getProductById(productId);
    }
}
