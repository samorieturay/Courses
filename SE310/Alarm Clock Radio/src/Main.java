public class Main {
    public static void main(String[] args) {
        AlarmClockRadio acr = new AlarmClockRadio();
        acr.setCurrentTime(new Time(8, 0, 0, "AM"));
        acr.setAlarm(new Time(8, 5, 0, "AM"));
        acr.setRadioStation("1060 AM");
        acr.turnRadioOn();

        System.out.println(acr.showTime() + " The radio was turned on and is playing " + acr.getRadioStation() + ".");

        for (int i = 0; i < 5; i++) {
            System.out.println(acr.showTime());
            for (int sec = 0; sec < 60; sec++) {
                acr.checkAlarm();
                acr.tick();
            }
        }

        System.out.println("Snooze was pressed");
        acr.snooze();

        for (int i = 0; i < 9; i++) {
            System.out.println(acr.showTime());
            for (int sec = 0; sec < 60; sec++) {
                acr.checkAlarm();
                acr.tick();
            }
        }

        System.out.println("The alarm was shut off.");
        acr.turnAlarmOff();
    }
}
