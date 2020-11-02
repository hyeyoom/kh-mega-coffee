package kr.or.iei.entities;

/**
 * DTO: Data Transfer Object
 */
public class OrderProductDto {

    // 선택할 상품의 아이디
    private int productId;
    
    // 수량
    private int quantity;

    public OrderProductDto(int productId, int quantity) {
        this.productId = productId;
        this.quantity = quantity;
    }

    public int getProductId() {
        return productId;
    }

    public int getQuantity() {
        return quantity;
    }

    @Override
    public String toString() {
        return "OrderProductDto{" +
                "productId=" + productId +
                ", quantity=" + quantity +
                '}';
    }
}
