public class CollectionReport {
    private String date;
    private double totalCollection;

    public CollectionReport(String date, double totalCollection) {
        this.date = date;
        this.totalCollection = totalCollection;
    }

    public void generateReport() {
        System.out.println("Daily Collection Report for " + date + ": $" + totalCollection);
    }
}
