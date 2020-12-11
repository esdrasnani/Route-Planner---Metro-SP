/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package routeplanner;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.UnknownHostException;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;


/**
 *
 * @author esdra
 */
public class RoutePlanner extends Application {
    
    @Override
    public void start(Stage primaryStage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("RoutePlannerFxml.fxml"));     
        primaryStage.setTitle("Route Planner - MetroSP");
        primaryStage.setScene(new Scene(root, 740, 807));
        primaryStage.show();
    }

    /**
     * @param args the command line arguments
     * @throws java.io.FileNotFoundException
     */
    public static void main(String[] args) throws FileNotFoundException, IOException, ClassNotFoundException, UnknownHostException, InterruptedException{
        launch(args);
    }
    
}
