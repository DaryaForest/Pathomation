package com.pathomation.util.data;


import com.pathomation.util.ConstantsPma2;
import com.pathomation.util.reporter.Reporter;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;

public class User {
    private String firstName = "foo";
    private String lastName = "bar";
    private String pass = "12345";
    private String login = "user_foo_bar";
    private String email = "test@test.com";
    private boolean isAdmin = false;
    private boolean isSuspended = false;
    private boolean canAnotate = false;

    @Override
    public String toString() {
        return "User{" +
                "firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", pass='" + pass + '\'' +
                ", login='" + login + '\'' +
                ", email='" + email + '\'' +
                ", isAdmin=" + isAdmin +
                ", isSuspended=" + isSuspended +
                ", canAnotate=" + canAnotate +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        User user = (User) o;

        if (isAdmin != user.isAdmin) return false;
        if (isSuspended != user.isSuspended) return false;
        if (canAnotate != user.canAnotate) return false;
        if (firstName != null ? !firstName.equals(user.firstName) : user.firstName != null) return false;
        if (lastName != null ? !lastName.equals(user.lastName) : user.lastName != null) return false;
        if (pass != null ? !pass.equals(user.pass) : user.pass != null) return false;
        if (login != null ? !login.equals(user.login) : user.login != null) return false;
        return email != null ? email.equals(user.email) : user.email == null;
    }


    public boolean isAdmin() {
        return isAdmin;
    }

    public void setAdmin(boolean admin) {
        isAdmin = admin;
    }

    public boolean isSuspended() {
        return isSuspended;
    }

    public void setSuspended(boolean suspended) {
        isSuspended = suspended;
    }

    public boolean isCanAnotate() {
        return canAnotate;
    }

    public void setCanAnotate(boolean canAnotate) {
        this.canAnotate = canAnotate;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getPass() {
        return pass;
    }

    public String getLogin() {
        return login;
    }

    public String getEmail() {
        return email;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void createUserApiCall() throws IOException, ParserConfigurationException {
        Reporter.log("Create a new user... ");
        String urlString = ConstantsPma2.ADMIN_API_CREATE_USER_URL_PMA2;

        String sessionId = System.getProperty("sessionID");//"V1FWAUwduizrsjOe46vLAg2";//"QDhAURormvgAT-I2RPFY_w2";
        HttpURLConnection connection = null;
        try {
            URL url = new URL(urlString);
            System.out.println("URL " + url.toString());

            connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("POST");
            connection.setUseCaches(false);
            connection.setDoOutput(true);

            connection.setRequestProperty("Content-Type",
                    "application/json; charset=UTF-8");
            connection.setRequestProperty("Accept", "application/json");
            connection.setRequestMethod("POST");

            String input = "{ \"sessionID\": \"" + sessionId
                    + "\" ,\"user\": { \"Login\": \"" + this.getLogin()
                    + "\", \"FirstName\": \"" + this.getFirstName()
                    + "\", \"LastName\": \"" + this.getLastName()
                    + "\", \"Email\": \"" + this.getEmail()
                    + "\", \"Password\": \"" + this.getPass() + "\"}}";
            OutputStream os = connection.getOutputStream();
            //Reporter.log("Request for add user" + input);
            os.write(input.getBytes("UTF-8"));
            os.close();

            BufferedReader br = new BufferedReader(new InputStreamReader(
                    (connection.getInputStream())));

            String output;
            System.out.println("Output from Server .... \n");
            while ((output = br.readLine()) != null) {
                System.out.println(output);
            }

        } finally {
            if (connection != null) {
                connection.disconnect();
            }
        }
    }
}
