//package lk.ijse.rms.model;
//
//import lk.ijse.rms.db.DbConnection;
//import lk.ijse.rms.dto.BroughtCoatDto;
//import lk.ijse.rms.dto.CustomerDto;
//
//import java.sql.Connection;
//import java.sql.PreparedStatement;
//import java.sql.ResultSet;
//import java.sql.SQLException;
//import java.util.ArrayList;
//import java.util.List;
//
//public class BroughtCoatModel {
//
//    public List<BroughtCoatDto> loadAllItems() throws SQLException {
//        Connection connection = DbConnection.getInstance().getConnection();
//
//        String sql = "select a.rentId,a.customerId,c.firstName,c.phoneNUmber,b.coatId,a.rebtalBond,b.rentDate,b.returnDate from rent a join rentalCoatDetail b on a.rentId = b.rentId join customer c on a.customerId\n" +
//                " = c.customerId";
//        PreparedStatement pstm = connection.prepareStatement(sql);
//
//        List<BroughtCoatDto> itemList = new ArrayList<>();
//        ResultSet resultSet = pstm.executeQuery();
//
//        while (resultSet.next()) {
//            itemList.add(new BroughtCoatDto(
//                    resultSet.getString(1),
//                    resultSet.getString(2),
//                    resultSet.getString(3),
//                    resultSet.getString(4),
//                    resultSet.getString(5),
//                    resultSet.getString(6),
//                    resultSet.getString(7),
//                    resultSet.getString(8)
//            ));
//        }
//        return itemList;
//    }
//}
