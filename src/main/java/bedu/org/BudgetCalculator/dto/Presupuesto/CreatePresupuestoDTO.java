package bedu.org.BudgetCalculator.dto.Presupuesto;

import bedu.org.BudgetCalculator.model.Concepto;
import jakarta.validation.constraints.*;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

public class CreatePresupuestoDTO {

    @NotBlank(message = "El campo nombre no puede estar vació, revisar el dato")
    private String nombre;
    @NotEmpty(message = "Se debe colocar minimo una actividad")
    private List<Concepto> concepto; //Debe llenarse con model de concepto

    @PositiveOrZero(message = "El total debe ser positivo")
    @DecimalMin(value = "1.0000",message = "El total debe ser mayor a 0")
    private double total;
    @DateTimeFormat(pattern = "dd/MM/yyyy HH:mm:ss")
    private LocalDateTime fecha_creacion;
    @DateTimeFormat(pattern = "dd/MM/yyyy")
    private LocalDate fecha_inicio;
    /*
    @DateTimeFormat
    private LocalDate fecha_fin;
    */
    @Min(value = 1, message = "Se debe seleccioanr un Estado")
    private int estado; // llenarse con un model de estados

    /*

    private boolean isActivo;
    private boolean isGenerado;
    private boolean isAceptado;

*/

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
/*
    public LocalDate getFecha_fin() {
        return fecha_fin;
    }

    public void setFecha_fin(LocalDate fecha_fin) {
        this.fecha_fin = fecha_fin;
    }
    */

    public int getEstado() {
        return estado;
    }

    public void setEstado(int estado) {
        this.estado = estado;
    }
/*
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
    */

}
