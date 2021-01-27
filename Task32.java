import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class Task32 {
    static int noofrecords = 0;

    static int[] price;
    static float[] rating;
    static String[] data;

    public static void main(String[] args) throws IOException {
        String l = "";
        FileReader f;
        try {
            f = new FileReader("flipkart_product_sample.csv");
            BufferedReader br = new BufferedReader(f);
            while ((l = br.readLine()) != null) {
                noofrecords++;
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        price = new int[100];
        rating = new float[100];
        data = new String[100];

        Task32.readFromFile();
        Task32.sortingOnPrice(price, data);
        // Task32.sortingOnRating(rating, data);
        

    }

    static void readFromFile() {
        String l = "";
        int i = 0;

        FileReader f;
        try {
            f = new FileReader("data.csv");
            BufferedReader br = new BufferedReader(f);
            l = br.readLine();
            String tempString = "";
            while ((l = br.readLine()) != null) {

                data[i] = l;
                tempString = l.replaceAll("No rating available", "0");
                String temp1[] = tempString.split(",");
                price[i] = Integer.parseInt(temp1[4]);
                rating[i] = Float.parseFloat(temp1[6]);
                i++;
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (Exception io) {
            io.printStackTrace();
        }
    }

    static void sortingOnPrice(int[] arr, String[] data) {
        String[] data1 = new String[100];
        int j = 0;

        for(j=0; j<100-1; j++){
            data1[j] = data[j];
        }

        int n = arr.length;
        String temp; int tmp;
        long startTime = System.currentTimeMillis();
        for (int i = 0; i < n - 1; i++) {
            for (int k = 0; k < n - i -1; k++) {
                if (arr[k]> arr[k+1]) {
                    temp = data1[k];
                    data1[k]  = data1[k+1];
                    data1[k+1] = temp;

                    tmp =arr[k];
                    arr[k] = arr[k+1];
                    arr[k+1] = tmp;
                }
            }
        }
        long endTime = System.currentTimeMillis();
        try {
            FileWriter fw = new FileWriter("OutputDataOnSortPrice.csv");
            BufferedWriter bw = new BufferedWriter(fw);
            bw.write("pid,product_name,brand,product_url,retail_price,discounted_price,product_rating\n");
            for (int i=0; i<100-1; i++) {
                bw.write(data1[i] + "\n");
                
                // l++;
            }
            bw.close();
        } catch (FileNotFoundException f) {
            f.printStackTrace();

        } catch (IOException e) {
            e.printStackTrace();

        }

        System.out.println("******************\nTime taken for Bubble sorting to run is: "+(endTime-startTime)+"\n********************\n");

    }


    static void sortingOnRating(float[] arr, String[] data){
        String[] data1 = new String[100];
        int j = 0;

        for(j=0; j<100-1; j++){
            data1[j] = data[j];
        }
        int n = arr.length;
        String temp; float tmp;
        for (int i = 0; i < n - 1; i++) {
            for (int k = 0; k < n - i -1; k++) {
                if (arr[k]< arr[k+1]) {
                    temp = data1[k];
                    data1[k]  = data1[k+1];
                    data1[k+1] = temp;
                    tmp =arr[k];
                    arr[k] = arr[k+1];
                    arr[k+1] = tmp;
                }
            }
        }
        try {
            FileWriter fw = new FileWriter("OutputDataOnSortRatings.csv");
            BufferedWriter bw = new BufferedWriter(fw);
            bw.write("pid,product_name,brand,product_url,retail_price,discounted_price,product_rating\n");
            for (int i=0; i<100-1; i++) {
                bw.write(data1[i] + "\n");
            }
            bw.close();
        } catch (FileNotFoundException f) {
            f.printStackTrace();

        } catch (IOException e) {
            e.printStackTrace();

        }

    }



}
