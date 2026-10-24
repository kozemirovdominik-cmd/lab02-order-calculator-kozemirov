package by.bseu.pp.lab02;

/**
 * Utility methods for calculating and validating simple order data.
 *
 * <p>Implement every TODO method without changing its signature.</p>
 */
public class OrderCalculator {

    public static double calculateSubtotal(double[] prices, int[] quantities) {

        if (prices == null || quantities == null) {
            throw new IllegalArgumentException();
        }

        if (prices.length == 0 || prices.length != quantities.length) {
            throw new IllegalArgumentException();
        }

        double subtotal = 0;

        for (int i = 0; i < prices.length; i++) {
            if (prices[i] < 0 || quantities[i] <= 0) {
                throw new IllegalArgumentException();
            }

            subtotal = subtotal + prices[i] * quantities[i];
        }

        return subtotal;

    }

    public static double determineDiscountRate(int previousOrdersCount) {

        if (previousOrdersCount < 0) {
            throw new IllegalArgumentException();
        }

        if (previousOrdersCount >= 100) {
            return 0.15;
        } else if (previousOrdersCount >= 20) {
            return 0.07;
        } else {
            return 0.0;
        }

    }

    public static double applyDiscount(double subtotal, double discountRate) {

        if (subtotal < 0) {
            throw new IllegalArgumentException();
        }

        if (discountRate < 0.0 || discountRate > 1.0) {
            throw new IllegalArgumentException();
        }

        return subtotal * (1 - discountRate);

    }

    public static double addTax(double amount, double taxRate) {

        if (amount < 0) {
            throw new IllegalArgumentException();
        }

        if (taxRate < 0.0 || taxRate > 1.0) {
            throw new IllegalArgumentException();
        }

        return amount * (1 + taxRate);

    }

    public static double calculateTotal(
            double[] prices,
            int[] quantities,
            int previousOrdersCount,
            double taxRate) {

        double subtotal = calculateSubtotal(prices, quantities);

        double discountRate = determineDiscountRate(previousOrdersCount);

        double discountedAmount = applyDiscount(subtotal, discountRate);

        double total = addTax(discountedAmount, taxRate);

        return total;

    }

    public static boolean isWithinCreditLimit(double total, double creditLimit) {
        if (total < 0) {
            throw new IllegalArgumentException();
        }
        if (creditLimit < 0) {
            throw new IllegalArgumentException();
        }
        return total <= creditLimit;
    }

    public static int countOrdersWithinLimit(double[] totals, double creditLimit) {
        if (totals == null) {
            throw new IllegalArgumentException();
        }
        if (creditLimit < 0) {
            throw new IllegalArgumentException();
        }

        int count = 0;

        for (int i = 0; i < totals.length; i++) {
            if (totals[i] < 0) {
                throw new IllegalArgumentException();
            }
            if (totals[i] <= creditLimit) {
                count++;
            }
        }

        return count;
    }
}
