package menu.back;

/**
 * this class simulates a digital clock which represent
 * hour,minute and second in 24 clock format
 *
 * @author Alireza Ghafari
 * @version 0.0
 */
public class ClockDisplay {

    private DisplayNumber hour = new DisplayNumber();
    private DisplayNumber minute = new DisplayNumber();
    private DisplayNumber second = new DisplayNumber();
    private String currentTime;

    /**
     * class constructor that send the user's input as the start time
     */
    public ClockDisplay() {
        limitDetermine();
        this.hour.setNumber(00);
        this.minute.setNumber(00);
        this.second.setNumber(00);
    }


    /**
     * a method to define the number limit of each part of display
     */
    public void limitDetermine() {
        hour.setLimit(24);
        minute.setLimit(60);
        second.setLimit(60);
    }

    /**
     * @return the current time to show on display
     */
    public String getCurrentTime() {
        tick();
        return currentTime;
    }

    /**
     * this method set the new numbers as a string
     */
    private void setNewNumbers() {
        currentTime = hour.getNumber() + ":" + minute.getNumber() + ":" + second.getNumber();
    }


    /**
     * a method to increase minute and hour number in specific time
     */
    public void tick() {
        second.increment();
        if (second.getNumber().equals("00"))
            minute.increment();
        if (second.getNumber().equals("00") && minute.getNumber().equals("00"))
            hour.increment();
        setNewNumbers();
    }
}
