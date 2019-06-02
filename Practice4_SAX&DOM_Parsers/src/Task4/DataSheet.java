package Task4;

import java.util.ArrayList;

public class DataSheet {
    private String name;
    private ArrayList<Data> date = new ArrayList<>();
    private DataSheet datasheet;

    public void setName(String name) {
        this.name = name;
    }

    public void setDate(Data date) {
        this.date.add(date);
    }

    public String getName() {
        return name;
    }

    public Data getDate() {
        return date.get(date.size()-1);
    }

    public Data getDate(int i) {
        return date.get(i);
    }

    public void CopyDataSheet(DataSheet ds) {
        this.name = ds.name;
        this.date = ds.date;
        this.datasheet = ds.datasheet;
    }

    public DataSheet getDataSheet() {
        return datasheet;
    }

    public void setDataSheet(DataSheet dsh) {
        this.datasheet = dsh;
    }


    public String getArray() {
        String str = "";
        for (Data d : date) {
            str += d.toString() + "\n\t\t   ";
        }
        return str;
    }

    public int getSize() {
        return this.date.size();
    }

    public String toString() {
       return "\t\"" + this.getName() + "\": {\n\t\t   " + getArray() + "}";
    }
}
