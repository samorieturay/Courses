public class AlarmClockRadio extends AlarmClock {
    private Radio radio = new Radio();

    public void setRadioStation(String station) {
        radio.setStation(station);
    }

    public String getRadioStation() {
        return radio.getStation();
    }

    public void turnRadioOn() {
        radio.turnOn();
    }

    public void turnRadioOff() {
        radio.turnOff();
    }

    public boolean isRadioOn() {
        return radio.isOn();
    }
}
