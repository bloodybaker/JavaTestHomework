import java.util.*;

public class ArrayTaskList extends TaskList {

    Task[] tasks = new Task[0];
    public void add(Task task){
        try {Task checkTask = checkTask(task);
            tasks = addElement(tasks,checkTask);
        }
        catch (TaskException a){
            System.out.println("Task is empty.");
        }
        catch (IntervalException b) {
            System.out.println("Interval <= 0.");
        }
        catch (ArgumentException c) {
            System.out.println("Argument < 0.");
        }
    }
    public int lookFor(Task[] elements, Task element1){
        for (int i = 0; i<elements.length; i++){
            if (elements[i].equals(element1))
                return i;
        }return -1;
    }
    public boolean remove(Task task){
        if (lookFor(tasks,task)== -1){
            return  false;
        }else{
            tasks = removeElement(tasks,lookFor(tasks,task));
            return true;
        }
    }
    public int size(){
        return tasks.length;
    }
    public Task getTask(int index) {
        try {
            int x1 = checkFindValue(index);
            return tasks[x1];
        } catch (ArgumentException e) {
            System.out.println("Out of Array ");
            return  null;
        }
    }
    Task[] addElement(Task[] a, Task e) {
        a  = Arrays.copyOf(a, a.length + 1);
        a[a.length - 1] = e;
        return a;
    }
    Task[] removeElement(Task[] begin, int element) {
        Task[] re = new Task[begin.length - 1];
        System.arraycopy(begin, 0, re, 0, element);
        System.arraycopy(begin, element + 1, re, element, begin.length - element - 1);
        return re;
    }
    public TaskList newTaskList(TaskList list){
        ArrayTaskList tmp = new ArrayTaskList();
        tmp.tasks = tasks;
        return tmp;
    }
    @Override
    public Iterator<Task> iterator() {
        return new ArrayIterator();
    }
    private class ArrayIterator implements Iterator<Task> {
        int cursor;       // index of next element to return
        int lastRet = -1; // index of last element returned; -1 if no such


        public boolean hasNext() {
            return cursor != size();
        }

        public Task next() {
            if (cursor >= size()) {
                throw new NoSuchElementException();
            }
            return  ArrayTaskList.this.tasks[lastRet = cursor++];
        }

        public void remove() {
            if (lastRet < 0)
                throw new IllegalStateException();
            try {

                ArrayTaskList.this.remove(getTask(lastRet));
                cursor = lastRet;
                lastRet = -1;
            } catch (IndexOutOfBoundsException ex) {
                throw new ConcurrentModificationException();
            }
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ArrayTaskList that = (ArrayTaskList) o;
        for (int i = 0; i < size(); i++) {
            if (!that.tasks[i].equals(that.tasks[i])) { 
                return false;
            }
        }
        return true;
    }
    @Override
    public int hashCode() {
        return Arrays.hashCode(tasks);
    }

    @Override
    public String toString() {
        return "ArrayTaskList{" +
                "tasks=" + Arrays.toString(tasks) +
                '}';
    }
}

