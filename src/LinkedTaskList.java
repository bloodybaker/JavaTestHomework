import java.util.*;

public class LinkedTaskList extends TaskList{
    private Node first;
    private Node last;
    private int size;
    public void add(Task task) {
        try {Task tempTask = (checkTask(task));
            Node newNode = new Node(tempTask);
            if (first == null) {
                newNode.setNext(null);
                newNode.setPrevious(null);
                first = newNode;
                last = newNode;
            } else {
                last.setNext(newNode);
                newNode.setPrevious(last);
                last = newNode;
            }
            size++;
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
    public boolean remove(Task task) {
        if (removeElement(first,task) == null){
            return false;
        }else {
            size--;
            return true;
        }
    }
    public Node removeElement (Node e,Task task){

        if (e.getElement().equals(task)){
            if (first.equals(e)){
                first = e.getNext();
            }
            if (last.equals(e)){
                last = e.getPrevious();
            }
            if (e.getNext() !=null) {
                if (e.getElement() !=null) {
                    e.getNext().setPrevious(e.getPrevious());
                }else {
                    e.getNext().setPrevious(null);
                }
            }
            if (e.getPrevious() !=null) {
                if (e.getNext() !=null) {
                    e.getPrevious().setNext(e.getNext()) ;
                }else {
                    e.getPrevious().setNext(null);
                }
            }

            return e;
        }else {
            if (e.getNext() == null){
                return null;
            }else {
                return removeElement(e.getNext(),task);
            }
        }
    }
    public Task getTask(int index) {
        try {
            return find(first, 0, index);
        }
        catch (ArgumentException b) {
            System.out.println("Invalid Argument");
            return null;
        }
    }
    public Task find(Node e,int currIndex, int index ) {
        if (currIndex == index){
            return e.getElement();
        }else {
            if (e.getNext() != null) {
                return find(e.getNext(), ++currIndex, index);
            }else {
                return null;
            }
        }
    }
    public int size(){
        return size;
    }
    public TaskList newTaskList(TaskList list){
        LinkedTaskList tmp = new LinkedTaskList();
        tmp.first = first;
        tmp.last = last;
        tmp.size = size;
        return tmp;
    }
    @Override
    public Iterator iterator() {
        return new LinkedIterator();
    }
    private class LinkedIterator implements Iterator {

        private Node lastRet = new Node(null,LinkedTaskList.this.first,null);
        private Node cursor = lastRet.getNext();

        public boolean hasNext() {
            return lastRet.getNext() != null;
        }
        public Task next() {
            if(lastRet.getNext() != null){
                lastRet = cursor;
                cursor = cursor.getNext();
                return lastRet.getElement();
            }else{
                throw new NoSuchElementException();
            }
        }
        public void remove() {
            if (lastRet.getElement() == null)
                throw new IllegalStateException();
            removeElement(lastRet,lastRet.getElement());
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        LinkedTaskList that = (LinkedTaskList) o;
        if (that.size == this.size) {
            for (int i = 0;i < this.size;i++){
                if (!this.getTask(i).equals(that.getTask(i))){
                    return false;
                }
            }
            return true;
        }else {
            return false;
        }
    }
    @Override
    public int hashCode() {
        Task hashTmp[] = new Task[size];
        Node e = first;
        for(int i = 0; e != null;i++){
            hashTmp[i] = e.getElement();
            e = e.getNext();
        }
        return Arrays.hashCode(hashTmp);
    }
    @Override
    public String toString() {
        return "LinkedTaskList{" +
                "first=" + first +
                ", last=" + last +
                ", size=" + size +
                '}';
    }
}
