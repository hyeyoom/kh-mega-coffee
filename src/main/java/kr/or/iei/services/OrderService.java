package kr.or.iei.services;

import kr.or.iei.entities.OrderDto;
import kr.or.iei.entities.OrderProductDto;
import kr.or.iei.entities.Product;

import java.util.List;

public class OrderService {

    private final ProductService productService = new ProductService();

    public void calculateOrder(OrderDto orderDto) {
        final List<OrderProductDto> ops = orderDto.getOrderProductDtos();
        int originCost = 0;
        for (OrderProductDto op : ops) {
            final int productId = op.getProductId();
            final Product product = productService.getProductById(productId);
            originCost += product.getPrice() * op.getQuantity();
        }
        System.out.println(originCost);
    }
}
