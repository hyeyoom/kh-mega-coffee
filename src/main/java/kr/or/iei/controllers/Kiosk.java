package kr.or.iei.controllers;

import kr.or.iei.entities.PackingType;
import kr.or.iei.entities.Product;
import kr.or.iei.entities.ProductType;
import kr.or.iei.services.ProductService;

import java.util.List;
import java.util.Scanner;

public class Kiosk {

    private final Scanner scanner = new Scanner(System.in);
    private final ProductService productService = new ProductService();

    public void run() {
        renderWelcomeMessage();

        // 1. 포장 여부 결정
        final PackingType packingType = choosePackingType();
        System.out.println(packingType);

        // 2. 메뉴 선택
        chooseProducts();
    }

    private void chooseProducts() {
        final ProductType productType = chooseProduct();
        System.out.println(productType);
        final List<Product> productsByProductType = productService.getProductListByType(productType);
        renderProducts(productsByProductType);
    }

    private void renderProducts(List<Product> productsByProductType) {
        for (Product product : productsByProductType) {
            System.out.printf("%d. %s(%d원)\n", product.getId(), product.getName(), product.getPrice());
        }

    }

    private ProductType chooseProduct() {
        System.out.println("1. 커피");
        System.out.println("2. 스무디");
        System.out.println("3. 케이크");
        System.out.print("종류 선택 >");
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
        System.out.println("1. 포장하기\n2. 매장");
        System.out.print("선택> ");
        final int selectedType = scanner.nextInt();
        return selectedType == 1 ? PackingType.TOGO : PackingType.INDOOR;
    }

    private void renderWelcomeMessage() {
        System.out.println("메가커피에 오신걸 환영합니다.");
    }
}
