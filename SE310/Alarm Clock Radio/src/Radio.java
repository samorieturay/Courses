public class Radio {
    public String station = "0000 AM";
    public boolean isOn = false;

    public void setStation(String station) {
        this.station = station;
    }

    public String getStation() {
        return station;
    }

    public void turnOn() {
        isOn = true;
    }

    public void turnOff() {
        isOn = false;
    }

    public boolean isOn() {
        return isOn;
    }
}
