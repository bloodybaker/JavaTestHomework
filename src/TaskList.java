import java.util.Arrays;

public abstract class TaskList implements Iterable,Cloneable {

    public abstract void add(Task task);

    public abstract boolean remove(Task task) ;

    public abstract int size();

    public abstract Task getTask(int index) ;

    public abstract TaskList newTaskList(TaskList list);

    public TaskList incoming(int from, int to) {
        int indexTmp[] = new int[0];
        TaskList buff = null;
        try {
            buff = this.clone();
        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
        }
        for (int i = 0; i < buff.size(); i++) {
            if (buff.getTask(i).nextTimeAfter(from)> to || buff.getTask(i).nextTimeAfter(from) == -1) {
                indexTmp = addElementInBuff(indexTmp, i);
            }
        }
        int tmp = 0;
        for (int i = 0; i < indexTmp.length; i++)
        {
            if (buff.remove(buff.getTask(indexTmp[i] - tmp))) {
                tmp++;
            }
        }
        return buff;
    }
    int[] addElementInBuff(int[] a, int e) {
        a = Arrays.copyOf(a, a.length + 1);
        a[a.length - 1] = e;
        return a;
    }
    public Task checkTask(Task x){
        if (x.getTime() >= 0 && x.getEndTime()>=0 && x.getEndTime() >=0){
            if (x.getRepeatInterval() > 0 || !x.isRepeated()){
                if (x != null){
                    return x;
                }else {
                    throw new TaskException("Task is empty");
                }
            }else {
                throw new IntervalException("Illegal Interval");
            }
        }else {
            throw new ArgumentException("Time <= 0");
        }
    }
    public int checkFindValue(int x){
        if (x >= size()){
            throw new ArgumentException("Element is outside the array");
        }else {
            return x;
        }
    }
    @Override
    public TaskList clone() throws CloneNotSupportedException {
        return (TaskList) super.clone();
    }
}
