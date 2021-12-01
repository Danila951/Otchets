package com.company.ui;


import com.company.entity.UserEntity;
import com.company.manager.UserEntytiManager;
import com.company.uhl.BaseForm;

import javax.swing.*;
import java.sql.SQLException;

public class TestForm extends BaseForm {
    private JPanel mainPanel;
    private JTextField fioField;
    private JTextField profField;
    private JButton saveButton;
    private JSpinner yearSpinner;
    private JComboBox typeBox;


    public TestForm() {
        super(500,300);
        setContentPane(mainPanel);

        saveButton.addActionListener(e -> {
            String fio = fioField.getText();
            if(fio.isEmpty() || fio.length() > 100) {
                System.out.println("проблемы с ФИО");
                return;
            }


            String profession = profField.getText();
            if(profession.isEmpty() || profession.length() > 100) {
                System.out.println("проблемы с Профессией");
                return;
            }

            int year = (int) yearSpinner.getValue();
            if(year <= 1900 || year > 2021) {
                System.out.println("Проблемы с годом");
                return;
            }

            UserEntity user = new UserEntity(fio, year, profession);

            try {
                UserEntytiManager.insert(user);
            } catch (SQLException ex) {
                ex.printStackTrace();
            }

            dispose();
            new InfoForm();

        });


        setVisible(true);
    }
}
