import lombok.Getter;

public class NewReleaseMovie implements Movie {
    @Getter
    private String title;

    public NewReleaseMovie(String title) {
        this.title = title;
    }

    @Override
    public double getPrice(int numberOfDays) {
        return 3.0 * numberOfDays;
    }

    @Override
    public int getPoints(int numberOfDays) {
        if (numberOfDays > 1) return 2;
        return 1;
    }
}
