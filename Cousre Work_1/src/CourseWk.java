import com.mongodb.BasicDBObject;
import com.mongodb.client.*;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;
import org.bson.Document;
import java.util.*;

public class CourseWk extends Application {
    private static final int SEATING_CAPACITY = 42;
    public static void main(String[] args) {
        launch();
    }
    @Override
    public void start (Stage primaryStage) throws Exception {
        String[][][] mainArray_1 = new String[30][SEATING_CAPACITY][2];   //Initialize 3D array(Colombo-Badulla)
        String[][][] mainArray_2 = new String[30][SEATING_CAPACITY][2];   //Initialize 3D array(Badulla-Colombo)
        for (int x = 0; x < 30; x++) {       //store data to relevant position one by one
            for (int y = 0; y < SEATING_CAPACITY; y++) {
                for (int z = 0; z < 2; z++) {
                    mainArray_1[x][y][z] = "";
                    mainArray_2[x][y][z] = "";
                }
            }
        }
        System.out.println("+---------------------------------+");
        System.out.println("|                                 |");
        System.out.println("|        Denuwara Menike          |");
        System.out.println("|     Intercity Express Train     |");
        System.out.println("|                                 |");
        System.out.println("+---------------------------------+");
        System.out.println();
        menu:
        while (true) {
            try{
                Scanner input = new Scanner(System.in);
                System.out.println();      //Main menu
                System.out.println("MENU BAR");
                System.out.println("'A'- Add customer to a seat");
                System.out.println("'V'- View all seats");
                System.out.println("'E'- Display empty seats");
                System.out.println("'D'- Delete customer from seat");
                System.out.println("'F'- Find the seat from given customer name");
                System.out.println("'O'- View seats ordered alphabetically by name");
                System.out.println("'S'- Store program data in to file");
                System.out.println("'L'- Load the program data from file");
                System.out.println("'Q'- Quit");
                System.out.print("Enter letter: ");
                String option = input.next();
                System.out.println();
                switch (option) {
                    case "A":
                    case "a":
                        System.out.print("1-Enter the date number(1-30): ");
                        int date = input.nextInt();
                        while (date < 1 || date > 30) {   //if date won't be betweeen 1-30
                            System.out.println("I'm not sure what you said, please try again");
                            System.out.print("Enter the date number(1-30): ");
                            date = input.nextInt();
                        }
                        System.out.println("2-Choose your departure and arrival station");
                        System.out.println("    1.Colombo Fort - Badulla");
                        System.out.println("    2.Badulla - Colombo Fort");
                        System.out.print("Your choice: ");
                        String choice = input.next();
                        if (choice.equals("1"))
                            Add_Customer(mainArray_1, date);
                        else if (choice.equals("2"))
                            Add_Customer(mainArray_2, date);
                        else
                            System.out.println("I'm not sure what you said, please try again");
                        break;
                    case "V":
                    case "v":
                        System.out.print("Enter the date number(1-30): ");
                        date = input.nextInt();
                        while (date < 1 || date > 30) {   //if date won't be betweeen 1-30
                            System.out.println("I'm not sure what you said, please try again");
                            System.out.print("Enter the date number(1-30): ");
                            date = input.nextInt();
                        }
                        System.out.println("Choose your departure and arrival station");
                        System.out.println("    1.Colombo Fort - Badulla");
                        System.out.println("    2.Badulla - Colombo Fort");
                        System.out.print("Your choice: ");
                        choice = input.next();
                        if (choice.equals("1"))
                            View_allSeats(mainArray_1, date);
                        else if (choice.equals("2"))
                            View_allSeats(mainArray_2, date);
                        else
                            System.out.println("I'm not sure what you said, please try again");
                        break;
                    case "E":
                    case "e":
                        System.out.print("Enter the date number(1-30): ");
                        date = input.nextInt();
                        while (date < 1 || date > 30) {   //if date won't be betweeen 1-30
                            System.out.println("I'm not sure what you said, please try again");
                            System.out.print("Enter the date number(1-30): ");
                            date = input.nextInt();
                        }
                        System.out.println("Choose your departure and arrival station");
                        System.out.println("    1.Colombo Fort - Badulla");
                        System.out.println("    2.Badulla - Colombo Fort");
                        System.out.print("Your choice: ");
                        choice = input.next();
                        if (choice.equals("1"))
                            View_emptySeats(mainArray_1, date);
                        else if (choice.equals("2"))
                            View_emptySeats(mainArray_2, date);
                        else
                            System.out.println("I'm not sure what you said, please try again");
                        break;
                    case "D":
                    case "d":
                        System.out.print("Enter the date number(1-30): ");
                        date = input.nextInt();
                        while (date < 1 || date > 30) {   //if date won't be betweeen 1-30
                            System.out.println("I'm not sure what you said, please try again");
                            System.out.print("Enter the date number(1-30): ");
                            date = input.nextInt();
                        }
                        System.out.println("Choose your departure and arrival station");
                        System.out.println("    1.Colombo Fort - Badulla");
                        System.out.println("    2.Badulla - Colombo Fort");
                        System.out.print("Your choice: ");
                        choice = input.next();
                        if (choice.equals("1"))
                            deleteCustomer(mainArray_1, date);
                        else if (choice.equals("2"))
                            deleteCustomer(mainArray_2, date);
                        else
                        System.out.println("I'm not sure what you said, please try again");
                        break;
                    case "F":
                    case "f":
                        findCustomer(mainArray_1,mainArray_2);
                        break;
                    case "O":
                    case "o":
                        System.out.print("Enter the date number(1-30): ");
                        date = input.nextInt();
                        while (date < 1 || date > 30) {   //if date won't be betweeen 1-30
                            System.out.println("I'm not sure what you said, please try again");
                            System.out.print("Enter the date number(1-30): ");
                            date = input.nextInt();
                        }
                        System.out.println("Choose your departure and arrival station");
                        System.out.println("    1.Colombo Fort - Badulla");
                        System.out.println("    2.Badulla - Colombo Fort");
                        System.out.print("Your choice: ");
                        choice = input.next();
                        if (choice.equals("1"))
                            viewOrderedSeats(mainArray_1, date);
                        else if (choice.equals("2"))
                            viewOrderedSeats(mainArray_2, date);
                        else
                            System.out.println("I'm not sure what you said, please try again");
                        break;
                    case "S":
                    case "s":
                        storeData(mainArray_1,mainArray_2);
                        break;
                    case "L":
                    case "l":
                        loadData(mainArray_1,mainArray_2);
                        break;
                    case "Q":
                    case "q":
                        System.out.println();
                        System.out.println("Thank you for booking your tickets at DENUWARA MENIKE!!!!!");
                        System.out.println("Have a good trip.......!!!!!");
                        break menu;
                    default:
                        System.out.println("I'm not sure what you said, please try again");
                }
            }catch (InputMismatchException e){
                System.out.println("I'm not sure what you said, please try again");
            }
        }
    }

    private static String[][][] Add_Customer(String[][][] mainArray, int date) {
        ArrayList<Integer> temp = new ArrayList();    //initialize array to store name
        Scanner input = new Scanner(System.in);

        System.out.print("3-Enter Your First Name: ");
        String fname = input.nextLine();

        System.out.print("4-Enter Your Surname Name: ");
        String sname = input.nextLine();

        Button[] buttons = new Button[42];    //button array
        GridPane gridPane = new GridPane();
        gridPane.setStyle("-fx-background-image:url('img1.jpg');-fx-background-size: stretch;");
        gridPane.setHgap(10);
        gridPane.setVgap(12);

        Button btnbook = new Button("Book");
        gridPane.add(btnbook, 2, 9);
        btnbook.setPrefHeight(40);
        btnbook.setPrefWidth(80);
        btnbook.setStyle("-fx-background-color:#2980B9;-fx-text-fill:white");

        Circle circle_1 = new Circle();
        gridPane.add(circle_1,10,1);
        circle_1.setRadius(9);
        circle_1.setFill(Paint.valueOf("#2ECC71"));

        Circle circle_2 = new Circle();
        gridPane.add(circle_2,10,2);
        circle_2.setRadius(9);
        circle_2.setFill(Paint.valueOf("#EC7063"));

        Label label_1 = new Label("Empty");
        gridPane.add(label_1,11,1);
        label_1.setStyle("-fx-text-fill:white");

        Label label_2 = new Label("Taken");
        gridPane.add(label_2,11,2);
        label_2.setStyle("-fx-text-fill:white");

        Stage stage = new Stage();
        stage.setTitle("Denuwara Manike Train- Select your seats");

        int n = 0;
        while (n < SEATING_CAPACITY) {        //creating GUI on gridpane
            buttons[n] = new Button("" + (n + 1));
            int finalX = n;
            buttons[n].setOnAction(event -> {
                buttons[finalX].setStyle("-fx-background-color:#EC7063");
                temp.add(finalX);
            });
            if (mainArray[date-1][n][0].equals("")) {       //changing seat color
                buttons[n].setStyle("-fx-background-color:#2ECC71 ");
            } else {
                buttons[n].setStyle("-fx-background-color:#EC7063");
            }
            buttons[n].setPrefSize(40, 40);
            n++;
        }
        for (int j = 0; j < 42; j++) {       // disable the button
            if (!mainArray[date-1][j][0].equals("")) {
               buttons[j].setDisable(true);
                buttons[j].setStyle("-fx-background-color:#EC7063");
            }
        }
        int num = 0;     //oder of seats
        for (int i = 0; i < 7; i++) {
            for (int j = 0; j < 6; j++) {
                gridPane.add(buttons[num++], j, i);
            }
        }
        btnbook.addEventHandler(MouseEvent.MOUSE_CLICKED,
                event -> {
                    stage.close();
                    for (int i = 0; i < temp.size(); i++) {    //storing data in 3D array in second trip
                        mainArray[date-1][temp.get(i)][0] = fname;
                        mainArray[date-1][temp.get(i)][1] = sname;
                    }
                    System.out.println(Arrays.deepToString(mainArray));
                });
        Scene sceneAdd = new Scene(gridPane, 500, 500);
        stage.setScene(sceneAdd);
        stage.showAndWait();
        return mainArray;
    }
    private static void View_allSeats(String[][][] mainArray, int date) {
        Stage stage = new Stage();
        stage.setTitle("Denuwara Manike Train- All Seats");
        Scene  sceneView;
        Button[] buttons = new Button[42];
        GridPane gridPane = new GridPane();
        gridPane.setStyle("-fx-background-image:url('img1.jpg');-fx-background-size: stretch;");
        gridPane.setHgap(10);
        gridPane.setVgap(10);

        Button btnbook = new Button("OK");
        gridPane.add(btnbook, 2, 9);
        btnbook.setPrefHeight(40);
        btnbook.setPrefWidth(80);
        btnbook.setStyle("-fx-background-color:#2980B9;-fx-text-fill:white");

        Circle circle_1 = new Circle();
        gridPane.add(circle_1,10,1);
        circle_1.setRadius(9);
        circle_1.setFill(Paint.valueOf("#2ECC71"));

        Circle circle_2 = new Circle();
        gridPane.add(circle_2,10,2);
        circle_2.setRadius(9);
        circle_2.setFill(Paint.valueOf("#EC7063"));

        Label label_1 = new Label("Empty");
        gridPane.add(label_1,11,1);
        label_1.setStyle("-fx-text-fill:white");

        Label label_2 = new Label("Taken");
        gridPane.add(label_2,11,2);
        label_2.setStyle("-fx-text-fill:white");

        int n = 0;
        while (n < SEATING_CAPACITY) {   //creating GUI on gridpane change seat colors
            buttons[n] = new Button("" + (n + 1));
            if (mainArray[date-1][n][0].equals("")) {     //changing seats color
                buttons[n].setStyle("-fx-background-color:#2ECC71");
            } else {
                buttons[n].setStyle("-fx-background-color:#EC7063");
            }
            buttons[n].setPrefSize(40, 40);
            n++;
        }
        for (int j = 0; j < 42; j++) {  // disable the selected button
            if (!mainArray[date-1][j][0].equals("")) {
                buttons[j].setStyle("-fx-background-color:#EC7063");
            }
        }
        int numb = 0;
        for (int i = 0; i < 7; i++) {
            for (int j = 0; j < 6; j++) {
                gridPane.add(buttons[numb++], j, i);
            }
        }
        btnbook.addEventHandler(MouseEvent.MOUSE_CLICKED,
                event -> {
                    stage.close();
                });
        sceneView = new Scene(gridPane, 500, 500);
        stage.setScene(sceneView);
        stage.showAndWait();
    }
    private static void View_emptySeats(String[][][] mainArray,int date) {
        Scanner input = new Scanner(System.in);
        Stage stage = new Stage();
        stage.setTitle("Denuwara Manike Train- Empty Seats");
        Scene  sceneView;
        Button[] buttons = new Button[42];
        GridPane gridPane = new GridPane();
        gridPane.setStyle("-fx-background-image:url('img1.jpg');-fx-background-size: stretch;");
        gridPane.setHgap(10);
        gridPane.setVgap(10);

        Button btnbook = new Button("OK");
        gridPane.add(btnbook, 2, 9);
        btnbook.setPrefHeight(40);
        btnbook.setPrefWidth(80);
        btnbook.setStyle("-fx-background-color:#2980B9;-fx-text-fill:white");

        Circle circle_1 = new Circle();
        gridPane.add(circle_1,10,1);
        circle_1.setRadius(9);
        circle_1.setFill(Paint.valueOf("#2ECC71"));

        Label label_1 = new Label("Empty");
        gridPane.add(label_1,11,1);
        label_1.setStyle("-fx-text-fill:white");

        int n = 0;
        while (n < SEATING_CAPACITY) {   //creating GUI on gridpane
            buttons[n] = new Button("" + (n + 1));
            mainArray[date-1][n][0].equals("");     //changing seats color
            buttons[n].setStyle("-fx-background-color:#2ECC71");
            buttons[n].setPrefSize(40, 40);
            n++;
        }
        for (int j = 0; j < 42; j++) {  // visible the selected buttons
            if (!mainArray[date-1][j][0].equals("")) {
                buttons[j].setVisible(false);
            }
        }
        int numb = 0;
        for (int i = 0; i < 7; i++) {
            for (int j = 0; j < 6; j++) {
                gridPane.add(buttons[numb++], j, i);
            }
        }
        btnbook.addEventHandler(MouseEvent.MOUSE_CLICKED,
                event -> {
                    stage.close();
                });
        sceneView = new Scene(gridPane, 500, 500);
        stage.setScene(sceneView);
        stage.showAndWait();
    }
    private static void deleteCustomer(String[][][] mainArray, int date) {
        Scanner input = new Scanner(System.in);
        System.out.print("What is the seat number you want delete(seats 1-42): ");
        int number = input.nextInt();
        if (number<=42){
            mainArray[date-1][number-1][0]=("");   //delete all data from array
            mainArray[date-1][number-1][1]=("");
            System.out.println("Seat number "+number+" Successfully deleted.......");
        }else{
            System.out.println("I'm not sure what you said, please try again");
        }
    }
    private static void findCustomer(String[][][] mainArray_1,String[][][] mainArray_2) {
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter the first name of the customer to find the seat number(s): ");
        String name = sc.nextLine();
        ArrayList<Integer> reserved = new ArrayList<>();
        ArrayList<Integer> date = new ArrayList<>();
        int a =0;
        for (int y=0;y<30;y++) {
            for (int x = 0; x < SEATING_CAPACITY; x++) {
                if (name.equalsIgnoreCase(mainArray_1[y][x][0])) {
                    reserved.add(x + 1);
                    date.add(y+1);
                }else if (name.equalsIgnoreCase(mainArray_2[y][x][0])){
                    reserved.add(x + 1);
                    date.add(y+1);
                    a++;
                }
            }
        }
        if (reserved.size()==0){
            System.out.println("> There is no having name "+ name);
        }else {            System.out.print("> "+name+" you reserved ");
            for (int i:reserved) {
                System.out.print(i+",");
            }
            System.out.println("seats.");
            System.out.println("> Your date number is "+date.get(0)+".");
            if (a==0){
                System.out.println("> You are going to Colombo to Badulla.");
            }else{
                System.out.println("> You are going to Badulla to Colombo.");
            }
        }
    }
    private static void viewOrderedSeats(String[][][] mainArray, int date) {
        String []seats = new String[SEATING_CAPACITY];
        int arrayLength = seats.length;
        for (int x=0;x<SEATING_CAPACITY;x++){   //store the name in new array
            seats[x]=mainArray[date-1][x][0];
        }
        int i, j;
        String temp;
        for ( i = 0; i < arrayLength-1; i++) {
            for ( j = 0; j < arrayLength-i-1; j++) {   // comparing strings and bubble sorting
                if (seats[j].compareTo(seats[j+1]) > 0) {
                    temp = seats[j];
                    seats[j] = seats[j+1];
                    seats[j+1] = temp;
                }
            }
        }
        System.out.println("The sorted names are: ");
        String[] orderNames = new String[SEATING_CAPACITY];
        for (int y =1;y<SEATING_CAPACITY;y++) {
            if (seats[y - 1].equals(seats[y])) {
                orderNames[y - 1] = seats[y - 1];
                orderNames[y] = "";
            } else {
                orderNames[y] = seats[y];
            }
        }
        //System.out.println(Arrays.toString(orderNames));
        for (int x=0; x<SEATING_CAPACITY; x++){
                if (!orderNames[x].equals("")){
                    System.out.println(orderNames[x]);
                }
                }
    }
    private void storeData(String[][][] Passenger_1,String[][][] Passenger_2) {
        MongoClient mongoClient = MongoClients.create("mongodb://Localhost:27017"); //create a client
        MongoDatabase database = mongoClient.getDatabase("TrainBookingSystem");  //get database
        MongoCollection<Document> collection = database.getCollection("DenuwaraManike");  //in database create a collection
        BasicDBObject document = new BasicDBObject();
        collection.deleteMany(document);    //delete old data from db

        for (int i = 0; i < 30; i++) {
            for (int j = 0; j < SEATING_CAPACITY; j++) {
                if (!Passenger_1[i][j][0].equals("")) {
                    Document record = new Document("title", "records")  //add data to the db
                            .append("SeatNum", j+1)
                            .append("fname", Passenger_1[i][j][0])
                            .append("sname", Passenger_1[i][j][1])
                            .append("date", i+1)
                            .append("trip", 1);
                    collection.insertOne(record);
                }
            }
        }
        for (int i = 0; i < 30; i++) {
            for (int j = 0; j < SEATING_CAPACITY; j++) {
                if (!Passenger_2[i][j][0].equals("")) {
                    Document record = new Document("title", "records")
                            .append("SeatNum", j+1)
                            .append("fname", Passenger_2[i][j][0])
                            .append("sname", Passenger_2[i][j][1])
                            .append("date", i+1)
                            .append("trip",2);
                    collection.insertOne(record);
                }
            }
        }
        System.out.println("Successfully data stored in database......");
    }
    private void loadData(String[][][] Passenger_1,String[][][] Passenger_2) {
        MongoClient mongoClient = MongoClients.create("mongodb://Localhost:27017");  //create a client
        MongoDatabase database = mongoClient.getDatabase("TrainBookingSystem");   //get database
        MongoCollection<Document> collection = database.getCollection("DenuwaraManike");  //in database create a collection
        FindIterable<Document>  data = collection.find();//find data in records
        for(Document record:data){  //get collection of data from records
            int date = Integer.parseInt(record.get("date").toString())-1;
            int seat = Integer.parseInt(record.get("SeatNum").toString())-1;
            String fname = record.get("fname").toString();
            String sname = record.get("sname").toString();
            String trip = record.get("trip").toString();
            if (trip.equals("1")) {
                Passenger_1[date][seat][0] = sname;
                Passenger_1[date][seat][1] = fname;
            }else if(trip.equals("2")) {
                Passenger_2[date][seat][0] = sname;
                Passenger_2[date][seat][1] = fname;
            }
        }
        System.out.println("Successfully data load to the program");
        System.out.println(Arrays.deepToString(Passenger_1));
    }
}