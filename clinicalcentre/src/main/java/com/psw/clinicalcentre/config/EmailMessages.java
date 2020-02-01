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



}
