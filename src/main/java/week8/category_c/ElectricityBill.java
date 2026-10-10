package week8.category_c;

import java.util.*;
import java.util.function.Function;

public class ElectricityBill {
    interface Room {
        double calculateBill();
    }

    static class Single implements Room {
        int units;
        Single(int units) { this.units = units; }
        public double calculateBill() { return units * 8.0; }
    }

    static class Shared implements Room {
        int units, occupants;
        Shared(int units, int occupants) {
            this.units = units;
            this.occupants = occupants;
        }
        public double calculateBill() {
            return (units * 6.0) / occupants;
        }
    }

    static class AC implements Room {
        int units;
        AC(int units) { this.units = units; }
        public double calculateBill() { return units * 10.0 + 200; }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Map<String, Function<String[], Room>> factory = new HashMap<>();

        factory.put("SINGLE", a -> new Single(Integer.parseInt(a[1])));
        factory.put("SHARED", a -> new Shared(
                Integer.parseInt(a[1]), Integer.parseInt(a[2])));
        factory.put("AC", a -> new AC(Integer.parseInt(a[1])));

        int n = Integer.parseInt(sc.nextLine());
        double total = 0;

        for (int i = 0; i < n; i++) {
            String[] data = sc.nextLine().trim().split("\\s+");
            Room room = factory.get(data[0]).apply(data);
            double bill = room.calculateBill();
            System.out.printf("%s: %.2f%n", data[0], bill);
            total += bill;
        }

        System.out.printf("Total: %.2f%n", total);
        sc.close();
    }
}
