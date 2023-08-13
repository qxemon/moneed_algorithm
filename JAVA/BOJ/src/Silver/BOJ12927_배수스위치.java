package Silver;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class BOJ12927_배수스위치 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        char[] switchs;
        switchs = br.readLine().toCharArray();
        int count = 0;

        for(int i = 0; i<switchs.length; i++){
            if(switchs[i]=='N') continue;
            else{
                for(int j=i+1;j<=switchs.length;j+= 1 * (i+1)) {
                    switchs[j - 1] = switchs[j - 1] == 'Y' ? 'N' : 'Y';
                }

                count++;
            }

        }

        for(int i=0; i<switchs.length;i++){
            if(switchs[i] == 'Y') count = -1;
        }

        System.out.println(count);



    }
}
