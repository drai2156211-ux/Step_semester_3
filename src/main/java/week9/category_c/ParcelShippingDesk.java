package week9.category_c;

import java.util.*;
import java.util.function.Function;

public class ParcelShippingDesk {
    interface Insurable {
        double insurance();
    }

    abstract static class Parcel {
        double weight;
        double declaredValue;

        Parcel(double weight, double declaredValue) {
            this.weight = weight;
            this.declaredValue = declaredValue;
        }

        abstract double shippingCharge();

        double insuranceAmount() {
            return 0.0;
        }

        final double totalCharge() {
            return shippingCharge() + insuranceAmount();
        }
    }

    static class Standard extends Parcel {
        Standard(double w, double v) { super(w, v); }

        double shippingCharge() {
            return 40 + 10 * weight;
        }
    }

    static class Express extends Parcel implements Insurable {
        Express(double w, double v) { super(w, v); }

        double shippingCharge() {
            return 80 + 15 * weight;
        }

        public double insurance() {
            return declaredValue * 0.02;
        }

        double insuranceAmount() {
            return insurance();
        }
    }

    static class Fragile extends Parcel implements Insurable {
        Fragile(double w, double v) { super(w, v); }

        double shippingCharge() {
            return 40 + 10 * weight + 50;
        }

        public double insurance() {
            return declaredValue * 0.02;
        }

        double insuranceAmount() {
            return insurance();
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Map<String, Function<double[], Parcel>> factory = new HashMap<>();

        factory.put("STANDARD", a -> new Standard(a[0], a[1]));
        factory.put("EXPRESS", a -> new Express(a[0], a[1]));
        factory.put("FRAGILE", a -> new Fragile(a[0], a[1]));

        int n = sc.nextInt();
        double grandTotal = 0;

        for (int i = 0; i < n; i++) {
            String type = sc.next();
            double weight = sc.nextDouble();
            double value = sc.nextDouble();

            Parcel parcel = factory.get(type).apply(
                    new double[]{weight, value});

            double charge = parcel.shippingCharge();
            double insurance = parcel.insuranceAmount();
            double total = parcel.totalCharge();

            System.out.printf(
                    "%s: Charge=%.2f Insurance=%.2f Total=%.2f%n",
                    type, charge, insurance, total);

            grandTotal += total;
        }

        System.out.printf("Grand Total: %.2f%n", grandTotal);
        sc.close();
    }
}
