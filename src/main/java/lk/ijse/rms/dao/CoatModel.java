package lk.ijse.rms.dao;

import lk.ijse.rms.db.DbConnection;
import lk.ijse.rms.dto.CoatDto;
import lk.ijse.rms.dto.tm.CartTm;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CoatModel {
    public static boolean coatDelete(String id) throws SQLException {
        Connection connection=DbConnection.getInstance().getConnection();
        PreparedStatement pstm = connection.prepareStatement("DELETE FROM coat WHERE coatId = ?");

        pstm.setString(1,id);
        return pstm.executeUpdate() > 0;

    }

    public boolean saveCoat(CoatDto coatDto) throws SQLException {
        Connection connection = DbConnection.getInstance().getConnection();
        PreparedStatement pstm = connection.prepareStatement("INSERT INTO coat VALUES (?,?,?,?,?,?,?)");
        pstm.setString(1,coatDto.getCoatId());
        pstm.setString(2,coatDto.getType());
        pstm.setString(3,coatDto.getColor());
        pstm.setString(4,coatDto.getAvailability());
        pstm.setString(5,coatDto.getDate());
        pstm.setString(6,coatDto.getPrice());
        pstm.setString(7,coatDto.getSize());

        return pstm.executeUpdate() > 0;
    }

    public String genarateNextMachineId() throws SQLException {
        Connection connection = DbConnection.getInstance().getConnection();

        String sql = "SELECT coatId FROM coat ORDER BY coatId DESC LIMIT 1";
        PreparedStatement ptsm = connection.prepareStatement(sql);

        ResultSet resultSet = ptsm.executeQuery();
        if (resultSet.next()){
            return splitCustomerID(resultSet.getString(1));
        }
        return splitCustomerID(null);
    }

    private String splitCustomerID(String currentCoatID) {
        if (currentCoatID != null){
            String [] split = currentCoatID.split("(COT)");

            int id = Integer.parseInt(split[1]);
            id++;
            return String.format("COT%03d", id);
        }else {
            return "COT001";
        }
    }

    public boolean updateCoat(CoatDto coatDto) throws SQLException {
        Connection connection =DbConnection.getInstance().getConnection();;
        PreparedStatement pstm = connection.prepareStatement("UPDATE coat SET type=?,color=?,avail=?,mfgDate=?,unitPrice=?,size=? WHERE coatId=?");

        pstm.setString(1,coatDto.getType());
        pstm.setString(2,coatDto.getColor());
        pstm.setString(3,coatDto.getAvailability());
        pstm.setString(4,coatDto.getDate());
        pstm.setString(5,coatDto.getPrice());
        pstm.setString(6,coatDto.getSize());
        pstm.setString(7,coatDto.getCoatId());

        return pstm.executeUpdate() > 0;
    }

    public boolean updateCoat(List<CartTm>cartTmList) throws SQLException {
        for(CartTm tm : cartTmList) {
            System.out.println("Coat: " + tm);
            if(!updateAvailability(tm.getCoatId())) {
                return false;
            }
        }
        return true;
    }

    public boolean updateAvailability(String coatId) throws SQLException {
        Connection connection = DbConnection.getInstance().getConnection();

        String sql = "UPDATE coat SET avail = 'NO' WHERE coatId = ?";
        PreparedStatement pstm = connection.prepareStatement(sql);

        pstm.setString(1, coatId);



        return pstm.executeUpdate() > 0; //false
    }


    public CoatDto searchCoat(String id) throws SQLException {
        Connection connection=DbConnection.getInstance().getConnection();
        PreparedStatement pstm = connection.prepareStatement("SELECT * FROM coat WHERE coatId = ?");

        pstm.setString(1, id);

        ResultSet resultSet = pstm.executeQuery();

        CoatDto coatDto =null;
        if (resultSet.next()){
            coatDto =new CoatDto(
                    resultSet.getString(1),
                    resultSet.getString(2),
                    resultSet.getString(3),
                    resultSet.getString(4),
                    resultSet.getString(5),
                    resultSet.getString(6),
                    resultSet.getString(7)
            );
        }
        return coatDto;
    }

    public List<CoatDto> getAllCoat() throws SQLException {
        Connection connection=DbConnection.getInstance().getConnection();
        PreparedStatement pstm = connection.prepareStatement("SELECT * FROM coat");

        ResultSet resultSet = pstm.executeQuery();

        ArrayList<CoatDto> dtoList = new ArrayList<>();

        while (resultSet.next()){
            dtoList.add(
                    new CoatDto(
                            resultSet.getString(1),
                            resultSet.getString(2),
                            resultSet.getString(3),
                            resultSet.getString(4),
                            resultSet.getString(5),
                            resultSet.getString(6),
                            resultSet.getString(7)
                    )
            );
        }
        return dtoList;
    }

    public List<CoatDto> loadAllItems() throws SQLException {
        Connection connection = DbConnection.getInstance().getConnection();

        String sql = "SELECT * FROM coat WHERE avail = 'YES'";
        PreparedStatement pstm = connection.prepareStatement(sql);

        List<CoatDto> coatList = new ArrayList<>();

        ResultSet resultSet = pstm.executeQuery();
        while (resultSet.next()) {
            coatList.add(new CoatDto(
                    resultSet.getString(1),
                    resultSet.getString(2),
                    resultSet.getString(3),
                    resultSet.getString(4),
                    resultSet.getString(5),
                    resultSet.getString(6),
                    resultSet.getString(7)
            ));
        }

        return coatList;
    }

}
