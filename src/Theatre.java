import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Theatre {
    private final String theatreName;
    private List<Seat> seats = new ArrayList<Seat>();

    public Theatre(String name, int numRows, int seatsPerRow) {
        this.theatreName = name;

        int lastRow = 'A' + (numRows - 1);
        for (char row = 'A';row <=lastRow;row++){
            for (int seatNum = 1;seatNum <= seatsPerRow; seatNum++){
                Seat seat = new Seat(row + String.format("%02d", seatNum));
                seats.add(seat);
            }
        }
    }
    public String getTheatreName() {
        return theatreName;
    }
    public boolean reserveSeat (String seatNumber){
        Seat reservedSeat = new Seat(seatNumber);
        int foundSeat = Collections.binarySearch(seats, reservedSeat, null);
        if (foundSeat >= 0){
            return seats.get(foundSeat).reserve();
        }
        System.out.println("There is no such seat!");
        return false;
    }
    public void getSeats (){
        for (Seat seat : seats){
            System.out.println(seat.getSeatNumber());
        }
    }
    public class Seat implements Comparable<Seat>{
        private String seatNumber;
        private boolean reserved = false;

        public Seat(String seatNumber) {
            this.seatNumber = seatNumber;
        }
        @Override
        public int compareTo(Seat o) {
            return this.seatNumber.compareToIgnoreCase(o.getSeatNumber());
         }
        public String getSeatNumber() {
            return seatNumber;
        }
        public boolean reserve(){
            if (!this.reserved){
                this.reserved = true;
                System.out.println("Seat reserved!");
                return true;
            }
                return false;
        }
        public boolean cancelReservation (){
            if (this.reserved){
                this.reserved = false;
                System.out.println("Reservation cancelled");
                return true;
            }
            return false;
        }
    }
}
