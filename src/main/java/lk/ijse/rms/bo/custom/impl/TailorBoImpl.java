package lk.ijse.rms.bo.custom.impl;

import lk.ijse.rms.bo.custom.TailorBo;
import lk.ijse.rms.dao.SQLUtil;
import lk.ijse.rms.dao.custom.TailorDAO;
import lk.ijse.rms.dto.TailorDto;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class TailorBoImpl implements TailorBo {
    public boolean save(TailorDto tailorDto) throws SQLException, ClassNotFoundException {
//        Connection connection = DbConnection.getInstance().getConnection();
//        PreparedStatement pstm =connection.prepareStatement("INSERT INTO tailor VALUES (?,?,?,?,?,?,?)");
//
//        pstm.setString(1,tailorDto.getTailorId());
//        pstm.setString(2,tailorDto.getFirstName());
//        pstm.setString(3,tailorDto.getLastName());
//        pstm.setString(4,tailorDto.getTailorNIC());
//        pstm.setString(5,tailorDto.getAddress());
//        pstm.setString(6,tailorDto.getPhoneNumber());
//        pstm.setString(7,tailorDto.getSalary());
//
//        return pstm.executeUpdate() > 0;

        return SQLUtil.execute("INSERT INTO tailor VALUES (?,?,?,?,?,?,?)",tailorDto.getTailorId(),tailorDto.getFirstName(),tailorDto.getLastName(),tailorDto.getTailorNIC(),tailorDto.getAddress(),tailorDto.getPhoneNumber(),tailorDto.getSalary());
    }

    public String generateNextID() throws SQLException, ClassNotFoundException {
//        Connection connection=DbConnection.getInstance().getConnection();
//        PreparedStatement pstm =connection.prepareStatement("SELECT tailorId FROM tailor ORDER BY  tailorId DESC LIMIT 1");
//
//        ResultSet resultSet =pstm.executeQuery();

        ResultSet resultSet =SQLUtil.execute("SELECT tailorId FROM tailor ORDER BY  tailorId DESC LIMIT 1");
        if (resultSet.next()){
            return splitCustomerID(resultSet.getString(1));
        }
        return  splitCustomerID(null);
    }

    public String splitCustomerID(String currentTailorID) {
        if (currentTailorID!=null){
            String[] split = currentTailorID.split("00");
            int id = Integer.parseInt(split[1]);
            id++;
            return "T00"+id;
        }else {
            return "T001";
        }
    }


    public boolean update(TailorDto tailorDto) throws SQLException, ClassNotFoundException {
//        Connection connection = DbConnection.getInstance().getConnection();
//        PreparedStatement pstm = connection.prepareStatement("UPDATE  tailor SET firstName =?, lastName=?, tailorNIC=?, address=?, phoneNUmber=?, salary=? WHERE tailorId = ?");
//
//        pstm.setString(1,tailorDto.getFirstName());
//        pstm.setString(2,tailorDto.getLastName());
//        pstm.setString(3,tailorDto.getTailorNIC());
//        pstm.setString(4,tailorDto.getAddress());
//        pstm.setString(5,tailorDto.getPhoneNumber());
//        pstm.setString(6,tailorDto.getSalary());
//        pstm.setString(7,tailorDto.getTailorId());
//
//        return pstm.executeUpdate() >0;

        return SQLUtil.execute("UPDATE  tailor SET firstName =?, lastName=?, tailorNIC=?, address=?, phoneNUmber=?, salary=? WHERE tailorId = ?",tailorDto.getFirstName(),tailorDto.getLastName(),tailorDto.getTailorNIC(),tailorDto.getAddress(),tailorDto.getPhoneNumber(),tailorDto.getSalary(),tailorDto.getTailorId());
    }

    public TailorDto search(String tailorId) throws SQLException, ClassNotFoundException {
//        Connection connection = DbConnection.getInstance().getConnection();
//        PreparedStatement pstm = connection.prepareStatement("SELECT * FROM tailor WHERE tailorId = ?");
//
//        pstm.setString(1,tailorId);
//
//        ResultSet resultSet = pstm.executeQuery();

        ResultSet resultSet = SQLUtil.execute("SELECT * FROM tailor WHERE tailorId = ?",tailorId);
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

    public boolean delete(String tailorId) throws SQLException, ClassNotFoundException {
//        Connection connection = DbConnection.getInstance().getConnection();
//        PreparedStatement pstm = connection.prepareStatement("DELETE FROM tailor WHERE tailorId = ?");
//
//        pstm.setString(1,tailorId);
//
//        return pstm.executeUpdate() > 0;

        return SQLUtil.execute("DELETE FROM tailor WHERE tailorId = ?",tailorId);
    }

    public List<TailorDto> getAll() throws SQLException, ClassNotFoundException {
//        Connection connection = DbConnection.getInstance().getConnection();
//        PreparedStatement pstm = connection.prepareStatement("SELECT * FROM tailor");
//
//        ResultSet resultSet = pstm.executeQuery();

        ResultSet resultSet = SQLUtil.execute("SELECT * FROM tailor");

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

    public List<TailorDto> getAllMachine() throws SQLException, ClassNotFoundException {
//        Connection connection = DbConnection.getInstance().getConnection();
//        String sql = "SELECT * FROM tailor";
//        PreparedStatement pstm = connection.prepareStatement(sql);
//        ResultSet resultSet = pstm.executeQuery();

        ResultSet resultSet = SQLUtil.execute("SELECT * FROM tailor");

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
