import { Component, Input, OnInit, Output, EventEmitter } from '@angular/core';
import { AuthService } from '../users/auth.service';

@Component({
  selector: 'app-header',
  templateUrl: './header.component.html',
  styleUrls: ['./header.component.scss']
})
export class HeaderComponent implements OnInit {
  // after injecting authService, we cannot use authService directly in the template
  // to fix this , we created functions in the component class which in turn
  // accessed the functions/properties of authService
  // now the template can call these functions of the component directly
  constructor(private authService: AuthService) { }

  ngOnInit(): void {
  }

  isLoggedIn() {
    return this.authService.isLoggedIn;
  }

  retrieveUserType() {
    return this.authService.retrieveUserType();
  }

}