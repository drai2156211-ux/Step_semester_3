package week9.category_c;

import java.util.*;
import java.util.function.Function;

public class MovieTicketCounter {
    static final double CONVENIENCE_FEE = 20.0;

    abstract static class Ticket {
        int count;

        Ticket(int count) {
            this.count = count;
        }

        abstract double pricePerTicket();

        final double calculateAmount() {
            return count * (pricePerTicket() + CONVENIENCE_FEE);
        }
    }

    static class Regular extends Ticket {
        Regular(int count) { super(count); }
        double pricePerTicket() { return 150.0; }
    }

    static class Premium extends Ticket {
        Premium(int count) { super(count); }
        double pricePerTicket() { return 250.0; }
    }

    static class Recliner extends Ticket {
        Recliner(int count) { super(count); }
        double pricePerTicket() { return 400.0; }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Map<String, Function<Integer, Ticket>> factory = new HashMap<>();
        factory.put("REGULAR", Regular::new);
        factory.put("PREMIUM", Premium::new);
        factory.put("RECLINER", Recliner::new);

        int n = sc.nextInt();
        double total = 0;

        for (int i = 0; i < n; i++) {
            String type = sc.next();
            int count = sc.nextInt();
            Ticket ticket = factory.get(type).apply(count);
            double amount = ticket.calculateAmount();
            System.out.printf("%s: %.2f%n", type, amount);
            total += amount;
        }

        System.out.printf("Total: %.2f%n", total);
        sc.close();
    }
}
