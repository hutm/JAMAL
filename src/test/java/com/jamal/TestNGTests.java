package com.jamal;

import com.jamal.client.MatlabClient;
import org.testng.Assert;
import org.testng.annotations.Test;

/**
 * @version 1.0 2/25/13 11:27 AM
 * @author: Hut
 */
public class TestNGTests {

    @Test
    public void testClient(){
        try {
            MatlabClient matlabClient = new MatlabClient(MatlabCaller.HOST_ADDRESS, MatlabCaller.MATLAB_EXECUTABLE_PATH);

            //First we pass an array of integers and calculate sum in Matlab
            Object[] inArgs = new Object[1];
            inArgs[0] = new int[]{1, 2, 3, 4};

            Object[] outputArgs = matlabClient.executeMatlabFunction("sum", inArgs, 1);
            double[] result = (double[]) outputArgs[0];
            System.out.println("The sum 1+2+3+4=" + result[0]);
            Assert.assertEquals(result[0], 10.0);

            //Now let's recieve an array of Integers from Matlab
            inArgs = new Object[1];
            inArgs[0] = new Integer(3);
            outputArgs = matlabClient.executeMatlabFunction("magic", inArgs, 1);
            double[] magicArray = (double[]) outputArgs[0];
            System.out.println("The magic array from Matlab:");
            for (int i = 0; i < magicArray.length; i++) {
                System.out.print(" " + magicArray[i] + "\r\n");
            }
            Assert.assertEquals(magicArray[0], 8.0);


            //Now let's concat two strings in Matlab
            inArgs = new Object[2];
            inArgs[0] = "cat";
            inArgs[1] = "dog";
            outputArgs = matlabClient.executeMatlabFunction("strcat", inArgs, 1);
            String concatted = (String) outputArgs[0];
            Assert.assertEquals(concatted.length(), 6);



            matlabClient.shutDownServer();

        } catch (JamalException e) {
            e.printStackTrace();
        }

    }



}
