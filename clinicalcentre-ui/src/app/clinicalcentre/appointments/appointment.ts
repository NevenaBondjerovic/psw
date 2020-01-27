import { Clinic } from 'src/app/clinicalcentre/clinic';

export interface Appointment {
  id: number,
  dateOfAppointment: string,
  timeOfAppointment: string,
  clinic: Clinic,
  hall: {id: number, name: string},
  doctor: {id: number, name: string, surname: string},
  type: {id: number, name: string},
  pricelist: {id: number, price: number, discount: number},
  scheduledFor: {id: number, name: string, surname: string}
};
