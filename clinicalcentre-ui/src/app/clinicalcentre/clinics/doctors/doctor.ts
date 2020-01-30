export interface Doctor {
  doctorId: number,
  doctorName: string,
  doctorSurname: string,
  score: number,
  appointmentData: Array<{
    id: number,
    date: string,
    time: string,
    type: string
  }>
};
