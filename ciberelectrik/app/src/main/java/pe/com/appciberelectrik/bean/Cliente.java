package pe.com.appciberelectrik.bean;

import java.util.Date;

public class Cliente {

    private int codigo;
    private String nombre;
    private String apellidos;
    private String dni;
    private String telefono;
    private String celular;
    private String direccion;
    private String correo;
    private Distrito distrito;
    private String fNacieminto;
    private int estado;

    public Cliente() {
    }

    public Cliente(int codigo, String nombre, String apellidos, String dni, String telefono, String celular, String direccion, String correo, Distrito distrito, String fNacieminto, int estado) {
        this.codigo = codigo;
        this.nombre = nombre;
        this.apellidos = apellidos;
        this.dni = dni;
        this.telefono = telefono;
        this.celular = celular;
        this.direccion = direccion;
        this.correo = correo;
        this.distrito = distrito;
        this.fNacieminto = fNacieminto;
        this.estado = estado;
    }

    public int getEstado() {
        return estado;
    }

    public void setEstado(int estado) {
        this.estado = estado;
    }

    public int getCodigo() {
        return codigo;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellidos() {
        return apellidos;
    }

    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }

    public String getDni() {
        return dni;
    }

    public void setDni(String dni) {
        this.dni = dni;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getCelular() {
        return celular;
    }

    public void setCelular(String celular) {
        this.celular = celular;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public Distrito getDistrito() {
        return distrito;
    }

    public void setDistrito(Distrito distrito) {
        this.distrito = distrito;
    }

    public String getfNacieminto() {
        return fNacieminto;
    }

    public void setfNacieminto(String fNacieminto) {
        this.fNacieminto = fNacieminto;
    }
}
