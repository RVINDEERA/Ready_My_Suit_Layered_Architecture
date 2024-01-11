package lk.ijse.rms.bo.custom;

import lk.ijse.rms.bo.SupperBO;
import lk.ijse.rms.dao.SQLUtil;
import lk.ijse.rms.dto.MachineDto;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public interface MachineBo extends SupperBO {
    public boolean save(MachineDto machineDto) throws SQLException, ClassNotFoundException ;
    public String generateNextID() throws SQLException, ClassNotFoundException ;
    public String splitMachineID(String currentMachineId) ;
    public boolean delete(String id) throws SQLException, ClassNotFoundException ;
    public boolean update(MachineDto machineDto) throws SQLException, ClassNotFoundException ;
    public MachineDto search(String id) throws SQLException, ClassNotFoundException ;
    public List<MachineDto> getAll() throws SQLException, ClassNotFoundException;
}
