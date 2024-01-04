package lk.ijse.rms.dao;

import lk.ijse.rms.dao.custom.impl.CoatDAOImpl;
import lk.ijse.rms.dao.custom.impl.RentDAOImpl;
import lk.ijse.rms.dao.custom.impl.RentalCoatDetailDAOImpl;
import lk.ijse.rms.db.DbConnection;
import lk.ijse.rms.dto.RentCoatDto;

import java.sql.Connection;
import java.sql.SQLException;

public class RentCoatDAOImpl {

    private RentDAOImpl rentDAOImpl = new RentDAOImpl();
    private CoatDAOImpl coatDAOImpl = new CoatDAOImpl();
    private RentalCoatDetailDAOImpl rentalCoatDetailDAOImpl =new RentalCoatDetailDAOImpl();
    public boolean placeRentOrder(RentCoatDto rentCoatDto) throws SQLException {

        String rentId = rentCoatDto.getRentId();
        String customerId = rentCoatDto.getCustomerId();
        String rentBond = rentCoatDto.getRentalBond();

        Connection connection = null;
        try {
            connection = DbConnection.getInstance().getConnection();
            connection.setAutoCommit(false);

            boolean isRentSaved = rentDAOImpl.saveRent(rentId,customerId,rentBond);
            if (isRentSaved){
                boolean isUpdated = coatDAOImpl.updateCoat(rentCoatDto.getCartTmList());
                if (isUpdated){
                    boolean isRentCOatDetailSaved = rentalCoatDetailDAOImpl.saveRentCoatDetails(rentCoatDto.getRentId(), rentCoatDto.getCartTmList());
                    if (isRentCOatDetailSaved){
                        connection.commit();
                        return true;
                    }else{
                        connection.rollback();
                    }
                }else {
                    connection.rollback();;
                }
            }else {
                connection.rollback();
            }

        }catch (SQLException e){
            if(connection !=null)connection.rollback();
            e.printStackTrace();
        } finally {
            if (connection != null) connection.setAutoCommit(true);
        }
        return false;
    }
}
