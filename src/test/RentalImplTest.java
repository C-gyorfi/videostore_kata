import com.github.javafaker.Faker;
import org.junit.jupiter.api.Test;
import java.util.concurrent.ThreadLocalRandom;

import static org.junit.Assert.assertEquals;

public class RentalImplTest {
    @Test
    void testCanCreateOneOrTwoDaysRentalWithRegularMovie() {
        var title = new Faker().book().title();
        var regularMovie = new RegularMovie(title);
        var numberOfDays = ThreadLocalRandom.current().nextInt(1, 3);
        var rental = new RentalImpl(regularMovie, numberOfDays);
        assertEquals(title, rental.getTitle());
        assertEquals(2.0, rental.getPrice(), 0);
        assertEquals(1, rental.getPoints());
    }

    @Test
    void testThreeDaysRentalsForRegularMovie() {
        var rental = new RentalImpl(new RegularMovie("Dummy"), 3);
        assertEquals(3.5, rental.getPrice(), 0);
        assertEquals(1, rental.getPoints());
    }

    @Test
    void testFiveDaysRentalsForRegularMovie() {
        var rental = new RentalImpl(new RegularMovie("Dummy"), 5);
        assertEquals(6.5, rental.getPrice(), 0);
        assertEquals(1, rental.getPoints());
    }

    @Test
    void testCanCreateOneDayNewReleaseRental() {
        var title = new Faker().book().title();
        var rental = new RentalImpl(new NewReleaseMovie(title), 1);
        assertEquals(title, rental.getTitle());
        assertEquals(3.0, rental.getPrice(), 0);
        assertEquals(1, rental.getPoints());
    }

    @Test
    void testCanCreateTwoDaysNewReleaseRental() {
        var rental = new RentalImpl(new NewReleaseMovie("Dummy"), 2);
        assertEquals(6.0, rental.getPrice(), 0);
        assertEquals(2, rental.getPoints());
    }

    @Test
    void testCanCreateOneToThreeDaysChildrensMovieRental() {
        var title = new Faker().book().title();
        var numberOfDays = ThreadLocalRandom.current().nextInt(1, 4);
        var rental = new RentalImpl(new ChildrensMovie(title), numberOfDays);
        assertEquals(title, rental.getTitle());
        assertEquals(1.5, rental.getPrice(), 0);
        assertEquals(1, rental.getPoints());
    }

    @Test
    void testCanCreateFourDaysChildrensMovieRental() {
        var rental = new RentalImpl(new ChildrensMovie("Dummy"), 4);
        assertEquals(3.0, rental.getPrice(), 0);
        assertEquals(1, rental.getPoints());
    }

    @Test
    void testCanCreateFiveDaysChildrensMovieRental() {
        var rental = new RentalImpl(new ChildrensMovie("Dummy"), 5);
        assertEquals(4.5, rental.getPrice(), 0);
        assertEquals(1, rental.getPoints());
    }
}
