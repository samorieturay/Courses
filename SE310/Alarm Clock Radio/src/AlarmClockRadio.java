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

    // AlarmClock class doesn't have direct access to the radio station since the Radio object is in the AlarmClockRadio class
    // So we can override the checkAlarm() method in this class instead
    @Override
    public void checkAlarm() {
        if (alarmOn && currentTime.equals(alarmTime) && !alarmTriggered) {
            System.out.println("The radio is playing " + radio.getStation());
            alarmTriggered = true;
        }
    }
}
