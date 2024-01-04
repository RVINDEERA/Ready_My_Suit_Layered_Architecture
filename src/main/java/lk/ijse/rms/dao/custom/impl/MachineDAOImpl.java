package lk.ijse.rms.dao.custom.impl;

import lk.ijse.rms.db.DbConnection;
import lk.ijse.rms.dto.MachineDto;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class MachineDAOImpl {

    public boolean machineSave(MachineDto machineDto) throws SQLException {
        Connection connection = DbConnection.getInstance().getConnection();
        PreparedStatement pstm = connection.prepareStatement("INSERT INTO machine VALUES (?,?,?,?,?)");
        pstm.setString(1,machineDto.getMachineId());
        pstm.setString(2,machineDto.getTailorId());
        pstm.setString(3,machineDto.getType());
        pstm.setString(4,machineDto.getDate());
        pstm.setString(5,machineDto.getAvail());

        return pstm.executeUpdate() > 0;
    }

    public String genarateNextMachineId() throws SQLException {
        Connection connection = DbConnection.getInstance().getConnection();

        String sql = "SELECT machineId FROM machine ORDER BY machineId DESC LIMIT 1";
        PreparedStatement ptsm = connection.prepareStatement(sql);

        ResultSet resultSet = ptsm.executeQuery();
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


    public boolean machineDelete(String id) throws SQLException {
        Connection connection=DbConnection.getInstance().getConnection();
        PreparedStatement pstm = connection.prepareStatement("DELETE FROM machine WHERE machineId = ?");

        pstm.setString(1,id);
        return pstm.executeUpdate() > 0;

    }

    public boolean updateCustomer(MachineDto machineDto) throws SQLException {
        Connection connection =DbConnection.getInstance().getConnection();;
        PreparedStatement pstm = connection.prepareStatement("UPDATE machine SET tailorId=?,type=?,date=?,avail=? WHERE machineId=?");

        pstm.setString(1,machineDto.getTailorId());
        pstm.setString(2,machineDto.getType());
        pstm.setString(3,machineDto.getDate());
        pstm.setString(4,machineDto.getAvail());
        pstm.setString(5,machineDto.getMachineId());

        return pstm.executeUpdate() > 0;

    }

    public MachineDto searchMachine(String id) throws SQLException {
        Connection connection=DbConnection.getInstance().getConnection();
        PreparedStatement pstm = connection.prepareStatement("SELECT * FROM machine WHERE machineId = ?");

        pstm.setString(1,id);

        ResultSet resultSet = pstm.executeQuery();

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

    public List<MachineDto> getAllCustomer() throws SQLException {
        Connection connection=DbConnection.getInstance().getConnection();
        PreparedStatement pstm = connection.prepareStatement("SELECT * FROM machine");

        ResultSet resultSet = pstm.executeQuery();

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
