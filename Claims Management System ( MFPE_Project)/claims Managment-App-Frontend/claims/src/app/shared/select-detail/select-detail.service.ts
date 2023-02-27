import { Injectable } from '@angular/core';

interface HospitalDetail {
  id: string;
  name: string;
  location: string;
}

interface PoilcyDetail {
  id: number;
  name: string;
}

interface BenefitDetail {
  id: string;
  name: string;
}

@Injectable({
  providedIn: 'root',
})
export class SelectDetailService {
  public hospitalDetails: Array<HospitalDetail> = [
    { id: 'h1', name: 'Apollo Hospital', location: 'Hyderabad' },
    { id: 'h2', name: 'KIMS Hospital', location: 'Srikakulam' },
    {
      id: 'h3',
      name: 'KGH',
      location: 'Hyderabad',
    },
    {
      id: 'h4',
      name: 'Ujwala Hospital',
      location: 'Vizag',
    },
    {
      id: 'h5',
      name: 'Vijay Hospital',
      location: 'Delhi',
    },
    {
      id: 'h6',
      name: 'Indus Hospital',
      location: 'Srikakulam',
    },
  ];

  public policyDetails: Array<PoilcyDetail> = [
    { id: 1, name: 'Health Plus Classic' },
    { id: 2, name: 'Health Plus Enhanced' },
    { id: 3, name: 'Health Plus Premium' },
  ];

  public benefitDetails: Array<BenefitDetail> = [
    { id: 'b1', name: 'Coverage for COVID-19' },
    { id: 'b1', name: 'Coverage for hospitalization at home' },
    { id: 'b2', name: 'Ambulance charges upto 2000 covered' },
    { id: 'b4', name: 'Life Time' },
    { id: 'b2', name: 'Ambulance charges upto 4000 covered' },
    {
      id: 'b1',
      name: 'Hospitalization charges for Premium Twin Sharing room covered',
    },
    { id: 'b3', name: 'Hospitalization charges for Deluxe room covered' },
    {
      id: 'b4',
      name: 'Hospitalization charges for Premium Deluxe room covered',
    },
  ];
}
