package taxFormLogin;

import java.io.IOException;
import java.util.Optional;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonType;
import javafx.scene.layout.Pane;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class Main extends Application {
	static Stage loginWindow;
	
	public static void main(String[]args){
		launch(args);
	}
	
	public void start(Stage primaryStage)throws Exception{
		loginWindow = primaryStage;
		loginWindow.setOnCloseRequest(e->{
			e.consume();
			closeWindow("are you sure?");
		});
		loginWindow.setResizable(false);
		showLogin();
	}
	
	protected static void showLogin() throws IOException{
		FXMLLoader loader = new FXMLLoader();
		loader.setLocation(Main.class.getResource("Home.fxml"));
		Pane home = loader.load();
		Scene scene = new Scene(home);
		
		loginWindow.setTitle("Login");
		loginWindow.setScene(scene);
		loginWindow.show();
	}
	
	protected static void closeWindow(String a){
		Alert closeWindow = new Alert(AlertType.CONFIRMATION);
		closeWindow.setTitle("Close Window");
		closeWindow.setHeaderText(null);
		closeWindow.setContentText(a);
		
		closeWindow.initModality(Modality.APPLICATION_MODAL);
		Optional<ButtonType> result = closeWindow.showAndWait();
		if (result.get() == ButtonType.OK){
		    loginWindow.close();
		} else {
		    closeWindow.close();
		}
	}
	
	protected static void alertWindow(String a){
		Alert alertWindow = new Alert(AlertType.INFORMATION);
		alertWindow.setTitle("Information Window");
		alertWindow.setHeaderText(null);
		alertWindow.setContentText(a);
		alertWindow.showAndWait();
	}
	

}
