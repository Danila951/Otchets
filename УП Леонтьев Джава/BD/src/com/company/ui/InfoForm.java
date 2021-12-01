package com.company.ui;

import com.company.entity.UserEntity;
import com.company.manager.UserEntytiManager;
import com.company.uhl.BaseForm;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.sql.Array;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class InfoForm extends BaseForm {
    private JPanel outForm;
    JTable clientsTable;
    private JButton ExitButton;
    private JButton redactButton;
    DefaultTableModel table;
    public InfoForm() {
        super(400,200);
        setContentPane(outForm);
        setVisible(true);

        ExitButton.addActionListener(e -> {
            dispose();
            new TestForm();
        });

    }

    private void createUIComponents() {
        // TODO: place custom component creation code here
        String[] name = {"ФИО", "Год рождения", "Профессия"};

        List<UserEntity> cells = new ArrayList<>();


        try {
            cells = UserEntytiManager.selectAll();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        table = new DefaultTableModel();
        table.setColumnIdentifiers(name);
        for (UserEntity user : cells) {
            String fio = user.getFio();
            int year = user.getYearOfBirth();
            String profession = user.getProfession();

            Object[] gg = {fio, year, profession};

            table.addRow(gg);
        }



        clientsTable = new JTable(table);

    }
}
