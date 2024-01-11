package lk.ijse.rms.dao.custom;

import lk.ijse.rms.dto.MachineDto;
import lk.ijse.rms.entity.Machine;

import java.sql.SQLException;
import java.util.List;

public interface MachineDAO extends CrudDAO<Machine> {
    String splitMachineID(String currentMachineId) ;
}
