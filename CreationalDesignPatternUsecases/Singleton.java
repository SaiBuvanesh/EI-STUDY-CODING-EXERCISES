package CreationalDesignPatternUsecases;

import java.util.Scanner;

public class Singleton {
    private static Singleton instance;
    private String message;

    private Singleton() {
    }

    public static Singleton getInstance() {
        if (instance == null) {
            instance = new Singleton();
        }
        return instance;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public void showMessage() {
        System.out.println("Message from Singleton: " + message);
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        Singleton singleton = Singleton.getInstance();

        System.out.print("Enter a message for Singleton: ");
        String inputMessage = scanner.nextLine();

        singleton.setMessage(inputMessage);
        singleton.showMessage();

        scanner.close();
    }
}
