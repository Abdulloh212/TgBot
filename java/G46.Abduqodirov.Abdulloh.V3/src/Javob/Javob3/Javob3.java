package Javob.Javob3;

import java.util.Scanner;

public class Javob3 {
    public static void main(String[] args) {
        Scanner scanner =new Scanner(System.in);
        Speaker speaker=new Speaker();
       while (true) {
           System.out.println("""
                   1.On/Off
                   2.Volume
                   """);
           switch (scanner.nextInt()) {
               case 1:
                   if (speaker.isStatus() == false) {
                       speaker.setStatus(true);
                       System.out.println("Yoqildi!");
                   } else if (speaker.isStatus() == true) {
                       speaker.setStatus(false);
                       System.out.println("O'chirildi!");
                   }
                   break;
               case 2:
                   while (true) {
                       if (speaker.isStatus() == false) {
                           System.out.println("O'chiq ");
                           break;
                       } else {
                           System.out.print("Krting: ");
                           int volume = scanner.nextInt();
                           if (volume < 0 || volume > 30) {
                               System.out.println("Eng past daraja 0  Eng yuqori daraja 30 ");
                           } else {
                               speaker.setVolume(volume);
                               break;
                           }
                       }
                   }
                   break;
           }
       }
    }
}
