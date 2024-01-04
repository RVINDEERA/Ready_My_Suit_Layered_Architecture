package lk.ijse.rms.dao.custom;

import lk.ijse.rms.dto.FabricDto;
import java.sql.SQLException;
import java.util.List;

public interface FabricDAO {
    String genarateNextFabricId() throws SQLException ;
    String splitFabricID(String currentFabricId) ;
    boolean fabricSave(FabricDto fabricDto) throws SQLException ;
    boolean fabricDelete(String id) throws SQLException ;
    boolean updateFabric(FabricDto fabricDto) throws SQLException ;
    FabricDto searchFabric(String id) throws SQLException ;
    List<FabricDto> getAllCustomer() throws SQLException;
}
