package controller;

import annotation.MyAnnotation;
import annotation.Named;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import model.Bean2;
import table.TableData;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.Date;
import java.text.SimpleDateFormat;

public class MainController {

    @FXML
    private TextField tf_className;

    @FXML
    private TextField fieldName;

    @FXML
    private TextField value;

    @FXML
    private ComboBox<Object> objectComboBox;

    @FXML
    private TableView<TableData> tableFields;

    @FXML
    @Named(value="No")
    private TableColumn<TableData, Integer> col_id;

    @FXML
    @Named(value="Type")
    private TableColumn<TableData, String> col_type;

    @FXML
    @Named(value="Name")
    private TableColumn<TableData, String> col_name;

    @FXML
    @Named(value="Value")
    private TableColumn<TableData, String> col_value;


    private ObservableList<TableData> OTableFields = FXCollections.observableArrayList();
    private ObservableList<Object> objectList = FXCollections.observableArrayList();

    Object classInstance = null;

    public void initialize() {
        col_id.setCellValueFactory(new PropertyValueFactory<>("id"));
        col_name.setCellValueFactory(new PropertyValueFactory<>("name"));
        col_type.setCellValueFactory(new PropertyValueFactory<>("type"));
        col_value.setCellValueFactory(new PropertyValueFactory<>("value"));
        tableFields.setItems(OTableFields);
        Annotation();
    }

    public void Annotation() {
        Field[] fieldsController = this.getClass().getDeclaredFields();
        Arrays.stream(fieldsController)
                .filter(field -> field.isAnnotationPresent(Named.class) && field.getType().equals(TableColumn.class))
                .forEach(field -> {
                    Named named = field.getAnnotation(Named.class);
                    try {
                        TableColumn tableColumn = (TableColumn) field.get(this);
                        tableColumn.setText(named.value());
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                });
    }

    public void CreateListFields(Object object) {
        OTableFields.clear();
        System.out.println("\nListowanie pól\n");
        int counter = 0;
        try {
            Field fields[] = object.getClass().getDeclaredFields();
            for (Field field : fields) {
                counter++;
                field.setAccessible(true);
                OTableFields.add(new TableData(counter, field.getGenericType().getTypeName(), field.getName(), field.get(object).toString()));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        tableFields.setItems(OTableFields);

    }

    public void EditField(Object object, String MethodName, String value, String type) {
        MethodName = "set" + MethodName.substring(0, 1).toUpperCase() + MethodName.substring(1);

        if (type.equals("java.lang.String")) {
            try {
                Method m = object.getClass().getMethod(MethodName, String.class);
                System.out.println(m.invoke(object, value));
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        else if (type.equals("double")) {
            try {
                Method m = object.getClass().getMethod(MethodName, double.class);
                System.out.println(m.invoke(object, Double.parseDouble(value)));
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        else if (type.equals("int")) {
            try {
                Method m = object.getClass().getMethod(MethodName, int.class);
                System.out.println(m.invoke(object, Integer.parseInt(value)));
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        else if (type.equals("java.lang.Boolean")) {
            try {
                Method m = object.getClass().getMethod(MethodName, Boolean.class);
                System.out.println(m.invoke(object, Boolean.valueOf(value)));
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        else if (type.equals("java.lang.Enum")) {
            try {
                Method m = object.getClass().getMethod(MethodName, Enum.class);
                System.out.println(m.invoke(object, Enum.valueOf(model.BeanEnum.class, value)));
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        else if (type.equals("java.util.Date")) {
            try {
                Method m = object.getClass().getMethod(MethodName, Date.class);
                Date date = new SimpleDateFormat("dd/MM/yyyy").parse(value);
                System.out.println(m.invoke(object, date));
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

    }

    public String GetFieldType(Object object, String name) {
        Field field = null;
        String type = null;
        try {
            field = object.getClass().getDeclaredField(name);
            System.out.println(field.getGenericType().getTypeName());
            type = field.getGenericType().getTypeName();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return type;

    }

    public void ChangeFieldValue() {
        Object selectedObject = objectComboBox.getSelectionModel().getSelectedItem();
        String type = GetFieldType(selectedObject, fieldName.getText());
        EditField(selectedObject, fieldName.getText(), value.getText(), type);
        CreateListFields(selectedObject);
    }

    public void ChooseObject() {
        Object objectName = objectComboBox.getSelectionModel().getSelectedItem();
        CreateListFields(objectName);
    }

    public void DeleteObject() {
        Object objectName = objectComboBox.getSelectionModel().getSelectedItem();
        objectList.remove(objectName);
        objectComboBox.setItems(objectList);
    }


    public void CreateObject() {
        String className = tf_className.getText();
        try {
            Class cls = Class.forName(className);
            classInstance = (Object) cls.newInstance();
            System.out.println("instance: " + classInstance.getClass());
        } catch (Exception e) {
            e.printStackTrace();
        }
        //zapisanie obiektu do listy (wyświetlanie w ComboBoxie)
        objectList.add(classInstance);
        objectComboBox.setItems(objectList);
        objectComboBox.getSelectionModel().select(objectList.get(objectList.size()-1));
        Object objectName = objectComboBox.getSelectionModel().getSelectedItem();
        CreateListFields(objectName);
    }


    public static void main(String[] args) {

    }
}
