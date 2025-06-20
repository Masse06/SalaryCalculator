package Data;

public interface SalaryCalculatorDAO {
    void addDayWorked(String numWorked, String pricePerHour, String date);
    double recoverSalary();
}
