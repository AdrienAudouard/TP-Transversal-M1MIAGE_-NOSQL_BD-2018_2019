---
services: documentdb
platforms: java
author: aliuy
---

# A Simple Todo List Application built w/ Java + Azure DocumentDB

The sample code in this Github repository demonstrates how to create a simple application using Java and Azure DocumentDB.

For a complete end-to-end walkthrough of creating the application, please visit the following [Azure documentation page](https://azure.microsoft.com/documentation/articles/documentdb-java-application/).

![My ToDo List Java application](./media/documentdb-java-application/image1.png)


##<a id="Requirements"></a>Requirements
Before you begin this application development tutorial, you must have the following:

- An active Azure DocumentDB account. 
  - If you don't have an account, you can find instructions on how to create one on our [Azure documentation page](https://azure.microsoft.com/documentation/articles/documentdb-create-account/).
- [Java Development Kit (JDK) 7+](http://www.oracle.com/technetwork/java/javase/downloads/index.html).
- [Eclipse IDE for Java EE Developers.](http://www.eclipse.org/downloads/packages/eclipse-ide-java-ee-developers/lunasr1)

If you're installing these tools for the first time, coreservlets.com provides a walk-through of the installation process in the Quick Start section of their [Tutorial: Installing TomCat7 and Using it with Eclipse](http://www.coreservlets.com/Apache-Tomcat-Tutorial/tomcat-7-with-eclipse.html) article. 

##<a id="Running"></a>Running the Code Sample

All the samples in this tutorial are included in the documentdb-java-todoapp project on [GitHub](https://github.com/Azure-Samples/documentdb-java-todoapp). To import the todo project into Eclipse, ensure you have the software and resources listed in the [Requirements](#Requirements) section, then do the following:

1. Install [Project Lombok](http://projectlombok.org/). Lombok is used to generate constructors, getters, setters in the project. Once you have downloaded the lombok.jar file, double-click it to install it or install it from the command line. 
2. If Eclipse is open, close it and restart it to load Lombok.
3. In Eclipse, on the **File** menu, click **Import**.
4. On the **Import Projects** screen, unselect the **DocumentDB** project, and then click **Finish**. The DocumentDB project contains the DocumentDB Java SDK, which we will add as a dependency instead.
5. In **Project Explorer**, right click the **azure-documentdb-java-sample**, click **Build Path**, and then click **Configure Build Path**.
6. On the **Java Build Path** screen, in the right pane, select the **Libraries** tab, and then click **Add External JARs**. Navigate to the location of the lombok.jar file, and click **Open**, and then click **OK**.
7. Use step 12 to open the **Properties** window again, and then in the left pane click **Targeted Runtimes**.
8. On the **Targeted Runtimes** screen, click **New**, select **Apache Tomcat v7.0**, and then click **OK**.
9. Use step 12 to open the **Properties** window again, and then in the left pane click **Project Facets**.
10. On the **Project Facets** screen, select **Dynamic Web Module** and **Java**, and then click **OK**.
11. On the **Servers** tab at the bottom of the screen, right-click **Tomcat v7.0 Server at localhost** and then click **Add and Remove**.
12. On the **Add and Remove** window, move **azure-documentdb-java-sample** to the **Configured** box, and then click **Finish**. 
13. In the **Server** tab, right-click **Tomcat v7.0 Server at localhost**, and then click **Restart**.
14. In a browser, navigate to `http://localhost:8080/azure-documentdb-java-sample/` and start adding to your task list. Note that if you changed your default port values, change `8080` to the value you selected.
