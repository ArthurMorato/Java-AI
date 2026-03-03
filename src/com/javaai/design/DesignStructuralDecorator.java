package com.javaai.design;

/**
 * DesignStructuralDecorator
 *
 * Objetivo:
 *  - Demonstrar o padrão estrutural Decorator para adicionar comportamento
 *    (ex.: descontos, taxas) sem modificar a classe original.
 */
public class DesignStructuralDecorator {

    interface Price {
        double value();
    }

    static class BasePrice implements Price {
        private final double value;

        BasePrice(double value) {
            this.value = value;
        }

        @Override
        public double value() {
            return value;
        }
    }

    static abstract class PriceDecorator implements Price {
        protected final Price delegate;

        protected PriceDecorator(Price delegate) {
            this.delegate = delegate;
        }
    }

    static class DiscountDecorator extends PriceDecorator {
        private final double percent;

        DiscountDecorator(Price delegate, double percent) {
            super(delegate);
            this.percent = percent;
        }

        @Override
        public double value() {
            return delegate.value() * (1.0 - percent);
        }
    }

    static class TaxDecorator extends PriceDecorator {
        private final double taxPercent;

        TaxDecorator(Price delegate, double taxPercent) {
            super(delegate);
            this.taxPercent = taxPercent;
        }

        @Override
        public double value() {
            return delegate.value() * (1.0 + taxPercent);
        }
    }

    public static void main(String[] args) {
        System.out.println("=== Decorator: combinando descontos e taxas ===");

        Price base = new BasePrice(100.0);
        Price withDiscount = new DiscountDecorator(base, 0.10);
        Price withDiscountAndTax = new TaxDecorator(withDiscount, 0.05);

        System.out.println("Base: " + base.value());
        System.out.println("Com desconto 10%: " + withDiscount.value());
        System.out.println("Com desconto 10% + imposto 5%: " + withDiscountAndTax.value());

        // ╔══════════════════════════════════════╗
        // ║  COMO TESTAR (JUnit 5)               ║
        // ╚══════════════════════════════════════╝
        // @Test
        // void decoratorDeveAplicarDescontoEImposto() {
        //     Price base = new BasePrice(100.0);
        //     Price withDiscount = new DiscountDecorator(base, 0.10);
        //     Price withDiscountAndTax = new TaxDecorator(withDiscount, 0.05);
        //     assertEquals(90.0, withDiscount.value(), 0.001);
        //     assertEquals(94.5, withDiscountAndTax.value(), 0.001);
        // }
    }
}

