package soloviev.perez.vicente.ritmos;

public enum Deporte {
    natacion, carrera;

    @Override
    public String toString() {
        switch (this) {
            case carrera: return "Carrera";
            case natacion: return "Natación";
        }

        return null;
    }


}
