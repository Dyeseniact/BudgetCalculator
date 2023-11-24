package bedu.org.BudgetCalculator.repository;

import bedu.org.BudgetCalculator.model.Presupuesto;
import org.springframework.stereotype.Repository;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

@Repository
public class PresupuestoRepository {
    private long currentId;
    private List<Presupuesto> presupuestos;

    public PresupuestoRepository(){
        currentId=0;
        presupuestos= new LinkedList<>();
    }
    public List<Presupuesto> getAll(){
        return presupuestos;
    }

    public Presupuesto save(Presupuesto data){
        data.setId(++currentId);
        presupuestos.add(data);
        return data;
    }

}
