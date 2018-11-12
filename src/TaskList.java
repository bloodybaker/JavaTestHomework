import java.util.Arrays;
public abstract class TaskList {

    Task[] tasks = new Task[0];
    public void add(Task task){
        tasks = addElement(tasks,task);
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
    public Task getTask(int index){
        return tasks[index];
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
   int[] addElementInBuff(int[] a, int e) {         
       a = Arrays.copyOf(a, a.length + 1);         
       a[a.length - 1] = e;         
       return a;     
   } 
public TaskList incoming(int from, int to) {         
    int indexTmp[] = new int[0];         
    TaskList buff = this;         
    for (int i = 0; i < tasks.length; i++) {                           
                if (buff.tasks[i].nextTimeAfter(from)> to || buff.tasks[i].nextTimeAfter(from) == -1) {                             
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
}
