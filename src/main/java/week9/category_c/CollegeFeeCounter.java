package week9.category_c;

import java.util.*;
import java.util.function.Function;

public class CollegeFeeCounter {
    static final double TRANSPORT_FEE = 12000.0;

    abstract static class Student {
        String name;

        Student(String name) {
            this.name = name;
        }

        abstract double tuitionFee();

        boolean usesBus() {
            return false;
        }

        final double totalFee() {
            return tuitionFee() + (usesBus() ? TRANSPORT_FEE : 0);
        }
    }

    static class DayScholar extends Student {
        DayScholar(String name) { super(name); }

        double tuitionFee() { return 40000.0; }

        boolean usesBus() { return true; }
    }

    static class Hosteller extends Student {
        Hosteller(String name) { super(name); }

        double tuitionFee() {
            return 40000.0 + 60000.0;
        }
    }

    static class Scholar extends Student {
        Scholar(String name) { super(name); }

        double tuitionFee() { return 20000.0; }

        boolean usesBus() { return true; }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Map<String, Function<String, Student>> factory = new HashMap<>();
        factory.put("DAY_SCHOLAR", DayScholar::new);
        factory.put("HOSTELLER", Hosteller::new);
        factory.put("SCHOLAR", Scholar::new);

        int n = sc.nextInt();
        double total = 0;

        for (int i = 0; i < n; i++) {
            String type = sc.next();
            String name = sc.next();

            Student student = factory.get(type).apply(name);
            double fee = student.totalFee();

            System.out.printf("%s: %.2f%n", name, fee);
            total += fee;
        }

        System.out.printf("Total Collected: %.2f%n", total);
        sc.close();
    }
}
