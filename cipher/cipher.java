import java.util.Scanner;

public class Cipher {

    static String map = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789!@#$%^&*()-_=+[]{};:',<.>/?";
    static String cea = "rqgbzn4m1=pBCKLAXowduDEG-QJxH_yM[{]:}23Z!(@kIv6+PetFsNTfO8Sa9RUYih7>/<cVWjl05%*)^#$&':,?.";

    public static void main(String[] args) {
        System.out.println(encrypt("somestring"));
        System.out.println(decrypt(encrypt("somestring")));
        Scanner s = new Scanner(System.in);
        String a = s.next();
    }

    static String encrypt(String s) {
        StringBuilder enc = new StringBuilder();
        for(int k = 0; k < s.length(); k++) {
            String n = String.valueOf(s.charAt(k));
            for(int i = 0; i < map.length(); i++) {
                String m = String.valueOf(map.charAt(i));
                if(n.equals(m)) {
                    enc.append(cea.charAt(i));
                }
            }
        }
        return enc.toString();
    }

    static String decrypt(String s) {
        StringBuilder enc = new StringBuilder();
        for(int k = 0; k < s.length(); k++) {
            String n = String.valueOf(s.charAt(k));
            for(int i = 0; i < cea.length(); i++) {
                String m = String.valueOf(cea.charAt(i));
                if(n.equals(m)) {
                    enc.append(map.charAt(i));
                }
            }
        }
        return enc.toString();
    }

}
