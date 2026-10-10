package week8.category_c;

import java.util.*;
import java.util.function.Function;

public class ParkingCalculator {
    interface Vehicle {
        double calculateCharge();
    }

    static class Bike implements Vehicle {
        int hours;
        Bike(int hours) { this.hours = hours; }
        public double calculateCharge() { return hours * 10.0; }
    }

    static class Car implements Vehicle {
        int hours;
        Car(int hours) { this.hours = hours; }
        public double calculateCharge() {
            return 30 + (hours - 1) * 20.0;
        }
    }

    static class Truck implements Vehicle {
        int hours;
        Truck(int hours) { this.hours = hours; }
        public double calculateCharge() {
            return Math.max(100, hours * 50.0);
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Map<String, Function<Integer, Vehicle>> factory = new HashMap<>();
        factory.put("BIKE", Bike::new);
        factory.put("CAR", Car::new);
        factory.put("TRUCK", Truck::new);

        int n = sc.nextInt();
        double total = 0;

        for (int i = 0; i < n; i++) {
            String type = sc.next();
            int hours = sc.nextInt();
            Vehicle vehicle = factory.get(type).apply(hours);
            double charge = vehicle.calculateCharge();
            System.out.printf("%s: %.2f%n", type, charge);
            total += charge;
        }

        System.out.printf("Total: %.2f%n", total);
        sc.close();
    }
}
