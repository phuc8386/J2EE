import java.util.*;
import java.util.stream.Collectors;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        List<Book> listBook = new ArrayList<>();
        int choice;

        do {
            System.out.println("\n===== MENU QUAN LY SACH =====");
            System.out.println("1. Them sach");
            System.out.println("2. Xoa sach theo ma");
            System.out.println("3. Sua sach theo ma");
            System.out.println("4. Xuat danh sach sach");
            System.out.println("5. Tim sach co tieu de chua 'Lap trinh'");
            System.out.println("6. Lay K sach co gia <= P");
            System.out.println("7. Tim sach theo danh sach tac gia");
            System.out.println("0. Thoat");
            System.out.print("Chon: ");
            choice = Integer.parseInt(sc.nextLine());

            switch (choice) {
                case 1 -> {
                    Book b = new Book();
                    b.input(sc);
                    listBook.add(b);
                    System.out.println("[OK] Them sach thanh cong!");
                }

                case 2 -> {
                    System.out.print("Nhap ma sach can xoa: ");
                    int id = Integer.parseInt(sc.nextLine());
                    listBook.removeIf(b -> b.getId() == id);
                    System.out.println("[OK] Da xoa (neu ton tai)");
                }

                case 3 -> {
                    System.out.print("Nhap ma sach can sua: ");
                    int id = Integer.parseInt(sc.nextLine());

                    listBook.stream()
                            .filter(b -> b.getId() == id)
                            .findFirst()
                            .ifPresentOrElse(b -> {
                                System.out.print("Ten moi: ");
                                b.setTitle(sc.nextLine());
                                System.out.print("Tac gia moi: ");
                                b.setAuthor(sc.nextLine());
                                System.out.print("Gia moi: ");
                                b.setPrice(Double.parseDouble(sc.nextLine()));
                                System.out.println("[OK] Sua thanh cong!");
                            }, () -> System.out.println("[ERR] Khong tim thay sach"));
                }

                case 4 -> {
                    System.out.println("[LIST] DANH SACH :");
                    listBook.forEach(Book::output);
                }

                case 5 -> {
            System.out.println("[SACH] Sach co tieu de chua 'Lap trinh':");
                    listBook.stream()
                .filter(b -> b.getTitle().toLowerCase().contains("lap trinh"))
                            .forEach(Book::output);
                }

                case 6 -> {
                    System.out.print("Nhap K: ");
                    int k = Integer.parseInt(sc.nextLine());
                    System.out.print("Nhap gia P: ");
                    double p = Double.parseDouble(sc.nextLine());

                    listBook.stream()
                            .filter(b -> b.getPrice() <= p)
                            .limit(k)
                            .forEach(Book::output);
                }

                case 7 -> {
            System.out.print("Nhap cac tac gia (cach nhau boi dau phay): ");
                    Set<String> authors = Arrays.stream(sc.nextLine().split(","))
                            .map(String::trim)
                            .collect(Collectors.toSet());

                    listBook.stream()
                            .filter(b -> authors.contains(b.getAuthor()))
                            .forEach(Book::output);
                }

                case 0 -> System.out.println("[BYE] Thoat chuong trinh!");

                default -> System.out.println("[ERR] Lua chon khong hop le!");
            }
        } while (choice != 0);
    }
}
