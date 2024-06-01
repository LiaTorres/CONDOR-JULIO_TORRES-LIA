package BackEndC3.ClinicaOdontologica.dao;

import BackEndC3.ClinicaOdontologica.model.Odontologo;
import BackEndC3.ClinicaOdontologica.model.Paciente;
import org.apache.log4j.Logger;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class OdontologoDAOH2 implements iDao<Odontologo>{
    private static final Logger logger= Logger.getLogger(OdontologoDAOH2.class);
    private static final String SQL_INSERT="INSERT INTO ODONTOLOGOS (MATRICULA, NOMBRE, APELLIDO) VALUES(?,?,?)";
    private static final String SQL_SELECT_ONE="SELECT * FROM ODONTOLOGOS WHERE ID=?";
    private static final String SQL_DELETE="DELETE FROM ODONTOLOGOS WHERE ID=?";
    private static final String SQL_UPDATE="UPDATE ODONTOLOGOS SET MATRICULA=?, NOMBRE=?, APELLIDO=? WHERE ID=?";
    private static final String SQL_SELECT_ALL="SELECT * FROM ODONTOLOGOS";
    private static final String SQL_SELECT_BY_MATRICULA="SELECT * ODONTOLOGOS WHERE MATRICULA=?";
    private static final String SQL_DELETE_BY_ID="DELETE  FROM ODONTOLOGOS WHERE ID=?";

    @Override
    public Odontologo guardar(Odontologo odontologo) {
        logger.info("iniciando las operaciones de: guardado de :  "+odontologo.getMatricula());
        Connection connection= null;
        try{
            connection= BD.getConnection();
            PreparedStatement psInsert= connection.prepareStatement(SQL_INSERT, Statement.RETURN_GENERATED_KEYS);
            psInsert.setString(1,odontologo.getMatricula());
            psInsert.setString(2, odontologo.getNombre());
            psInsert.setString(3, odontologo.getApellido());
            psInsert.execute();
            ResultSet clave= psInsert.getGeneratedKeys();
            while (clave.next()){
                odontologo.setId(clave.getInt(1));
            }

        }catch (Exception e){
            logger.error(e.getMessage());
        }
        return odontologo;
    }

    @Override
    public Odontologo buscarPorId(Integer id) {
        logger.info("iniciando las operaciones de Busqueda por ID: ");

        Connection connection= null;
        Odontologo odontologo= null;
        try{
            connection= BD.getConnection();
            Statement statement= connection.createStatement();
            PreparedStatement psSElectOne= connection.prepareStatement(SQL_SELECT_ONE);
            psSElectOne.setInt(1,id);
            ResultSet rs= psSElectOne.executeQuery();
            while (rs.next()){
                //construir el odontologo
                odontologo= new Odontologo(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4));
            }
        }catch (Exception e){
            logger.error(e.getMessage());
        }
        return odontologo;
    }

    @Override
    public void eliminar(Integer id) {
        logger.warn("iniciando las operaciones de eliminación de un odontologo con id : "+ id);
        Connection connection= null;
        try{
            connection= BD.getConnection();
            PreparedStatement ps= connection.prepareStatement(SQL_DELETE_BY_ID);
            ps.setInt(1,id);
            ps.execute();

        }catch (Exception e){
            logger.error(e.getMessage());
        }
    }

    @Override
    public void actualizar(Odontologo odontologo) {
        logger.warn("iniciando las operaciones de actualizacion de un paciente con id : "+odontologo.getId());
        Connection connection= null;
        try{
            connection= BD.getConnection();
            PreparedStatement psUpdate= connection.prepareStatement(SQL_UPDATE);
            psUpdate.setString(1, odontologo.getNombre());
            psUpdate.setString(2, odontologo.getApellido());
            psUpdate.setString(3, odontologo.getMatricula());
            psUpdate.execute();

        }catch (Exception e){
            logger.error(e.getMessage());
        }
    }

    @Override
    public List<Odontologo> buscarTodos() {
        logger.info("iniciando las operaciones de:  listado de todos los odontologos");
        List<Odontologo> listaOdontologos= new ArrayList<>();
        Odontologo odontologo= null;
        Connection connection= null;
        try{
            connection= BD.getConnection();
            Statement statement= connection.createStatement();
            ResultSet rs= statement.executeQuery(SQL_SELECT_ALL);
            while (rs.next()){
                //construir el odontologo
                odontologo= new Odontologo(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4));
                //solo me resta agregarselo a la lista.
                listaOdontologos.add(odontologo);
            }

        }catch (Exception e){
            logger.error(e.getMessage());
        }
        return listaOdontologos;
    }

    @Override
    public Odontologo buscarPorString(String string) {
        logger.info("iniciando las operaciones de: ");
        Connection connection= null;
        try{
            connection= BD.getConnection();

        }catch (Exception e){
            logger.error(e.getMessage());
        }
        return null;
    }
}
