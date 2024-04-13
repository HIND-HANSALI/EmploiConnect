import { Component, OnInit } from '@angular/core';
import { AngularFireStorage } from '@angular/fire/compat/storage';
import { FormBuilder, FormControl, FormGroup, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { ApplicationRequestDto } from 'src/app/dtos/requests/ApplicationRequestDTO';
import { ApplicationService } from 'src/app/services/application/application.service';

@Component({
  selector: 'app-register-appliaction',
  templateUrl: './register-appliaction.component.html',
  styleUrls: ['./register-appliaction.component.css']
})
export class RegisterAppliactionComponent implements OnInit {
  applicationForm: FormGroup;
  offerId: number=0;

  errorMessage: string = '';

  constructor(
    private route: ActivatedRoute,
    private formBuilder: FormBuilder,
    private applicationService: ApplicationService,private fireStorage:AngularFireStorage 
  ) {
    this.applicationForm = this.formBuilder.group({
      title: ['', Validators.required],
      profile: ['', Validators.required],
      cv: [null, Validators.required]
    });
  }
  ngOnInit(): void {
    this.route.params.subscribe(params => {
      this.offerId = params['id']; //use the offerId to save the application
    });
  }

  onSubmit(): void {
    
    if (this.applicationForm.invalid) {
      return;
    }

    // const formData: ApplicationRequestDto = {
    //   title: this.applicationForm.get('title')?.value,
    //   profile: this.applicationForm.get('profile')?.value || '',
    //   file: this.applicationForm.get('file')?.value || ''
    // };
    //   console.log(formData);

    const fileInput = document.getElementById('file') as HTMLInputElement;
      const file = (fileInput.files as FileList)[0];
        console.log(file.name)
        console.log("Selected file:", fileInput.value);
    if (file) {
      this.readAndUploadFile(file);
    } else {
      console.error('No file selected');
    }
  }

  private async readAndUploadFile(file: File) {
    const path = `application_files/${new Date().getTime()}_${file.name}`;
    const uploadTask = this.fireStorage.upload(path, file);

    try {
      const snapshot = await uploadTask;
      const downloadURL = await snapshot.ref.getDownloadURL();
      console.log('File uploaded successfully. Download URL:', downloadURL);
      
      const formData: ApplicationRequestDto = {
        title: this.applicationForm.get('title')?.value,
        profile: this.applicationForm.get('profile')?.value || '',
        cv: downloadURL
      };
      console.log('formData successfully:', formData);


    this.applicationService.saveApplication(formData,this.offerId).subscribe(
      (response) => {
        console.log('Application saved successfully:', response);
        
        this.applicationForm.reset();
      },
      (error) => {
        console.error('Error saving application:', error);
        this.errorMessage = error.error.message;
      }
    );
  }catch(error) {
    console.error('Error uploading file:', error);
  }







  // const formData = new FormData();
  
  // const fileInput = document.getElementById('file') as HTMLInputElement;
  // console.log("Selected file:", fileInput.value);
  // const fileList: FileList | null = fileInput.files;
  
  // // Check if files were selected
  // if (fileList && fileList.length > 0) {
  //   const file: File = fileList[0]; // Get the first file in the list
  
  //   // Append the file to FormData
  //   formData.append('file', file, file.name);
  // } else {
  //   console.error('No file selected or file input is missing.');
  //   return;
  // }
  // // formData.append('file', this.applicationForm.get('cv')?.files[0]);
  // formData.append('title', this.applicationForm.get('title')?.value);
  // formData.append('profile', this.applicationForm.get('profile')?.value);
  // console.log("FormData with file:", formData);
  // this.applicationService.saveApplicationNew(formData, this.offerId).subscribe(
  //   (response) => {
  //     console.log('Application saved successfully:', response);
  //     this.applicationForm.reset();
  //   },
  //   (error) => {
  //     console.error('Error saving application:', error);
  //     this.errorMessage = error.error.message;
  //   }
  // );




  }

    // onFileSelected(event: any): void {
    //   const file: File = event.target.files[0];
    //   this.applicationForm.patchValue({
    //     cv: file
    //   });
    // }
  
}
