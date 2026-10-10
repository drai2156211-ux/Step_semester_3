package week9.category_c;

import java.util.*;
import java.util.function.Function;

public class HomeApplianceEnergyReport {
    interface SaverMode {
        double applySaver(double units);
    }

    abstract static class Appliance {
        double hours;

        Appliance(double hours) {
            this.hours = hours;
        }

        abstract double powerWatts();

        final double normalUnits() {
            return powerWatts() * hours / 1000.0;
        }

        double units(boolean saver) {
            return normalUnits();
        }

        final double cost(boolean saver) {
            return units(saver) * 8.0;
        }
    }

    static class Fridge extends Appliance {
        Fridge(double h) { super(h); }
        double powerWatts() { return 150.0; }
    }

    static class AC extends Appliance implements SaverMode {
        AC(double h) { super(h); }
        double powerWatts() { return 1500.0; }

        public double applySaver(double units) {
            return units * 0.75;
        }

        double units(boolean saver) {
            double units = normalUnits();
            return saver ? applySaver(units) : units;
        }
    }

    static class TV extends Appliance {
        TV(double h) { super(h); }
        double powerWatts() { return 100.0; }
    }

    static class Washer extends Appliance implements SaverMode {
        Washer(double h) { super(h); }
        double powerWatts() { return 500.0; }

        public double applySaver(double units) {
            return units * 0.75;
        }

        double units(boolean saver) {
            double units = normalUnits();
            return saver ? applySaver(units) : units;
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Map<String, Function<Double, Appliance>> factory = new HashMap<>();
        factory.put("FRIDGE", Fridge::new);
        factory.put("AC", AC::new);
        factory.put("TV", TV::new);
        factory.put("WASHER", Washer::new);

        int n = sc.nextInt();
        double totalCost = 0;

        for (int i = 0; i < n; i++) {
            String type = sc.next();
            double hours = sc.nextDouble();
            boolean saver = sc.hasNext("SAVER");

            if (saver) {
                sc.next();
            }

            Appliance appliance = factory.get(type).apply(hours);

            if (saver && !(appliance instanceof SaverMode)) {
                System.out.printf("%s: saver mode not supported%n", type);
                continue;
            }

            double units = appliance.units(saver);
            double cost = appliance.cost(saver);

            System.out.printf("%s: Units=%.2f Cost=%.2f%n",
                    type, units, cost);
            totalCost += cost;
        }

        System.out.printf("Total Cost: %.2f%n", totalCost);
        sc.close();
    }
}
