package yp;

import java.awt.EventQueue;
import javax.swing.*;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class Main {

    private JFrame frame;
    private JTextField nameField;
    private JTextField valueField;
    private JTextArea resultArea;
    private JComboBox<String> typeComboBox;
    private JComboBox<String> unitComboBox;

    // Константы для единиц измерений
    private static final String[] Вес_UNITS = {"кг", "г", "т"};
    private static final String[] Длина_UNITS = {"м", "см", "км"};
    private static final String[] TYPES = {"Вес", "Длина"};

    // Метод для запуска программы
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    Main window = new Main();
                    window.frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    // Конструктор приложения
    public Main() {
        initialize();
    }

    // Инициализация компонентов
    private void initialize() {
        frame = new JFrame();
        frame.setBounds(100, 100, 450, 300);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().setLayout(null);

        JLabel nameLabel = new JLabel("Введите ваше имя:");
        nameLabel.setBounds(10, 10, 150, 25);
        frame.getContentPane().add(nameLabel);

        nameField = new JTextField();
        nameField.setBounds(170, 10, 200, 25);
        frame.getContentPane().add(nameField);
        nameField.setColumns(10);

        JLabel typeLabel = new JLabel("Выберите тип:");
        typeLabel.setBounds(10, 45, 150, 25);
        frame.getContentPane().add(typeLabel);

        typeComboBox = new JComboBox<>(TYPES);
        typeComboBox.setBounds(170, 45, 200, 25);
        typeComboBox.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                updateUnitComboBox();
            }
        });
        frame.getContentPane().add(typeComboBox);

        JLabel unitLabel = new JLabel("Выберите единицу измерения:");
        unitLabel.setBounds(10, 80, 150, 25);
        frame.getContentPane().add(unitLabel);

        unitComboBox = new JComboBox<>();
        unitComboBox.setBounds(170, 80, 200, 25);
        frame.getContentPane().add(unitComboBox);

        JLabel valueLabel = new JLabel("Введите значение:");
        valueLabel.setBounds(10, 115, 150, 25);
        frame.getContentPane().add(valueLabel);

        valueField = new JTextField();
        valueField.setBounds(170, 115, 200, 25);
        frame.getContentPane().add(valueField);
        valueField.setColumns(10);

        JButton resultButton = new JButton("Перевести");
        resultButton.setBounds(170, 150, 100, 25);
        resultButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                showResult();
            }
        });
        frame.getContentPane().add(resultButton);

        resultArea = new JTextArea();
        resultArea.setBounds(10, 180, 400, 70);
        frame.getContentPane().add(resultArea);

        // Инициализация доступных единиц для перевода
        updateUnitComboBox();
    }

    // Обновление ComboBox с единицами в зависимости от выбранного типа
    private void updateUnitComboBox() {
        String type = (String) typeComboBox.getSelectedItem();
        if ("Вес".equals(type)) {
            unitComboBox.setModel(new DefaultComboBoxModel<>(Вес_UNITS));
        } else if ("Длина".equals(type)) {
            unitComboBox.setModel(new DefaultComboBoxModel<>(Длина_UNITS));
        }
    }

    // Метод для выполнения перевода и вывода результата
    private void showResult() {
        String name = nameField.getText();
        String type = (String) typeComboBox.getSelectedItem();
        String unit = (String) unitComboBox.getSelectedItem();
        double value;
        
        try {
            value = Double.parseDouble(valueField.getText());
        } catch (NumberFormatException e) {
            resultArea.setText("Invalid value!");
            return;
        }

        StringBuilder result = new StringBuilder("Привет, " + name + "!\n");
        
        if ("Вес".equals(type)) {
            result.append("Вы ввели ").append(value).append(" ").append(unit).append("\n");
            if ("кг".equals(unit)) {
                result.append("В граммах: ").append(value * 1000).append(" г\n");
                result.append("В тоннах: ").append(value / 1000).append(" т\n");
            } else if ("г".equals(unit)) {
                result.append("В килограммах: ").append(value / 1000).append(" кг\n");
                result.append("В тоннах: ").append(value / 1000000).append(" т\n");
            } else if ("т".equals(unit)) {
                result.append("В килограммах: ").append(value * 1000).append(" кг\n");
                result.append("В граммах: ").append(value * 1000000).append(" г\n");
            }
        } else if ("Длина".equals(type)) {
            result.append("Вы ввели ").append(value).append(" ").append(unit).append("\n");
            if ("м".equals(unit)) {
                result.append("В сантиметрах: ").append(value * 100).append(" cm\n");
                result.append("В километрах: ").append(value / 1000).append(" km\n");
            } else if ("см".equals(unit)) {
                result.append("В метрах: ").append(value / 100).append(" m\n");
                result.append("В километрах: ").append(value / 100000).append(" km\n");
            } else if ("км".equals(unit)) {
                result.append("В метрах: ").append(value * 1000).append(" m\n");
                result.append("В сантиметрах: ").append(value * 100000).append(" cm\n");
            }
        }

        resultArea.setText(result.toString());
    }
}