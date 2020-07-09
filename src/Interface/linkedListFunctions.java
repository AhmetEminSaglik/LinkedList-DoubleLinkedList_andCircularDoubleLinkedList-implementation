package Interface;

public interface linkedListFunctions<E> {

   
    public void addAtHead(E Type);

    public void addIndexOf(int index, E Type);

    public void addAtLast(E Type);

    public void removeValueOf(E Type); // start from Head, if much more same text or value,  will be removed first one

    public void removeAtIndex(int index);

    public void replace(int firstIndex, int SecondIndex);

    public void ifStringGetTextUsingTrim(E Type);

    public Object getValueOfIndex(int index);

    public int ListSize();
}
