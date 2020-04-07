package soloviev.perez.vicente.ritmos;

public class Marca {
    private Deporte tipo;
    private String descripcion;
    private String distancia;
    private String tiempo;

    public Marca(Deporte tipo, String descripcion, String distancia, String tiempo) {
        this.tipo = tipo;
        this.descripcion = descripcion;
        this.distancia = distancia;
        this.tiempo = tiempo;
    }

    public Deporte getTipo() {
        return tipo;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public String getDistancia() {
        return distancia;
    }

    public String getTiempo() {
        return tiempo;
    }
}
