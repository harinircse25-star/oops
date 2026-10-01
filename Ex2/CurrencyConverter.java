public class CurrencyConverter {

    public void dollarToINR(double dollar) {
        System.out.println("Dollar to INR = " + (dollar * 83));
    }

    public void inrToDollar(double inr) {
        System.out.println("INR to Dollar = " + (inr / 83));
    }

    public void euroToINR(double euro) {
        System.out.println("Euro to INR = " + (euro * 90));
    }

    public void inrToEuro(double inr) {
        System.out.println("INR to Euro = " + (inr / 90));
    }

    public void yenToINR(double yen) {
        System.out.println("Yen to INR = " + (yen * 0.56));
    }

    public void inrToYen(double inr) {
        System.out.println("INR to Yen = " + (inr / 0.56));
    }
}