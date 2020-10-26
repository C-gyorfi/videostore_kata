class RentalImpl implements Rental {
    private Movie movie;
    private int numberOfDays;

    public RentalImpl(Movie movie, int numberOfDays) {
        this.movie = movie;
        this.numberOfDays = numberOfDays;
    }

    @Override
    public double getPrice() {
        return this.movie.getPrice(this.numberOfDays);
    }

    @Override
    public int getPoints() {
        return this.movie.getPoints(this.numberOfDays);
    }

    @Override
    public String getTitle() {
        return this.movie.getTitle();
    }
}

