package src;
import src.*;
import sun.reflect.generics.tree.Tree;

import java.util.*;

public class Solution {
    public static void main(String[] args) {

        List<List<Integer>> ret = September.generate(5);
        System.out.println(ret);

    }

    //605
    public boolean canPlaceFlowers(int[] flowerbed, int n) {
        int plantNum = 0;
        for (int i = 0; i < flowerbed.length; i++) {
            if (flowerbed[i] == 0) {
                if (i == 0 || flowerbed[i - 1] == 0) {
                    if (i == flowerbed.length - 1 || flowerbed[i + 1] == 0) {
                        flowerbed[i] = 1;
                        plantNum++;
                        if (plantNum == n) {
                            return true;
                        }
                    }
                }
            }
        }
        if (plantNum < n) {
            return false;
        }
        return true;
    }

}
