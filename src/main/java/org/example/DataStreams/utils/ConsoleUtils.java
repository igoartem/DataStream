package org.example.DataStreams.utils;

import java.io.BufferedReader;
import java.io.Console;
import java.io.IOException;
import java.io.InputStreamReader;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ConsoleUtils {
    private static final Logger log = LoggerFactory.getLogger(ConsoleUtils.class);
    private static Console console = System.console();

    public static void pauseExecution() {
        System.out.print("Press Enter to Continue... ");
        console.readLine();
    }

//    public static boolean requestConfirmation() {
//        while (true) {
//            System.out.print("Confirm Operation (y/n)... ");
//            String in = console.readLine().toLowerCase();
//            if (in.equals("y") || in.equals("yes"))
//                return true;
//            else if (in.equals("n") || in.equals("no"))
//                return false;
//        }
//    }

    public static <T> T readNumbers(String message, NumberParser<T> numberParser) {
        boolean work = true;
        String inp;
        T option = null;
        while (work) {
            try {
                inp = readString(message);
                option = numberParser.parse(inp);
                work = false;
            } catch (NumberFormatException e) {
                System.out.println("Неверный формат");
            }
        }
        return option;
    }

    public interface NumberParser<U> {
        U parse(String input);
    }

    public static String readString(String message) {
        boolean readLine = true;
        BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
        String inp = null;
        while (readLine) {
            System.out.println(message);
            try {
                inp = input.readLine();
            } catch (IOException e) {
                log.error("", e);
            }
            if (StringUtils.isNotEmpty(inp)) {
                readLine = false;
            } else {
                System.out.println("Строка пустая");
            }
        }
        return inp;
    }

}