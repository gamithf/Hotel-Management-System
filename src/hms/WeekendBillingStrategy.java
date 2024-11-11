package hms;

public class WeekendBillingStrategy implements BillingStrategy{
    @Override
    public double calculateBill(double baseAmount) {
        return baseAmount * 1.2;
    }
}
