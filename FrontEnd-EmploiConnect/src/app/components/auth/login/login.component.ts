import { Component } from '@angular/core';
import { Router } from '@angular/router';
import { LoginDto } from 'src/app/dtos/requests/LoginDto';
import { AuthService } from 'src/app/services/auth/auth.service';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent {
  email: string= '';
  password: string= '';
  constructor(private authService: AuthService, private route: Router) { }

  onSubmit() {
    const loginDto: LoginDto = {
      email : this.email,
      password : this.password,
    }
    this.authService.login(loginDto).subscribe(
      (response) => {
        console.log('Login successful!', response);

        localStorage.setItem('role', response.role.name);
        localStorage.setItem('firstName', response.firstName);
        localStorage.setItem('familyName', response.familyName);
       
        this.route.navigate(["offers"])
      },
      (error) => {
        
        console.error('Login failed!', error);
      }
    );
  }


}
