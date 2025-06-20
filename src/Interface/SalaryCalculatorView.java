package Interface;

import Data.SalaryCalclatorDAOImpl;
import Data.SalaryCalculatorDAO;

import javax.swing.*;
import java.awt.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class SalaryCalculatorView extends JFrame {

    private JTextField numOfHours;
    private JTextField pricePerHour;
    private JComboBox<Integer> dayCombo;
    private JComboBox<String> monthCombo;
    private JComboBox<Integer> yearCombo;
    private JButton addDayWorked;
    private JLabel totalSalary;
    private JLabel author;

    SalaryCalculatorDAO salaryDAO = new SalaryCalclatorDAOImpl();


    public SalaryCalculatorView() {
        super("Calculadora de Salario");
        setSize(600, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        JPanel panel = new JPanel(new GridLayout(9, 2, 10, 10));
        panel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20)); // Bordes

        LocalDate todayDate = LocalDate.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        String date = todayDate.format(formatter);

        // Inicializamos los componentes
        numOfHours = new JTextField();
        pricePerHour = new JTextField();

        dayCombo = new JComboBox<>();
        for (int i = 1; i <= 31; i++) {
            dayCombo.addItem(i);
        }

        String[] months = {"Enero", "Febrero", "Marzo", "Abril", "Mayo", "Junio",
                "Julio", "Agosto", "Septiembre", "Octubre", "Noviembre", "Diciembre"};
        monthCombo = new JComboBox<>(months);
        yearCombo = new JComboBox<>();
        for (int i = 2020; i <= 2040; i++) {
            yearCombo.addItem(i);
        }

        addDayWorked = new JButton("Añadir día trabajado");
        totalSalary = new JLabel("Salario total: 0 €");
        author = new JLabel("Masse");

        // Añadimos los componentes al panel
        panel.add(new JLabel()); // Espacio en blanco
        panel.add(new JLabel("Fecha actual: " + date));

        panel.add(new JLabel("Ingrese las horas trabajadas"));
        panel.add(numOfHours);

        panel.add(new JLabel("Ingrese el precio por hora trabajada"));
        panel.add(pricePerHour);

        panel.add(new JLabel("Selecciona el día:"));
        panel.add(dayCombo);

        panel.add(new JLabel("Selecciona el mes:"));
        panel.add(monthCombo);

        panel.add(new JLabel("Selecciona el año:"));
        panel.add(yearCombo);

        panel.add(new JLabel());
        panel.add(addDayWorked);

        panel.add(new JLabel()); // Espacio en blanco
        panel.add(totalSalary);

        panel.add(new JLabel()); // Espacio en blanco
        panel.add(author);

        add(panel);

        // Funcionalidad
        addDayWorked.addActionListener(e -> {
            int day = (int) dayCombo.getSelectedItem();
            int month = monthCombo.getSelectedIndex() + 1; // enero = 0
            int year = (int) yearCombo.getSelectedItem();

            LocalDate localDate = LocalDate.of(year, month, day);
            String finalDate = localDate.format(DateTimeFormatter.ofPattern("dd/MM/yyyy"));

            salaryDAO.addDayWorked(numOfHours.getText(), pricePerHour.getText(), finalDate);
            updateSalary();
        });
        updateSalary();
    }

    private void updateSalary() {
        totalSalary.setText(String.format("Salario total: %.2f €", salaryDAO.recoverSalary()));
    }
}
