package eecs_swag.cs446.foodiehub;

import android.app.Service;
import android.location.Location;
import android.location.LocationListener;

/**
 * Created by JieJerryLin on 2015-05-22.
 */
public class GPSLocator extends Service implements LocationListener {
    private final Context mContext;

    boolean isGPSEnabled = false;
    boolean isNetworkEnabled = false;
    boolean canGetLocation = false;

    Location location;
    double latitude;
    double longitude;

    // The distance to change Updates in meters, 10 meters
    private static final long DISTANCE_UPDATES = 10;

    // The time between updates in milliseconds, 30 secs
    private static final long TIME_UPDATES = 30 * 1000;

    // Declaring a Location Manager
    protected LocationManager locationManager;

    public GPSLocator(Context context) {
        this.mContext = context;
        getLocation();
    }

    @Override
    public void onLocationChanged() {
        onLocationChanged();
    }

    @Override
    public void onLocationChanged(Location argLocation) {
        location = argLocation;
    }

    public void onProviderDisabled(String provider) {

    }

    public void onProviderEnabled(String provider) {

    }

    public void onStatusChanged(String provider, int status, Bundle extras) {

    }
}
