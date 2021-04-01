package main.common;

public class Test3 {

    private static final String[] lowerLetters = {"a","b","c","d","e","f","g","h","i","j","k","l","m"
                    ,"n","o","p","q","r","s","t","u","v","w","x","y","z"};

    public static void main(String[] args) {
        System.out.println(testRandomLetter(8) + "-" + testRandomLetter(4) + "-" + testRandomLetter(4) + "-" + testRandomLetter(4) + "-"
        + testRandomLetter(8));
    }

    public static String testRandomLetter(int len){
        StringBuilder s = new StringBuilder("");
        for(int j=0;j<len;j++){
            s.append(lowerLetters[((int)(Math.random()*26))]);
        }
        return s.toString();
    }
}
