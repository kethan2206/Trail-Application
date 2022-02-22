package com.example.testing2;

import android.content.Context;
import android.os.RemoteException;
import android.util.Log;

import org.altbeacon.beacon.BeaconConsumer;
import org.altbeacon.beacon.BeaconManager;
import org.altbeacon.beacon.BeaconParser;
import org.altbeacon.beacon.Identifier;
import org.altbeacon.beacon.MonitorNotifier;
import org.altbeacon.beacon.RangeNotifier;
import org.altbeacon.beacon.Region;

import java.util.HashMap;
import java.util.ArrayList;
import java.util.IdentityHashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import static java.lang.String.valueOf;

public class BeaconManagerWrapper {

    // Beacon Management Variables
    private final Context mContext;
    private BeaconManager mBeaconManager;
    private final BeaconConsumer mBeaconConsumer;
    private final MonitorNotifier mBeaconMonitorNotifier;
    private final RangeNotifier mBeaconRangeNotifier;
    //private Region region = null;

    //Region beaconRegion = new Region("Default",Identifier.parse("f9ddc3e9-b0ca-4d77-bf7d-858ba910582"),Identifier.parse("1"),Identifier.parse("2"));

    // Region Holder (Boolean value indicates if region has been entered this charge cycle)
    public Map<Region, Boolean> mBeaconRegions = new HashMap<>();

    public BeaconManagerWrapper(Context context,
                                BeaconConsumer beaconConsumer,
                                MonitorNotifier monitorNotifier,
                                RangeNotifier rangeNotifier) {
        mContext = context;
        mBeaconConsumer = beaconConsumer;
        mBeaconMonitorNotifier = monitorNotifier;
        mBeaconRangeNotifier = rangeNotifier;
    }

    /**
     * Initialisation
     */

    public void init() {
        // Get Singleton Instance
        mBeaconManager = BeaconManager.getInstanceForApplication(mContext.getApplicationContext());

        // Clear All Existing Regions From Disk
        mBeaconManager.setRegionStatePersistenceEnabled(false);

        // Set Rate
        int intervalBetweenScans = App.mConfig.getScanParameters().getIntervalBetweenScans();
        int scanDuration = App.mConfig.getScanParameters().getScanDuration();
        mBeaconManager.setBackgroundBetweenScanPeriod(Math.max(intervalBetweenScans, 0));
        mBeaconManager.setBackgroundScanPeriod(Math.min(scanDuration, 1100));

        // Set Beacon Layouts
        mBeaconManager.getBeaconParsers().clear();
        for (String beaconLayout : App.mConfig.getScanParameters().getBeaconLayouts()) {
            mBeaconManager.getBeaconParsers().add(new BeaconParser().setBeaconLayout(BeaconParser.ALTBEACON_LAYOUT));
        }

        //Set Beacon UUid
        //mBeaconManager.getMonitoredRegions().clear();
        //mBeaconManager.getMonitoredRegions().add(new Region("Default",Identifier.parse("f9ddc3e9-b0ca-4d77-bf7d-858ba9105820"),Identifier.parse("1"),Identifier.parse("2")));
        //Set Beacon Region
        //Region region = new Region("Default", Identifier.parse("f9ddc3e9-b0ca-4d77-bf7d-858ba9105820"), Identifier.parse("1"), Identifier.parse("2"));
        //Region region = new Region("backgroundRegion", null, null, null);


        // Bind to BeaconConsumer
        mBeaconManager.bind(mBeaconConsumer);
    }

    public void deInit() {
        // Unbind BeaconConsumer
        mBeaconManager.unbind(mBeaconConsumer);

        // Stop Monitoring
        try {
            stopMonitoringForBeaconsInAllRegions();
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }

    /**
     * Scanning
     */

    public void startMonitoringForBeaconsInAllRegions() throws RemoteException {
        // Reset Notifiers
        mBeaconManager.removeAllMonitorNotifiers();

        // Add New Notifier
        mBeaconManager.addMonitorNotifier(mBeaconMonitorNotifier);
        mBeaconManager.addRangeNotifier(mBeaconRangeNotifier);

        // Build Regions from Config
        for (Config.Beacon beacon : App.mConfig.getBeacons()) {
            //String regionId = String.valueOf(beacon.getBeaconName());
            String regionId = String.valueOf(App.mConfig.getBeacons().indexOf(beacon));
            String regionBtMac = String.valueOf(beacon.getBeaconMac());
            //String region01 = String.valueOf(beacon.getUuids().get(0));
            //String region02 = String.valueOf(beacon.getUuids().get(1));
            //String region03 = String.valueOf(beacon.getUuids().get(2));

            /*for (Config.Beacon.Uuid beaconUuid : App.mBeacon.getUuids()) {
                String region01 = String.valueOf(beaconUuid.getId1());
                String region02 = String.valueOf(beaconUuid.getId2());
                String region03 = String.valueOf(beaconUuid.getId3());

                List<Identifier> uuidList = new ArrayList<Identifier>();
                uuidList.add(Identifier.parse(region01));
                uuidList.add(Identifier.parse(region02));
                uuidList.add(Identifier.parse(region03));*/


            //String region01 = String.valueOf(beacon.getBeaconId1());
            //String region02 = String.valueOf(beacon.getBeaconId2());
            //String region03 = String.valueOf(beacon.getBeaconId3());

            List<Identifier> uuidList = new ArrayList<>();
            uuidList.add(Identifier.parse("f9ddc3e9-b0ca-4d77-bf7d-858ba9105820"));
            uuidList.add(Identifier.parse("1"));
            uuidList.add(Identifier.parse("2"));


            mBeaconRegions.put(new Region(regionId, uuidList, regionBtMac), false);
            //mBeaconRegions.put(new Region(beacon.getBeaconMac(),beacon.getBeaconId1(),beacon.getBeaconId2(),beacon.getBeaconId3()), false);
            //}

        }

        // Start Monitoring Region
        for (Region region : mBeaconRegions.keySet()) {
            mBeaconManager.startMonitoringBeaconsInRegion(region);
            Log.i(this.getClass().getName(), "Started Monitoring region: "
                    + region.getUniqueId() + " | MAC: + " + region.getBluetoothAddress() + " Id 1 " + region.getId1() + " Id 2 " + region.getId2() + " Id 3 " + region.getId3());
        }
    }

    public void stopMonitoringForBeaconsInAllRegions() throws RemoteException {
        for (Region beaconRegion : mBeaconRegions.keySet()) {
            mBeaconManager.stopMonitoringBeaconsInRegion(beaconRegion);
            mBeaconManager.stopRangingBeaconsInRegion(beaconRegion);
        }
    }

    public void startRangingBeaconsInRegion(Region region) throws RemoteException {
        mBeaconManager.startRangingBeaconsInRegion(region);
    }

    /**
     * Region Monitoring
     */

    public void declareRegionEntered(Region region) {
        mBeaconRegions.put(region, true);
    }

    public void resetAllRegionStates() {
        for (Region region : mBeaconRegions.keySet()) {
            mBeaconRegions.put(region, false);


        }
    }
}
