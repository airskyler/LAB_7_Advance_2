// LAB 7, Advanced Problem 1, 2, 3, 4, 5, 6, 7, 8
// I got help from Learning Center at MCTC for coding


// Problem 1 - Answer is...
// Static variable are there in code, because, you want to use a static variable to change variable of instance since,
// variable of each instance is little different and make up a new variable for instance, using part of a static variable.


// Problem 5 - Answer is...
//  I rather add two new variables to the ticket class, because,
//  it is less code to write and I don't need to create a new object for resolving ticket.



package com.TicketsOOP;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.io.*;

public class TicketManager {
    static Scanner scan = new Scanner(System.in);
    static LinkedList<Ticket> resolvedTickets = new LinkedList<>();


    public static void main(String[] args) {
        LinkedList<Ticket> ticketQueue = new LinkedList<Ticket>();

        // Creating a variable for a "Open_Ticket.txt"
        String fileNameOpen = "Open_Ticket.txt";


        // Creating a BufferedReader bReader to read the file "Open_Ticket.txt"
        try (BufferedReader bReader = new BufferedReader(new FileReader(fileNameOpen));){

            // reading a line from a "Open_Ticket.txt" and Creating a variable name "line"
            String line = bReader.readLine();


            // Using while loop, while line is not equal to null... and splitting the line variable data with
            // space between them.
            while (line !=null){
                String[] lineData = line.split(" ");

                // Creating SimpleDateFormat formatter variable to format the date in certain ways
                SimpleDateFormat formatter = new SimpleDateFormat("E MMM dd HH:mm:ss zzz yyyy");

                // Creating a dateInString variable from the data information from the lineData Array.
                String dateInString = lineData[11]+" "+ lineData[12] + " " + lineData[13]+ " " + lineData[14] +
                        " " + lineData[15] + " " + lineData[16];


                // formatting the dateInString variable  date information by using a formatter
                Date date = formatter.parse(dateInString);


                // Creating a variable for "priority" and "id" from a lineData Array of index position of data stored
                int priority = Integer.parseInt(lineData[5]);
                int id = Integer.parseInt(lineData[1]);


                // Creating a openTicketData instance in a Ticket class with perimeter
                // and adding the data to LinkedList of ticketQueue
                Ticket openTicketData = new Ticket(lineData[3], priority, lineData[8], date, id);
                ticketQueue.add(openTicketData);

                line = bReader.readLine();


            }
        }
        // Catching any file related exception and parse exception
        catch (IOException ioe){
            System.out.println("Error creating or writing file " +ioe);
        }
        catch (ParseException pe){

            System.out.println("Couldn't parse the date");
        }



        while(true){

            // Using while loop to display the user choice on console with a issue of Ticket
            // depends on the user input choice...  it will call a different method to do a certain task
            System.out.println("1. Enter Ticket\n2. Delete by ID\n3. Display All Tickets\n4. Delete by Issue\n5. Search by Name\n6. Quit");
            int task = Integer.parseInt(scan.nextLine());

            // Making user to input a choice of number between 1 to 6
            if (task <= 0 || task > 6){
                System.out.println("Please, pick a number between 1 to 6 \n");
                continue;
            }

            else if (task == 1) {
                //Call addTickets, which will let us enter any number of new tickets
                addTickets(ticketQueue);
            } else if (task == 2) {
                //delete a ticket
                deleteTicket(ticketQueue);
            } else if ( task == 6 ) {
                //Quit. Future prototype may want to save all tickets to a file
                System.out.println("Quitting program");
                break;
            } else if (task == 4){

                // calling a delete by issue method
                deleteByIssue(ticketQueue);

                // calling a searchByName method
            } else if (task == 5){
                searchByName(ticketQueue);
            }


            else {
                //this will happen for 3 or any other selection that is a valid int
                //Default will be print all tickets
                printAllTickets(ticketQueue);
            }
        }

        scan.close();



        Date fileDate = new Date();
        //Creating a object simple with format style of ("MMM_dd_yyyy")
        // so that variable fileDateString can be formatted to certain style in later program
        // for fileName variable (file.txt)
        SimpleDateFormat simple = new SimpleDateFormat("MMM_dd_yyyy");
        String fileDateString = simple.format(fileDate);

        String fileName = "Resolved_tickets_as_of_" +fileDateString+".txt";


        // Creating a BufferedWriter bWriter to write a data in variable name "fileName"
        try (BufferedWriter bWriter = new BufferedWriter(new FileWriter(fileName));){

            // using for loop on resolvedTicket to get each data
            // and writing the data to resolved ticket txt.file
            for(Ticket j : resolvedTickets) {
                bWriter.write(j.toString() + "\n");

            }

            // using for loop to write a each open ticket data from the ticketQueue
            // to the Open ticket txt.file
            BufferedWriter kWriter = new BufferedWriter(new FileWriter(fileNameOpen));
                for(Ticket k : ticketQueue ){
                    kWriter.write(k.toString()+ "\n");
                }

            // close both writer and save the data on txt.file
            bWriter.close();
            kWriter.close();


        }

        // catch any file related exception
        catch (IOException ioe){
            System.out.println("Error creating or writing file " +ioe);
        }

    }

    private static void deleteByIssue(LinkedList<Ticket> ticketQueue) {
        boolean found = false;
        LinkedList<Ticket> NewTicketQueue = new LinkedList<>();
        do {
            System.out.println("What is the description/issue of the ticket you're looking for? ");
            String searchName = scan.nextLine();
            for (Ticket t : ticketQueue) {
                if (t.getDescription().contains(searchName)) {
                    NewTicketQueue.add(t);
                    found = true;
                }

            }
            if (!found){
                System.out.println("Name not found! try again.");
            }
        }
        while (!found);

        deleteTicket(NewTicketQueue);

    }

    public static void resolvingTicket(Ticket toResolve){
        System.out.println("How was the ticketed resolved?");
        String resolvedMethod = scan.nextLine();
        Date resolvedNow = new Date();

        toResolve.setResolution(resolvedMethod);
        toResolve.setResolutionDate(resolvedNow);



    }

    private static void searchByName(LinkedList<Ticket> ticketQueue) {
        boolean found = false;
        LinkedList<Ticket> NewTicketQueue = new LinkedList<>();
        do {
            System.out.println("What is the description of the ticket you're looking for? ");
            String searchName = scan.nextLine();
            for (Ticket t : ticketQueue) {
                if (t.getDescription().contains(searchName)) {
                    NewTicketQueue.add(t);
                    found = true;
                }

            }
            if (!found){
                System.out.println("Name not found! try again.");
            }
        }
        while (!found);

        printAllTickets(NewTicketQueue);

    }

    // LAB 7  Advance,  Problem 2

    protected static void deleteTicket(LinkedList<Ticket> ticketQueue) {

        printAllTickets(ticketQueue); //display list for user
        Integer[] deletedIDstatus = new Integer[77];


        Scanner deleteScanner = new Scanner(System.in);
        while (true) {

            System.out.println("Enter ID of ticket to delete");


            String deleteID = deleteScanner.nextLine();


            try {
                int AdeletedID = Integer.parseInt(deleteID);


                //Loop over all tickets. Delete the one with this ticket ID
                boolean found = false;
                for (Ticket ticket : ticketQueue) {
                    if (ticket.getTicketID() == AdeletedID) {
                        found = true;
                        ticketQueue.remove(ticket);
                        resolvingTicket(ticket);
                        System.out.println(String.format("Ticket %d deleted", AdeletedID));
                        System.out.println(ticket);
                        resolvedTickets.add(ticket);


                        break; //don't need loop any more.
                    }
                }

                // if ticket is not found, display the message to ask a user to try entering a ticketID again
                if (found == false) {
                    System.out.println("Ticket ID not found, please enter the ticketID again.\n");


                }
                else {
                    break;
                }

                printAllTickets(ticketQueue);
            }//print updated list

            catch (NumberFormatException e) {
                System.out.println("Entry Error... Please enter a integer value for a ticketID again.");
                System.out.println(e.toString() + "\n");
                continue;

            }

        }



    }


    //Move the adding ticket code to a method
    protected static void addTickets(LinkedList<Ticket> ticketQueue) {
        Scanner sc = new Scanner(System.in);
        boolean moreProblems = true;
        String description;
        String reporter;
        //let's assume all tickets are created today, for testing. We can change this later if needed
        Date dateReported = new Date(); //Default constructor creates date with current date/time
        int priority;
        while (moreProblems){
            System.out.println("Enter problem");
            description = sc.nextLine();
            System.out.println("Who reported this issue?");
            reporter = sc.nextLine();
            System.out.println("Enter priority of " + description);
            priority = Integer.parseInt(sc.nextLine());
            Ticket t = new Ticket(description, priority, reporter, dateReported);
            ticketQueue.add(t);
            //To test, let's print out all of the currently stored tickets
            printAllTickets(ticketQueue);
            System.out.println("More tickets to add?");
            String more = sc.nextLine();
            if (more.equalsIgnoreCase("N")) {
                moreProblems = false;
            }
        }

    }
    protected static void printAllTickets(LinkedList<Ticket> tickets) {
        System.out.println(" ------- All open tickets ----------");
        for (Ticket t : tickets) {
            System.out.println(t); //Write a toString method in Ticket class
            //println will try to call toString on its argument
        }
        System.out.println(" ------- End of ticket list ----------");
    }






}

/*TicketManager class – next prototype
        - Have added a menu
        - Each menu item calls a method
        - Add tickets works, show all tickets works
        - Delete tickets to be implemented
        - Input validation to be implemented
        Tickets class is unchanged
        Code too big to fit on slide - please select all (ctrl-A or Command-A) and copy
        */


