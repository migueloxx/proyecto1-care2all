package application.login;

import application.model.User;
import java.io.InputStream;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.fxml.JavaFXBuilderFactory;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;


public class DetailsController implements Initializable {

    @FXML TextField txtUserName;
    @FXML Button btnReturn;

    private User user;
    private Stage stage;

    /**
     * Initializes the controller class.
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {

        //evento cuando se presiona el boton "volver"
        btnReturn.setOnAction((ActionEvent t) -> {
         try{
            FXMLLoader loader = new FXMLLoader();
            InputStream in = LoginController.class.getResourceAsStream("LoginView.fxml");
            loader.setBuilderFactory(new JavaFXBuilderFactory());
            loader.setLocation(LoginController.class.getResource("LoginView.fxml"));
            AnchorPane page;
            try {
                page = (AnchorPane) loader.load(in);
            } finally {
                in.close();
            }
            Scene scene = new Scene(page, 800,500);
            stage.setScene(scene);
            stage.sizeToScene();
            LoginController login = (LoginController)  loader.getController();
            login.setStage(stage);
            stage.show();
         }  catch(Exception ex) {
            System.err.println(ex.getMessage());
        }
       });
    }

    /**
     * @param stage
     */
    public void setStage (Stage stage){
        this.stage = stage;
    }

    /**
     * @param user
     */
    public void setUser(User user) {
        this.user = user;
        txtUserName.setText( user.getFullName() );
    }
}