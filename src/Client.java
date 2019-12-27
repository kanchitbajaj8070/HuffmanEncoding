import java.util.HashSet;

public class Client {
    public static void main(String[] args) {
        HuffmanEncoder encoder= new HuffmanEncoder();
        StringBuilder sb= new StringBuilder();
        for (int i = 1; i <=60 ; i++) {
            sb.append('a');
            if(i<=30)
            sb.append("b");
            if(i<=8)
                sb.append('c');
            if(i<=2)
                sb.append('d');
        }

        encoder.buildHuffmanEncoder(sb.toString());
        String encodedString=encoder.EncodeString(sb.toString());
        System.out.println(encodedString);
        String decodedString=encoder.DecodeString(encodedString);
        System.out.println(decodedString);
    }

}
