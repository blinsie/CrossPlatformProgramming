package Task4;

import java.lang.reflect.Array;
import java.util.Arrays;

public class BaseClass {

    public static Object arrayCreater(Class clss, int size){
        return Array.newInstance(clss, size);
    }

    public static Object matrixCreater (Class clss, int size) {
        return Array.newInstance(clss, size, size);
    }

    public static Object changeArraySize(Object arr, int new_size) {
        Class c = arr.getClass();
        Object newArr = Array.newInstance(c.getComponentType(), new_size);

        int oldSize = Array.getLength(arr);
        if (oldSize < new_size) new_size = oldSize;

        System.arraycopy(arr,0, newArr,0, new_size);
        return newArr;
    }

    public static Object changeMatrixSize(Object matrix, int new_size) {
        Class c = matrix.getClass();
        Object newMatrix = matrixCreater(c.getComponentType().getComponentType(), new_size);

        int oldSize = Array.getLength(matrix);

        if (oldSize < new_size) {
            for (int i = 0; i < oldSize; i++)
                Array.set(newMatrix, i, changeArraySize(Array.get(matrix, i), new_size));
            for (int i = oldSize; i < new_size; i++)
                Array.set(newMatrix, i, arrayCreater(c.getComponentType().getComponentType(), new_size));
        } else {
            for (int i = 0; i < new_size; i++)
                Array.set(newMatrix, i, changeArraySize(Array.get(matrix, i), new_size));
        }

        return newMatrix;
    }

    public static void main(String[] args) {

        System.out.println("\tARRAY");

        String[] array = (String[]) arrayCreater(String.class, 5);
        for (int i = 0; i < array.length; i++)
            array[i] = "Good job";
        System.out.println(toString(array));

        array = (String[]) changeArraySize(array, 10);
        System.out.println(Arrays.toString(array));

        System.out.println("\tMATRIX");

        int[][] matrix = (int[][]) matrixCreater(int.class, 5);
        for (int i = 0; i < matrix.length; i++)
            matrix[i][i] = i+5;
        System.out.println(Arrays.deepToString(matrix));

        matrix = (int[][]) changeMatrixSize(matrix, 6);
        System.out.println(toString(matrix));

    }

    private static String toString(Object array) {
        StringBuilder builder = new StringBuilder();
        Class clss = array.getClass();
     //   System.out.println(clss.toString());

        if (!clss.isArray()) {
            builder.append(array.toString());
        } else {
            int size = Array.getLength(array);

            for (int i = 0; i < size; i++) {
                if (i == 0) builder.append("{");
                Object val = Array.get(array, i);
                builder.append(toString(val));
                if (i != size-1)
                    builder.append(", ");
                else builder.append("}");
            }
        }
        return builder.toString();
    }

}
