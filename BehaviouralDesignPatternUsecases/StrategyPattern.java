package BehaviouralDesignPatternUsecases;

import java.util.Scanner;

interface PaymentStrategy {
    void pay(int amount);
}
class CreditCardPayment implements PaymentStrategy {
    private String cardNumber;

    public CreditCardPayment(String cardNumber) {
        this.cardNumber = cardNumber;
    }

    @Override
    public void pay(int amount) {
        System.out.println("Paid " + amount + " using Credit Card ending with " + cardNumber.substring(cardNumber.length() - 4));
    }
}

class PayPalPayment implements PaymentStrategy {
    private String email;

    public PayPalPayment(String email) {
        this.email = email;
    }

    @Override
    public void pay(int amount) {
        System.out.println("Paid " + amount + " using PayPal account " + email);
    }
}

class BitcoinPayment implements PaymentStrategy {
    private String walletAddress;

    public BitcoinPayment(String walletAddress) {
        this.walletAddress = walletAddress;
    }

    @Override
    public void pay(int amount) {
        System.out.println("Paid " + amount + " using Bitcoin wallet " + walletAddress);
    }
}

class ShoppingCart {
    private PaymentStrategy paymentStrategy;

    public void setPaymentStrategy(PaymentStrategy paymentStrategy) {
        this.paymentStrategy = paymentStrategy;
    }

    public void checkout(int amount) {
        paymentStrategy.pay(amount);
    }
}

public class StrategyPattern {
    @SuppressWarnings("resource")
    public static void main(String[] args) {
        ShoppingCart cart = new ShoppingCart();
        Scanner scanner = new Scanner(System.in);

        System.out.println("Enter payment amount:");
        int amount = scanner.nextInt();

        System.out.println("Choose payment method: 1. Credit Card 2. PayPal 3. Bitcoin");
        int choice = scanner.nextInt();

        switch (choice) {
            case 1:
                cart.setPaymentStrategy(new CreditCardPayment("12345678901234590861"));
                break;
            case 2:
                cart.setPaymentStrategy(new PayPalPayment("user@gmail.com"));
                break;
            case 3:
                cart.setPaymentStrategy(new BitcoinPayment("1A1zP1eP5QGefi2DMPTfTL5SLmv7DivfNanagty"));
                break;
            default:
                System.out.println("Invalid choice");
                return;
        }

        cart.checkout(amount);
        scanner.close();
    }
}
