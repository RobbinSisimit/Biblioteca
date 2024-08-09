package com.robbinsisimit.webapp.biblioteca.service;
import java.util.List;

import com.robbinsisimit.webapp.biblioteca.model.Empleado;
public interface IEmpleadoService {
    public List<Empleado> listarEmpleado();
    public Empleado buscarEmpleadoPorId(Long id);
    public Boolean guardarEmpleado(Empleado empleado);
    public void eliminarEmpleado(Empleado empleado);
    public Boolean verificarDpiDuplicado(Empleado empleadoNuevo);
}
