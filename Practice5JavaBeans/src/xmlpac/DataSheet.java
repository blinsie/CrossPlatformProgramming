package xmlpac;

import java.util.ArrayList;

public class DataSheet {
    private String name;
    private static ArrayList<Data> date;

    public DataSheet() {
        this.name = "";
        this.date = new ArrayList<>();
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setDateItem(Data date) {
        this.date.add(date);
    }

    public Data getDateItem(int i) {
        return this.date.get(i);
    }

    public void setDate(ArrayList<Data> date) {
        this.date = date;
    }

    public static ArrayList<Data> getDate() {
        return date;
    }

    public void removeDataItem(int i) {
        this.date.remove(i);
    }

    public String getArray() {
        String str = "";
        for (Data d : date) {
            str += d.toString() + "\n\t\t   ";
        }
        return str;
    }

    public void CopyDataSheet(DataSheet ds) {
        this.name = ds.name;
        this.date = date;
    }

    public String toString() {
        return "\t\"" + this.getName() + "\": {\n\t\t   " + getArray() + "}";
    }

    public int size() {
        return this.date.size();
    }

}
