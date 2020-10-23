import com.github.javafaker.Faker;
import org.junit.jupiter.api.Test;

import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

import static org.junit.Assert.assertEquals;

public class RentalTest {
    @Test
    void testCanCreateOneOrTwoDaysRentalWithRegularMovie() {
        var title = new Faker().book().title();
        var regularMovie = new RegularMovie(title);
        var numberOfDays = ThreadLocalRandom.current().nextInt(1, 3);
        var rental = new Rental(regularMovie, numberOfDays);
        assertEquals(title, rental.getTitle());
        assertEquals(2.0, rental.getPrice(), 0);
        assertEquals(1, rental.getPoints());
    }

    @Test
    void testThreeDaysRentalsForRegularMovie() {
        var rental = new Rental(new RegularMovie("Dummy"), 3);
        assertEquals(3.5, rental.getPrice(), 0);
        assertEquals(1, rental.getPoints());
    }

    @Test
    void testFiveDaysRentalsForRegularMovie() {
        var rental = new Rental(new RegularMovie("Dummy"), 5);
        assertEquals(6.5, rental.getPrice(), 0);
        assertEquals(1, rental.getPoints());
    }

    @Test
    void testCanCreateOneDayNewReleaseRental() {
        var title = new Faker().book().title();
        var rental = new Rental(new NewReleaseMovie(title), 1);
        assertEquals(title, rental.getTitle());
        assertEquals(3.0, rental.getPrice(), 0);
        assertEquals(1, rental.getPoints());
    }

    @Test
    void testCanCreateTwoDaysNewReleaseRental() {
        var rental = new Rental(new NewReleaseMovie("Dummy"), 2);
        assertEquals(6.0, rental.getPrice(), 0);
        assertEquals(2, rental.getPoints());
    }
}
