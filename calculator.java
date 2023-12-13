import java.io.IOException;
import java.util.Scanner;
public class calculator {
    public static void main(String[] args) throws ScannerException, IOException {
        Scanner scanner = new Scanner(System.in);

        //Вводим математическую операцию
        System.out.println("Введите математическую операцию c использованием(+ , - , * , /) арибскимим цифрама(от 1 до 10) или римскими (I , II , III , IV , V , VI , VII , VIII , IX , X)");
        String input = scanner.nextLine();
        System.out.println("ответ: "+calc(input));
    }

    public static String calc(String input) throws ScannerException {
        //Ищем математический оператор
        int index = input.indexOf("+");
        if (input.indexOf("+")>-1){
            index = input.indexOf("+");
        } else if (input.indexOf("-")>-1) {
            index = input.indexOf("-");
        } else if  (input.indexOf("*")>-1) {
            index = input.indexOf("*");
        } else if (input.indexOf("/")>-1){
            index = input.indexOf("/");
        } else {
            throw new ScannerException("Математическая операция введена неверно");
        }

        // chisloOdin присваиваем то, что содержиться до мат. оператора
        String chisloOdin = input.substring(0, index);
        System.out.println("chisloOdin="+chisloOdin);

        // chisloOdin присваиваем то, что содержиться после мат. оператора
        String chisloDva = input.substring(index+1);
        System.out.println("chisloDva="+chisloDva);

        //Проверяем первое число Римское или Нет
        boolean chisloOdinType;
        chisloOdinType = rimskieOrNet(chisloOdin);

        //Проверяем второе число Римское или Нет
        boolean chisloDvaType;
        chisloDvaType = rimskieOrNet(chisloDva);

        //Если первое число римское конвертируем в арабское
        int intOdin;
        Rimskie rimskie;
        if(chisloOdinType){
            rimskie = Rimskie.valueOf(chisloOdin);
            intOdin = rimskie.getChisloArab();
        } else {
            // конвертируем первый операнд в тип int
            try{
                intOdin = Integer.parseInt(chisloOdin);
            }
            catch (NumberFormatException e) {
                throw new ScannerException("Операнд один не принадлежит ни арабским ни римским числам");
            }
            System.out.println("intOdin="+intOdin);
        }

        //Если второе число римское конвертируем в арабское
        int arabDva;
        int intDva;
        if(chisloDvaType){
            rimskie = Rimskie.valueOf(chisloDva);
            intDva = rimskie.getChisloArab();
        } else {
            // конвертируем второй операнд в тип int
            try {
                intDva = Integer.parseInt(chisloDva);
            }
            catch (NumberFormatException e) {
                throw new ScannerException("Операнд два не принадлежит ни арабским ни римским числам");
            }
            System.out.println("chisloDva="+chisloDva);
        }

        //Если одно из чисел римское, а другое нет выбрасываем исключение
        if ((chisloOdinType && !chisloDvaType) || (!chisloOdinType && chisloDvaType)){
            throw new ScannerException("Однвременное использование разных типов операндов");
        }

        //Если оба операнда входят в диапазон от 1 до 10 идем дальше иначе выбрасываем исключение
        if(intOdin>10 || intOdin<1 || intDva>10 || intDva<1 ){
            throw new ScannerException("В операции присутствуют числа вне диапазона от 1 до 10 или от I до X");
        }

        // input присваиваем математический оператор
        input = input.substring(index, index+1);

        // В зависимости от мат. оператора производим математическую операцию
        int result;
        switch(input){
            case("+"):
                result=intOdin+intDva;
                //System.out.println("ответ: "+intOdin+"+"+intDva+"="+result);
                break;
            case("-"):
                result=intOdin-intDva;
                //System.out.println("ответ: "+intOdin+"-"+intDva+"="+result);
                break;
            case("*"):
                result=intOdin*intDva;
                //System.out.println("ответ: "+intOdin+"*"+intDva+"="+result);
                break;
            case("/"):
                result=intOdin/intDva;
                //System.out.println("ответ: "+intOdin+"/"+intDva+"="+result);
                break;
            default:
                result = -101;
                throw new ScannerException("Математическая операция введена неверно");
        }

        // Вывод результата либо римскими либо арабскими числами
        if(chisloOdinType && chisloDvaType){
            Rimskie resultRimskie;
            try {
                resultRimskie = Rimskie.values()[result - 1];
            }
            catch(ArrayIndexOutOfBoundsException e) {
                throw new ScannerException("Ответ операции для римских чисел не может быть отрицательным");
            }
            return String.valueOf(resultRimskie);
        } else {
            return String.valueOf(result);
        }
    }

    //Проверка на римские числа
    private static boolean rimskieOrNet(String input) {
        //Проверяем принадлежит ли значение введенное с клавиатуры Enum Rimskie
        Rimskie rimskieinput;
        boolean rimskieOrNot;
        try {
            rimskieinput = Rimskie.valueOf(input); // Получаем значение Enum
            rimskieOrNot = true;
        } catch (IllegalArgumentException e) {
            rimskieinput = Rimskie.X;
            rimskieOrNot = false;
        }
        return rimskieOrNot;
    }
}

enum Rimskie {
    I(1) , II(2) , III(3) , IV(4) , V(5) , VI(6) , VII(7) , VIII(8) , IX(9) , X(10) , XI(11) , XII(12) , XIII(13) , XIV(14) , XV(15) , XVI(16) , XVII(17) , XVIII(18) , XIX(19) , XX(20) , XXI(21) , XXII(22) , XXIII(23) , XXIV(24) , XXV(25) , XXVI(26) , XXVII(27) , XXVIII(28) , XXIX(29) , XXX(30) , XXXI(31) , XXXII(32) , XXXIII(33) , XXXIV(34) , XXXV(35) , XXXVI(36) , XXXVII(37) , XXXVIII(38) , XXXIX(39) , XL(40) , XLI(41) , XLII(42) , XLIII(43) , XLIV(44) , XLV(45) , XLVI(46) , XLVII(47) , XLVIII(48) , XLIX(49) , L(50) , LI(51) , LII(52) , LIII(53) , LIV(54) , LV(55) , LVI(56) , LVII(57) , LVIII(58) , LIX(59) , LX(60) , LXI(61) , LXII(62) , LXIII(63) , LXIV(64) , LXV(65) , LXVI(66) , LXVII(67) , LXVIII(68) , LXIX(69) , LXX(70) , LXXI(71) , LXXII(72) , LXXIII(73) , LXXIV(74) , LXXV(75) , LXXVI(76) , LXXVII(77) , LXXVIII(78) , LXXIX(79) , LXXX(80) , LXXXI(81) , LXXXII(82) , LXXXIII(83) , LXXXIV(84) , LXXXV(85) , LXXXVI(86) , LXXXVII(87) , LXXXVIII(88) , LXXXIX(89) , XC(90) , XCI(91) , XCII(92) , XCIII(93) , XCIV(94) , XCV(95) , XCVI(96) , XCVII(97) , XCVIII(98) , XCIX(99) , C(100);

    private int chisloArab;

    Rimskie(int chisloArab){
        this.chisloArab = chisloArab;
    }

    public int getChisloArab(){
        return chisloArab;
    }
}

class ScannerException extends Exception {
    public ScannerException(String description) {
        super(description);
    }
}
