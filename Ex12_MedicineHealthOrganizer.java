import java.sql.*;

import java.util.Scanner;

public class MedicineHealthOrganizer {

// MySQL connection details

static final String URL = "jdbc:mysql://localhost:3306/HealthDB";

static final String USER = "root";

static final String PASSWORD = "root";   // Change according to your MySQL password

static Scanner sc = new Scanner(System.in);

static Connection con;

// ---------------- DATABASE CONNECTION ----------------

static void connectDatabase() {

try {

Class.forName("com.mysql.cj.jdbc.Driver");

con = DriverManager.getConnection(URL, USER, PASSWORD);

System.out.println("\nDatabase connected successfully!");

createTables();

} catch (Exception e) {

System.out.println("Database connection failed!");

System.out.println(e);

}

}

// ---------------- CREATE TABLES ----------------

static void createTables() {

try {

Statement st = con.createStatement();

String patient =

"CREATE TABLE IF NOT EXISTS Patient (" +

"patient_id INT AUTO_INCREMENT PRIMARY KEY," +

"name VARCHAR(100)," +

"age INT," +

"gender VARCHAR(20)," +

"phone VARCHAR(15))";

String doctor =

"CREATE TABLE IF NOT EXISTS Doctor (" +

"doctor_id INT AUTO_INCREMENT PRIMARY KEY," +

"name VARCHAR(100)," +

"specialization VARCHAR(100)," +

"phone VARCHAR(15))";

String medicine =

"CREATE TABLE IF NOT EXISTS Medicine (" +

"medicine_id INT AUTO_INCREMENT PRIMARY KEY," +

"name VARCHAR(100)," +

"dosage VARCHAR(100)," +

"timing VARCHAR(100))";

String appointment =

"CREATE TABLE IF NOT EXISTS Appointment (" +

"appointment_id INT AUTO_INCREMENT PRIMARY KEY," +

"patient_id INT," +

"doctor_id INT," +

"appointment_date DATE," +

"appointment_time TIME," +

"reason VARCHAR(255)," +

"FOREIGN KEY(patient_id) REFERENCES Patient(patient_id)," +

"FOREIGN KEY(doctor_id) REFERENCES Doctor(doctor_id))";

String prescription =

"CREATE TABLE IF NOT EXISTS Prescription (" +

"prescription_id INT AUTO_INCREMENT PRIMARY KEY," +

"appointment_id INT," +

"medicine_id INT," +

"duration VARCHAR(50)," +

"FOREIGN KEY(appointment_id) REFERENCES Appointment(appointment_id)," +

"FOREIGN KEY(medicine_id) REFERENCES Medicine(medicine_id))";

st.executeUpdate(patient);

st.executeUpdate(doctor);

st.executeUpdate(medicine);

st.executeUpdate(appointment);

st.executeUpdate(prescription);

System.out.println("Tables created successfully!");

} catch (SQLException e) {

System.out.println("Table creation error: " + e);

}

}

// ---------------- ADD PATIENT ----------------

static void addPatient() {

try {

System.out.println("\n--- ADD PATIENT ---");

System.out.print("Enter patient name: ");

String name = sc.nextLine();

System.out.print("Enter age: ");

int age = Integer.parseInt(sc.nextLine());

System.out.print("Enter gender: ");

String gender = sc.nextLine();

System.out.print("Enter phone number: ");

String phone = sc.nextLine();

String sql =

"INSERT INTO Patient(name, age, gender, phone) VALUES (?, ?, ?, ?)";

PreparedStatement ps = con.prepareStatement(sql);

ps.setString(1, name);

ps.setInt(2, age);

ps.setString(3, gender);

ps.setString(4, phone);

ps.executeUpdate();

System.out.println("Patient added successfully!");

} catch (Exception e) {

System.out.println("Error: " + e);

}

}

// ---------------- ADD DOCTOR ----------------

static void addDoctor() {

try {

System.out.println("\n--- ADD DOCTOR ---");

System.out.print("Enter doctor name: ");

String name = sc.nextLine();

System.out.print("Enter specialization: ");

String specialization = sc.nextLine();

System.out.print("Enter phone number: ");

String phone = sc.nextLine();

String sql =

"INSERT INTO Doctor(name, specialization, phone) VALUES (?, ?, ?)";

PreparedStatement ps = con.prepareStatement(sql);

ps.setString(1, name);

ps.setString(2, specialization);

ps.setString(3, phone);

ps.executeUpdate();

System.out.println("Doctor added successfully!");

} catch (Exception e) {

System.out.println("Error: " + e);

}

}

// ---------------- ADD MEDICINE ----------------

static void addMedicine() {

try {

System.out.println("\n--- ADD MEDICINE ---");

System.out.print("Enter medicine name: ");

String name = sc.nextLine();

System.out.print("Enter dosage: ");

String dosage = sc.nextLine();

System.out.print("Enter timing: ");

String timing = sc.nextLine();

String sql =

"INSERT INTO Medicine(name, dosage, timing) VALUES (?, ?, ?)";

PreparedStatement ps = con.prepareStatement(sql);

ps.setString(1, name);

ps.setString(2, dosage);

ps.setString(3, timing);

ps.executeUpdate();

System.out.println("Medicine added successfully!");

} catch (Exception e) {

System.out.println("Error: " + e);

}

}

// ---------------- BOOK APPOINTMENT ----------------

static void bookAppointment() {

try {

System.out.println("\n--- BOOK APPOINTMENT ---");

System.out.print("Enter patient ID: ");

int patientId = Integer.parseInt(sc.nextLine());

System.out.print("Enter doctor ID: ");

int doctorId = Integer.parseInt(sc.nextLine());

System.out.print("Enter appointment date (YYYY-MM-DD): ");

String date = sc.nextLine();

System.out.print("Enter appointment time (HH:MM:SS): ");

String time = sc.nextLine();

System.out.print("Enter reason: ");

String reason = sc.nextLine();

String sql =

"INSERT INTO Appointment " +

"(patient_id, doctor_id, appointment_date, appointment_time, reason) " +

"VALUES (?, ?, ?, ?, ?)";

PreparedStatement ps = con.prepareStatement(sql);

ps.setInt(1, patientId);

ps.setInt(2, doctorId);

ps.setDate(3, Date.valueOf(date));

ps.setTime(4, Time.valueOf(time));

ps.setString(5, reason);

ps.executeUpdate();

System.out.println("Appointment booked successfully!");

} catch (Exception e) {

System.out.println("Error: " + e);

}

}

// ---------------- ADD PRESCRIPTION ----------------

static void addPrescription() {

try {

System.out.println("\n--- ADD PRESCRIPTION ---");

System.out.print("Enter appointment ID: ");

int appointmentId = Integer.parseInt(sc.nextLine());

System.out.print("Enter medicine ID: ");

int medicineId = Integer.parseInt(sc.nextLine());

System.out.print("Enter duration: ");

String duration = sc.nextLine();

String sql =

"INSERT INTO Prescription " +

"(appointment_id, medicine_id, duration) " +

"VALUES (?, ?, ?)";

PreparedStatement ps = con.prepareStatement(sql);

ps.setInt(1, appointmentId);

ps.setInt(2, medicineId);

ps.setString(3, duration);

ps.executeUpdate();

System.out.println("Prescription added successfully!");

} catch (Exception e) {

System.out.println("Error: " + e);

}

}

// ---------------- VIEW PATIENTS ----------------

static void viewPatients() {

try {

System.out.println("\n--- PATIENT LIST ---");

Statement st = con.createStatement();

ResultSet rs =

st.executeQuery("SELECT * FROM Patient");

System.out.println(

"ID\tName\tAge\tGender\tPhone"

);

while (rs.next()) {

System.out.println(

rs.getInt("patient_id") + "\t" +

rs.getString("name") + "\t" +

rs.getInt("age") + "\t" +

rs.getString("gender") + "\t" +

rs.getString("phone")

);

}

} catch (SQLException e) {

System.out.println("Error: " + e);

}

}

// ---------------- VIEW DOCTORS ----------------

static void viewDoctors() {

try {

System.out.println("\n--- DOCTOR LIST ---");

Statement st = con.createStatement();

ResultSet rs =

st.executeQuery("SELECT * FROM Doctor");

System.out.println(

"ID\tName\tSpecialization\tPhone"

);

while (rs.next()) {

System.out.println(

rs.getInt("doctor_id") + "\t" +

rs.getString("name") + "\t" +

rs.getString("specialization") + "\t" +

rs.getString("phone")

);

}

} catch (SQLException e) {

System.out.println("Error: " + e);

}

}

// ---------------- VIEW MEDICINES ----------------

static void viewMedicines() {

try {

System.out.println("\n--- MEDICINE LIST ---");

Statement st = con.createStatement();

ResultSet rs =

st.executeQuery("SELECT * FROM Medicine");

System.out.println(

"ID\tMedicine\tDosage\tTiming"

);

while (rs.next()) {

System.out.println(

rs.getInt("medicine_id") + "\t" +

rs.getString("name") + "\t" +

rs.getString("dosage") + "\t" +

rs.getString("timing")

);

}

} catch (SQLException e) {

System.out.println("Error: " + e);

}

}

// ---------------- VIEW APPOINTMENTS ----------------

static void viewAppointments() {

try {

System.out.println("\n--- APPOINTMENT LIST ---");

String sql =

"SELECT a.appointment_id, " +

"p.name AS patient, " +

"d.name AS doctor, " +

"d.specialization, " +

"a.appointment_date, " +

"a.appointment_time, " +

"a.reason " +

"FROM Appointment a " +

"JOIN Patient p ON a.patient_id = p.patient_id " +

"JOIN Doctor d ON a.doctor_id = d.doctor_id";

Statement st = con.createStatement();

ResultSet rs = st.executeQuery(sql);

while (rs.next()) {

System.out.println(

"\nAppointment ID : " +

rs.getInt("appointment_id") +

"\nPatient        : " +

rs.getString("patient") +

"\nDoctor         : " +

rs.getString("doctor") +

"\nSpecialization : " +

rs.getString("specialization") +

"\nDate           : " +

rs.getDate("appointment_date") +

"\nTime           : " +

rs.getTime("appointment_time") +

"\nReason         : " +

rs.getString("reason")

);

System.out.println("-----------------------------");

}

} catch (SQLException e) {

System.out.println("Error: " + e);

}

}

// ---------------- VIEW PRESCRIPTIONS ----------------

static void viewPrescriptions() {

try {

System.out.println("\n--- PRESCRIPTION LIST ---");

String sql =

"SELECT pr.prescription_id, " +

"p.name AS patient, " +

"m.name AS medicine, " +

"m.dosage, " +

"m.timing, " +

"pr.duration " +

"FROM Prescription pr " +

"JOIN Appointment a ON pr.appointment_id = a.appointment_id " +

"JOIN Patient p ON a.patient_id = p.patient_id " +

"JOIN Medicine m ON pr.medicine_id = m.medicine_id";

Statement st = con.createStatement();

ResultSet rs = st.executeQuery(sql);

while (rs.next()) {

System.out.println(

"\nPrescription ID : " +

rs.getInt("prescription_id") +

"\nPatient         : " +

rs.getString("patient") +

"\nMedicine        : " +

rs.getString("medicine") +

"\nDosage          : " +

rs.getString("dosage") +

"\nTiming          : " +

rs.getString("timing") +

"\nDuration        : " +

rs.getString("duration")

);

System.out.println("-----------------------------");

}

} catch (SQLException e) {

System.out.println("Error: " + e);

}

}

// ---------------- SEARCH PATIENT ----------------

static void searchPatient() {

try {

System.out.println("\n--- SEARCH PATIENT ---");

System.out.print("Enter patient name: ");

String name = sc.nextLine();

String sql =

"SELECT * FROM Patient WHERE name LIKE ?";

PreparedStatement ps =

con.prepareStatement(sql);

ps.setString(1, "%" + name + "%");

ResultSet rs = ps.executeQuery();

boolean found = false;

while (rs.next()) {

found = true;

System.out.println(

"\nPatient ID : " +

rs.getInt("patient_id") +

"\nName       : " +

rs.getString("name") +

"\nAge        : " +

rs.getInt("age") +

"\nGender     : " +

rs.getString("gender") +

"\nPhone      : " +

rs.getString("phone")

);

}

if (!found) {

System.out.println("Patient not found.");

}

} catch (SQLException e) {

System.out.println("Error: " + e);

}

}

// ---------------- MAIN MENU ----------------

public static void main(String[] args) {

connectDatabase();

int choice;

do {

System.out.println("\n======================================");

System.out.println(" MEDICINE & HEALTH APPOINTMENT ORGANIZER");

System.out.println("======================================");

System.out.println("1. Add Patient");

System.out.println("2. Add Doctor");

System.out.println("3. Add Medicine");

System.out.println("4. Book Appointment");

System.out.println("5. Add Prescription");

System.out.println("6. View Patients");

System.out.println("7. View Doctors");

System.out.println("8. View Medicines");

System.out.println("9. View Appointments");

System.out.println("10. View Prescriptions");

System.out.println("11. Search Patient");

System.out.println("0. Exit");

System.out.print("\nEnter your choice: ");

try {

choice = Integer.parseInt(sc.nextLine());

} catch (Exception e) {

choice = -1;

}

switch (choice) {

case 1:

addPatient();

break;

case 2:

addDoctor();

break;

case 3:

addMedicine();

break;

case 4:

bookAppointment();

break;

case 5:

addPrescription();

break;

case 6:

viewPatients();

break;

case 7:

viewDoctors();

break;

case 8:

viewMedicines();

break;

case 9:

viewAppointments();

break;

case 10:

viewPrescriptions();

break;

case 11:

searchPatient();

break;

case 0:

System.out.println(

"\nThank you for using the Health Organizer!"

);

break;

default:

System.out.println(

"Invalid choice. Please try again."

);

}

} while (choice != 0);

try {

if (con != null)

con.close();

} catch (SQLException e) {

System.out.println(e);

}

sc.close();

}

}