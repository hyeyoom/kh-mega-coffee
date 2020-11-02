package kr.or.iei.controllers;

import kr.or.iei.entities.*;
import kr.or.iei.services.OrderService;
import kr.or.iei.services.ProductService;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Kiosk {

    private final Scanner scanner = new Scanner(System.in);
    private final ProductService productService = new ProductService();
    private final OrderService orderService = new OrderService();

    public void run() {
        // 0. 안내 메세지 그려달라
        renderWelcomeMessage();

        // 1. 포장 여부 결정 해달라
        final PackingType packingType = choosePackingType();
        System.out.println(packingType);

        // 2. 메뉴 선택 해달라
        final List<OrderProductDto> orderProductDtos = chooseProducts();
        System.out.println(orderProductDtos);

        final OrderDto orderDto = new OrderDto(packingType, orderProductDtos);
        orderService.calculateOrder(orderDto);
    }

    private List<OrderProductDto> chooseProducts() {
        // 사용자에게 입력을 받음
        final ProductType productType = chooseProductType();
        System.out.println(productType);

        final List<Product> productsByProductType = productService.getProductListByType(productType);
        renderProducts(productsByProductType);

        // 선택할 상품, 수량
        OrderProductDto orderProductDto = chooseProduct();
        final List<OrderProductDto> list = new ArrayList<>();
        list.add(orderProductDto);
        return list;
    }

    private OrderProductDto chooseProduct() {
        System.out.print("상품 선택 > ");
        final int productId = scanner.nextInt();
        System.out.print("상품 수량 > ");
        final int quantity = scanner.nextInt();
        return new OrderProductDto(productId, quantity);
    }

    private void renderProducts(List<Product> productsByProductType) {
        for (Product product : productsByProductType) {
            System.out.printf("%d. %s(%d원)\n", product.getId(), product.getName(), product.getPrice());
        }
    }

    private ProductType chooseProductType() {
        // 출력
        System.out.println("1. 커피");
        System.out.println("2. 스무디");
        System.out.println("3. 케이크");
        System.out.print("종류 선택 >");
        // 입력 from user
        final int selectedMenu = scanner.nextInt();
        switch (selectedMenu) {
            case 1:
                return ProductType.COFFEE;
            case 2:
                return ProductType.SMOOTHIE;
            case 3:
                return ProductType.CAKE;
            default:
                return null;
        }
    }

    private PackingType choosePackingType() {
        // 출력 - 선택지
        System.out.println("1. 포장하기\n2. 매장");
        // 입력 - from user
        System.out.print("선택 > ");
        final int selectedType = scanner.nextInt();
        return selectedType == 1 ? PackingType.TOGO : PackingType.INDOOR;
    }

    private void renderWelcomeMessage() {
        System.out.println("메가커피에 오신걸 환영합니다.");
    }
}
