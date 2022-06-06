package com.company.uhl;

import javax.swing.table.AbstractTableModel;
import java.lang.reflect.Field;
import java.util.List;

public class CustomTableModel<T> extends AbstractTableModel {
    private Class<T>  cls;
    private String[] name;
    private List<T> rows;

    public CustomTableModel(Class<T> cls, String[] name, List<T> rows) {
        this.cls = cls;
        this.name = name;
        this.rows = rows;
    }

    @Override
    public int getRowCount() {

        return rows.size();
    }

    @Override
    public String getColumnName(int column) {
        return name[column];
    }

    @Override
    public int getColumnCount() {
        return cls.getDeclaredFields().length;
    }

    public List<T> getRows() {
        return rows;
    }

    public void setRows(List<T> rows) {
        this.rows = rows;
    }

    @Override
    public Class<?> getColumnClass(int columnIndex) {
        return cls.getDeclaredFields()[columnIndex].getType();
    }

    @Override
    public Object getValueAt(int i, int i1) {

        try {
            T t = rows.get(i);
            Field field = cls.getDeclaredFields()[i1];
            field.setAccessible(true);
            return field.get(t);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
            return "ERR";
        }


    }
}
