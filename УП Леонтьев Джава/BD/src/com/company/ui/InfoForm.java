package com.company.ui;

import com.company.entity.UserEntity;
import com.company.manager.UserEntytiManager;
import com.company.uhl.BaseForm;
import com.company.uhl.CustomTableModel;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.Array;
import java.sql.SQLException;
import java.util.*;
import java.util.List;

public class InfoForm extends BaseForm {
    private JPanel outForm;
    private JButton ExitButton;
    private JButton redactButton;
    private JTable clientsTable;
    private JComboBox AuthorCo;
    private JComboBox yearFilterBox;
    private JButton idSortButton;

    private boolean idSort = true;

    private CustomTableModel<UserEntity> model;

    public InfoForm() {
        super(400,200);
        setContentPane(outForm);

        initTables();
        initButtons();
        initBox();

        setVisible(true);
    }

    private void initBox() {
        AuthorCo.addItem("Все");
        try {
            List<UserEntity> list = UserEntytiManager.selectAll();
            Set<String> authors = new HashSet<>();
            for(UserEntity a : list) {
                authors.add(a.getFio());
            }
            for(String b : authors) {
                AuthorCo.addItem(b);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        yearFilterBox.addItem("Все, кто ещё не здох");
        yearFilterBox.addItem("1950-1960");
        yearFilterBox.addItem("1960-1970");
        yearFilterBox.addItem("1970-1980");
        yearFilterBox.addItem("1980-2022");

        AuthorCo.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent itemEvent) {
                if(itemEvent.getStateChange() == ItemEvent.SELECTED){
                    applyFilters();
                }
            }
        });

        yearFilterBox.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent itemEvent) {
                if(itemEvent.getStateChange() == ItemEvent.SELECTED){
                    applyFilters();
                }
            }
        });



    }

    private void applyFilters()
    {
        try {
            List<UserEntity> list = UserEntytiManager.selectAll();

            if(AuthorCo.getSelectedIndex() != 0) {
                list.removeIf(b -> !b.getFio().equals(AuthorCo.getSelectedItem()));
            }

            int index = yearFilterBox.getSelectedIndex();
            if(index == 2) {
                list.removeIf(b -> b.getYearOfBirth() > 1950);
            } else if(index == 3) {
                list.removeIf(b -> b.getYearOfBirth() <= 1950 || b.getYearOfBirth() > 1960);
            } else if(index == 4) {
                list.removeIf(b -> b.getYearOfBirth() <= 1960 || b.getYearOfBirth() > 1970);
            } else if(index == 5) {
                list.removeIf(b -> b.getYearOfBirth() <= 1970 || b.getYearOfBirth() > 1980);
            } else if(index == 6) {
                list.removeIf(b -> b.getYearOfBirth() <= 2022);
            }


            idSort = true;

            model.setRows(list);
            model.fireTableDataChanged();

        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    private void initTables() {
        // TODO: place custom component creation code here
        clientsTable.getTableHeader().setReorderingAllowed(false);

        try {
            model = new CustomTableModel<>(
                    UserEntity.class,
                    new String[] {"ID", "ФИО", "Год рождения", "Профессия"},
                    UserEntytiManager.selectAll()
            );
        } catch (SQLException e) {
            e.printStackTrace();
        }

        clientsTable.setModel(model);
        clientsTable.addMouseListener(new MouseAdapter() {
                                          @Override
                                          public void mouseClicked(MouseEvent e) {
                                              if(e.getClickCount() == 2) {
                                                  int row = clientsTable.rowAtPoint(e.getPoint());
                                                  if (row != -1) {
                                                      dispose();
                                                      new EditForm(model.getRows().get(row));
                                                  }
                                              }
                                          }
                                      }
        );
    }

    private void initButtons()
    {

        ExitButton.addActionListener(e -> {
            dispose();
            new TestForm();
        });

        idSortButton.addActionListener(e -> {
            Collections.sort(model.getRows(), new Comparator<UserEntity>() {
                @Override
                public int compare(UserEntity o1, UserEntity o2) {
                    if(idSort) {
                        return Integer.compare(o2.getId(), o1.getId());
                    } else {
                        return Integer.compare(o1.getId(), o2.getId());
                    }
                }
            });
            idSort = !idSort;
            model.fireTableDataChanged();
        });

    }
}
