package lk.ijse.rms.bo.custom;

import lk.ijse.rms.bo.SupperBO;
import lk.ijse.rms.dto.RentCoatDto;

import java.sql.SQLException;

public interface RentCoatBo extends SupperBO {
    boolean placeRentOrder(RentCoatDto rentCoatDto) throws SQLException;
}
