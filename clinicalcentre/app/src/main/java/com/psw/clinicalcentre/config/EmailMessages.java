package com.psw.clinicalcentre.config;

import com.psw.clinicalcentre.appointments.requests.AppointmentRequests;
import com.psw.clinicalcentre.appointments.requests.AppointmentRequestsDTO;

public class EmailMessages {

    public static String acceptedMessage(String linkAddress){
        return "Hi,\n" +
                "\n" +
                "We are informing you that your registration request for Clinical Centre application " +
                "has been processed and APPROVED.\n" +
                "To be able to use the application, please activate the account on the following link: "
                + linkAddress + ".\n" +
                "\n" +
                "Thank you for using our application.\n" +
                "Regards,\n" +
                "Clinical Centre team";
    }

    public static String rejectedMessage(String rejectReason) {
        return "Hi,\n" +
                "\n" +
                "We are informing you that your registration request for Clinical Centre application has been REJECTED.\n" +
                "The reason for rejecting is the following:\n" +
                "    '" + rejectReason + "'\n" +
                "\n" +
                "Regards,\n" +
                "Clinical Centre team\n";
    }

    public static String appointmentRequestArrived(AppointmentRequests request){
        return "Hi,\n" +
                "\n" +
                "New appointment request has arrived.\n"
                +"Details are shown below: \n"
                +"Date and time of the appointment: " + request.getAppointment().getDateOfAppointment().toString()
                +", " + request.getAppointment().getTimeOfAppointment() + "\n"
                +"Clinic: "
                + request.getAppointment().getClinic().getName() + ", "
                + request.getAppointment().getClinic().getAddress() + "\n"
                + "Type of the appointment: " + request.getAppointment().getType().getName() + "\n"
                + "Patient: " + request.getUser().getName() + " " + request.getUser().getSurname() + "\n";
    }

    public static String appointmentRequestApproved(AppointmentRequests request){
        return "Hi, \n\n"
                + "Your request has been approved.\n\n"
                + "Please go to the following link to accept the request proposal: \n"
                + "http://localhost:4200/confirmation/" + request.getAppointment().getId()
                + "\n\n"
                + "Regards,\n"
                + "Clinical centre team\n";
    }

    public static String appointmentRequestDeclined(AppointmentRequests request){
        return "Hi, \n\n"
                + "Your request has been declined.\n"
                + "There is no free slot for the wanted appointment.\n"
                + "Please try another day."
                + "\n\n"
                + "Regards,\n"
                + "Clinical centre team\n";
    }

}
