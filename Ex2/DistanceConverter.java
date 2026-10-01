public class DistanceConverter {

    public void meterToKM(double meter) {
        System.out.println("Meter to KM = " + (meter / 1000));
    }

    public void kmToMeter(double km) {
        System.out.println("KM to Meter = " + (km * 1000));
    }

    public void milesToKM(double miles) {
        System.out.println("Miles to KM = " + (miles * 1.609));
    }

    public void kmToMiles(double km) {
        System.out.println("KM to Miles = " + (km / 1.609));
    }
}