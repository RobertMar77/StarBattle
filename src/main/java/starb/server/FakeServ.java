package starb.server;

import java.util.ArrayList;

public class FakeServ {

    private static ArrayList<int[][]> answers;
    private static ArrayList<int[][]> regions;

    public FakeServ(){
        answers= new ArrayList<>();
        regions= new ArrayList<>();
        int[][] answer1 = {
                {0, 1, 0, 0, 1, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 1, 0, 1, 0},
                {0, 1, 0, 1, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 1, 0, 1},
                {0, 0, 0, 1, 0, 1, 0, 0, 0, 0},
                {1, 0, 0, 0, 0, 0, 0, 1, 0, 0},
                {0, 0, 1, 0, 1, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 1, 0, 1, 0},
                {1, 0, 1, 0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 1, 0, 0, 0, 1}
        };


        int[][] region1 = {
                {1, 1, 1, 1, 1, 1, 1, 1, 2, 2},
                {1, 1, 1, 1, 1, 1, 5, 1, 2, 2},
                {3, 4, 4, 4, 1, 5, 5, 5, 2, 2},
                {3, 3, 3, 3, 6, 7, 7, 5, 2, 2},
                {3, 3, 3, 6, 6, 6, 7, 7, 2, 2},
                {3, 3, 8, 6, 6, 6, 7, 7, 2, 2},
                {3, 3, 8, 8, 8, 7, 7, 7, 9, 2},
                {3, 3, 3, 3, 3, 3, 7, 7, 9, 2},
                {3, 10, 10, 10, 10, 10, 10, 10, 9, 9},
                {10, 10, 10, 10, 10, 10, 10, 10, 10, 9}
        };


        answers.add(answer1);

        regions.add(region1);
    }


    public int[][] getAnswer(int i){
        return answers.get(i);
    }

    public int[][] getLayout(int i){
        return regions.get(i);
    }

}
