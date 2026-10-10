package week8.category_c;

import java.util.*;
import java.util.function.Function;

public class FestivalBonus {
    interface Employee {
        String getName();
        double calculateBonus();
    }

    static class FullTime implements Employee {
        String name;
        double salary;
        FullTime(String name, double salary) {
            this.name = name;
            this.salary = salary;
        }
        public String getName() { return name; }
        public double calculateBonus() { return salary * 0.10; }
    }

    static class PartTime implements Employee {
        String name;
        double salary;
        PartTime(String name, double salary) {
            this.name = name;
            this.salary = salary;
        }
        public String getName() { return name; }
        public double calculateBonus() { return salary * 0.05; }
    }

    static class Intern implements Employee {
        String name;
        double salary;
        Intern(String name, double salary) {
            this.name = name;
            this.salary = salary;
        }
        public String getName() { return name; }
        public double calculateBonus() { return 2000.0; }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Map<String, Function<String[], Employee>> factory = new HashMap<>();

        factory.put("FULLTIME", a -> new FullTime(
                a[1], Double.parseDouble(a[2])));
        factory.put("PARTTIME", a -> new PartTime(
                a[1], Double.parseDouble(a[2])));
        factory.put("INTERN", a -> new Intern(
                a[1], Double.parseDouble(a[2])));

        int n = Integer.parseInt(sc.nextLine());
        double total = 0;

        for (int i = 0; i < n; i++) {
            String[] data = sc.nextLine().trim().split("\\s+");
            Employee employee = factory.get(data[0]).apply(data);
            double bonus = employee.calculateBonus();
            System.out.printf("%s: %.2f%n", employee.getName(), bonus);
            total += bonus;
        }

        System.out.printf("Total Bonus: %.2f%n", total);
        sc.close();
    }
}
