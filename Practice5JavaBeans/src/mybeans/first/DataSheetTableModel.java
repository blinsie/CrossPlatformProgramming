package mybeans.first;

import xmlpac.Data;
import xmlpac.DataSheet;

import javax.swing.event.TableModelListener;
import javax.swing.table.TableModel;
import java.util.*;

public class DataSheetTableModel implements TableModel {

    private static final long serialVersionUID = 1L;

    private int columnCount = 3;
    private int rowCount = 1;
    private DataSheet dataSheet = new DataSheet();
    String[] columnNames = {"Date", "X Value", "Y Value"};

    // список слушателей
    private ArrayList<DataSheetChangeListener> listenerList = new ArrayList<DataSheetChangeListener>();

    private Set<TableModelListener> listeners = new HashSet<TableModelListener>();

    // один универсальный объект-событие
    private DataSheetChangeEvent event = new DataSheetChangeEvent(this);

    public DataSheetTableModel(List<Data> date) {
        dataSheet.setDate((ArrayList<Data>) date);
    }

    // метод, присоединяющий слушателя события
    public void addDataSheetChangeListener(DataSheetChangeListener l) {
        listenerList.add(l);
    }

    // метод, отсоединяющий слушателя события
    public void removeDataSheetChangeListener(DataSheetChangeListener l) {
        listenerList.remove(l);
    }

    // метод, оповещающий зарегистрированных слушателей о событии
    protected void fireDataSheetChange() {
        Iterator<DataSheetChangeListener> i = listenerList.iterator();
         while ( i.hasNext() )
             (i.next()).dataChanged(event);
    }

    public DataSheet getDataSheet() {
        return dataSheet;
    }

    public void setDataSheet(DataSheet dataSheet) {
        this.dataSheet = dataSheet;
        if (dataSheet != null)
          rowCount = this.dataSheet.size();
        else rowCount = 1;
        fireDataSheetChange();
    }

    @Override
    public int getColumnCount() {
        return columnCount;
    }

    @Override
    public int getRowCount() {
        return rowCount;
    }

    @Override
    public String getColumnName(int columnIndex) {
        return columnNames[columnIndex];
    }

    @Override
    public Class<?> getColumnClass(int columnIndex) {
        return String.class;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        if (dataSheet.size() != 0) {
            Data bean = dataSheet.getDateItem(rowIndex);
            switch (columnIndex) {
                case 0:
                    return bean.getDate();
                case 1:
                    return bean.getX();
                case 2:
                    return bean.getY();
            }
        }
        return "";
    }

    @Override
    public void setValueAt(Object value, int rowIndex, int columnIndex) {
        try {
            double d;
            if (dataSheet != null) {
                if (columnIndex == 0) {
                    dataSheet.getDateItem(rowIndex).setDate((String) value);
                } else if (columnIndex == 1) {
                    d = Double.parseDouble((String) value);
                    dataSheet.getDateItem(rowIndex).setX(d);
                } else if (columnIndex == 2) {
                    d = Double.parseDouble((String) value);
                    dataSheet.getDateItem(rowIndex).setY(d);
                }
            }
            fireDataSheetChange();
        } catch (Exception ex) {}
    }

    @Override
    public void addTableModelListener(TableModelListener l) {
        listeners.add(l);
    }

    @Override
    public void removeTableModelListener(TableModelListener l) {
        listeners.remove(l);
    }

    @Override
    public boolean isCellEditable(int rowIndex, int columnIndex) {
        return columnIndex >= 0;
    }

    public void setRowCount(int rowCount) {
        if (rowCount > 0)
            this.rowCount = rowCount;

    }

}
