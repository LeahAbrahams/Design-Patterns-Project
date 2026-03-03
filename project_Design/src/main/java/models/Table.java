package models;

public class Table {

    private String name;
    private Schema schema;
    private final java.util.List<Row> rows = new java.util.ArrayList<>();

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Schema getSchema() {
        return schema;
    }
   
    // public void setSchema(Schema schema) {
    //     this.schema = schema;
    // }
    public java.util.List<Row> getRows() {
        return rows;
    }

    public void   addColumn(Column column) {
         schema.addColumn(column);
    }

    public boolean addRow(Row row) {
      return  rows.add(row);
        
    }

    public void removeRow(Row row) {
        if (rows.remove(row)) {
            System.out.println("Row removed from table " + name);
        } else {
            System.out.println("Row not found in table " + name);
        }
    }

}
