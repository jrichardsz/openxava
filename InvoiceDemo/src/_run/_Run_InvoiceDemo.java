package _run; 

import org.openxava.util.*;

/**
 * Execute this class to start the application.
 * 
 * With Eclipse: Right mouse button > Run As > Java Application
 */

public class _Run_InvoiceDemo { 

	public static void main(String[] args) throws Exception {
		DBServer.start("InvoiceDemoDB"); // To use your own database comment this line and configure web/META-INF/context.xml
        AppServer.run("InvoiceDemo");
	}

}
