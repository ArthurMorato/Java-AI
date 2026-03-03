package com.javaai.design;

/**
 * DesignBehavioralStrategy
 *
 * Objetivo:
 *  - Demonstrar o padrão comportamental Strategy para políticas de desconto
 *    em livros.
 */
public class DesignBehavioralStrategy {

    interface DiscountStrategy {
        double apply(double basePrice);
    }

    static class NoDiscount implements DiscountStrategy {
        @Override
        public double apply(double basePrice) {
            return basePrice;
        }
    }

    static class PercentageDiscount implements DiscountStrategy {
        private final double percent;

        PercentageDiscount(double percent) {
            this.percent = percent;
        }

        @Override
        public double apply(double basePrice) {
            return basePrice * (1.0 - percent);
        }
    }

    static class DiscountService {
        private DiscountStrategy strategy;

        DiscountService(DiscountStrategy strategy) {
            this.strategy = strategy;
        }

        void changeStrategy(DiscountStrategy strategy) {
            this.strategy = strategy;
        }

        double priceWithDiscount(double basePrice) {
            return strategy.apply(basePrice);
        }
    }

    public static void main(String[] args) {
        System.out.println("=== Strategy: políticas de desconto ===");

        DiscountService service = new DiscountService(new NoDiscount());
        double basePrice = 100.0;

        System.out.println("Sem desconto: " + service.priceWithDiscount(basePrice));

        service.changeStrategy(new PercentageDiscount(0.15));
        System.out.println("Com desconto 15%: " + service.priceWithDiscount(basePrice));

        // ╔══════════════════════════════════════╗
        // ║  COMO TESTAR (JUnit 5)               ║
        // ╚══════════════════════════════════════╝
        // @Test
        // void strategyDeveMudarPrecoConformePolitica() {
        //     DiscountService service = new DiscountService(new NoDiscount());
        //     assertEquals(100.0, service.priceWithDiscount(100.0), 0.001);
        //     service.changeStrategy(new PercentageDiscount(0.1));
        //     assertEquals(90.0, service.priceWithDiscount(100.0), 0.001);
        // }
    }
}

