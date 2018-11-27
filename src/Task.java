import java.util.Objects;

public class Task implements Cloneable {

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
    public void setTitle(String title) {
        this.title = title;
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
        start = 0;
        end = 0;
        interval = 0;
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
        time = 0;
        repeated = false;
    }

    public boolean isRepeated() {
        return !repeated;
    }
    public boolean isActive(){
        return active;
    }
    public int nextTimeAfter(int current) {
        if (active) {
            if (repeated) {
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Task task = (Task) o;

        if (time != task.time) return false;
        if (start != task.start) return false;
        if (end != task.end) return false;
        if (interval != task.interval) return false;
        if (active != task.active) return false;
        if (repeated != task.repeated) return false;
        return title != null ? title.equals(task.title) : task.title == null;
    }
    @Override
    public int hashCode() {
        return Objects.hash(title, time, start, end, interval, active, repeated);
    }
    @Override
    public String toString() {
        return "Task{" +
                "title='" + title + '\'' +
                ", time=" + time +
                ", start=" + start +
                ", end=" + end +
                ", interval=" + interval +
                ", active=" + active +
                ", repeated=" + repeated +
                '}';
    }
}
