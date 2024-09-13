package com.robbinsisimit.webapp.biblioteca.controller.FXController;
import java.net.URL;
import java.util.ResourceBundle;

import org.springframework.beans.factory.annotation.Autowired;

import com.robbinsisimit.webapp.biblioteca.model.Categoria;
import com.robbinsisimit.webapp.biblioteca.service.CategoriaService;
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
public class CategoriaControllerFX implements Initializable {

    
    @FXML
    TextField tfId, tfNombre;
    @FXML
    Button btnGuardar,btnLimpiar,btnEliminar;
    @FXML
    TableView tblCategorias;
    @FXML
    TableColumn colId,colNombre;

    @Setter
    private Main stage;

    @Autowired
    CategoriaService categoriaService;


    @Override
    public void initialize(URL location, ResourceBundle resources) {
       cargarDatos();
    }

    public void handleButtonAction(ActionEvent event) {
    // Código del evento
    }

    public void cargarDatos(){
        tblCategorias.setItems(listarCategorias());
        colId.setCellValueFactory(new PropertyValueFactory<Categoria,Long>("id"));
        colNombre.setCellValueFactory(new PropertyValueFactory<Categoria,String>("nombreCategoria"));
    }

    public ObservableList<Categoria> listarCategorias(){

        return FXCollections.observableList(categoriaService.listarCategoria());
    }


}
