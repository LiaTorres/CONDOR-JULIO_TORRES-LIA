package BackEndC3.ClinicaOdontologica.service;

import BackEndC3.ClinicaOdontologica.dao.OdontologoDAOH2;
import BackEndC3.ClinicaOdontologica.dao.iDao;
import BackEndC3.ClinicaOdontologica.model.Odontologo;

import java.util.List;
public class OdontologoService {
    private iDao<Odontologo> odontologoiDao;
    public OdontologoService() {
        odontologoiDao=new OdontologoDAOH2();
    }
    public List<Odontologo> buscarOdontologos(){
        return odontologoiDao.buscarTodos();
    }
    public Odontologo buscarPorID(Integer id){
        return odontologoiDao.buscarPorId(id);
    }
    public void actualizarOdontologo(Odontologo odontologo){
         odontologoiDao.actualizar(odontologo);
    }
    public Odontologo crearOdontologos(Odontologo odontologo){
        return odontologoiDao.guardar(odontologo);
    }
}