package lk.ijse.rms.dao.custom;


import lk.ijse.rms.dao.SupperDAO;
import lk.ijse.rms.dto.RentalBondDto;

import java.sql.SQLException;
import java.util.List;

public interface RentBondDAO extends SupperDAO {
    List<RentalBondDto> loadAllBond() throws SQLException, ClassNotFoundException;
}
