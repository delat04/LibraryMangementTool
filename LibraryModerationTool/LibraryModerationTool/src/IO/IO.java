package IO;

import Control.mainMenu;
import DAO.MoneyDAO;

import java.util.InputMismatchException;
import java.util.Scanner;

public class IO {


    public static final String RESET = "\u001B[0m";
    public static final String BLACK = "\u001B[30m";
    public static final String RED = "\u001B[31m";
    public static final String GREEN = "\u001B[32m";
    public static final String YELLOW = "\u001B[33m";
    public static final String BLUE = "\u001B[34m";
    public static final String PURPLE = "\u001B[35m";
    public static final String CYAN = "\u001B[36m";
    public static final String WHITE = "\u001B[37m";

    public static void warning(Object o) {
        println(RED + o + RESET);
    }

    public static void sucess(Object o) {
        println(GREEN + o + RESET);
    }

    public static void important(Object o) {
        println(YELLOW + o + RESET);
    }

    public static void println(Object o) {
        System.out.println(o);
    }

    public static void wall() {

        println(yellow("===============================[ Money: " +  MoneyDAO.getMoney() + " DT  ]"));
    }

    public static void thinWall() {
        println("-------------------------------");
    }

    public static boolean getBool(String title) {
        while (true) {
            Scanner scn = new Scanner(System.in);
            important(
                    title + " YES OR NO ( Y/N )"
            );
            String answer = scn.nextLine();
            warning(answer);
            if (answer.equals("Y")) return true;
            if (answer.equals("N")) return false;
        }
    }

    public static int getInt(String text, int min, int max) {
        boolean notOkey = true;

        while (true) {
            Scanner scn = new Scanner(System.in);
            System.out.println(
                    "Enter " + text
                            + "(min:" + min
                            + "|"
                            + "max:" + max
                            + ") [[ 0 to EXIT ]]"
            );

            try {
                int num = scn.nextInt();
                scn.nextLine();
                if(num==0) {
                    mainMenu.Menu();
                }
                if (num > max || num < min) {
                    warning(
                            "Integer should be between "
                                    + min
                                    + " and "
                                    + max
                    );
                } else {
                    return num;
                }
            } catch (InputMismatchException e) {
                warning("Must be an Integer");
            }

        }
    }

    public static String getString(String text, int min, int max) {
        boolean notOkey = true;

        while (notOkey) {
            Scanner scn = new Scanner(System.in);
            System.out.println(
                    "Enter " + text
                            + "(min length:" + min + " char"
                            + "|"
                            + "max length:" + max + " char"
                            + ") [[q to EXIT]]"
            );


            String str = scn.nextLine();
            if(str.equals("q")){ mainMenu.Menu(); }
            if (str.length() > max || str.length() < min) {
                warning(
                        text + " should be between "
                                + min
                                + " characters and "
                                + max + "characters"
                );
            } else {
                return str;
            }

        }
        return null;
    }

    public static void exitText() {
        important("Exiting!");
    }

    public static String red(Object s) {
        return RED + s + RESET;
    }

    public static String yellow(Object s) {
        return YELLOW + s + RESET;
    }

    public static String green(Object s) {
        return GREEN + s + RESET;
    }
}
