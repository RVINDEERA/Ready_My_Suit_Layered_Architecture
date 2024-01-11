package lk.ijse.rms.bo;

import lk.ijse.rms.bo.custom.impl.*;
import lk.ijse.rms.dao.custom.impl.*;

public class BOFactory {
    private static BOFactory boFactory;

    private BOFactory() {}

    public static BOFactory getBoFactory () {
        return (boFactory == null) ? boFactory = new BOFactory() : boFactory;
    }

    public enum Botypes{
        COAT,COATMEASUREMENT,CUSTOMER,FABRIC,ITEM,MACHINE,ORDER,RENTBOND,RENT,SHIRTMEASUREMENT,TAILOR,TROUSER,PLACE_ORDER,RENT_COAT
    }
    public SupperBO getDAO(Botypes botypes) {
        switch (botypes) {
            case COAT:
                return new CoatBoImpl();

            case COATMEASUREMENT:
                return new CoatMeasurementBoImpl();
            case CUSTOMER:
                return new CustomerBoImpl();

            case FABRIC:
                return new FabricBoImpl();

            case ITEM:
                return new ItemBoImpl();

            case MACHINE:;
                return new MachineBoImpl();

            case ORDER:
                return new OrderBoImpl();

            case RENTBOND:
                return new RentBondBoImpl();

            case RENT:
                return new RentBoImpl();

            case SHIRTMEASUREMENT:
                return new ShirtMeasurementBoImpl();

            case TAILOR:
                return new TailorBoImpl();

            case TROUSER:
                return new TrouserBoImpl();
            case PLACE_ORDER:
                return new PlaceOrderBoImpl();
            case RENT_COAT:
                return new RentCoatBoImpl();
            default:
                return null;
        }
    }
}
