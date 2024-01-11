package lk.ijse.rms.dao.custom.impl;

import lk.ijse.rms.dao.SQLUtil;
import lk.ijse.rms.dao.custom.FabricDAO;
import lk.ijse.rms.db.DbConnection;
import lk.ijse.rms.dto.FabricDto;
import lk.ijse.rms.entity.Fabric;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class FabricDAOImpl implements FabricDAO {
    public String generateNextID() throws SQLException, ClassNotFoundException {
        ResultSet resultSet = SQLUtil.execute("SELECT fabricId FROM fabric ORDER BY fabricId DESC LIMIT 1");
        if (resultSet.next()){
            return splitFabricID(resultSet.getString(1));
        }
        return splitFabricID(null);
    }

    public String splitFabricID(String currentFabricId) {
        if (currentFabricId != null){
            String [] split = currentFabricId.split("(F)");

            int id = Integer.parseInt(split[1]);
            id++;
            return String.format("F%03d", id);
        }else {
            return "F001";
        }

    }

    public boolean save(Fabric entity) throws SQLException, ClassNotFoundException {
        return SQLUtil.execute("INSERT INTO fabric VALUES (?,?,?,?,?)",entity.getFabricId(),entity.getName(),entity.getRollqty(),entity.getType(),entity.getColour());
    }

    public boolean delete(String id) throws SQLException, ClassNotFoundException {
        return SQLUtil.execute("DELETE FROM fabric WHERE fabricId = ?",id);
    }

    public boolean update(Fabric fabricDto) throws SQLException, ClassNotFoundException {
        return SQLUtil.execute("UPDATE fabric SET name=?,rollQty=?,type=?,colour=? WHERE fabricId=?",fabricDto.getName(),fabricDto.getRollqty(),fabricDto.getType(),fabricDto.getColour(),fabricDto.getFabricId());

    }

    public Fabric search(String id) throws SQLException, ClassNotFoundException {
        ResultSet resultSet = SQLUtil.execute("SELECT * FROM fabric WHERE fabricId = ?",id);

        Fabric entitylist =null;
        if (resultSet.next()){
            entitylist =new Fabric(
                    resultSet.getString(1),
                    resultSet.getString(2),
                    resultSet.getString(3),
                    resultSet.getString(4),
                    resultSet.getString(5)
            );
        }
        return entitylist;

    }

    public List<Fabric> getAll() throws SQLException, ClassNotFoundException {
        ResultSet resultSet = SQLUtil.execute("SELECT * FROM fabric");
        ArrayList<Fabric> dtoList = new ArrayList<>();

        while (resultSet.next()){
            dtoList.add(
                    new Fabric(
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

