package Business;

import Interface.linkedListFunctions;
import javax.swing.JOptionPane;

public class DoubleLinkedList implements linkedListFunctions<Object> {

    Node firstNode;
    Node lastNode;
    boolean Circular;

    public DoubleLinkedList(boolean Circular) {
        firstNode = null;
        this.Circular = Circular;
    }

    @Override
    public void addAtHead(Object Type) {
        ifStringGetTextUsingTrim(Type);
        if (this.firstNode == null) {

            this.firstNode = new Node(Type);
            this.lastNode = this.firstNode;
            firstNode.next = lastNode;
            lastNode.before = firstNode;
            if (isCircular()) {
                firstNode.before = lastNode;
                lastNode.next = firstNode;
            }
        } else {
            Node newNode = new Node(Type);
            newNode.next = this.firstNode;
            this.firstNode = newNode;
            this.firstNode.next.before = this.firstNode;
            firstNode.before = lastNode;
            lastNode.next = firstNode;

        }
    }

    @Override
    public void addIndexOf(int index, Object Type) {

        ifStringGetTextUsingTrim(Type);
        if (this.firstNode == null || index == 0) {
            if (this.firstNode == null) {
                ThrowException("OUT OF INDEX  --> NODE IS EMPTY");
                return;
            }
            addAtHead(Type);
            return;
        }
        Node newNode = new Node(Type);
        Node temp = this.firstNode;
        int counter = 1;

        while (true) { //temp.next != null

            if (counter == index) {

                break;
            }
            if (temp.next == firstNode) {
                System.out.println("---------------------");
                AyrintiliListele();
                ThrowException("OUT OF INDEX  --> (function) -->  getValueOfIndex -->\n"
                        + "your index : " + index + " || your Node size : " + ListSize());
                return;
            }
            temp = temp.next;
            counter++;

        }

        newNode.next = temp.next;
        newNode.before = temp;
        temp.next = newNode;

    }

    @Override
    public void addAtLast(Object Type) {
        ifStringGetTextUsingTrim(Type);
        if (this.firstNode == null) {
            addAtHead(Type);
        } else {
            Node newNode = new Node(Type);

            newNode.before = lastNode;
            lastNode.next = newNode;
            lastNode = newNode;
            lastNode.next = lastNode; // after last againg last  !!! if we want to last.next null than here must be null 
            if (isCircular()) { // if doubleLinked list is circular  then  we have to connect lastNode to firstNode node AND firstNode to LastNode
                newNode.next = firstNode;
                this.firstNode.before = lastNode;
            }

        }
    }

    @Override
    public void removeValueOf(Object Type) { // if there are much more same value remove first one

        ifStringGetTextUsingTrim(Type);
        Node temp = this.firstNode;
        if (this.firstNode == null) {
            System.out.println("Empty Node cant remove anything");
            return;
        }

        if (temp.data == Type) {
            this.firstNode = this.firstNode.next;
            lastNode.next = this.firstNode;
            DeletedInfo(Type);
            return;
        }

        while (temp.next != null && temp.next.data != Type) {

            //  JOptionPane.showMessageDialog(null, " 1 temp.data : " + temp.data + " / Type : " + Type);
            if (temp == lastNode) {
                ThrowException("did not found the value you want to remove  : " + Type);
                return;
            }
            System.out.println("temp.data  : " + temp.data + " /-> lastnode data : " + lastNode.data);
            temp = temp.next;

        }

        if (temp.next == null) {
            NotFoundInfo(Type);
        } else if (temp.next.data == Type) {
            //   JOptionPane.showMessageDialog(null, "IN IF \n temp.data : " + temp.data + " / Type : " + Type);
            boolean lastnodeChange = true;
            if (getValueOfIndex(ListSize() - 1) == lastNode.data) {
                lastnodeChange = true;
            }
            temp.next = temp.next.next;
            temp.next.before = temp.next.before.before;
            if (lastnodeChange == true) {

            }
            DeletedInfo(Type);

        }
    }

    @Override
    public void removeAtIndex(int index) {

        if (this.firstNode == null) {
            System.out.println("Empty Node cant remove anything");
            return;
        }
        if (index == 0) {
            firstNode = firstNode.next;
            lastNode.next = lastNode;
            firstNode.before = firstNode;
            if (isCircular()) {
                firstNode.before = lastNode;
                lastNode.next = firstNode;
            }
            return;
        }
        Node temp = this.firstNode;
        int counter = 1; // will remove index
        while (counter != index) {
            if (temp == lastNode) {
                return;
            }
            temp = temp.next;
            counter++;
        }
        if (counter == index) {
            boolean lastnodeChange = false;
            if (index == ListSize() - 1) {
                lastnodeChange = true;
                   }
            
            temp.next = temp.next.next;
            temp.next.before = temp;

            if (lastnodeChange) {
                
                AyrintiliListele();
                 
                lastNode = temp;
                lastNode.next = lastNode;
                if (isCircular()) {
                    lastNode.next = firstNode;
                }
              
                AyrintiliListele();
              
                
            } else {
                showMessage("son düpüm değiştir girmedi iii ");
            }

        } else {
            System.out.println("too much index  --> total index  [0," + counter + "] your index : " + index);

        }
    }

    @Override
    public void replace(int firstIndex, int SecondIndex) {
        String ExceptionText = "";
        if (firstIndex > SecondIndex) {
            ExceptionText = "first parameter must be smaller than second parameter\n"
                    + "-----------------------------------------------------\n"
                    + "your parameter  : replace(" + firstIndex + "," + SecondIndex + ") \n"
                    + "you have to write like this : replace(" + SecondIndex + "," + firstIndex;
            ThrowException(ExceptionText);

        } else if (firstIndex < 0) {
            ExceptionText = "first parameter must be  positive integer";
            ThrowException(ExceptionText);

        } else if (firstIndex == SecondIndex) {
            ExceptionText = "first parameter and second parameter must be different";
            ThrowException(ExceptionText);

        } else if (firstIndex < SecondIndex) {
            addIndexOf(firstIndex, getValueOfIndex(SecondIndex));

            removeAtIndex(SecondIndex + 1);

            addIndexOf(firstIndex + (SecondIndex - firstIndex + 1), getValueOfIndex(firstIndex + 1));

            removeAtIndex(firstIndex + 1);

        } else {
            System.out.println("unexpected situation");
        }

    }

    @Override
    public void ifStringGetTextUsingTrim(Object Type) {
        if (Type.getClass() == String.class) {
            String text = (String) Type;
            Type = text.trim();
        }
    }

    public void showMessage(String text) {
        JOptionPane.showMessageDialog(null, text);
    }

    public void showMessage(int text) {
        JOptionPane.showMessageDialog(null, text);
    }

    public void AyrintiliListele() {
        Node temp = this.firstNode;
        System.out.println("||||||||||||||||||||||||||");
        int counter = 0;

        while (true) {

            System.out.println("(" + counter + ")   before : " + temp.before.data + " temp.data : " + temp.data + " / next  : " + temp.next.data);
            // showMessage("(" + counter + ")temp.data : " + temp.data);
            if (isCircular() == false) {
                if (temp == lastNode) {
                    return;
                }
            }
            temp = temp.next;
            counter++;

            if (isCircular()) {
                if (temp == firstNode) {
                    return;
                }
            }

        }

    }

    public void listele() {
        Node temp = this.firstNode;

        int counter = 0;
        System.out.println("size : " + ListSize());
        while (true) {

            System.out.println("(" + counter + ")   temp.data : " + temp.data);
            if (isCircular() == false) {

                if (temp == lastNode) {// 
                    System.out.println("çıktı2");
                    return;
                }
            }
            temp = temp.next;
            counter++;

            if (isCircular()) {
                if (temp == firstNode) {// 

                    System.out.println("çıktı1");
                    return;
                }
            }
        }

    }

    public void DeletedInfo(Object text) {
        System.out.println(" DELETED : " + text);
    }

    public void NotFoundInfo(Object text) {
        System.out.println("Did not found :" + text);
    }

    public boolean isCircular() {
        return Circular;
    }

    @Override
    public Object getValueOfIndex(int index) {
        if (firstNode == null) {
            System.out.println("DID NOT ADD ANY VALUE ");
        }
        int counter = 0;
        Node temp = this.firstNode;

        if (isEqualOrGreaterThanZero(index) == false) {

            ThrowException("Index must be equal 0 or greater than 0 --> your index : " + index);

        }

        while (counter != index) {
            if (isCircular()) {
                if (temp.next == firstNode) {
                    ThrowException("OUT OF INDEX  --> (function) -->  getValueOfIndex -->\n"
                            + "your index : " + index + " || your Node size : " + ListSize());

                }
            } else {
                if (temp == lastNode) {
                    ThrowException("OUT OF INDEX  --> (function) -->  getValueOfIndex -->\n"
                            + "your index : " + index + " || your Node size : " + ListSize());
                }
            }
            temp = temp.next;
            counter++;
        }

        //System.out.println("OUT OF INDEX  --> (function) -->  getValueOfIndex");
        /*    throw new Exception("OUT OF INDEX  --> (function) -->  getValueOfIndex -->\n"
                    + "your index : " + index + " || your Node size : " + ListSize());*/
        return temp.data;

    }

    @Override
    public int ListSize() {
        Node temp = this.firstNode;
        if (this.firstNode == null) {
            return 0;
        } else {
            int counter = 1;

            while (true) {
                if (isCircular()) {
                    if (temp.next == firstNode) {
                        return counter;
                    }
                } else {
                    if (temp == lastNode) {

                        return counter;
                    }
                }
                counter++;
                temp = temp.next;
            }
        }
    }

    boolean isEqualOrGreaterThanZero(int number) {
        if (number >= 0) {
            return true;
        }
        return false;
    }

    public void ThrowException(String text) {
        try {
            throw new Exception(text);
        } catch (Exception ex) {
            ex.printStackTrace();
            System.exit(0);
        }
    }
}
