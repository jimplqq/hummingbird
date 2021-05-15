package controllers.jav;

import play.mvc.Controller;
import play.mvc.Result;

/** @author qingyun */
public class Application extends Controller {

  public Result index() {
    return ok("hello world");
  }

  public static void main(String[] args) {
    System.out.println(String.format("%0" + 2 + "d", 1));
    //
    int m = 9;
    int max = m * m;
    int length = String.valueOf(max).length();
    System.out.println(length);
    String[][] arr = new String[m][m];

    int dd = 0;
    int firstLoop = m - 1;
    for (int i = 0; i < m; i++) {
      //      System.out.println("firstLoop:" + firstLoop);
      m = m - 1;
      dd = a(arr, dd, i, i, firstLoop, length);
      if (dd > max) {
        break;
      }
      dd = b(arr, dd, i, m, firstLoop, length);
      if (dd > max) {
        break;
      }
      dd = c(arr, dd, m, m, firstLoop, length);
      if (dd > max) {
        break;
      }
      dd = d(arr, dd, m, i, firstLoop, length);
      if (dd > max) {
        break;
      }
      firstLoop = firstLoop - 2;
    }

    for (int x = 0; x < arr.length; x++) {
      for (int y = 0; y < arr[x].length; y++) {
        System.out.print("     " + arr[x][y] + "     ");
      }
      System.out.println("");
    }
  }

  public static int d(String[][] arr, int s, int row, int column, int loop, int length) {
    //    System.out.println("d");
    for (int i = 0; i < loop; i++) {
      s = s + 1;
      //      System.out.println("row:" + (row - i) + ",i:" + column + ",s:" + s);
      arr[row - i][column] = String.format("%0" + length + "d", s);
    }
    return s;
  }

  public static int c(String[][] arr, int s, int row, int column, int loop, int length) {
    //    System.out.println("c");
    for (int i = 0; i < loop; i++) {
      s = s + 1;
      //      System.out.println("row:" + row + ",i:" + (column - i) + ",s:" + s);
      arr[row][column - i] = String.format("%0" + length + "d", s);
    }
    return s;
  }

  public static int b(String[][] arr, int s, int row, int column, int loop, int length) {
    //    System.out.println("b");
    for (int i = 0; i < loop; i++) {
      s = s + 1;
      //      System.out.println("row:" + (row + i) + ",i:" + column + ",s:" + s);
      arr[row + i][column] = String.format("%0" + length + "d", s);
    }
    return s;
  }

  public static int a(String[][] arr, int s, int row, int column, int loop, int length) {
    if (loop == 0) {
      //      System.out.println("row:" + row + ",i:" + (column) + ",s:" + s);
      arr[row][column] = String.format("%0" + length + "d", s + 1);
    }
    //    System.out.println("a");
    for (int i = 0; i < loop; i++) {
      s = s + 1;
      //      System.out.println("row:" + row + ",i:" + (column + i) + ",s:" + s);
      arr[row][column + i] = String.format("%0" + length + "d", s);
    }
    return s;
  }
}
