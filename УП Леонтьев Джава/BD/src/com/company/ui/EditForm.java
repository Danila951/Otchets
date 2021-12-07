package com.company.ui;


import com.company.entity.UserEntity;
import com.company.manager.UserEntytiManager;
import com.company.uhl.BaseForm;
import com.company.uhl.DialogUhl;
import com.company.ui.InfoForm;

import javax.swing.*;
import java.sql.SQLException;

public class EditForm extends BaseForm {
    private JPanel mainPanel;
    private JTextField fioField;
    private JTextField profField;
    private JButton saveButton;
    private JSpinner yearSpinner;
    private JComboBox typeBox;
    private JButton vievButton;
    private JTextField idField;
    private JButton deleteButton;


    private UserEntity user;


    public EditForm(UserEntity user) {
        super(500,300);
        this.user = user;
        setContentPane(mainPanel);


        initFields();
        InitButton();


        setVisible(true);
    }

    private void initFields()
    {
        idField.setEditable(false);
        idField.setText(String.valueOf(user.getId()));
        fioField.setText(user.getFio());
        profField.setText(user.getProfession());
        yearSpinner.setValue(user.getYearOfBirth());

    }

    private void InitButton() {
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

            user.setFio(fio);
            user.setProfession(profession);
            user.setYearOfBirth(year);

            try {
                UserEntytiManager.update(user);
            } catch (SQLException ex) {
                ex.printStackTrace();
                return;
            }

            DialogUhl.showInfo(this, "Книжка успешно отредактирована");
            dispose();
            new InfoForm();

        });



        vievButton.addActionListener(e -> {
            dispose();
            new InfoForm();
        });

        deleteButton.addActionListener(e-> {
            try {
                UserEntytiManager.delete(user);
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
            dispose();
            new InfoForm();
        });


    }

    private void createUIComponents() {
        // TODO: place custom component creation code here
    }
}
