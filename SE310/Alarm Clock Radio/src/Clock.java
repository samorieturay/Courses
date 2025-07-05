public class Clock {
     Time currentTime;

    public void setCurrentTime(Time t) {
        this.currentTime = t;
    }

    public Time getCurrentTime() {
        return currentTime;
    }

    public void tick() {
        currentTime.tick();
    }

    public String showTime() {
        return currentTime.toString();
    }
}
