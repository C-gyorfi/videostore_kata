class Rental implements IRental {
    @Override
    public double getPrice() {
        return Double.parseDouble(null);
    }

    @Override
    public int getPoints() {
        return Integer.parseInt(null);
    }

    @Override
    public String getTitle() {
        return null;
    }
}

interface IRental {
    public double getPrice();
    public int getPoints();
    public String getTitle();
}