import java.util.Scanner;

public class Cinema {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter the number of rows:");
        int rows = sc.nextInt();
        System.out.println("Enter the number of seats in each row:");
        int seats = sc.nextInt();
        char[][] room = makeCinema(rows, seats);
        int option = 0;
        int income = 0;
        float purschasedTickets = 0;
        do {
            option = options(sc);
            if (option == 1) {
                showCinema(room);
            } else if (option == 2) {
                income += buyTicket(rows, seats, room, sc);
                purschasedTickets++;
            } else if (option == 3) {
                statistic(rows, seats, income, purschasedTickets);
            }
        } while (option != 0);
    }

    public static void showCinema(char[][] room) {
        System.out.println("Cinema:");
        for (int i = 0; i < room.length; i++) {
            for (int j = 0; j < room[0].length; j++) {
                System.out.print(room[i][j] + " ");
            }
            System.out.println();
        }
    }

    public static char[][] makeCinema(int rows, int seats) {
        char[][] room = new char[rows + 1][seats + 1];
        for (int i = 1; i <= rows; i++) {
            room[i][0] = (char) (i + '0');
        }
        for (int i = 1; i <= seats; i++) {
            room[0][i] = (char) (i + '0');
        }
        room[0][0] = ' ';
        for (int i = 1; i <= rows; i++) {
            for (int j = 1; j <= seats; j++) {
                room[i][j] = 'S';
            }
        }
        return room;
    }
  
    public static int buyTicket(int rows, int seats, char[][] room, Scanner sc) {
        int rowscc = 0;
        int seatscc = 0;
        boolean loop = true;
        while (loop) {
            System.out.println("Enter a row number:");
            rowscc = sc.nextInt();
            System.out.println("Enter a seat number in that row:");
            seatscc = sc.nextInt();
            if (rowscc > rows || seatscc > seats) {
                System.out.println("Wrong input!");
            } else if (room[rowscc][seatscc] == 'B') {
                System.out.println("That ticket has already been purchased!");
            } else {
                loop = false;
            }
        }
        int price;
        if (rows * seats <= 60 || rowscc <= rows / 2) {
            price = 10;
        } else {
            price = 8;
        }
        System.out.println("Ticket price: $" + price);
        room[rowscc][seatscc] = 'B';
        return price;
    }
  
    public static int options(Scanner sc) {
        System.out.println("1. Show the seats\n" +
                "2. Buy a ticket\n" +
                "3. Statistics\n" +
                "0. Exit");
        int qq = sc.nextInt();
        return qq;
    }
  
    public static void statistic(int rows, int seats, int income, float purchasedTickets) {
        float percent = purchasedTickets / (rows * seats) * 100;
        int totalIncome;
        int rows2 = rows - rows / 2;
        if (rows * seats <= 60) {
            totalIncome = rows * seats * 10;
        } else {
            totalIncome = rows / 2 * seats * 10 + rows2 * seats * 8;
        }
        System.out.println("Number of purchased tickets: " + (int)purchasedTickets);
        System.out.printf("Percentage: %.2f%%\n", percent);
        System.out.println("Current income: $" + income);
        System.out.println("Total income: $" + totalIncome);
    }
}
