package pojoClasses;

import java.util.ArrayList;

public class HouseData {
    public HouseData(Integer floorCount, Integer price, ArrayList<ParkingPlace> parkingPlaces) {
        this.floorCount = floorCount;
        this.price = price;
        this.parkingPlaces = parkingPlaces;
    }

    public Integer getFloorCount() {
        return floorCount;
    }

    public Integer getPrice() {
        return price;
    }

    public ArrayList<ParkingPlace> getParkingPlaces() {
        return parkingPlaces;
    }

    private Integer floorCount;
    private Integer price;
    private ArrayList<ParkingPlace> parkingPlaces;

    public static class ParkingPlace{
        private Boolean isWarm;
        private Boolean isCovered;
        private Integer placesCount;
    }
}
