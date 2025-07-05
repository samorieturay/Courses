public class AlarmClock extends Clock {
     Time alarmTime;
     boolean alarmOn = false;
     boolean alarmTriggered = false;

    public void setAlarm(Time t) {
        alarmTime = t;
        alarmOn = true;
        alarmTriggered = false;
    }

    public void turnAlarmOff() {
        alarmOn = false;
    }

    public boolean isAlarmOn() {
        return alarmOn;
    }

    public void snooze() {
        int snoozeMinutes = 9;
        for (int i = 0; i < snoozeMinutes; i++) {
            alarmTime.tick();
        }
        alarmTriggered = false;
    }

    public void checkAlarm() {
        if (alarmOn && currentTime.equals(alarmTime) && !alarmTriggered) {
            System.out.println("Buzz Buzz Buzz");
            alarmTriggered = true;
        }
    }
}
