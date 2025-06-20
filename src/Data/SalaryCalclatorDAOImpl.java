package Data;

import util.Config;

import java.io.*;
import java.util.Date;

public class SalaryCalclatorDAOImpl implements SalaryCalculatorDAO {

    @Override
    public void addDayWorked(String numWorked, String pricePerHour, String date) {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(Config.SALARY_PER_DAY_PATH, true))){
            bw.write(numWorked + "&" + pricePerHour + "&" + date);
            bw.newLine();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public double recoverSalary() {
        double salary = 0;
        try (BufferedReader br = new BufferedReader(new FileReader(Config.SALARY_PER_DAY_PATH))){
            String line;
            while ((line = br.readLine()) != null) {
                String[] data = line.split("&");

                if (line.isEmpty() || line == null) {
                    return 0.0;
                }

                double hoursWorked = Double.parseDouble(data[0]);
                double pricePerHour = Double.parseDouble(data[1]);

                salary += hoursWorked * pricePerHour;
            }
            return salary;
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

}
