package lk.ijse.rms.bo.custom.impl;

import lk.ijse.rms.bo.custom.MachineBo;
import lk.ijse.rms.dao.DAOFactory;
import lk.ijse.rms.dao.SQLUtil;
import lk.ijse.rms.dao.custom.MachineDAO;
import lk.ijse.rms.dto.MachineDto;
import lk.ijse.rms.entity.Machine;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class MachineBoImpl implements MachineBo {

    MachineDAO machineDAO = (MachineDAO) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOTypes.MACHINE);

    public boolean save(MachineDto machineDto) throws SQLException, ClassNotFoundException {
        return machineDAO.save(new Machine(machineDto.getMachineId(),machineDto.getTailorId(),machineDto.getType(),machineDto.getDate(),machineDto.getAvail()));
    }

    public String generateNextID() throws SQLException, ClassNotFoundException {
        ResultSet resultSet = SQLUtil.execute("SELECT machineId FROM machine ORDER BY machineId DESC LIMIT 1");
        if (resultSet.next()){
            return splitMachineID(resultSet.getString(1));
        }
        return splitMachineID(null);
    }

    public String splitMachineID(String currentMachineId) {
        if (currentMachineId != null){
            String [] split = currentMachineId.split("(JKM)");

            int id = Integer.parseInt(split[1]);
            id++;
            return String.format("JKM%03d", id);
        }else {
            return "JKM001";
        }
    }


    public boolean delete(String id) throws SQLException, ClassNotFoundException {
        return SQLUtil.execute("DELETE FROM machine WHERE machineId = ?",id);

    }

    public boolean update(MachineDto machineDto) throws SQLException, ClassNotFoundException {
        return SQLUtil.execute("UPDATE machine SET tailorId=?,type=?,date=?,avail=? WHERE machineId=?",machineDto.getTailorId(),machineDto.getType(),machineDto.getDate(),machineDto.getAvail(),machineDto.getMachineId());

    }

    public MachineDto search(String id) throws SQLException, ClassNotFoundException {
        ResultSet resultSet = SQLUtil.execute("SELECT * FROM machine WHERE machineId = ?",id);

        MachineDto machineDto =null;
        if (resultSet.next()){
            machineDto =new MachineDto(
                    resultSet.getString(1),
                    resultSet.getString(2),
                    resultSet.getString(3),
                    resultSet.getString(4),
                    resultSet.getString(5)
            );
        }
        return machineDto;
    }

    public List<MachineDto> getAll() throws SQLException, ClassNotFoundException {
        ResultSet resultSet = SQLUtil.execute("SELECT * FROM machine");

        ArrayList<MachineDto> dtoList = new ArrayList<>();

        while (resultSet.next()){
            dtoList.add(
                    new MachineDto(
                            resultSet.getString(1),
                            resultSet.getString(2),
                            resultSet.getString(3),
                            resultSet.getString(4),
                            resultSet.getString(5)
                    )
            );
        }
        return dtoList;
    }
}
