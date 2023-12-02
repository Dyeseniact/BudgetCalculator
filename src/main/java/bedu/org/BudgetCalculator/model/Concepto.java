package bedu.org.BudgetCalculator.model;

import java.time.LocalDate;

public class Concepto {
    private long id;
    private long presupuestoId;
    private String concepto; //Debe llenarse con model de actividad
    private double cantidad;
    private double precioUnit;
    private double subtotal;

    private LocalDate fecha_inicio;
    private LocalDate fecha_fin;

    public Concepto(long id, long presupuestoId, String concepto, double cantidad, double precioUnit, double subtotal, LocalDate fecha_inicio, LocalDate fecha_fin) {
        this.id = id;
        this.presupuestoId = presupuestoId;
        this.concepto = concepto;
        this.cantidad = cantidad;
        this.precioUnit = precioUnit;
        this.subtotal = subtotal;
        this.fecha_inicio = fecha_inicio;
        this.fecha_fin = fecha_fin;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getPresupuestoId() {
        return presupuestoId;
    }

    public void setPresupuestoId(long presupuestoId) {
        this.presupuestoId = presupuestoId;
    }

    public String getConcepto() {
        return concepto;
    }

    public void setConcepto(String concepto) {
        this.concepto = concepto;
    }

    public double getCantidad() {
        return cantidad;
    }

    public void setCantidad(double cantidad) {
        this.cantidad = cantidad;
    }

    public double getPrecioUnit() {
        return precioUnit;
    }

    public void setPrecioUnit(double precioUnit) {
        this.precioUnit = precioUnit;
    }

    public double getSubtotal() {
        return subtotal;
    }

    public void setSubtotal(double subtotal) {
        this.subtotal = subtotal;
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
}
