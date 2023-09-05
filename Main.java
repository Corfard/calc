import java.io.IOException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner vvod = new Scanner(System.in);
        while (true){
            // Ввод данных
            System.out.println("Input:");
            String input = vvod.nextLine();

            // Вывод результата
            if (!calc(input).equals("throws Exception")) {
                System.out.println("Output:\n" + calc(input) + "\n");
            } else break;
        }
    }
    public static String calc (String input){
        // Определение знака
        String[] znak = {"+", "-", "*", "/"};
        String[] znak1 = {"\\+", "-", "\\*", "/"};
        int znakVvoda = -1;
        for (int i = 0; i<znak.length; i++){
            if (input.contains(znak[i])){
                znakVvoda = i;
                break;
            }
        }
        if (znakVvoda == -1) {
            try {
                throw new IOException();
            } catch (IOException e) {
                System.out.println("throws Exception");
                return "throws Exception";
            }
        }

        // деление по знаку
        String[] dvaChisla = input.split(znak1[znakVvoda], 2);
        Chislo chislo1 = new Chislo();
        chislo1.chisloVvoda = dvaChisla[0].trim();
        Chislo chislo2 = new Chislo();
        chislo2.chisloVvoda = dvaChisla[1].trim();

        // Проверка правильности ввода
        int chisloRascheta1 = chislo1.proverka1();
        int chisloRascheta2 = chislo2.proverka1();
        if (chisloRascheta1 == 0 || chisloRascheta2 == 0) {
            try {
                throw new IOException();
            } catch (IOException e) {
                System.out.println("throws Exception");
                return "throws Exception";
            }
        }

        // Проверка принадлежности одному виду
        if (chislo1.proverka2() != chislo2.proverka2()) {
            try {
                throw new IOException();
            } catch (IOException e) {
                System.out.println("throws Exception");
                return "throws Exception";
            }
        }

        // Вычисление
        int rezultat1 = 0;
        switch (znak[znakVvoda]) {
            case "+" -> rezultat1 = chisloRascheta1 + chisloRascheta2;
            case "-" -> rezultat1 = chisloRascheta1 - chisloRascheta2;
            case "*" -> rezultat1 = chisloRascheta1 * chisloRascheta2;
            case "/" -> rezultat1 = chisloRascheta1 / chisloRascheta2;
        }

        // Перевод ответа в римские
        Rim rim = new Rim();
        rim.rezultatRim = rezultat1;
        if (chislo1.proverka2() == 0) {
            if (rezultat1 > 0){
                return rim.otvetRim();
            } else
                try {
                    throw new IOException();
                } catch (IOException e) {
                    System.out.println("throws Exception");
                    return "throws Exception";
                }
        }

        // Возврат результата
        return String.valueOf(rezultat1);
    }
}
class Chislo{
    String chisloVvoda;
    int chisloRascheta;
    int vid;
    String[] arab = {"1", "2", "3", "4", "5", "6", "7", "8", "9", "10"};

    int proverka1(){
        switch (chisloVvoda) {
            case "1", "I" -> chisloRascheta = 1;
            case "2", "II" -> chisloRascheta = 2;
            case "3", "III" -> chisloRascheta = 3;
            case "4", "IV" -> chisloRascheta = 4;
            case "5", "V" -> chisloRascheta = 5;
            case "6", "VI" -> chisloRascheta = 6;
            case "7", "VII" -> chisloRascheta = 7;
            case "8", "VIII" -> chisloRascheta = 8;
            case "9", "IX" -> chisloRascheta = 9;
            case "10", "X" -> chisloRascheta = 10;
            default -> {
                return 0;
            }
        }
        return chisloRascheta;
    }
    int proverka2(){
        for (int i = 0; i<arab.length; i++) {
                if (chisloVvoda.equals(arab[i])) {
                    vid = 1;
                    break;
                }
            }
        return vid;
    }
}
class Rim{
    int rezultatRim;
    String[] rim10 = {"X", "XX","XXX", "XL", "L", "LX", "LXX", "LXXX", "XC", "C"};
    String[] rim1 = {"I", "II","III", "IV", "V", "VI", "VII", "VIII", "IX"};

    String otvetRim(){
        int desyatki = rezultatRim / 10;
        int edenica = rezultatRim - desyatki * 10;
        if (edenica == 0) {
            return rim10[desyatki - 1];
        } else  if (desyatki == 0) {
            return rim1[edenica - 1];
        }
        return rim10[desyatki - 1].concat(rim1[edenica - 1]);
    }
}