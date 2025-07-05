public class Time {
    public int hour, minute, second;
    public String ampm;

    public Time(int hour, int minute, int second, String ampm) {
        this.hour = hour;
        this.minute = minute;
        this.second = second;
        this.ampm = ampm;
    }

    public void tick() {
        second++;
        if (second == 60) {
            second = 0;
            minute++;
            if (minute == 60) {
                minute = 0;
                hour++;
                if (hour == 12) {
                    ampm = ampm.equals("AM") ? "PM" : "AM";
                } else if (hour == 13) {
                    hour = 1;
                }
            }
        }
    }

    public boolean equals(Time t) {
        return hour == t.hour && minute == t.minute && ampm.equals(t.ampm);
    }

    public String toString() {
        return String.format("%d:%02d %s", hour, minute, ampm);
    }
}
