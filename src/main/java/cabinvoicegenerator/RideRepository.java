package cabinvoicegenerator;

import java.util.*;

public class RideRepository {
    Map<String, ArrayList<Ride>> userRides=null;
    ArrayList<Ride> rideArrayList=null;

    public RideRepository() {
        this.userRides = new HashMap();
    }
    public void addRides(String userId, Ride[] rides){
        List<Ride> ridesList = new ArrayList<>(Arrays.asList(rides));
        List<Ride> list = this.userRides.get(userId);
        if (list != null) ridesList.addAll(list);
        this.userRides.put(userId, (ArrayList<Ride>) ridesList);
    }
    public Ride[] getRides(String userId){
        return this.userRides.get(userId).toArray(new Ride[0]);
    }
}
