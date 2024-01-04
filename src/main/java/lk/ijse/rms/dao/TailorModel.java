package lk.ijse.rms.dao;

import lk.ijse.rms.db.DbConnection;
import lk.ijse.rms.dto.TailorDto;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class TailorModel {
    public boolean saveTailor(TailorDto tailorDto) throws SQLException {
        Connection connection = DbConnection.getInstance().getConnection();
        PreparedStatement pstm =connection.prepareStatement("INSERT INTO tailor VALUES (?,?,?,?,?,?,?)");

        pstm.setString(1,tailorDto.getTailorId());
        pstm.setString(2,tailorDto.getFirstName());
        pstm.setString(3,tailorDto.getLastName());
        pstm.setString(4,tailorDto.getTailorNIC());
        pstm.setString(5,tailorDto.getAddress());
        pstm.setString(6,tailorDto.getPhoneNumber());
        pstm.setString(7,tailorDto.getSalary());

        return pstm.executeUpdate() > 0;
    }

    public String genarateNextTailorId() throws SQLException {
        Connection connection=DbConnection.getInstance().getConnection();
        PreparedStatement pstm =connection.prepareStatement("SELECT tailorId FROM tailor ORDER BY  tailorId DESC LIMIT 1");

        ResultSet resultSet =pstm.executeQuery();
        if (resultSet.next()){
            return splitCustomerID(resultSet.getString(1));
        }
        return  splitCustomerID(null);
    }

    private String splitCustomerID(String currentTailorID) {
        if (currentTailorID!=null){
            String[] split = currentTailorID.split("00");
            int id = Integer.parseInt(split[1]);
            id++;
            return "T00"+id;
        }else {
            return "T001";
        }
    }


    public boolean updateTailor(TailorDto tailorDto) throws SQLException {
        Connection connection = DbConnection.getInstance().getConnection();
        PreparedStatement pstm = connection.prepareStatement("UPDATE  tailor SET firstName =?, lastName=?, tailorNIC=?, address=?, phoneNUmber=?, salary=? WHERE tailorId = ?");

        pstm.setString(1,tailorDto.getFirstName());
        pstm.setString(2,tailorDto.getLastName());
        pstm.setString(3,tailorDto.getTailorNIC());
        pstm.setString(4,tailorDto.getAddress());
        pstm.setString(5,tailorDto.getPhoneNumber());
        pstm.setString(6,tailorDto.getSalary());
        pstm.setString(7,tailorDto.getTailorId());

        return pstm.executeUpdate() >0;
    }

    public TailorDto searchTailor(String tailorId) throws SQLException {
        Connection connection = DbConnection.getInstance().getConnection();
        PreparedStatement pstm = connection.prepareStatement("SELECT * FROM tailor WHERE tailorId = ?");

        pstm.setString(1,tailorId);

        ResultSet resultSet = pstm.executeQuery();
        TailorDto tailorDto=null;

        if (resultSet.next()){
            tailorDto= new TailorDto(
                    resultSet.getString(1),
                    resultSet.getString(2),
                    resultSet.getString(3),
                    resultSet.getString(4),
                    resultSet.getString(5),
                    resultSet.getString(6),
                    resultSet.getString(7)
            );

        }
        return tailorDto;
    }

    public boolean deleteTailor(String tailorId) throws SQLException {
        Connection connection = DbConnection.getInstance().getConnection();
        PreparedStatement pstm = connection.prepareStatement("DELETE FROM tailor WHERE tailorId = ?");

        pstm.setString(1,tailorId);

        return pstm.executeUpdate() > 0;
    }

    public List<TailorDto> getAllTailor() throws SQLException {
        Connection connection = DbConnection.getInstance().getConnection();
        PreparedStatement pstm = connection.prepareStatement("SELECT * FROM tailor");

        ResultSet resultSet = pstm.executeQuery();

        ArrayList<TailorDto> dtoList = new ArrayList<>();

        while (resultSet.next()){
            dtoList.add(
                    new TailorDto(
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

    public List<TailorDto> getAllMachine() throws SQLException {
        Connection connection = DbConnection.getInstance().getConnection();
        String sql = "SELECT * FROM tailor";
        PreparedStatement pstm = connection.prepareStatement(sql);
        ResultSet resultSet = pstm.executeQuery();

        ArrayList<TailorDto> dtoList = new ArrayList<>();

        while(resultSet.next()) {
            dtoList.add(
                    new TailorDto(
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
}
