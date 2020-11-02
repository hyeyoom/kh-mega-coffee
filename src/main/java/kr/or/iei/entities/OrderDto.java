package kr.or.iei.entities;

import java.util.List;

public class OrderDto {

    private final PackingType packingType;
    private final List<OrderProductDto> orderProductDtos;

    public OrderDto(PackingType packingType, List<OrderProductDto> orderProductDtos) {
        this.packingType = packingType;
        this.orderProductDtos = orderProductDtos;
    }

    public List<OrderProductDto> getOrderProductDtos() {
        return this.orderProductDtos;
    }

    @Override
    public String toString() {
        return "OrderDto{" +
                "packingType=" + packingType +
                ", orderProductDtos=" + orderProductDtos +
                '}';
    }
}
