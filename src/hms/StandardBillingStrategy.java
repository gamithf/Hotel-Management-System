package hms;

public class StandardBillingStrategy implements BillingStrategy{
    @Override
    public double calculateBill(double baseAmount) {
        return baseAmount;
    }
}
