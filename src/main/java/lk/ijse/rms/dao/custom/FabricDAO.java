package lk.ijse.rms.dao.custom;

import lk.ijse.rms.dto.FabricDto;
import java.sql.SQLException;
import java.util.List;

public interface FabricDAO extends CrudDAO<FabricDto> {
    String splitFabricID(String currentFabricId) ;

}
