package lk.ijse.rms.bo.custom;

import lk.ijse.rms.bo.SupperBO;
import lk.ijse.rms.dao.SQLUtil;
import lk.ijse.rms.dto.RentalBondDto;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public interface RentBondBo extends SupperBO {
    List<RentalBondDto> loadAllBond() throws SQLException, ClassNotFoundException ;
}
