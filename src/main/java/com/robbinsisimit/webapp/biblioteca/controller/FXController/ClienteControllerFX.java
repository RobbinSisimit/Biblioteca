package com.robbinsisimit.webapp.biblioteca.controller.FXController;
import java.net.URL;
import java.util.ResourceBundle;

import org.springframework.beans.factory.annotation.Autowired;

import com.robbinsisimit.webapp.biblioteca.model.Cliente;
import com.robbinsisimit.webapp.biblioteca.service.ClienteService;
import com.robbinsisimit.webapp.biblioteca.system.Main;
import org.springframework.stereotype.Component;

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
public class ClienteControllerFX implements Initializable {
    
    @FXML
    TextField tfDPI, tfNombre, tfApellido, tfTelefono;
    @FXML
    Button btnGuardar, btnLimpiar, btnEliminar, btnRegresar;
    @FXML
    TableView tblClientes;
    @FXML
    TableColumn colDPI, colNombre, colApellido, colTelefono;

    @Setter
    private Main stage;

    @Autowired
    ClienteService clienteService ;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
       cargarDatos();
    }

    public void handleButtonAction(ActionEvent event){
        if(event.getSource() == btnGuardar){
            if(tfDPI.getText().isBlank()){
                agregarCliente();
            }else{
                editarCliente();
            }
        }else if(event.getSource() == btnLimpiar){
            limpiarForm();
        }else if(event.getSource() == btnEliminar){
            eliminarCliente();
        }else if(event.getSource() == btnRegresar){
            stage.indexView();

        }
    }

    public void cargarDatos(){
        tblClientes.setItems(listarClientes());
        colDPI.setCellValueFactory(new PropertyValueFactory<Cliente, Long>("DPI"));
        colNombre.setCellValueFactory(new PropertyValueFactory<Cliente, String>("nombreCliente"));
        colApellido.setCellValueFactory(new PropertyValueFactory<Cliente, String>("apellidoCliente"));
        colTelefono.setCellValueFactory(new PropertyValueFactory<Cliente, String>("telefonoCliente"));
    }

    public ObservableList<Cliente> listarClientes(){
        return FXCollections.observableList(clienteService.listarCliente());
    }

    public void limpiarForm(){
        tfDPI.clear();
        tfNombre.clear();
        tfApellido.clear();
        tfTelefono.clear();
    }    

    public void agregarCliente(){
        Cliente cliente = new Cliente();
        cliente.setNombreCliente(tfNombre.getText());
        cliente.setApellidoCliente(tfApellido.getText());
        cliente.setTelefonoCliente(tfTelefono.getText());
        clienteService.guardarCliente(cliente);
        cargarDatos();
    }

    public void cargarFormEditar(){
        Cliente cliente = (Cliente)tblClientes.getSelectionModel().getSelectedItem();
        if(cliente != null){
            tfDPI.setText(Long.toString(cliente.getDPI()));
            tfNombre.setText(cliente.getNombreCliente());
            tfApellido.setText(cliente.getApellidoCliente());
            tfTelefono.setText(cliente.getTelefonoCliente());
        }
    }

    public void editarCliente(){
        Cliente cliente = clienteService.buscarClientePorId(Long.parseLong(tfDPI.getText()));
        cliente.setNombreCliente(tfNombre.getText());
        cliente.setApellidoCliente(tfApellido.getText());
        cliente.setTelefonoCliente(tfTelefono.getText());
        clienteService.guardarCliente(cliente);
        cargarDatos();
    }

    public void eliminarCliente(){
        Cliente cliente = clienteService.buscarClientePorId(Long.parseLong(tfDPI.getText()));
        clienteService.eliminarCliente(cliente);
        cargarDatos();
    }

}
