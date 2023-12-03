package bedu.org.BudgetCalculator.dto.Presupuesto;


import bedu.org.BudgetCalculator.model.Estatus;
import jakarta.validation.constraints.*;
import lombok.Data;

import java.time.LocalDate;
import java.time.LocalDateTime;


@Data
public class CreatePresupuestoDTO {

    @NotBlank(message = "El campo nombre no puede estar vació, revisar el dato")
    private String nombre;

    /*
    @NotEmpty(message = "Se debe colocar minimo una actividad")
    private List<Concepto> concepto; //Debe llenarse con model de concepto
    private long conceptoId;
    */

    @PositiveOrZero(message = "El total debe ser positivo")
    @DecimalMin(value = "1.0000",message = "El total debe ser mayor a 0")
    private double total;

    //@DateTimeFormat(pattern = "dd/MM/yyyy HH:mm:ss")
    private LocalDateTime fecha_creacion;
/*
    @NotBlank
    @DateTimeFormat(pattern = "dd/MM/yyyy")*/
    private LocalDate fecha_inicio;
/*
    @NotBlank
    @DateTimeFormat(pattern = "dd/MM/yyyy")*/
    private LocalDate fecha_fin;

    @NotNull
    private Estatus estado; // llenarse con un model de estados


    private boolean Activo;

    private boolean Generado;

    private boolean Aceptado;




}
