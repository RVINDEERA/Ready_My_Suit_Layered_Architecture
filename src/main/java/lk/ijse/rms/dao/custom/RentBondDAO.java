package lk.ijse.rms.dao.custom;


import lk.ijse.rms.dto.RentalBondDto;

import java.sql.SQLException;
import java.util.List;

public interface RentBondDAO {
    List<RentalBondDto> loadAllBond() throws SQLException ;
}
