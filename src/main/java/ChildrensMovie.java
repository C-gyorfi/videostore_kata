public class ChildrensMovie implements Movie {
    private final String title;

    public ChildrensMovie(String title) {
        this.title = title;
    }

    @Override
    public String getTitle() {
        return this.title;
    }

    @Override
    public double getPrice(int numberOfDays) {
        if (numberOfDays <= 3) return 1.5;
        return (numberOfDays - 3) * 1.5 + 1.5;
    }

    @Override
    public int getPoints(int numberOfDays) {
        return 1;
    }
}
