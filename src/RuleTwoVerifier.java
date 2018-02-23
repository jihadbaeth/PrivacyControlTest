import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

public class RuleTwoVerifier implements Runnable {

    public static void main(String[] args) {
        for (int i = 0; i < 10; i++) {
            new Thread(new RuleTwoVerifier()).start();


        }
    }

    public void run() {

        try {
            File folder = new File("C:\\Users\\jehad\\Desktop\\Dax\\");
            File[] listOfFiles = folder.listFiles();
            FileWriter f0 = new FileWriter("C:\\Users\\jehad\\Desktop\\Dax\\results\\output2.csv");
            String newLine = System.getProperty("line.separator");


            System.out.println();
            for (File file : listOfFiles) {
                if (file.isFile()) {
                    long startTime = System.nanoTime();
                    ArrayList<Jobs> jobs;
                    jobs = DaxParser.getDax(file.getAbsolutePath());
                    Jobs mainTweet = jobs.get(0);
                    HashMap<String, Jobs> searchMap = new HashMap<>();
                    for (Jobs tempJob : jobs) {
                        searchMap.put(tempJob.getUsername(), tempJob);
                    }
                    int numberOfTocuhes = 0;

                    for (int index = 1; index < jobs.size(); index++) {
                        Jobs temp = jobs.get(index);
                        if (temp.getTweetAffected() != null && temp.getTweetAffected().equals(mainTweet.getTweetID()) &&
                                !mainTweet.existInFollowersList(temp.getUsername())) {
                            //if not in friends of friends list
                            for (String originatorFriend : mainTweet.getFollowers())
                                if (searchMap.get(originatorFriend) != null) {
                                    if (!searchMap.get(originatorFriend).existInFollowersList(temp.getUsername())) {
                                        System.out.println(temp.getUsername() + " Has violated rule Two policy");
                                        numberOfTocuhes++;
                                        break;

                                    }

                                }


                        }

                    }
                    System.out.println("Found " + numberOfTocuhes + " Rule two privacy policy violations");
                    long endTime = System.nanoTime();
                    long totalTime = endTime - startTime;
                    System.out.println("Execution time " + totalTime);
                    f0.write(file.getName() + ", " + totalTime + "," + numberOfTocuhes + newLine);

                }

            }
            f0.close();

        } catch (IOException e) {
            e.printStackTrace();
        }


    }
}