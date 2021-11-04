package pe.com.appciberelectrik.bean;

public class Categoria {
    private int id;
    private String name;
    private int estado;

    public Categoria() {
    }

    public Categoria(int id, String name, int estado) {
        this.id = id;
        this.name = name;
        this.estado = estado;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getEstado() {
        return estado;
    }

    public void setEstado(int estado) {
        this.estado = estado;
    }
}
