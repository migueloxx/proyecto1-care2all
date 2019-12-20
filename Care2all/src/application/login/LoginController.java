package application.login;

import application.model.Authentication;
import java.io.InputStream;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.fxml.JavaFXBuilderFactory;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class LoginController implements Initializable {

    @FXML Button btnOk;
    @FXML TextField txtUser;
    @FXML TextField txtPass;
    @FXML Label txtMsgError;

    private Stage stage;

    /**
     * Initializes the controller class.
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {

        //oculta mensaje de error
        txtMsgError.setVisible(false);

         //cuando cualquier de los  textfield gane foco
         //si existe mensaje de error visible -> se oculta
        txtUser.focusedProperty().addListener((ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) -> {
             if (newValue){
                txtMsgError.setVisible(false);
             }
        });
        txtPass.focusedProperty().addListener((ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) -> {
             if (newValue){
                txtMsgError.setVisible(false);
             }
        });
    }

    public void setStage (Stage stage){
        this.stage = stage;
    }

    /**
     * Acción que se produce al presionar el boton "Iniciar Sesión"
     * @param event
     */
    @FXML
    private void verifyUserData(ActionEvent event) throws Exception{
        Authentication authentication = new Authentication();
        //verifica que datos introducidos sean correctos
        boolean response = authentication.userExists(txtUser.getText(), txtPass.getText());
        if( response ){ // los datos son correctos
            //reemplaza el stage actual por el de la vista "DetailsView"
            FXMLLoader loader = new FXMLLoader();
            
//            InputStream in = LoginController.class.getResourceAsStream("DoctorView.fxml");
//            loader.setBuilderFactory(new JavaFXBuilderFactory());
//            loader.setLocation(LoginController.class.getResource("DoctorView.fxml"));
//            AnchorPane page;

            InputStream in = LoginController.class.getResourceAsStream("DetailsView.fxml");
            loader.setBuilderFactory(new JavaFXBuilderFactory());
            loader.setLocation(LoginController.class.getResource("DetailsView.fxml"));
            AnchorPane page;
            try {
                page = (AnchorPane) loader.load(in);
            } finally {
                in.close();
            }
            Scene scene = new Scene(page, 800,500);
            stage.setScene(scene);
            stage.sizeToScene();
            DetailsController login = (DetailsController)loader.getController();
            login.setStage(stage);
            login.setUser( authentication.getUser( txtUser.getText(), txtPass.getText()).get() );
            stage.show();
        }
        else{
            txtMsgError.setVisible(true);
        }
    }

}//LoginController:end