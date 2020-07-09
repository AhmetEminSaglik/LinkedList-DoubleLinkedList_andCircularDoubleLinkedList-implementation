package Business;

import Interface.linkedListFunctions;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

public class LinkedList implements linkedListFunctions<Object> {

    Node firstNode;

    public LinkedList() {
        firstNode = null;
    }

    @Override
    public void addAtHead(Object Type) {

        ifStringGetTextUsingTrim(Type);
        if (this.firstNode == null) {

            this.firstNode = new Node(Type);

        } else {
            Node newNode = new Node(Type);
            newNode.next = this.firstNode;
            this.firstNode = newNode;
        }
    }

    @Override
    public void addIndexOf(int index, Object Type) {
        ifStringGetTextUsingTrim(Type);
        if (this.firstNode == null || index == 0) {
            if (this.firstNode == null) {
                System.out.println("OUT OF INDEX  --> NODE IS EMPTY");
                showMessage("OUT OF INDEX ");
                return;
            }
            addAtHead(Type);
            return;
        }
        Node newNode = new Node(Type);
        Node temp = this.firstNode;
        int counter = 1;
        while (temp.next != null) {
            if (counter == index) {
                break;
            }
            counter++;
            temp = temp.next;

        }
        newNode.next = temp.next;
        temp.next = newNode;

    }

    @Override
    public void addAtLast(Object Type) {
        ifStringGetTextUsingTrim(Type);
        Node newNode = new Node(Type);
        if (this.firstNode == null) {
            this.firstNode = newNode;
        } else {
            Node temp = firstNode;
            while (temp.next != null) {
                temp = temp.next;
            }
            temp.next = newNode;
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
            DeletedInfo(Type);
            return;
        }
        while (temp.next != null && temp.next.data != Type) {
            temp = temp.next;

        }

        if (temp.next == null) {
            NotFoundInfo(Type);
        } else if (temp.next.data == Type) {
            temp.next = temp.next.next;
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
            return;
        }
        Node temp = this.firstNode;
        int counter = 1; // will remove index
        while (counter != index) {
            if (temp.next.next == null) {
                System.out.println("OUT OF INDEX  --> |your Index : " + index + " / Size : " + counter + "|-->   did not remove ");
                return;
            }
            temp = temp.next;
            counter++;

        }
        if (counter == index) {
            System.out.println("COUNTER : " + counter + " / INDEX : " + index + " data :" + temp.data);

            temp.next = temp.next.next;

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
    public void ifStringGetTextUsingTrim(Object Type) { // If input is string return  of input after trim it
        if (Type.getClass() == String.class) {
            String text = (String) Type;
            Type = text.trim();
        }
    }

    public void AyrintiliListele() {
        Node temp = this.firstNode;

        int counter = 1;
        while (true) {
            System.out.println("||||||||||||||||||||||||||");
            System.out.println("(" + counter + ")  temp.data : " + temp.data + " / next  : " + temp.next.data);
            // showMessage("(" + counter + ")temp.data : " + temp.data);
            temp = temp.next;
            counter++;

            if (temp.next == null) {
                return;
            }

        }

    }

    public void listele() {
        Node temp = this.firstNode;
        int counter = 0;
        while (temp != null) {
            System.out.println("(" + counter + ")temp.data : " + temp.data);
            temp = temp.next;
            counter++;
        }

    }

    public void showMessage(String text) {
        JOptionPane.showMessageDialog(null, text);
    }

    public void showMessage(int text) {
        JOptionPane.showMessageDialog(null, text);
    }

    public void DeletedInfo(Object text) {
        System.out.println(" DELETED : " + text);
    }

    public void NotFoundInfo(Object text) {
        System.out.println("Did not found :" + text);
    }

    @Override
    public Object getValueOfIndex(int index) {
        if (firstNode == null) {
            System.out.println("DID NOT ADD ANY VALUE ");
        }
        int counter = 0;
        Node temp = this.firstNode;
        while (counter != index && temp != null) {
            temp = temp.next;
            counter++;
        }
        if (temp == null) {

            ThrowException("OUT OF INDEX  --> (function) -->  getValueOfIndex -->\n"
                    + "your index : " + index + " || your Node size : " + ListSize());

        }
        return temp.data;
    }

    @Override
    public int ListSize() {
        Node temp = this.firstNode;
        int counter = 0;
        while (temp != null) {
            counter++;
            temp = temp.next;
        }
        return counter;
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
