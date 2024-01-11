package lk.ijse.rms.bo.custom.impl;

import lk.ijse.rms.bo.custom.FabricBo;
import lk.ijse.rms.dao.DAOFactory;
import lk.ijse.rms.dao.SQLUtil;
import lk.ijse.rms.dao.custom.FabricDAO;
import lk.ijse.rms.dto.FabricDto;
import lk.ijse.rms.entity.Fabric;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class FabricBoImpl implements FabricBo {

    FabricDAO fabricDAO = (FabricDAO) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOTypes.FABRIC);

    public String generateNextID() throws SQLException, ClassNotFoundException {
        return fabricDAO.generateNextID();
    }

    public boolean save(FabricDto fabricDto) throws SQLException, ClassNotFoundException {
        return fabricDAO.save(new Fabric(fabricDto.getFabricId(),fabricDto.getName(),fabricDto.getRollqty(),fabricDto.getType(),fabricDto.getColour()));
    }

    public boolean delete(String id) throws SQLException, ClassNotFoundException {
        return fabricDAO.delete(id);
    }

    public boolean update(FabricDto fabricDto) throws SQLException, ClassNotFoundException {
        return fabricDAO.update(new Fabric(fabricDto.getFabricId(),fabricDto.getName(),fabricDto.getRollqty(),fabricDto.getType(),fabricDto.getColour()));
    }

    public FabricDto search(String id) throws SQLException, ClassNotFoundException {
        Fabric fabric = fabricDAO.search(id);
        if (fabric != null) {
            return new FabricDto(fabric.getFabricId(),fabric.getName(),fabric.getRollqty(),
                    fabric.getType(),fabric.getColour());
        } else {
            return null;
        }
    }

    public List<FabricDto> getAll() throws SQLException, ClassNotFoundException {
        ArrayList<FabricDto>fabricDtos= new ArrayList<>();
        List<Fabric> fabrics = fabricDAO.getAll();
        for (Fabric fabric : fabrics){
            fabricDtos.add(new FabricDto(fabric.getFabricId(), fabric.getName(), fabric.getRollqty(), fabric.getType(), fabric.getColour()));
        }
        return fabricDtos;
    }
}

