package StructuralDesignPatternUsecases;
import java.util.Scanner;

class LegacyPayment {
    public void makePayment(String paymentDetails) {
        System.out.println("Processing legacy payment: " + paymentDetails);
    }
}

interface PaymentProcessor {
    void processPayment(String paymentDetails);
}

class PaymentAdapter implements PaymentProcessor {
    private LegacyPayment legacyPayment;

    public PaymentAdapter(LegacyPayment legacyPayment) {
        this.legacyPayment = legacyPayment;
    }

    @Override
    public void processPayment(String paymentDetails) {
        legacyPayment.makePayment(paymentDetails);
    }
}

public class Adapter {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        
        LegacyPayment legacyPayment = new LegacyPayment();
        PaymentProcessor paymentProcessor = new PaymentAdapter(legacyPayment);

        System.out.print("Enter payment details: ");
        String paymentDetails = scanner.nextLine();
        
        paymentProcessor.processPayment(paymentDetails);
        
        scanner.close();
    }
}
