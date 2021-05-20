package model;

public class TableData {
    private int id;
    private String type;
    private String name;
    private String value;

    public TableData(int id, String type, String name, String value) {
        this.id = id;
        this.type = type;
        this.name = name;
        this.value = value;
    }

    public TableData(int id, String type, String name) {
        this.id = id;
        this.type = type;
        this.name = name;
    }

    public TableData() {
        this.id = 1;
        this.type = "unknown";
        this.name = "name";
        this.value = "1";
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}
