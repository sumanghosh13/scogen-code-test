package compare;

import java.util.Arrays;
import java.util.Date;
import java.util.Random;
import java.util.stream.IntStream;

public class RandomNumberGenerator {

    public static void main(String[] args) {
        Random rd = new Random();
        QuikSortL lamuthQiuckSort = new QuikSortL();
        QuickSortH hoarsQuickSort = new QuickSortH();
        for (Integer num_range : Arrays.asList(10,100,1_000, 10_000, 1_00_000, 10_00_000, 1_00_00_000)) { //

            IntStream input = rd.ints(num_range, 0, 1000);
            int [] inputArray = input.toArray();
            int len = inputArray.length;
            Long lamuthStart = new Date().getTime();
            lamuthQiuckSort.quickSortL(inputArray, 0 , len-1);
            System.out.println(String.format("time taken by lamuth's for %d elements is %d milli seconds"
                    ,len, (new Date().getTime()- lamuthStart)));
            IntStream inputForHoars = rd.ints(num_range, 0, 1000);
            int [] inputHArray = inputForHoars.toArray();
            Long hoarsStart = new Date().getTime();
            len = inputHArray.length;
            hoarsQuickSort.quickSortH(inputHArray, 0 , len-1);
            System.out.println(String.format("time taken by Hoars's for %d elements is %d milli seconds"
                    ,len, (new Date().getTime()- hoarsStart)));

        }

    }
}
