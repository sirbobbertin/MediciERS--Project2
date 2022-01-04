import { BrowserAnimationsModule } from "@angular/platform-browser/animations";
import { NgModule } from "@angular/core";
import { FormsModule } from "@angular/forms";
import { RouterModule } from "@angular/router";
import { AppRoutingModule } from "./app-routing.module";
import { AppComponent } from "./app.component";
import { HttpClientModule } from "@angular/common/http";

import { BsDropdownModule } from "ngx-bootstrap/dropdown";
import { ProgressbarModule } from "ngx-bootstrap/progressbar";
import { TooltipModule } from "ngx-bootstrap/tooltip";
import { CollapseModule } from "ngx-bootstrap/collapse";
import { TabsModule } from "ngx-bootstrap/tabs";
import { PaginationModule } from "ngx-bootstrap/pagination";
import { AlertModule } from "ngx-bootstrap/alert";
import { BsDatepickerModule } from "ngx-bootstrap/datepicker";
import { CarouselModule } from "ngx-bootstrap/carousel";
import { ModalModule } from "ngx-bootstrap/modal";

import { PagesModule } from "./pages/pages.module";

import { IndexComponent } from "./pages/index/index.component";
import { ProfilepageComponent } from "./pages/examples/profilepage/profilepage.component";
import { RegisterpageComponent } from "./pages/examples/registerpage/registerpage.component";
import { LandingpageComponent } from "./pages/examples/landingpage/landingpage.component";
import { LoginComponent } from './users/login/login.component';
import { LogoutComponent } from './users/logout/logout.component';
import { HelloEmployeeComponent } from './hello/hello-employee/hello-employee.component';
import { HelloManagerComponent } from './hello/hello-manager/hello-manager.component';
import { UploadFilesComponent } from './upload-files/upload-files/upload-files.component';
import { HeaderComponent } from './header/header.component';
import { ReimbursementSubmitComponent } from './reimbursement-http/employee/reimbursement-submit/reimbursement-submit.component';
import { ReimbursementEditComponent } from './reimbursement-http/employee/reimbursement-edit/reimbursement-edit.component';
import { ReimbursementResolvedEmpComponent } from './reimbursement-http/employee/reimbursement-resolved-emp/reimbursement-resolved-emp.component';
import { ReimbursementEmployeeComponent } from "./reimbursement-http/manager/reimbursement-employee/reimbursement-employee.component";
import { ReimbursementPendingComponent } from './reimbursement-http/manager/reimbursement-pending/reimbursement-pending.component';
import { ReimbursementResolvedComponent } from "./reimbursement-http/manager/reimbursement-resolved/reimbursement-resolved.component";
import { ManagerComponent } from './users/manager/manager.component';
import { UserEditComponent } from './users/user-edit/user-edit.component';
@NgModule({
  declarations: [
    AppComponent,
    LoginComponent,
    LogoutComponent,
    HelloEmployeeComponent,
    HelloManagerComponent,
    UploadFilesComponent,
    HeaderComponent,
    ReimbursementSubmitComponent,
    ReimbursementEditComponent,
    ReimbursementResolvedEmpComponent,
    ReimbursementEmployeeComponent,
    ReimbursementPendingComponent,
    ReimbursementResolvedComponent,
    ManagerComponent,
    UserEditComponent
    // IndexComponent,
    // ProfilepageComponent,
    // RegisterpageComponent,
    // LandingpageComponent
  ],
  imports: [
    BrowserAnimationsModule,
    FormsModule,
    HttpClientModule,
    RouterModule,
    AppRoutingModule,
    CollapseModule.forRoot(),
    // BsDropdownModule.forRoot(),
    // ProgressbarModule.forRoot(),
    // TooltipModule.forRoot(),
    // CollapseModule.forRoot(),
    // TabsModule.forRoot(),
    PagesModule
    // PaginationModule.forRoot(),
    // AlertModule.forRoot(),
    // BsDatepickerModule.forRoot(),
    // CarouselModule.forRoot(),
    // ModalModule.forRoot()
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule {}
