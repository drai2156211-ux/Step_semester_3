package week9.category_c;

import java.util.*;
import java.util.function.Function;

public class CityCabFareMeter {
    static final double MINIMUM_FARE = 100.0;

    interface NightService {
        double nightFare(double baseFare);
    }

    abstract static class Cab {
        abstract double ratePerKm();

        final double baseFare(double km) {
            return Math.max(MINIMUM_FARE, km * ratePerKm());
        }

        double calculateFare(double km, boolean night) {
            return baseFare(km);
        }
    }

    static class Mini extends Cab {
        double ratePerKm() { return 10.0; }
    }

    static class Sedan extends Cab implements NightService {
        double ratePerKm() { return 14.0; }

        public double nightFare(double fare) {
            return fare * 1.20;
        }

        double calculateFare(double km, boolean night) {
            double fare = baseFare(km);
            return night ? nightFare(fare) : fare;
        }
    }

    static class SUV extends Cab implements NightService {
        double ratePerKm() { return 18.0; }

        public double nightFare(double fare) {
            return fare * 1.20;
        }

        double calculateFare(double km, boolean night) {
            double fare = baseFare(km);
            return night ? nightFare(fare) : fare;
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Map<String, Function<Double, Cab>> factory = new HashMap<>();
        factory.put("MINI", km -> new Mini());
        factory.put("SEDAN", km -> new Sedan());
        factory.put("SUV", km -> new SUV());

        int n = sc.nextInt();
        double total = 0;

        for (int i = 0; i < n; i++) {
            String type = sc.next();
            double km = sc.nextDouble();
            String time = sc.next();

            Cab cab = factory.get(type).apply(km);
            boolean night = time.equals("NIGHT");

            if (night && !(cab instanceof NightService)) {
                System.out.printf("%s: night service not available%n", type);
                continue;
            }

            double fare = cab.calculateFare(km, night);
            System.out.printf("%s: %.2f%n", type, fare);
            total += fare;
        }

        System.out.printf("Total: %.2f%n", total);
        sc.close();
    }
}
