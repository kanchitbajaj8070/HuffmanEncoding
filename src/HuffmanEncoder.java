import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;

public class HuffmanEncoder {
    // data compression techniques to reduce the space required to store data
    // two hashmaps - onr for encoding and one for decoding
    //Step 1- pass the string called feeder
    //Step 2- make frequency map of character
    //Step 3 - for each key in frequency map , we need to create a node and insert that node
    // in a minheap or a Priority queue
    //Step 4 - loop through the elements in a heap and remove two element and combine them
    // and keep on doing this until the size of heap is 1;
    //cost of new node will be sum of both the nodes that were removed and insert back
    // the new node into the heap
    //Step 5 -> remove the last element of heap and transverse the tree and add 0 while
    //going left and add 1 while going right untill we reach a leaf
    //Step 6-> fill the encoder and decoder hashmaps
    //huffman encoder ensures that the code for any character is not a prefix of some
    // other character. This is the beauty of huffan encoder.

    HashMap<Character,String> encoder= new HashMap<>();
    HashMap<String,Character> decoder= new HashMap<>();
    HashMap<Character,Integer> frequency=new HashMap<>();
    public class Node implements Comparable<Node>
    {
        int cost;
        char data;
        Node left;
        Node right;
        Node(char c, int d)
        {
            data=c;
            cost=d;
        }

        @Override
        public int compareTo(Node o) {
            return this.cost-o.cost;
        }
    }
    public void buildHuffmanEncoder(String feeder)
    {
        for (int i = 0; i < feeder.length(); i++) {
            char c=feeder.charAt(i);
            if(frequency.containsKey(c))
            frequency.put( c, frequency.get(c)+1);
            else
                frequency.put(c,1);
        }
        PriorityQueue<Node> minHeap= new PriorityQueue<>();
        for(Map.Entry<Character,Integer> entry:frequency.entrySet())
        {
            Node nn= new Node( entry.getKey(),entry.getValue());
            minHeap.add(nn);
        }
        for(Map.Entry<Character,Integer> entry:frequency.entrySet())
        {
            System.out.println(entry.getKey()+" -> "+entry.getValue());
        }
        while(minHeap.size()>1)
        {
            Node first=minHeap.poll();
            Node second= minHeap.poll();
            if(first!=null&&second!=null) {
                Node nn = new Node('\0',first.cost+second.cost);
                nn.left=second;
                nn.right=first;
                minHeap.add(nn);
            }

        }

        fillEncoderAndDecoder( minHeap.remove(),"");
        printEncoderAndDecoder();
    }

    private void printEncoderAndDecoder() {
        System.out.println("**************");
        System.out.println("encoder is....");
        for(Map.Entry<Character,String> entry:encoder.entrySet())
        {
        System.out.println(entry.getKey()+" -> "+entry.getValue());
    }
        System.out.println("*************");
        System.out.println("DECODER IS......");
        for(Map.Entry<String,Character> entry:decoder.entrySet())
        {
            System.out.println(entry.getKey()+" -> "+entry.getValue());
        }
        System.out.println("**************");

}

    private void fillEncoderAndDecoder(Node node,String ans) {
        if(node==null)
            return;
        if(node.left==null&&node.right==null)
        {
            encoder.put(node.data,ans);
            decoder.put(ans,node.data);
            return;
        }
        fillEncoderAndDecoder(node.left,ans+0);
        fillEncoderAndDecoder(node.right,ans+1);

    }
    public String EncodeString( String a)
    {
        StringBuilder sb= new StringBuilder();
        for (int i = 0; i < a.length(); i++) {
            String s= this.encoder.get(a.charAt(i));
            sb.append(s);
        }
        return sb.toString();
    }
    public String DecodeString( String a)
    {
        StringBuilder sb= new StringBuilder();
        String key="";
        for (int i = 0; i < a.length(); i++) {
           key+=a.charAt(i);
        if( decoder.containsKey(key))
        {
            sb.append(decoder.get(key));
        key="";
        }

        }
        return sb.toString();
    }


}
