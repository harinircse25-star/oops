import java.util.Scanner;

class ElectricityBill {

int consumerNo;

String consumerName;

int previousReading;

int currentReading;

String connectionType;

int units;

double billAmount;

void getInput() {

Scanner sc = new Scanner(System.in);

System.out.print("Enter Consumer Number: ");

consumerNo = sc.nextInt();

sc.nextLine();

System.out.print("Enter Consumer Name: ");

consumerName = sc.nextLine();

System.out.print("Enter Previous Month Reading: ");

previousReading = sc.nextInt();

System.out.print("Enter Current Month Reading: ");

currentReading = sc.nextInt();

sc.nextLine();

System.out.print("Enter Connection Type (Domestic/Commercial): ");

connectionType = sc.nextLine();

units = currentReading - previousReading;

}

void calculateBill() {

if (connectionType.equalsIgnoreCase("Domestic")) {

if (units <= 100)

billAmount = units * 1.5;

else if (units <= 200)

billAmount = (100 * 1.5) + (units - 100) * 3;

else if (units <= 500)

billAmount = (100 * 1.5) + (100 * 3) + (units - 200) * 4.5;

else

billAmount = (100 * 1.5) + (100 * 3) + (300 * 4.5) + (units - 500) * 7;

} else if (connectionType.equalsIgnoreCase("Commercial")) {

if (units <= 100)

billAmount = units * 2.5;

else if (units <= 200)

billAmount = (100 * 2.5) + (units - 100) * 5;

else if (units <= 500)

billAmount = (100 * 2.5) + (100 * 5) + (units - 200) * 6.5;

else

billAmount = (100 * 2.5) + (100 * 5) + (300 * 6.5) + (units - 500) * 9;

} else {

System.out.println("Invalid Connection Type!");

return;

}

}

void displayBill() {

System.out.println("\n------ Electricity Bill ------");

System.out.println("Consumer Number : " + consumerNo);

System.out.println("Consumer Name   : " + consumerName);

System.out.println("Connection Type : " + connectionType);

System.out.println("Units Consumed  : " + units);

System.out.println("Bill Amount     : Rs. " + billAmount);

}

public static void main(String[] args) {

ElectricityBill eb = new ElectricityBill();

eb.getInput();

eb.calculateBill();

eb.displayBill();

}

}