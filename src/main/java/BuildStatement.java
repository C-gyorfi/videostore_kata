import java.util.ArrayList;
import java.util.List;

public class BuildStatement {
    private final String customer_name;
    private List<IRental> rentals;

    public BuildStatement(String customer_name) {
        this.customer_name = customer_name;
        this.rentals = new ArrayList<>();
    }

    public String execute() {
        return  String.format("Rental Record for %s\n", customer_name) +
                this.listRentals() +
                String.format("You owe £%s\n", this.totalPrice()) +
                String.format("You earned %s frequent renter points", this.pointsEarned());
    }

    public void addRental(IRental rental) {
        this.rentals.add(rental);
    }

    private int pointsEarned() {
        int sum = 0;
        for (IRental rental : this.rentals) sum += rental.getPoints();
        return sum;
    }

    private String listRentals(){
        var result = "";
        for (IRental rental : this.rentals)
            result += String.format("   %s  £%s\n", rental.getTitle(), rental.getPrice());
        return result;
    }

    private double totalPrice(){
        double sum = 0.0;
        for (IRental rental : this.rentals) sum += rental.getPrice();
        return sum;
    }
}
