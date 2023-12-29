package Utils;

import java.util.Scanner;

public class InnputManger {
    Scanner sc = new Scanner(System.in);

  public int getIntValue(String msg, int start, int end) {
    while (true) {
      System.out.printf("▶ %s[ %d - %d ] 입력: ", msg, start, end);
      try {
        int num = sc.nextInt();
        sc.nextLine();
        if (num < start || num > end) {
          System.out.printf("%d - %d 사이값 입력해주세요 %n", start, end);
          continue;
        }
        return num;
      } catch (Exception e) {
        sc.nextLine();
        System.out.println("숫자값을 입력하세요");
      }
    }
  }

  public String getStringValue(String msg) {
    System.out.printf("▶ %s 입력: ", msg);
    return sc.next();
  }

}
