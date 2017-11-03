package notebook;

import java.util.ArrayList;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        Scanner in = new Scanner(System.in);
        DBHandler handler = new DBHandler();
        int command;

        while (true) {

            System.out.println("1) Список контактов");
            System.out.println("2) Добавить контакт");
            System.out.println("3) Удалить контакт");
            System.out.println("4) Выход");

            command = in.nextInt();
            in.nextLine();

            switch (command) {
                case 1 : {
                    System.out.println("+-----------------------------------------------------------------------------------------------------------------------------------------------------------+");
                    System.out.printf("|%-12s|%-20s|%-20s|%-100s|\n", "*Номер*", "*Имя*", "*Email*", "*Заметка*");
                    System.out.println("|-----------------------------------------------------------------------------------------------------------------------------------------------------------|");
                    for (ArrayList<String> element : handler.selectAll()) {
                        System.out.printf("|%-12s|%-20s|%-20s|%-100s|\n", element.get(0), element.get(1), element.get(2), element.get(3));
                        System.out.println("|-----------------------------------------------------------------------------------------------------------------------------------------------------------|");
                    }
                    System.out.println();
                    break;
                }
                case 2 : {
                    String phone = "";
                    String name = "";
                    String email = "";
                    String note = "";
                    System.out.print("Введите телефон: ");
                    phone = in.nextLine();
                    System.out.print("Введите имя: ");
                    name = in.nextLine();
                    System.out.print("Введите email: ");
                    email = in.nextLine();
                    System.out.print("Введите заметку: ");
                    note = in.nextLine();
                    handler.insertRow(phone, name, email, note);
                    break;
                }
                case 3 : {
                    System.out.print("Введите телефон, который нужно удалить: ");
                    String phone = in.nextLine();
                    handler.removeRow(phone);
                    break;
                }
                case 4 : return;
                default : break;//TODO
            }

        }


    }

}
