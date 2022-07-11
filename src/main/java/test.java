import java.util.Scanner;

public class test {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        String str = in.next();
        //String str = "66666666664123+Who-32didn't love? Solo32..";
        //str = str.replaceAll("[0-9]|\\pP","");
        String[] list = str.split("[0-9]|\\pP|[ ]|[\\\\+\\\\-\\\\*\\\\/]");
        String sesult = "";
        for (String stritem:list
             ) {
            if(!"".equals(stritem)){
                stritem = stritem.substring(0,1).toUpperCase()+stritem.substring(1);
                sesult += stritem+" ";
            }
        }
        System.out.println(sesult.substring(0,sesult.length()-1)+".");
    }
}
