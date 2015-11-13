

    package com.TicketsOOP;

    import java.util.Date;
    public class Ticket {
        private int priority;
        private String reporter; //Stores person or department who reported issue
        private String description;
        private Date dateReported;
        private String resolution;
        private Date resolutionDate;


        public String getResolution() {
            return resolution;
        }

        public void setResolution(String resolution) {
            this.resolution = resolution;
        }

        public Date getResolutionDate() {
            return resolutionDate;
        }

        public void setResolutionDate(Date resolutionDate) {
            this.resolutionDate = resolutionDate;
        }




        //STATIC Counter - accessible to all Ticket objects.
        //If any Ticket object modifies this counter, all Ticket objects will have the modified value
        //Make it private - only Ticket objects should have access
        private static int staticTicketIDCounter = 1;
        //The ID for each ticket - instance variable. Each Ticket will have it's own ticketID variable
        protected int ticketID;

        public Ticket(String desc, int p, String rep, Date date) {
            this.description = desc;
            this.priority = p;
            this.reporter = rep;
            this.dateReported = date;
            this.ticketID = staticTicketIDCounter;
            staticTicketIDCounter++;
        }

        public Ticket(String desc, int p, String rep, Date date, int id) {
            this.description = desc;
            this.priority = p;
            this.reporter = rep;
            this.dateReported = date;
            this.ticketID = id;
            staticTicketIDCounter = id + 1;
        }

        protected int getPriority() {
            return priority;
        }

        public String getDescription() {
            return description;
        }

        public String toString() {

            String resolvedDateString = ( resolutionDate == null) ? "Unresolved" : this.resolutionDate.toString();
            String resolutionString = ( this.resolution == null) ? "Unresolved" : this.resolution;


            return ("ID= " + this.ticketID + " Issued: " + this.description + " Priority: " + this.priority + " Reported by: "
                    + this.reporter + " Reported on: " + this.dateReported + " Resolved Date = " + resolvedDateString +
                    " Resolution = " + resolutionString);

        }


        public int getTicketID() {


        return ticketID;
    }


    }


