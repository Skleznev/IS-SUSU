package Lab2;


import java.io.IOException;
import java.util.Scanner;

class Lab2 {
    public static void main(String[] args) {
        Search search = new Search();

        System.out.print("ВВЕДИТЕ ФРАЗУ ДЛЯ ПОИСКА: ");
        String str = (new Scanner(System.in)).nextLine();

        search.searchSystem(str);
    }
}
