package week8.category_c;

import java.util.*;
import java.util.function.Function;

public class CanteenBilling {
    interface Bill {
        double calculate();
    }

    static class Student implements Bill {
        double amount;
        Student(double amount) { this.amount = amount; }
        public double calculate() { return amount * 0.90; }
    }

    static class Staff implements Bill {
        double amount;
        Staff(double amount) { this.amount = amount; }
        public double calculate() { return amount * 0.95; }
    }

    static class Guest implements Bill {
        double amount;
        Guest(double amount) { this.amount = amount; }
        public double calculate() { return amount + 10; }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Map<String, Function<Double, Bill>> factory = new HashMap<>();
        factory.put("STUDENT", Student::new);
        factory.put("STAFF", Staff::new);
        factory.put("GUEST", Guest::new);

        int n = sc.nextInt();
        double total = 0;

        for (int i = 0; i < n; i++) {
            String type = sc.next();
            double amount = sc.nextDouble();
            Bill bill = factory.get(type).apply(amount);
            double result = bill.calculate();
            System.out.printf("%s: %.2f%n", type, result);
            total += result;
        }

        System.out.printf("Total: %.2f%n", total);
        sc.close();
    }
}
