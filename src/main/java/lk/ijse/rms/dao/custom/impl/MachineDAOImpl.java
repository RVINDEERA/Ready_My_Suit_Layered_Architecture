package lk.ijse.rms.dao.custom.impl;

import lk.ijse.rms.dao.SQLUtil;
import lk.ijse.rms.dao.custom.MachineDAO;
import lk.ijse.rms.db.DbConnection;
import lk.ijse.rms.dto.MachineDto;
import lk.ijse.rms.entity.Machine;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class MachineDAOImpl implements MachineDAO {

    public boolean save(Machine entity) throws SQLException, ClassNotFoundException {

        return SQLUtil.execute("INSERT INTO machine VALUES (?,?,?,?,?)",entity.getMachineId(),entity.getTailorId(),entity.getType(),entity.getDate(),entity.getAvail());
    }

    public String generateNextID() throws SQLException, ClassNotFoundException {
        ResultSet resultSet = SQLUtil.execute("SELECT machineId FROM machine ORDER BY machineId DESC LIMIT 1");
        if (resultSet.next()){
            return splitMachineID(resultSet.getString(1));
        }
        return splitMachineID(null);
    }

    public String splitMachineID(String currentMachineId) {
        if (currentMachineId != null){
            String [] split = currentMachineId.split("(JKM)");

            int id = Integer.parseInt(split[1]);
            id++;
            return String.format("JKM%03d", id);
        }else {
            return "JKM001";
        }
    }


    public boolean delete(String id) throws SQLException, ClassNotFoundException {
//        Connection connection=DbConnection.getInstance().getConnection();
//        PreparedStatement pstm = connection.prepareStatement("DELETE FROM machine WHERE machineId = ?");
//
//        pstm.setString(1,id);
//        return pstm.executeUpdate() > 0;

        return SQLUtil.execute("DELETE FROM machine WHERE machineId = ?",id);

    }

    public boolean update(Machine entity) throws SQLException, ClassNotFoundException {
//        Connection connection =DbConnection.getInstance().getConnection();;
//        PreparedStatement pstm = connection.prepareStatement("UPDATE machine SET tailorId=?,type=?,date=?,avail=? WHERE machineId=?");
//
//        pstm.setString(1,machineDto.getTailorId());
//        pstm.setString(2,machineDto.getType());
//        pstm.setString(3,machineDto.getDate());
//        pstm.setString(4,machineDto.getAvail());
//        pstm.setString(5,machineDto.getMachineId());
//
//        return pstm.executeUpdate() > 0;

        return SQLUtil.execute("UPDATE machine SET tailorId=?,type=?,date=?,avail=? WHERE machineId=?",entity.getTailorId(),entity.getType(),entity.getDate(),entity.getAvail(),entity.getMachineId());

    }

    public Machine search(String id) throws SQLException, ClassNotFoundException {
//        Connection connection=DbConnection.getInstance().getConnection();
//        PreparedStatement pstm = connection.prepareStatement("SELECT * FROM machine WHERE machineId = ?");
//
//        pstm.setString(1,id);
//
//        ResultSet resultSet = pstm.executeQuery();

        ResultSet resultSet = SQLUtil.execute("SELECT * FROM machine WHERE machineId = ?",id);

        Machine machineDto =null;
        if (resultSet.next()){
            machineDto =new Machine(
                    resultSet.getString(1),
                    resultSet.getString(2),
                    resultSet.getString(3),
                    resultSet.getString(4),
                    resultSet.getString(5)
            );
        }
        return machineDto;
    }

    public List<Machine> getAll() throws SQLException, ClassNotFoundException {
//        Connection connection=DbConnection.getInstance().getConnection();
//        PreparedStatement pstm = connection.prepareStatement("SELECT * FROM machine");
//
//        ResultSet resultSet = pstm.executeQuery();

        ResultSet resultSet = SQLUtil.execute("SELECT * FROM machine");

        ArrayList<Machine> dtoList = new ArrayList<>();

        while (resultSet.next()){
            dtoList.add(
                    new Machine(
                            resultSet.getString(1),
                            resultSet.getString(2),
                            resultSet.getString(3),
                            resultSet.getString(4),
                            resultSet.getString(5)
                    )
            );
        }
        return dtoList;
    }

}
