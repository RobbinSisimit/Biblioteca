package com.robbinsisimit.webapp.biblioteca.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.robbinsisimit.webapp.biblioteca.model.Empleado;
import com.robbinsisimit.webapp.biblioteca.repository.EmpleadoRepository;
@Service
public class EmpleadoService implements IEmpleadoService{
    @Autowired
    private EmpleadoRepository empleadoRepository;
    @Override
    public List<Empleado> listarEmpleado(){
        return empleadoRepository.findAll();
    }
    @Override
    public Empleado buscarEmpleadoPorId(Long id) {
        return empleadoRepository.findById(id).orElse(null);
    }
    @Override
    public Boolean guardarEmpleado(Empleado empleado) {
        if(!verificarDpiDuplicado(empleado)){
            empleadoRepository.save(empleado);
            return true;
        }else{
            return false;
        }
    }
    @Override
    public void eliminarEmpleado(Empleado empleado) {
        empleadoRepository.delete(empleado);
    }
    @Override
    public Boolean verificarDpiDuplicado(Empleado empleadoNuevo) {
        List<Empleado> empleados = listarEmpleado();
        Boolean flag = false;
        for(Empleado empleado : empleados){
            if(empleado.getDPI().equals(empleadoNuevo.getDPI())&& !empleado.getId().equals(empleadoNuevo.getId())){
                flag = true;
            }
        }
        return flag;
    }
}
