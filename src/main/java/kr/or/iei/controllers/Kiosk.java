package kr.or.iei.controllers;

import kr.or.iei.entities.PackingType;

import java.util.Scanner;

public class Kiosk {

    private final Scanner scanner = new Scanner(System.in);

    public void run() {
        renderWelcomeMessage();

        // 1. 포장 여부 결정
        final PackingType packingType = choosePackingType();
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
