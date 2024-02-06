import { Component, OnInit } from '@angular/core';
import { Resident } from './resident';
import { ResidentService } from './resident.service';
import { HttpErrorResponse } from '@angular/common/http';
import { NgForm } from '@angular/forms';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})

export class AppComponent implements OnInit{
  //Holds the residents coming from the Backend
  public residents: Resident[];
  public editResident: Resident;
  public deleteResident: Resident;

  constructor(private residentService: ResidentService){}
  
  ngOnInit(): void {
   this.getResidents();
  }

  public getResidents(): void {
    this.residentService.getResidents().subscribe(
      (response: Resident[]) => {
        this.residents = response;
      },
      (error: HttpErrorResponse) => {
        alert(error.message);
      }
    );
  }


  public searchResidents(key: string):void {
    const results: Resident[] = [];
    for (const resident of this.residents) {
      if (resident.firstName.toLowerCase().indexOf( key.toLowerCase()) !== -1 || 
          resident.lastName.toLowerCase().indexOf( key.toLowerCase()) !== -1 ||
          resident.email.toLowerCase().indexOf( key.toLowerCase()) !== -1) {
        console.log(resident.firstName.toLowerCase().indexOf( key.toLowerCase()));
        results.push(resident);
      }
    }
    this.residents = results;
    if (results.length === 0 || !key) {
      this.getResidents();
    }
  }

  public onOpenModal(resident: Resident | null, mode: string): void {
    const container = document.getElementById('main-container');
    const button = document.createElement('button');
    button.type = 'button';
    button.style.display = 'none';
    button.setAttribute('data-toggle', 'modal');

    if (mode === 'add') {
      button.setAttribute('data-target', '#registerResidentModal');
    }

    if (mode === 'edit') {
      if(resident != null) this.editResident = resident;
      button.setAttribute('data-target', '#updateResidentModal');
    }

    if (mode === 'delete') {
      if(resident != null) this.deleteResident = resident;
      button.setAttribute('data-target', '#deleteResidentModal');
    }
    
    if(container != null) container.appendChild(button);
    //else return an internal error maybe 

    button.click();
  } 

  public onRegisterResident(registerForm: NgForm): void {
    document.getElementById('add-resident-form')?.click();
    this.residentService.registerNewResident(registerForm.value).subscribe(
      (response: Resident[]) => {
        
        console.log(response);
        this.getResidents();
        registerForm.reset();
      },

      (error: HttpErrorResponse) => {
        alert(error.message);
        registerForm.reset();
      }
    );
  }


  public onUpdateResident(resident: Resident): void {
    document.getElementById('update-resident-form')?.click();
    
    this.residentService.updateResident(resident).subscribe(
      (response: Resident) => {
        console.log(response);
        this.getResidents();
      },
      (error: HttpErrorResponse) => {
        alert(error.message);
      }
    );
  }

  public onDeleteResident(residentId: number): void {
    document.getElementById('delete-resident-form')?.click();
    
    this.residentService.deleteResident(residentId).subscribe(
      () => {
        console.log('Successfully Deleted');
        this.getResidents();
      },
      (error: HttpErrorResponse) => {
        alert(error.message);
      }
    );
  }

}