import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.hamcrest.CoreMatchers;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

public class BuildStatementTest {
    @ParameterizedTest
    @ValueSource(strings = {"Bruce Wayne", "The Joker"})
    void testStatementHasCustomersName(String name) {
        var build_statement = new BuildStatement(name);
        String expected = "Rental Record for " + name + "\n" +
                "You owe £0.0\n" +
                "You earned 0 frequent renter points";
        Assert.assertThat(build_statement.execute(), CoreMatchers.is(expected));
    }

    @Test
    void testCanBuildStatementWithARental() {
        var price = 2;
        var points = 1;
        var title = "Crazynotes";
        var rental = new RentalStub(price, points, title);
        var build_statement = new BuildStatement("The Joker");
        build_statement.addRental(rental);
        String expected = "Rental Record for The Joker\n" +
                "   Crazynotes  £2.0\n" +
                "You owe £2.0\n" +
                "You earned 1 frequent renter points";
        Assert.assertThat(build_statement.execute(), CoreMatchers.is(expected));
    }

    @Test
    void testCanBuildStatementWithADifferentRental() {
        var price = 1.5;
        var points = 1;
        var title = "Kids Movie";
        var rental = new RentalStub(price, points, title);
        var build_statement = new BuildStatement("The Joker");
        build_statement.addRental(rental);
        String expected = "Rental Record for The Joker\n" +
                "   Kids Movie  £1.5\n" +
                "You owe £1.5\n" +
                "You earned 1 frequent renter points";
        Assert.assertThat(build_statement.execute(), CoreMatchers.is(expected));
    }

    @Test
    void testCanBuildStatementWithMultipleRentals() {
        var build_statement = new BuildStatement("Richard John Grayson");
        var rental1 = new RentalStub(2, 1, "Crazynotes");
        var rental2 = new RentalStub(1.5, 1, "Kids Movie");

        build_statement.addRental(rental1);
        build_statement.addRental(rental2);

        String expected = "Rental Record for Richard John Grayson\n" +
                "   Crazynotes  £2.0\n" +
                "   Kids Movie  £1.5\n" +
                "You owe £3.5\n" +
                "You earned 2 frequent renter points";
        Assert.assertThat(build_statement.execute(), CoreMatchers.is(expected));
    }

    @Test
    void testCanBuildStatementEndToEnd() {
        var build_statement = new BuildStatement("Andy Toend");
        var regularRental = new RentalImpl(new RegularMovie("The Regular"), 4);
        var newRelease = new RentalImpl(new NewReleaseMovie("The New"), 6);
        var childrens = new RentalImpl(new ChildrensMovie("Child's Play"), 4);

        build_statement.addRental(regularRental);
        build_statement.addRental(newRelease);
        build_statement.addRental(childrens);

        String expected = "Rental Record for Andy Toend\n" +
                "   The Regular  £5.0\n" +
                "   The New  £18.0\n" +
                "   Child's Play  £3.0\n" +
                "You owe £26.0\n" +
                "You earned 4 frequent renter points";
        Assert.assertThat(build_statement.execute(), CoreMatchers.is(expected));
    }

    private class RentalStub implements Rental {

        private int points;
        private double price;
        private String title;

        public RentalStub(double price, int points, String title) {
            this.title = title;
            this.price = price;
            this.points = points;
        }

        @Override
        public double getPrice() {
            return this.price;
        }

        @Override
        public int getPoints() {
            return this.points;
        }

        @Override
        public String getTitle() {
            return this.title;
        }
    }
}
