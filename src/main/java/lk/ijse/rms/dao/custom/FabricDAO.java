package lk.ijse.rms.dao.custom;

import lk.ijse.rms.dto.FabricDto;
import lk.ijse.rms.entity.Fabric;

import java.sql.SQLException;
import java.util.List;

public interface FabricDAO extends CrudDAO<Fabric> {
    String splitFabricID(String currentFabricId) ;

}
