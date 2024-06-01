package BackEndC3.ClinicaOdontologica.service;

import BackEndC3.ClinicaOdontologica.dao.PacienteDAOH2;
import BackEndC3.ClinicaOdontologica.dao.iDao;
import BackEndC3.ClinicaOdontologica.model.Odontologo;
import BackEndC3.ClinicaOdontologica.model.Paciente;

import java.util.List;

public class PacienteService {
private iDao<Paciente> pacienteiDao;

    public PacienteService() {
        pacienteiDao= new PacienteDAOH2();
    }
    //metodos manuales
    public List<Paciente> buscarPacientes(){
        return pacienteiDao.buscarTodos();
    }
    public Paciente guardarPaciente(Paciente paciente){
        return pacienteiDao.guardar(paciente);
    }
    public Paciente buscarPorID(Integer id){
        return pacienteiDao.buscarPorId(id);
    }

    public void actualizarPaciente(Paciente paciente){
        pacienteiDao.actualizar(paciente);
    }
    public String pacienteAEliminar(Paciente paciente){
        try{
            pacienteiDao.eliminar(paciente.getId());
            //domicilioiDao.eliminar(paciente.getDomicilio().getId());
            return "El paciente fue eliminado con éxito";

        }catch (Exception e){
            return "Error al eliminar paciente";
        }

    }
}
