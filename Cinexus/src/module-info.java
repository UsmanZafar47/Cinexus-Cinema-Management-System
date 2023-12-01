module Cinexus {
	exports application;
	exports dbhandler;
	
    requires javafx.controls;
    requires java.sql;
	requires javafx.fxml;
	requires javafx.graphics;

    opens application to javafx.graphics, javafx.fxml;
    opens uipackage to javafx.graphics, javafx.fxml;
}