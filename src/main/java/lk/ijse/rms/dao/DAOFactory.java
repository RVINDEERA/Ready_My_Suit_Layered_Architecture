package lk.ijse.rms.dao;

import lk.ijse.rms.dao.custom.impl.*;

public class DAOFactory {
    private static DAOFactory daoFactory;
    private DAOFactory(){

    }
    public static DAOFactory getDaoFactory(){
        return (daoFactory == null) ? daoFactory = new DAOFactory() : daoFactory;
    }
    public enum DAOTypes{
        COAT,COATMEASUREMENT,CUSTOMER,FABRIC,ITEM,LOGIN,MACHINE,ORDER,REGISTER,RENTBOND,RENT,SHIRTMEASUREMENT,TAILOR,TROUSER,ORDER_DETAIL,RENT_DETAIL
    }
    public SupperDAO getDAO(DAOTypes daoTypes) {
        switch (daoTypes) {
            case COAT:
                return new CoatDAOImpl();

            case COATMEASUREMENT:
                return new CoatMeasurementDA();
            case CUSTOMER:
                return new CustomerDAOImpl();

            case FABRIC:
                return new FabricDAOImpl();

            case ITEM:
                return new ItemDAOImpl();

            case LOGIN:
                return new LoginDAOImpl();

            case MACHINE:
                return new MachineDAOImpl();

            case ORDER:
                return new OrderDAOImpl();

            case REGISTER:
                return new RegisterDAOImpl();

            case RENTBOND:
                return new RentBondDAOImpl();

            case RENT:
                return new RentDAOImpl();

            case SHIRTMEASUREMENT:
                return new ShirtMeasurementDAOImpl();

            case TAILOR:
                return new TailorDAOImpl();

            case TROUSER:
                return new TrouserDAOImpl();

            case ORDER_DETAIL:
                return new OrderItemDAOImpl();

            case RENT_DETAIL:
                return new RentalCoatDetailDAOImpl();
            default:
                return null;
        }
    }
}
