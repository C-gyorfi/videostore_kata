import lombok.Getter;

public class RegularMovie implements Movie {
    @Getter
    private String title;

    public RegularMovie(String title) {
        this.title = title;
    }

    @Override
    public double getPrice(int numberOfDays) {
        if (numberOfDays <= 2) return 2.0;
        return (numberOfDays - 2) * 1.5 + 2.0;
    }

    @Override
    public int getPoints(int numberOfDays) {
        return 1;
    }
}
