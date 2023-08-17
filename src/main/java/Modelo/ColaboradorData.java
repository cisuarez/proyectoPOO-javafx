public class ColaboradorData {
    private String nombre;
    private String email;
    private String cedula;
    private String telefono;
    private Empleo tipoEmpleado;
    private Estado estado;
    private LocalDate fechaInicioActividades;
    private LocalDate fechaFinActividades;

    // Constructor de ColaboradorData
    public ColaboradorData(String nombre, String email, String cedula, String telefono,
                           Empleo tipoEmpleado, Estado estado, LocalDate fechaInicioActividades,
                           LocalDate fechaFinActividades) {
        this.nombre = nombre;
        this.email = email;
        this.cedula = cedula;
        this.telefono = telefono;
        this.tipoEmpleado = tipoEmpleado;
        this.estado = estado;
        this.fechaInicioActividades = fechaInicioActividades;
        this.fechaFinActividades = fechaFinActividades;
    }

    // Getters para los atributos
}
