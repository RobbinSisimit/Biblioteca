package com.robbinsisimit.webapp.biblioteca.controller.FXController;

import java.net.URL;
import java.util.ResourceBundle;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.robbinsisimit.webapp.biblioteca.model.Categoria;
import com.robbinsisimit.webapp.biblioteca.service.CategoriaService;
import com.robbinsisimit.webapp.biblioteca.system.Main;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.MenuItem;
import lombok.Setter;


@Component
public class IndexController implements Initializable{
    @Setter
    private Main stage;
    @FXML
    MenuItem btnCategoria,btnClientes,btnLibros,btnEmpleados,btnPrestamo;

    @Override
    public void initialize(URL url, ResourceBundle resources) {
        
    }
    @FXML
    public void handleButtonAction(ActionEvent event){
        if(event.getSource() == btnCategoria){
            stage.categoriaView();
        }else if(event.getSource() == btnClientes){
            stage.clienteView();
        }else if(event.getSource() == btnEmpleados){
            stage.empleadoView();
        }else if(event.getSource() == btnLibros){
            stage.libroView();
        }else if(event.getSource() == btnPrestamo){
            stage.prestamoView();
            
        }
    }
    
    
}
