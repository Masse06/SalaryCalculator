import Interface.SalaryCalculatorView;

import javax.swing.*;

public class Main {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            SalaryCalculatorView appView = new SalaryCalculatorView();
            appView.setVisible(true);
        });
    }
}
