package com.javaai.design;

/**
 * DesignIntegrador
 *
 * Objetivo:
 *  - Programa integrador da seção "design".
 *  - Combina:
 *    - SOLID (SRP/OCP)
 *    - Factory (criacional)
 *    - Decorator (estrutural)
 *    - Strategy (comportamental)
 */
public class DesignIntegrador {

    public static void main(String[] args) {
        System.out.println("=== Design Integrador ===");

        // SOLID: reutiliza BookPricingService e estratégias de SolidFundamentos.
        SolidFundamentos.Book book = new SolidFundamentos.Book("1", "Clean Architecture", "Robert Martin");
        SolidFundamentos.PricingStrategy standard = new SolidFundamentos.StandardPricingStrategy();
        SolidFundamentos.BookPricingService pricingService = new SolidFundamentos.BookPricingService(standard);
        System.out.println("Preço (SOLID/OCP): " + pricingService.calculatePrice(book));

        // Factory: cria versões física e digital de um título.
        DesignCreationalFactory.BookFactory physicalFactory = new DesignCreationalFactory.PhysicalBookFactory();
        DesignCreationalFactory.BookFactory ebookFactory = new DesignCreationalFactory.EBookFactory();
        System.out.println("Factory física: " + physicalFactory.create("Clean Code").title());
        System.out.println("Factory ebook:  " + ebookFactory.create("Clean Code").title());

        // Decorator: aplica desconto e imposto em cima de um preço base vindo do serviço.
        double basePrice = pricingService.calculatePrice(book);
        DesignStructuralDecorator.Price price = new DesignStructuralDecorator.BasePrice(basePrice);
        DesignStructuralDecorator.Price discounted = new DesignStructuralDecorator.DiscountDecorator(price, 0.10);
        DesignStructuralDecorator.Price finalPrice = new DesignStructuralDecorator.TaxDecorator(discounted, 0.05);
        System.out.println("Preço final (Decorator): " + finalPrice.value());

        // Strategy: outra forma de trocar política de desconto em tempo de execução.
        DesignBehavioralStrategy.DiscountService discountService =
                new DesignBehavioralStrategy.DiscountService(new DesignBehavioralStrategy.NoDiscount());
        System.out.println("Strategy sem desconto: " + discountService.priceWithDiscount(basePrice));
        discountService.changeStrategy(new DesignBehavioralStrategy.PercentageDiscount(0.20));
        System.out.println("Strategy com desconto 20%: " + discountService.priceWithDiscount(basePrice));

        // ╔══════════════════════════════════════╗
        // ║  COMO TESTAR (JUnit 5)               ║
        // ╚══════════════════════════════════════╝
        // @Test
        // void integradorDeveExecutarSemErros() {
        //     assertDoesNotThrow(() -> DesignIntegrador.main(new String[0]));
        // }
    }
}

