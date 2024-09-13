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
        if(event.getSource() == btnGuardar){
            if(tfId.getText().isBlank()){
                agregarCategoria();
            }else{
                editarCategoria();
            }
        }else if(event.getSource() == btnLimpiar){
            limpiarForm();
        }else if(event.getSource() == btnEliminar){
            eliminarCategoria();
        }
    }

    public void cargarDatos(){
        tblCategorias.setItems(listarCategorias());
        colId.setCellValueFactory(new PropertyValueFactory<Categoria,Long>("id"));
        colNombre.setCellValueFactory(new PropertyValueFactory<Categoria,String>("nombreCategoria"));
    }
    
    public ObservableList<Categoria> listarCategorias(){

        return FXCollections.observableList(categoriaService.listarCategoria());
    }
    
    public void limpiarForm(){
        tfId.clear();
        tfNombre.clear();
    }


    public void agregarCategoria(){
        Categoria categoria = new Categoria();
        categoria.setNombreCategoria(tfNombre.getText());
        categoriaService.guardarCategoria(categoria);
        cargarDatos();
    }

    public void cargarFormEditar(){
        Categoria categoria = (Categoria)tblCategorias.getSelectionModel().getSelectedItem();
        if(categoria != null){
            tfId.setText(Long.toString(categoria.getId()));
            tfNombre.setText(categoria.getNombreCategoria());
        }
    }
    public void editarCategoria(){
        Categoria categoria = categoriaService.busCategoriaPorId(Long.parseLong(tfId.getText()));
        categoria.setNombreCategoria(tfNombre.getText());
        categoriaService.guardarCategoria(categoria);
        cargarDatos();
    }

    public void eliminarCategoria(){
        Categoria categoria = categoriaService.busCategoriaPorId(Long.parseLong(tfId.getText()));
        categoriaService.eliminarCategoria(categoria);
        cargarDatos();
    }
}
