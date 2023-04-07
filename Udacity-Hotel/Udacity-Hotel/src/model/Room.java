package model;

public class Room implements IRoom{
    public String roomNumber;
    public Double roomPrice;
    public RoomType roomType;
    public final boolean isFree;

    public Room(String roomNumber,double roomPrice,RoomType roomType) {
        this.roomNumber = roomNumber;
        this.roomPrice = roomPrice;
        this.roomType = roomType;

        if(roomPrice==0){
            isFree=true;
        }else{
            isFree=false;
        }
    }

    @Override
    public String getRoomNumber() {
        return this.roomNumber;
    }

    public void setRoomNumber(String roomNumber) {
        this.roomNumber = roomNumber;
    }

    @Override
    public double getRoomPrice() {
        return roomPrice;
    }

    public void setRoomPrice(Double roomPrice) {
        this.roomPrice = roomPrice;
    }

    @Override
    public RoomType getRoomType() {
        return roomType;
    }

    public void setRoomType(RoomType roomType) {
        this.roomType = roomType;
    }

    @Override
    public boolean isFree() {
        return isFree;
    }

    @Override
    public String toString() {
        return "Room{" +
                "roomNumber='" + roomNumber + '\'' +
                ", roomPrice=" + roomPrice +
                ", roomType=" + roomType +
                ", isFree=" + isFree +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Room)) return false;
        Room room = (Room) o;
        boolean RoomNumberEqual=(this.roomNumber==null && room.roomNumber==null) || (this.roomNumber!=null && this.roomNumber.equals(room.roomNumber));
        boolean RoomPriceEqual=(this.roomPrice==null && room.roomPrice==null) || (this.roomPrice!=null && this.roomPrice.equals(room.roomPrice));
        boolean RoomTypeEqual=(this.roomType==null && room.roomType==null) || (this.roomType!=null && this.roomType.equals(room.roomType));
        return RoomNumberEqual && RoomPriceEqual && RoomTypeEqual;
    }

    @Override
    public final int hashCode() {
        int result = 12;
        if (roomNumber != null) {
            result = 31 * result + roomNumber.hashCode();
        }
        if (roomType != null) {
            result = 31 * result + roomType.hashCode();
        }
        if (roomPrice != null) {
            result = 31 * result + roomPrice.hashCode();
        }
        return result;
    }
}
