package com.robbinsisimit.webapp.biblioteca.controller.FXController;
import java.net.URL;
import java.util.ResourceBundle;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.robbinsisimit.webapp.biblioteca.model.Empleado;
import com.robbinsisimit.webapp.biblioteca.service.EmpleadoService;
import com.robbinsisimit.webapp.biblioteca.system.Main;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import lombok.Setter;

@Component
public class EmpleadoControllerFX implements Initializable {

    @FXML
    TextField tfId, tfNombre, tfApellido,tfTelefono, tfDireccion, tfDpi;
    @FXML
    Button btnGuardar, btnLimpiar, btnEliminar,btnRegresar;
    @FXML
    TableView tblEmpleados;
    @FXML
    TableColumn colId, colNombre, colApellido, colTelefono, colDireccion, colDpi;

    @Setter
    private Main stage;

    @Autowired
    EmpleadoService empleadoService;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        cargarDatos();
    }

    public void handleButtonAction(ActionEvent event){
        if(event.getSource() == btnGuardar){
            if(tfId.getText().isBlank()){
                agregarEmpleado();
            }else{
                editarEmpleado();
            }
        }else if(event.getSource() == btnLimpiar){
            limpiarForm();
        }else if(event.getSource() == btnEliminar){
            eliminarEmpleado();
        }else if(event.getSource() == btnRegresar){
            stage.indexView();
        }
    }

    public void cargarDatos(){
        tblEmpleados.setItems(listarEmpleados());
        colId.setCellValueFactory(new PropertyValueFactory<Empleado,Long>("Id"));
        colNombre.setCellValueFactory(new PropertyValueFactory<Empleado,String>("nombre"));
        colApellido.setCellValueFactory(new PropertyValueFactory<Empleado,String>("apellido"));
        colTelefono.setCellValueFactory(new PropertyValueFactory<Empleado,String>("telefono"));
        colDireccion.setCellValueFactory(new PropertyValueFactory<Empleado,String>("direccion"));
        colDpi.setCellValueFactory(new PropertyValueFactory<Empleado,String>("DPI"));
    }

    public void cargarForm(){
        Empleado empleado = (Empleado)tblEmpleados.getSelectionModel().getSelectedItem();
        if (empleado != null) {
            tfId.setText(Long.toString(empleado.getId()));
            tfNombre.setText(empleado.getNombre());
            tfApellido.setText(empleado.getApellido());
            tfTelefono.setText(empleado.getTelefono());
            tfDireccion.setText(empleado.getDireccion());
            tfDpi.setText(empleado.getDPI());
        }
    }


    public void limpiarForm(){
        tfId.clear();
        tfNombre.clear();
        tfApellido.clear();
        tfTelefono.clear();
        tfDireccion.clear();
        tfDpi.clear();
    }

    public ObservableList<Empleado> listarEmpleados(){
        return FXCollections.observableList(empleadoService.listarEmpleado());
    }

    public void agregarEmpleado(){
        Empleado empleado = new Empleado();
        empleado.setNombre(tfNombre.getText());
        empleado.setApellido(tfApellido.getText());
        empleado.setTelefono(tfTelefono.getText());
        empleado.setDireccion(tfDireccion.getText());
        empleado.setDPI(tfDpi.getText());
        empleadoService.guardarEmpleado(empleado);
        cargarDatos();
    }

    public void editarEmpleado(){
        Empleado empleado = empleadoService.buscarEmpleadoPorId(Long.parseLong(tfId.getText()));
        empleado.setNombre(tfNombre.getText());
        empleado.setApellido(tfApellido.getText());
        empleado.setTelefono(tfTelefono.getText());
        empleado.setDireccion(tfDireccion.getText());
        empleado.setDPI(tfDpi.getText());
        empleadoService.guardarEmpleado(empleado);
        cargarDatos();
    }

    public void eliminarEmpleado(){
        Empleado empleado = empleadoService.buscarEmpleadoPorId(Long.parseLong(tfId.getText()));
        empleadoService.eliminarEmpleado(empleado);
        cargarDatos();
    }

}
