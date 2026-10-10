package week8.category_c;

import java.time.LocalDate;
import java.util.*;
import java.util.function.Function;

public class StreamingRenewal {
    interface Plan {
        String getName();
        LocalDate renewalDate();
    }

    static class Basic implements Plan {
        String name;
        LocalDate startDate;
        Basic(String name, LocalDate startDate) {
            this.name = name;
            this.startDate = startDate;
        }
        public String getName() { return name; }
        public LocalDate renewalDate() { return startDate.plusDays(30); }
    }

    static class Standard implements Plan {
        String name;
        LocalDate startDate;
        Standard(String name, LocalDate startDate) {
            this.name = name;
            this.startDate = startDate;
        }
        public String getName() { return name; }
        public LocalDate renewalDate() { return startDate.plusDays(90); }
    }

    static class Premium implements Plan {
        String name;
        LocalDate startDate;
        Premium(String name, LocalDate startDate) {
            this.name = name;
            this.startDate = startDate;
        }
        public String getName() { return name; }
        public LocalDate renewalDate() { return startDate.plusDays(365); }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Map<String, Function<String[], Plan>> factory = new HashMap<>();

        factory.put("BASIC", a -> new Basic(
                a[1], LocalDate.parse(a[2])));
        factory.put("STANDARD", a -> new Standard(
                a[1], LocalDate.parse(a[2])));
        factory.put("PREMIUM", a -> new Premium(
                a[1], LocalDate.parse(a[2])));

        int n = Integer.parseInt(sc.nextLine());

        for (int i = 0; i < n; i++) {
            String[] data = sc.nextLine().trim().split("\\s+");
            Plan plan = factory.get(data[0]).apply(data);
            System.out.printf("%s: %s%n",
                    plan.getName(), plan.renewalDate());
        }

        sc.close();
    }
}
