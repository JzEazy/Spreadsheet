package UI;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;
import java.awt.print.Book;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.filechooser.FileFilter;
import javax.swing.table.TableCellEditor;
import javax.xml.stream.XMLStreamException;


public class SpreadsheetGUI {

    private JFrame frame;
    private JTable table;
    private DefaultTableModel model;



    public SpreadsheetGUI() {
        frame = new JFrame("Awsome Spreadsheet!");

        model = new DefaultTableModel(0, 0) {
            @Override
            public String getColumnName(int column) {
                return columnName(column);
            }

            @Override
            public boolean isCellEditable(int row, int column) {
                // Expand table if editing the last row or column
                if (row > (getRowCount() - 3)) {
                    addRow(new Object[getColumnCount()]);
                }
                if (column > (getColumnCount() - 3)) {
                    addColumn(columnName(getColumnCount()));
                }
                return true;
            }
        };

        table = new JTable(model);
        initializeSpreadsheet(20, 10); // Initially 20 rows, 10 columns

        JScrollPane scrollPane = new JScrollPane(table);
        JList<String> rowHeader = createRowHeader(20);
        scrollPane.setRowHeaderView(rowHeader);

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.add(scrollPane, BorderLayout.CENTER);
        frame.setSize(800, 600);
    }

    public void show() {
        frame.setVisible(true);
    }

    private void initializeSpreadsheet(int rows, int columns) {
        for (int i = 0; i < columns; i++) {
            model.addColumn("");
        }
        for (int i = 0; i < rows; i++) {
            model.addRow(new Object[columns]);
        }
        table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
        table.setRowHeight(25);
        table.getTableHeader().setReorderingAllowed(false);
    }

    private String columnName(int index) {
        StringBuilder columnName = new StringBuilder();
        while (index >= 0) {
            columnName.insert(0, (char) ('A' + index % 26));
            index = (index / 26) - 1;
        }
        return columnName.toString();
    }

    //TODO FIX
    private JList<String> createRowHeader(int rows) {
        String[] headers = new String[rows];
        for (int i = 0; i < rows; i++) {
            headers[i] = String.valueOf(i + 1);
        }
        JList<String> rowHeader = new JList<>(headers);
        rowHeader.setFixedCellWidth(50);
        rowHeader.setFixedCellHeight(table.getRowHeight());
        rowHeader.setCellRenderer(new RowHeaderRenderer());
        return rowHeader;
    }

    static class RowHeaderRenderer extends JLabel implements ListCellRenderer<String> {
        RowHeaderRenderer() {
            setOpaque(true);
            setHorizontalAlignment(CENTER);
            setBorder(BorderFactory.createMatteBorder(0, 0, 1, 1, Color.GRAY));
        }

        @Override
        public Component getListCellRendererComponent(JList<? extends String> list, String value, int index, boolean isSelected, boolean cellHasFocus) {
            setText((value != null) ? value : "");
            return this;
        }
    }
}
