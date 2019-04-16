package Task2;

import java.lang.reflect.*;
import java.lang.reflect.Field;


public class TestClass {

   public static void Analysis (Object o) {
       try {
           Class clss = o.getClass(); int i, ii;

           System.out.println("Поля класса: \n");
           Field[] fields = clss.getDeclaredFields();
           for (Field field : fields) {
               System.out.println(field);
               field.setAccessible(true);
               System.out.print(field.get(o) + "\n");
           }

           System.out.println("\n");
           System.out.println("Открытые методы класса: \n");
           Method[] methods = clss.getDeclaredMethods();

           for (i = 0; i < methods.length - 1; i++)
               System.out.println(i+1 + ") " + methods[i]);

           java.util.Scanner in = new java.util.Scanner(System.in);
           try {
               do {
                   System.out.println("\nВведите порядковый номер: ");
                   ii = in.nextInt();
               } while ((ii<1) || (ii>i));
               Parameter[] parameters = methods[ii-1].getParameters();
               if (parameters.length == 0) {
                   System.out.print("Результат вызова метода: ");
                   System.out.println(methods[ii - 1].invoke(o));
               }
               else
                   System.out.println("Метод содержит параметры.");
           } catch (Throwable ex) {
               ex.printStackTrace();
           }

       } catch (Throwable e) {
           e.printStackTrace();
       }
   }

    public static void main(String[] args) {
        TestCase test = new TestCase();
        Analysis(test);

    }
}

class TestCase {
    private int i = 9;
    private static final float MAX = 100;

    public TestCase() {
        i = 22;
    }

    private TestCase(int i) {
        this.i = i;
    }

    public void Testing() {
        System.out.println("Testing...");
    }

    private static void Deleting() {
        System.out.println("Deleting...");
    }

    public int Value(int v) {
        System.out.println(v);
        return v + 3;
    }
}