package lk.ijse.rms.model;

import lk.ijse.rms.db.DbConnection;
import lk.ijse.rms.dto.FabricDto;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class FabricModel {
    public String genarateNextFabricId() throws SQLException {
        Connection connection = DbConnection.getInstance().getConnection();

        String sql = "SELECT fabricId FROM fabric ORDER BY fabricId DESC LIMIT 1";
        PreparedStatement ptsm = connection.prepareStatement(sql);

        ResultSet resultSet = ptsm.executeQuery();
        if (resultSet.next()){
            return splitFabricID(resultSet.getString(1));
        }
        return splitFabricID(null);
    }

    private String splitFabricID(String currentFabricId) {
        if (currentFabricId != null){
            String [] split = currentFabricId.split("(F)");

            int id = Integer.parseInt(split[1]);
            id++;
            return String.format("F%03d", id);
        }else {
            return "F001";
        }

    }

    public boolean fabricSave(FabricDto fabricDto) throws SQLException {
        Connection connection = DbConnection.getInstance().getConnection();
        PreparedStatement pstm =connection.prepareStatement("INSERT INTO fabric VALUES (?,?,?,?,?)");

        pstm.setString(1,fabricDto.getFabricId());
        pstm.setString(2,fabricDto.getName());
        pstm.setString(3,fabricDto.getRollqty());
        pstm.setString(4,fabricDto.getType());
        pstm.setString(5,fabricDto.getColour());


        return pstm.executeUpdate() > 0;
    }

    public boolean fabricDelete(String id) throws SQLException {
        Connection connection=DbConnection.getInstance().getConnection();
        PreparedStatement pstm = connection.prepareStatement("DELETE FROM fabric WHERE fabricId = ?");

        pstm.setString(1,id);
        return pstm.executeUpdate() > 0;
    }

    public boolean updateFabric(FabricDto fabricDto) throws SQLException {
        Connection connection =DbConnection.getInstance().getConnection();
        PreparedStatement pstm = connection.prepareStatement("UPDATE fabric SET name=?,rollQty=?,type=?,colour=? WHERE fabricId=?");

        pstm.setString(1,fabricDto.getName());
        pstm.setString(2,fabricDto.getRollqty());
        pstm.setString(3,fabricDto.getType());
        pstm.setString(4,fabricDto.getColour());
        pstm.setString(5,fabricDto.getFabricId());

        return pstm.executeUpdate() > 0;

    }

    public FabricDto searchFabric(String id) throws SQLException {
        Connection connection=DbConnection.getInstance().getConnection();
        PreparedStatement pstm = connection.prepareStatement("SELECT * FROM fabric WHERE fabricId = ?");

        pstm.setString(1,id);

        ResultSet resultSet = pstm.executeQuery();

        FabricDto fabricDto =null;
        if (resultSet.next()){
            fabricDto =new FabricDto(
                    resultSet.getString(1),
                    resultSet.getString(2),
                    resultSet.getString(3),
                    resultSet.getString(4),
                    resultSet.getString(5)
            );
        }
        return fabricDto;

    }

    public List<FabricDto> getAllCustomer() throws SQLException {
        Connection connection=DbConnection.getInstance().getConnection();
        PreparedStatement pstm = connection.prepareStatement("SELECT * FROM fabric");

        ResultSet resultSet = pstm.executeQuery();

        ArrayList<FabricDto> dtoList = new ArrayList<>();

        while (resultSet.next()){
            dtoList.add(
                    new FabricDto(
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

