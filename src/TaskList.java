public abstract class TaskList {

    public abstract void add(Task task);
    public abstract int lookFor(Task[] elements, Task element1);
    public abstract boolean remove(Task task);
    public abstract int size();
    public abstract Task getTask(int index);
    public abstract Task[]  addElement(Task[] a, Task e);
    public abstract Task[] removeElement(Task[] begin, int element);
    public abstract  int[] addElementInBuff(int[] a, int e);
    public abstract TaskList incoming(int from, int to);
}
