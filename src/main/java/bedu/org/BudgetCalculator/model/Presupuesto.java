package bedu.org.BudgetCalculator.model;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

public class Presupuesto {
    private long id;
    private String nombre;
    private List<Concepto> concepto; //Debe llenarse con model de concepto

    private double total;
    private LocalDateTime fecha_creacion;
    private LocalDate fecha_inicio;
    private LocalDate fecha_fin;
    private int estado; // llenarse con un model de estados

    private boolean isActivo;
    private boolean isGenerado;
    private boolean isAceptado;

    public Presupuesto(long id, String nombre, List<Concepto> concepto, double total, LocalDateTime fecha_creacion, LocalDate fecha_inicio, LocalDate fecha_fin, int estado, boolean isActivo, boolean isGenerado, boolean isAceptado) {
        this.id = id;
        this.nombre = nombre;
        this.concepto = concepto;
        this.total = total;
        this.fecha_creacion = fecha_creacion;
        this.fecha_inicio = fecha_inicio;
        this.fecha_fin = fecha_fin;
        this.estado = estado;
        this.isActivo = isActivo;
        this.isGenerado = isGenerado;
        this.isAceptado = isAceptado;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public List<Concepto> getConcepto() {
        return concepto;
    }

    public void setConcepto(List<Concepto> concepto) {
        this.concepto = concepto;
    }

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }

    public LocalDateTime getFecha_creacion() {
        return fecha_creacion;
    }

    public void setFecha_creacion(LocalDateTime fecha_creacion) {
        this.fecha_creacion = fecha_creacion;
    }

    public LocalDate getFecha_inicio() {
        return fecha_inicio;
    }

    public void setFecha_inicio(LocalDate fecha_inicio) {
        this.fecha_inicio = fecha_inicio;
    }

    public LocalDate getFecha_fin() {
        return fecha_fin;
    }

    public void setFecha_fin(LocalDate fecha_fin) {
        this.fecha_fin = fecha_fin;
    }

    public int getEstado() {
        return estado;
    }

    public void setEstado(int estado) {
        this.estado = estado;
    }

    public boolean isActivo() {
        return isActivo;
    }

    public void setActivo(boolean activo) {
        isActivo = activo;
    }

    public boolean isGenerado() {
        return isGenerado;
    }

    public void setGenerado(boolean generado) {
        isGenerado = generado;
    }

    public boolean isAceptado() {
        return isAceptado;
    }

    public void setAceptado(boolean aceptado) {
        isAceptado = aceptado;
    }
}
