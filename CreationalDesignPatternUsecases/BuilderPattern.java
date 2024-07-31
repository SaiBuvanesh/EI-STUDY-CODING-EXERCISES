package CreationalDesignPatternUsecases;
import java.util.Scanner;

class Car {
    private String name;
    private String model;
    private int year;
    private String color;

    // Private constructor to enforce the use of the builder
    private Car(Builder builder) {
        this.name = builder.name;
        this.model = builder.model;
        this.year = builder.year;
        this.color = builder.color;
    }

    @Override
    public String toString() {
        return "Car [name=" + name + ", model=" + model + ", year=" + year + ", color=" + color + "]";
    }

    // Static nested Builder class
    public static class Builder {
        private String name;
        private String model;
        private int year;
        private String color;

        public Builder setMake(String name) {
            this.name = name;
            return this;
        }

        public Builder setModel(String model) {
            this.model = model;
            return this;
        }

        public Builder setYear(int year) {
            this.year = year;
            return this;
        }

        public Builder setColor(String color) {
            this.color = color;
            return this;
        }

        public Car build() {
            return new Car(this);
        }
    }
}

public class BuilderPattern {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Get user input for Car attributes
        System.out.print("Enter car name: ");
        String name = scanner.nextLine();

        System.out.print("Enter car model: ");
        String model = scanner.nextLine();

        System.out.print("Enter car year: ");
        int year = scanner.nextInt();
        scanner.nextLine();  // Consume the newline character left after nextInt()

        System.out.print("Enter car color: ");
        String color = scanner.nextLine();

        // Use the builder to create a Car object
        Car car = new Car.Builder()
                    .setMake(name)
                    .setModel(model)
                    .setYear(year)
                    .setColor(color)
                    .build();

        // Print the car details
        System.out.println(car);

        scanner.close(); // Close the scanner
    }
}
