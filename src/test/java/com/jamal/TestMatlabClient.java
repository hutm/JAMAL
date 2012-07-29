/*
 * This file is part of JAMAL.
 *
 * JAMAL is free software; you can redistribute it and/or modify it
 * under the terms of the GNU General Public License version 2, as published
 * by the Free Software Foundation.
 *
 * JAMAL is distributed in the hope that it will be useful, but
 * WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with JAMAL; if not, write to the Free Software Foundation,
 * Inc., 59 Temple Place, Suite 330, Boston, MA  02111-1307  USA
 */

package com.jamal;

import com.jamal.client.MatlabClient;


/**
 * @version 1.0 Jan 17, 2010 9:01:13 PM
 * @author: Hut
 */
public class TestMatlabClient {


    public static void main(String[] args) {
        try {
            MatlabClient matlabClient = new MatlabClient(MatlabCaller.HOST_ADDRESS, MatlabCaller.MATLAB_EXECUTABLE_PATH);

            //First we pass an array of integers and calculate sum in Matlab
            Object[] inArgs = new Object[1];
            inArgs[0] = new int[]{1, 2, 3, 4};

            Object[] outputArgs = matlabClient.executeMatlabFunction("sum", inArgs, 1);
            double[] result = (double[]) outputArgs[0];
            System.out.println("The sum 1+2+3+4=" + result[0]);


            //Now let's recieve an array of Integers from Matlab
            inArgs = new Object[1];
            inArgs[0] = new Integer(3);
            outputArgs = matlabClient.executeMatlabFunction("magic", inArgs, 1);
            double[] magicArray = (double[]) outputArgs[0];
            System.out.println("The magic array from Matlab:");
            for (int i = 0; i < magicArray.length; i++) {
                System.out.print(" " + magicArray[i]);
            }
            System.out.println("");


            //More sophisticated test - pass 2d array to Matlab. Requires test2dArray.m and javaCellArgs2Matlab.m to be in the Matlab path
            inArgs = new Object[1];
            inArgs[0] = new int[][]{{1, 2, 3, 4}, {4, 5, 6, 7}, {7, 4, 7, 5}};
//
//            outputArgs = matlabClient.executeMatlabFunction("test2dArray", inArgs, 1);
//            result = (double[]) outputArgs[0];
//            System.out.println("Sum of 2-D array is:" + result[0]);


//            testPassingHugeMatrices();

            testPersistance();

            matlabClient.shutDownServer();



        } catch (JamalException e) {
            e.printStackTrace();
        }

    }

    public static void testPassingHugeMatrices() {
        try {
            MatlabClient matlabClient = new MatlabClient(MatlabCaller.HOST_ADDRESS, MatlabCaller.MATLAB_EXECUTABLE_PATH);

            //First we pass an array of integers and calculate sum in Matlab
            Object[] inArgs = new Object[1];

            int length = 8000;
            int[][] matrix = new int[length][length];
            for(int i = 0; i < matrix.length; i++){
                for(int j = 0; j < matrix[0].length; j++){
                    matrix[i][j] = i+j;
                }
            }
            inArgs[0] = matrix;

            Object[] outputArgs = matlabClient.executeMatlabFunction("test2dArray", inArgs, 1);
            double[] result = (double[]) outputArgs[0];
            System.out.println("Sum of 2-D array is:" + result[0]);

        } catch (JamalException e) {
            e.printStackTrace();
        }
    }


    public static void testPersistance() {
        try {
            MatlabClient matlabClient = new MatlabClient(MatlabCaller.HOST_ADDRESS, MatlabCaller.MATLAB_EXECUTABLE_PATH);

            //First we pass an array of integers and calculate sum in Matlab
            Object[] inArgs = new Object[1];


            inArgs[0] = new Integer(2);

            Object[] outputArgs = matlabClient.executeMatlabFunction("testSum", inArgs, 1);
            double[] result = (double[]) outputArgs[0];
            System.out.println("Result is:" + result[0]);

        } catch (JamalException e) {
            e.printStackTrace();
        }
    }

}
