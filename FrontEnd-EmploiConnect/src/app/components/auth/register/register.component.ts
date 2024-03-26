import { Component } from '@angular/core';
import { Router } from '@angular/router';
import { RegisterDto } from 'src/app/dtos/requests/RegisterDto';
import { AuthService } from 'src/app/services/auth/auth.service';

@Component({
  selector: 'app-register',
  templateUrl: './register.component.html',
  styleUrls: ['./register.component.css']
})
export class RegisterComponent {
  email: string= '';
  password: string= '';
  firstName: string= '';
  familyName: string= '';

  constructor(private authService: AuthService, private route: Router) { }


  onSubmit() {
    const registerDto: RegisterDto = {
      firstName:this.firstName,
      familyName:this.familyName,
      email : this.email,
      password : this.password,
    }

    this.authService.register(registerDto).subscribe(
        () => {
          console.log('Registration successful');

          // After successful registration, navigate to login page
          this.route.navigateByUrl('/login');
        },
        (error: any) => {
          console.error('Registration failed:', error);
        }
      );
  }
}
