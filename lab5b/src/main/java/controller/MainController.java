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
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.text.SimpleDateFormat;
import java.util.List;

public class MainController {

    @FXML
    private TextField tf_className;

    @FXML
    private TextField fieldName;

    @FXML
    private TextField value;

    @FXML
    private ComboBox<Class> ClassComboBox;

    @FXML
    private TableView<TableData> tableFields;

    @FXML
    private TableColumn<TableData, Integer> col_id;

    @FXML
    private TableColumn<TableData, String> col_type1;

    @FXML
    private TableColumn<TableData, String> col_type2;

    @FXML
    private TableColumn<TableData, String> col_type3;

    @FXML
    private TableColumn<TableData, String> col_type4;

    private ObservableList<TableData> OTableFields = FXCollections.observableArrayList();
    private ObservableList<Object> objectList = FXCollections.observableArrayList();
    private ObservableList<Object> objectList2 = FXCollections.observableArrayList();
    private ObservableList<Object> objectList3 = FXCollections.observableArrayList();
    private ObservableList<Class> ClassList = FXCollections.observableArrayList();

    private ObservableList<String> AdnotationList = FXCollections.observableArrayList();

    Object classInstance = null;

    public void initialize() {
        try {
            Class class1 = Class.forName("model.Bean");
            ClassList.add(class1);
            Class class2 = Class.forName("model.Bean2");
            ClassList.add(class2);
            Class class3 = Class.forName("model.Bean3");
            ClassList.add(class3);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        ClassComboBox.setItems(ClassList);

        col_id.setCellValueFactory(new PropertyValueFactory<>("object"));
        col_type1.setCellValueFactory(new PropertyValueFactory<>("type1"));
        col_type2.setCellValueFactory(new PropertyValueFactory<>("type2"));
        col_type3.setCellValueFactory(new PropertyValueFactory<>("type3"));
        col_type4.setCellValueFactory(new PropertyValueFactory<>("type4"));
        tableFields.setItems(OTableFields);
        //Annotation();
    }


    public void CreateListFields(ObservableList<Object> objectList) {
        OTableFields.clear();
        System.out.println("\nListowanie pól\n");
        Object object;
        for (int i = 0; i < objectList.size(); i++) {
            object = objectList.get(i);
            try {
                Field fields[] = object.getClass().getDeclaredFields();
                ArrayList<String> pola = new ArrayList<String>();
                for (Field field : fields) {
                    field.setAccessible(true);
                    pola.add(field.get(object).toString());
                    System.out.println(field.get(object));
                }
                System.out.println(pola.get(2));
                OTableFields.add(new TableData(object, pola.get(0), pola.get(1), pola.get(2), pola.get(3)));
            } catch (Exception e) {
                e.printStackTrace();
            }
            tableFields.setItems(OTableFields);
        }
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
        Object selectedObject = tableFields.getSelectionModel().getSelectedItem().getObject();
        String type = GetFieldType(selectedObject, fieldName.getText());
        EditField(selectedObject, fieldName.getText(), value.getText(), type);
        CreateListFields(CheckClass(selectedObject.getClass()));
    }

    public void DeleteObject() {
        Object objectName = tableFields.getSelectionModel().getSelectedItem().getObject();
        CheckClass(objectName.getClass()).remove(objectName);
        CreateListFields(CheckClass(objectName.getClass()));
    }
    
    public ObservableList<Object> CheckClass(Class cls) {
        if(cls.equals(ClassList.get(0))) {
            return objectList;
        }
        else if(cls.equals(ClassList.get(1))) {
            return objectList2;
        }
        else if(cls.equals(ClassList.get(2))) {
            return objectList3;
        }
        return null;
    }

    public void ChooseObject() {
        Class className = ClassComboBox.getSelectionModel().getSelectedItem();
        Annotation(className);
        CreateListFields(CheckClass(className));
    }

    public void Annotation(Class className) {
        AdnotationList.clear();
        Field[] fieldsController = className.getDeclaredFields();
        Arrays.stream(fieldsController)
                .filter(field -> field.isAnnotationPresent(Named.class))
                .forEach(field -> {
                    Named named = field.getAnnotation(Named.class);
                    try {
                        AdnotationList.add(named.value());
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                });
        col_type1.setText(AdnotationList.get(0));
        col_type2.setText(AdnotationList.get(1));
        col_type3.setText(AdnotationList.get(2));
        col_type4.setText(AdnotationList.get(3));
    }


    public void CreateObject() {
        Class className = ClassComboBox.getSelectionModel().getSelectedItem();
        try {
            classInstance = (Object) className.newInstance();
            System.out.println("instance: " + classInstance.getClass());
        } catch (Exception e) {
            e.printStackTrace();
        }
        CheckClass(classInstance.getClass()).add(classInstance);
        CreateListFields(CheckClass(classInstance.getClass()));
    }


    public static void main(String[] args) {

    }
}
