import javax.imageio.IIOException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        int countData = 5;
        while(true) {
            System.out.println("Введите ФИО, дату рождения, номер телефона, пол (разделяйте данные пробелами):");
            Scanner scanner = new Scanner(System.in);
            String input = scanner.nextLine();
            int result = isCurrentCount(input, countData);
            switch (result) {
                case -1:
                    System.out.println("Вы ввели меньше данных, чем требуется!");
                    continue;
                case -2:
                    System.out.println("Вы ввели больше данных, чем требуется!");
                    continue;
            }
            try {
                String[] dataHuman = parseStr(input, countData);
                outputFile(dataHuman);
                break;
            }
            catch (Exception e) {
                e.printStackTrace();
            }
        }

    }

    static int isCurrentCount(String inputStr, int countData) {
        int countInput = inputStr.length() - inputStr.replaceAll(" ", "").length();
        if(countInput == countData) return 0;
        if(countInput < countData) return -1;
        return -2;
    }

    static String[] parseStr(String input, int countData) {
        String[] result = new String[countData+1];
        String[] temp = input.split(" ");
        for(int i=0; i<temp.length; i++){
            if(temp[i].equalsIgnoreCase("f") || temp[i].equalsIgnoreCase("m")) {
                if(result[5] == null) {
                    result[5] = temp[i];
                    continue;
                }
                else {
                    throw new IllegalArgumentException("Введены некорректные данные. " +
                            "Пол человека был введен больше одного раза");
                }
            }
            if(temp[i].contains(".")) {
                if(result[3] == null) {
                    result[3] = temp[i];
                    continue;
                }
                else {
                    throw new IllegalArgumentException("Введены некорректные данные. " +
                            "Дата рождения была введена больше одного раза");
                }
            }
            if(isPhone(temp[i])) {
                if(result[4] == null) {
                    result[4] = temp[i];
                    continue;
                }
                else {
                    throw new IllegalArgumentException("Введены некорректные данные. " +
                            "Номер телефона был введен больше одного раза");
                }
            }
            if(result[0] == null) {
                result[0] = temp[i];
                continue;
            }
            if(result[1] == null) {
                result[1] = temp[i];
                continue;
            }
            if(result[2] == null) {
                result[2] = temp[i];
                continue;
            }
            throw new IllegalArgumentException("Введены некорректные данные. " +
                    "Фамилия, имя или отчество было введено больше одного раза");
        }
        return result;
    }

    static boolean isPhone(String text) {
        try {
            Long.parseLong(text);
        }
        catch (NumberFormatException e) {
            return false;
        }
        return true;
    }

    static void outputFile(String[] data) {
        try {
            FileWriter writer = new FileWriter(data[0], true);
            StringBuilder stringBuilder = new StringBuilder();
            for(int i=0; i<data.length; i++) {
                stringBuilder.append("<");
                stringBuilder.append(data[i]);
                stringBuilder.append(">");
            }
            stringBuilder.append("\n");
            writer.write(stringBuilder.toString());
            writer.flush();
            writer.close();
        }
        catch (IOException e) {
            e.getMessage();
        }
    }
}

