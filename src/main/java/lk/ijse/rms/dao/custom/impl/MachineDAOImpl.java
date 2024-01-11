package lk.ijse.rms.dao.custom.impl;

import lk.ijse.rms.dao.SQLUtil;
import lk.ijse.rms.dao.custom.MachineDAO;
import lk.ijse.rms.db.DbConnection;
import lk.ijse.rms.dto.MachineDto;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class MachineDAOImpl implements MachineDAO {

    public boolean save(MachineDto machineDto) throws SQLException, ClassNotFoundException {

        return SQLUtil.execute("INSERT INTO machine VALUES (?,?,?,?,?)",machineDto.getMachineId(),machineDto.getTailorId(),machineDto.getType(),machineDto.getDate(),machineDto.getAvail());
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

    public boolean update(MachineDto machineDto) throws SQLException, ClassNotFoundException {
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

        return SQLUtil.execute("UPDATE machine SET tailorId=?,type=?,date=?,avail=? WHERE machineId=?",machineDto.getTailorId(),machineDto.getType(),machineDto.getDate(),machineDto.getAvail(),machineDto.getMachineId());

    }

    public MachineDto search(String id) throws SQLException, ClassNotFoundException {
//        Connection connection=DbConnection.getInstance().getConnection();
//        PreparedStatement pstm = connection.prepareStatement("SELECT * FROM machine WHERE machineId = ?");
//
//        pstm.setString(1,id);
//
//        ResultSet resultSet = pstm.executeQuery();

        ResultSet resultSet = SQLUtil.execute("SELECT * FROM machine WHERE machineId = ?",id);

        MachineDto machineDto =null;
        if (resultSet.next()){
            machineDto =new MachineDto(
                    resultSet.getString(1),
                    resultSet.getString(2),
                    resultSet.getString(3),
                    resultSet.getString(4),
                    resultSet.getString(5)
            );
        }
        return machineDto;
    }

    public List<MachineDto> getAll() throws SQLException, ClassNotFoundException {
//        Connection connection=DbConnection.getInstance().getConnection();
//        PreparedStatement pstm = connection.prepareStatement("SELECT * FROM machine");
//
//        ResultSet resultSet = pstm.executeQuery();

        ResultSet resultSet = SQLUtil.execute("SELECT * FROM machine");

        ArrayList<MachineDto> dtoList = new ArrayList<>();

        while (resultSet.next()){
            dtoList.add(
                    new MachineDto(
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
