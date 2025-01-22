import java.util.Scanner;

public class MovieTheatre {
    private static final int ROWS = 25;
    private static final int SEATS_PER_ROW = 20;
    private static final char AVAILABLE = 'O';
    private static final char TAKEN = 'X';

    private final char[][] seatingChart;

    public MovieTheatre() {
        seatingChart = new char[ROWS][SEATS_PER_ROW];
        for (int r = 0; r < ROWS; r++) {
            for (int s = 0; s < SEATS_PER_ROW; s++) {
                seatingChart[r][s] = AVAILABLE;
            }
        }
    }

    public void displaySeatingChart() {
        System.out.println("Seating Chart:");
        for (int r = 0; r < ROWS; r++) {
            for (int s = 0; s < SEATS_PER_ROW; s++) {
                System.out.print(seatingChart[r][s] + " ");
            }
            System.out.println();
        }
    }

    public void reserveSeat(int row, int seat) {
        if (row < 1 || row > ROWS || seat < 1 || seat > SEATS_PER_ROW) {
            System.out.println("Invalid seat selection. Please try again.");
            return;
        }

        if (seatingChart[row - 1][seat - 1] == TAKEN) {
            System.out.println("Seat is already taken. Suggesting an available seat...");
            suggestSeat();
        } else {
            seatingChart[row - 1][seat - 1] = TAKEN;
            System.out.println("Seat reserved successfully.");
        }
    }

    public void cancelSeat(int row, int seat) {
        if (row < 1 || row > ROWS || seat < 1 || seat > SEATS_PER_ROW) {
            System.out.println("Invalid seat selection. Please try again.");
            return;
        }

        if (seatingChart[row - 1][seat - 1] == AVAILABLE) {
            System.out.println("Seat is already available.");
        } else {
            seatingChart[row - 1][seat - 1] = AVAILABLE;
            System.out.println("Seat cancellation successful.");
        }
    }

    private void suggestSeat() {
        for (int r = 0; r < ROWS; r++) {
            for (int s = 0; s < SEATS_PER_ROW; s++) {
                if (seatingChart[r][s] == AVAILABLE) {
                    System.out.println("Suggested seat: Row " + (r + 1) + " Seat " + (s + 1));
                    return;
                }
            }
        }
        System.out.println("No seats available.");
    }

    public static void main(String[] args) {
        MovieTheatre theatre = new MovieTheatre();
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("\nMenu:");
            System.out.println("1. Display Seating Chart");
            System.out.println("2. Reserve a Seat");
            System.out.println("3. Cancel a Reservation");
            System.out.println("4. Exit");
            System.out.print("Choose an option: ");

            int choice = scanner.nextInt();

            switch (choice) {
                case 1 -> theatre.displaySeatingChart();
                case 2 -> {
                    System.out.print("Enter row number (1-" + ROWS + "): ");
                    int reserveRow = scanner.nextInt();
                    System.out.print("Enter seat number (1-" + SEATS_PER_ROW + "): ");
                    int reserveSeat = scanner.nextInt();
                    theatre.reserveSeat(reserveRow, reserveSeat);
                }
                case 3 -> {
                    System.out.print("Enter row number (1-" + ROWS + "): ");
                    int cancelRow = scanner.nextInt();
                    System.out.print("Enter seat number (1-" + SEATS_PER_ROW + "): ");
                    int cancelSeat = scanner.nextInt();
                    theatre.cancelSeat(cancelRow, cancelSeat);
                }
                case 4 -> {
                    System.out.println("Exiting system. Goodbye!");
                    scanner.close();
                    return;
                }
                default -> System.out.println("Invalid option. Please try again.");
            }
        }
    }
}
