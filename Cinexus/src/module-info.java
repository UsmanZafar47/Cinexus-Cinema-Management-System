module Cinexus {
	exports application;
	exports dbhandler;
	
    requires javafx.controls;
	requires javafx.fxml;
	requires javafx.graphics;
    requires java.sql;
    
    opens application to javafx.graphics, javafx.fxml;
    opens uipackage to javafx.graphics, javafx.fxml;
}