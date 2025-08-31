import java.util.ArrayList;

public class LinkedListMain {
    public static void main(String[] args) {
        ArrayList<LinkedList> llArr = new ArrayList<>();
        llArr.add(new LinkedList(3, 2));  // idx0
        llArr.add(new LinkedList(1, 3));  // idx1
        llArr.add(new LinkedList(4, -1)); // idx2
        llArr.add(new LinkedList(2, 0));  // idx3
        

        // add element
        LinkedList.add(llArr, 5, 2);

        // head finder
        int head = LinkedList.headFinder(llArr);
        
        // can delete after finding head 
        head = LinkedList.delete(llArr, head, 1);

        // print logically
        LinkedList.printSorted(llArr, head);
    }
}

class LinkedList {
    int data;
    int ni;

    LinkedList(int d, int n) {
        data = d;
        ni = n;
    }

    // find list head index
    static int headFinder(ArrayList<LinkedList> arr){
        boolean[] headFind = new boolean[arr.size()];
        for (int i = 0; i < arr.size(); i++) {
            if (arr.get(i).ni != -1) {
                headFind[arr.get(i).ni] = true;
            }
        }

        int head = -1;
        for (int i = 0; i < headFind.length; i++) {
            if (!headFind[i]) {
                head = i;
                break;
            }
        }

        return head;
    }

    // add element function
    static void add(ArrayList<LinkedList> arr, int number, int prevIdx) {
        LinkedList newNode = new LinkedList(number, arr.get(prevIdx).ni);
        arr.add(newNode);
        int newIndex = arr.size() - 1;
    
        arr.get(prevIdx).ni = newIndex;
    }

    // delete element
    static int delete(ArrayList<LinkedList> arr, int head, int key){
        int currIdx = head;
        int prevIdx = -1;

        while (currIdx != -1 && arr.get(currIdx).data != key) {
            prevIdx = currIdx;
            currIdx = arr.get(currIdx).ni;
        }

        if (currIdx == -1) {
            return head; // not found
        }

        if (prevIdx == -1) {
            head = arr.get(head).ni; // delete head
        }else {
            arr.get(prevIdx).ni = arr.get(currIdx).ni; // bypass current
        }

        return head;
    }

    // print data in sorted order
    static void printSorted(ArrayList<LinkedList> arr, int head){
        for (int i = head; i != -1; i = arr.get(i).ni) {
            System.out.println(arr.get(i).data);
        }
    }
    
}