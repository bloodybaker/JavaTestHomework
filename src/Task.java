

public class Task {

    private String title;
    private int time;
    private int start;
    private int end;
    private int interval;
    private boolean active;
    private boolean repeated;

    public Task(String title, int time) {
        this.title = title;
        this.time = time;
        repeated = true;
    }

    public Task(String title, int start, int end, int interval) {
        this.title = title;
        this.start = start;
        this.end = end;
        this.interval = interval;
    }

    public String getTitle() {
        return title;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public int getTime() {
        if (repeated == true) {
            return time;
        } else {
            return start;
        }
    }

    public void setTime(int time) {
        this.time = time;
        repeated = true;
    }

    public int getStartTime() {
        if (repeated == true) {
            return time;
        } else {
            return start;
        }
    }

    public int getEndTime() {
        if (repeated == true) {
            return time;
        } else {
            return end;
        }
    }

    public int getRepeatInterval() {
        if (repeated == true) {
            return 0;
        } else {
            return interval;
        }
    }

    public void setTime(int start, int end, int interval) {
        this.start = start;
        this.end = end;
        this.interval = interval;
        repeated = false;
    }

    public boolean isRepeated() {
        return !repeated;
    }
    public boolean isActive(){
        return active;
    }
    public int nextTimeAfter(int current) {
        if (active == true) {
            if (repeated == true) {
                if (time > current) {
                    return time;
                } else {
                    return -1;
                }
            } else {
                int hold = start;
                while (hold <= current) {
                    hold += interval;
                }
                if (hold > end) {
                    return -1;
                } else {
                    return hold;
                }
            }
        } else {
            return -1;
        }
    }

}

