package BehaviouralDesignPatternUsecases;

import java.util.List;
import java.util.ArrayList;
import java.util.Scanner;

interface Stock {
    void registerObserver(Investor investor);
    void removeObserver(Investor investor);
    void notifyObservers();
}

class StockMarket implements Stock {
    private List<Investor> investors = new ArrayList<>();
    private double stockPrice;

    public void setStockPrice(double stockPrice) {
        this.stockPrice = stockPrice;
        notifyObservers();
    }

    @Override
    public void registerObserver(Investor investor) {
        investors.add(investor);
    }

    @Override
    public void removeObserver(Investor investor) {
        investors.remove(investor);
    }

    @Override
    public void notifyObservers() {
        for (Investor investor : investors) {
            investor.update(stockPrice);
        }
    }
}

interface Investor {
    void update(double stockPrice);
}

class ConcreteInvestor implements Investor {
    private String name;

    public ConcreteInvestor(String name) {
        this.name = name;
    }

    @Override
    public void update(double stockPrice) {
        System.out.println("Investor " + name + " notified. New stock price: " + stockPrice);
    }
}

public class ObserverPattern {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        StockMarket stockMarket = new StockMarket();

        System.out.print("Enter the name of the first investor: ");
        String investor1Name = scanner.nextLine();
        Investor investor1 = new ConcreteInvestor(investor1Name);

        System.out.print("Enter the name of the second investor: ");
        String investor2Name = scanner.nextLine();
        Investor investor2 = new ConcreteInvestor(investor2Name);

        stockMarket.registerObserver(investor1);
        stockMarket.registerObserver(investor2);

        System.out.print("Enter the new stock price: ");
        double stockPrice = scanner.nextDouble();
        stockMarket.setStockPrice(stockPrice);

        System.out.print("Enter another new stock price: ");
        stockPrice = scanner.nextDouble();
        stockMarket.setStockPrice(stockPrice);

        scanner.close();
    }
}
