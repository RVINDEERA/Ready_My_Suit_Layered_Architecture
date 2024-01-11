package lk.ijse.rms.dao.custom;

import lk.ijse.rms.dto.MachineDto;
import java.sql.SQLException;
import java.util.List;

public interface MachineDAO extends CrudDAO<MachineDto> {
    //boolean machineSave(MachineDto machineDto) throws SQLException ;
    //String genarateNextMachineId() throws SQLException ;
    String splitMachineID(String currentMachineId) ;
  //  boolean machineDelete(String id) throws SQLException ;
   // boolean updateCustomer(MachineDto machineDto) throws SQLException ;
  //  MachineDto searchMachine(String id) throws SQLException ;
   // List<MachineDto> getAllCustomer() throws SQLException ;
}
