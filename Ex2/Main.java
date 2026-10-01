public class Main {

    public static void main(String[] args) {

        CurrencyConverter c = new CurrencyConverter();
        DistanceConverter d = new DistanceConverter();
        TimeConverter t = new TimeConverter();

        System.out.println("===== Currency Converter =====");
        c.dollarToINR(10);
        c.inrToDollar(830);
        c.euroToINR(5);
        c.inrToEuro(450);
        c.yenToINR(100);
        c.inrToYen(560);

        System.out.println("\n===== Distance Converter =====");
        d.meterToKM(5000);
        d.kmToMeter(5);
        d.milesToKM(10);
        d.kmToMiles(16.09);

        System.out.println("\n===== Time Converter =====");
        t.hoursToMinutes(2);
        t.minutesToHours(120);
        t.hoursToSeconds(2);
        t.secondsToHours(7200);
    }
}