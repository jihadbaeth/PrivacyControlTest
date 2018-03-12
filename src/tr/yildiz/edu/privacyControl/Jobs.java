package tr.yildiz.edu.privacyControl; /**
 *
 */


import java.util.ArrayList;

/**
 * @author jihad
 */
public class Jobs {


    private String activityID;
    private String userData;
    private String opType;
    private String timestamp;
    private String derrivedFrom;
    private String legitimacy;
    private String availability;
    private String username;
    private String popularity;
    private String tweetID;
    private String[] followers;
    private String tweetAffected;

    public Jobs() {
    }

    public Jobs(String activityID, String userData, String opType, String timestamp, String derrivedFrom,
                String legitimacy, String availability, String username, String popularity, String tweetID, String[] followers,
                String tweetAffected) {
        super();
        this.setActivityID(activityID);
        this.setUserData(userData);
        this.setOpType(opType);
        this.setTimestamp(timestamp);
        this.setDerrivedFrom(derrivedFrom);
        this.setLegitimacy(legitimacy);
        this.setAvailability(availability);
        this.setUsername(username);
        this.setPopularity(popularity);
        this.setTweetID(tweetID);
        this.setFollowers(followers);
        this.setTweetAffected(tweetAffected);
    }

    public String getTweetAffected() {
        return tweetAffected;
    }

    public void setTweetAffected(String tweetAffected) {
        this.tweetAffected = tweetAffected;
    }

    public String getTweetID() {
        return tweetID;
    }

    public void setTweetID(String tweetID) {
        this.tweetID = tweetID;
    }

    public void setMetricValues() {
        //System.out.println("User's data before parsin: "+ getUserData());
        String[] tokens = getUserData().split(",");
        //for(int i=0;i<tokens.length;i++)
        //{
        //System.out.println(" -->"+aList.get(i));
//		    System.out.println("0: "+tokens[0].split(" ")[1]);
        setUsername(tokens[0].split(" ")[1].trim());
        //System.out.println(tokens[1].split(" ")[2]);
//		    System.out.println("2: "+tokens[2].split(" ")[2]);
        setPopularity(tokens[2].split(" ")[2].trim());
//		    System.out.println("3: "+tokens[3].split(" ")[2]);
        setAvailability(tokens[3].split(" ")[2].trim());
//		    System.out.println("4: "+tokens[4].split(" ")[2]);
        setLegitimacy(tokens[4].split(" ")[2].trim());
        ArrayList<String> followersList = new ArrayList<String>();
        for (int numOfFollowers = 5; numOfFollowers < tokens.length; numOfFollowers++) {
            followersList.add(tokens[numOfFollowers]);
        }
        setFollowers(followersList.toArray(new String[0]));

        //System.out.println("6: "+tokens[6].split(" ")[2]);

        //}

    }

    public String getDerrivedFrom() {
        return derrivedFrom;
    }

    public void setDerrivedFrom(String derrivedFrom) {
        this.derrivedFrom = derrivedFrom;
    }

    public String getActivityID() {
        return activityID;
    }

    public void setActivityID(String activityID) {
        this.activityID = activityID;
    }

    public String getUserData() {
        return userData;
    }

    public void setUserData(String userData) {
        this.userData = userData;
    }

    public String getOpType() {
        return opType;
    }

    public void setOpType(String opType) {
        this.opType = opType;
    }

    public String getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }

    public String getLegitimacy() {
        return legitimacy;
    }

    public void setLegitimacy(String legitimacy) {
        this.legitimacy = legitimacy;
    }

    public String getAvailability() {
        return availability;
    }

    public void setAvailability(String availability) {
        this.availability = availability;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPopularity() {
        return popularity;
    }

    public void setPopularity(String popularity) {
        this.popularity = popularity;
    }


    @Override
    public String toString() {
        return "Jobs [activityID= " + getActivityID() + " , opType=" + getOpType() + ", timestamp="
                + getTimestamp() + " Derrived from: " + getDerrivedFrom() + "]" + "TweetID" + getTweetID()
                + " Popularity " + getPopularity() + " Legitimacy " + getLegitimacy() + " Availability " + getAvailability();
    }

    public String[] getFollowers() {
        return followers;
    }

    public void setFollowers(String[] followers) {
        this.followers = followers;
    }

    public boolean existInFollowersList(String userID) {
        for (String temp : followers) {
            if (temp.equals(userID)) {
                return true;
            }
        }
        return false;
    }


}
