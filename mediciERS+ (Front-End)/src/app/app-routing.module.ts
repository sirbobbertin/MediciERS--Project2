import { NgModule } from "@angular/core";
import { CommonModule } from "@angular/common";
import { BrowserModule } from "@angular/platform-browser";
import { Routes, RouterModule } from "@angular/router";

import { IndexComponent } from "./pages/index/index.component";
import { ProfilepageComponent } from "./pages/examples/profilepage/profilepage.component";
import { RegisterpageComponent } from "./pages/examples/registerpage/registerpage.component";
import { LandingpageComponent } from "./pages/examples/landingpage/landingpage.component";
import { AdminGuard } from "./users/admin.guard";
import { LoginComponent } from "./users/login/login.component";
import { LogoutComponent } from "./users/logout/logout.component";
import { HelloEmployeeComponent } from "./hello/hello-employee/hello-employee.component";
import { HelloManagerComponent } from "./hello/hello-manager/hello-manager.component";
import { ReimbursementHttpService } from "./reimbursement-http/reimbursement-http.service";
import { ReimbursementPendingComponent } from "./reimbursement-http/manager/reimbursement-pending/reimbursement-pending.component";
import { ReimbursementResolvedComponent } from "./reimbursement-http/manager/reimbursement-resolved/reimbursement-resolved.component";
import { ReimbursementSubmitComponent } from "./reimbursement-http/employee/reimbursement-submit/reimbursement-submit.component";
import { ReimbursementResolvedEmpComponent } from "./reimbursement-http/employee/reimbursement-resolved-emp/reimbursement-resolved-emp.component";
import { ReimbursementEmployeeComponent } from "./reimbursement-http/manager/reimbursement-employee/reimbursement-employee.component";
import { CollapseModule } from "ngx-bootstrap/collapse";
import { UploadFilesComponent } from "./upload-files/upload-files/upload-files.component";
import { UserEditComponent } from "./users/user-edit/user-edit.component";
import { ReimbursementEditComponent } from "./reimbursement-http/employee/reimbursement-edit/reimbursement-edit.component";
import { ManagerComponent } from "./users/manager/manager.component";

const routes: Routes = [
  { path: "", redirectTo: "home", pathMatch: "full" },
  { path: "home", component: IndexComponent },
  { path: "profile", component: ProfilepageComponent },
  { path: "register", component: RegisterpageComponent },
  { path: "landing", component: LandingpageComponent },
  { path: "login", component: LoginComponent},
  { path: "logout", component: LogoutComponent},
  { path: "hello-manager", component: HelloManagerComponent, canActivate: [AdminGuard]},
  { path: "hello-employee", component: HelloEmployeeComponent, canActivate: [AdminGuard]},
  { path: "reimbursement-http-service", component: ReimbursementHttpService, canActivate: [AdminGuard]},
  { path: "reimbursement-pending", component: ReimbursementPendingComponent, canActivate: [AdminGuard]},
  { path: "reimbursement-resolved", component: ReimbursementResolvedComponent, canActivate: [AdminGuard]},
  { path: "reimbursement-submit", component: ReimbursementSubmitComponent, canActivate: [AdminGuard]},
  { path: "reimbursement-resolved-emp", component: ReimbursementResolvedEmpComponent, canActivate: [AdminGuard]},
  { path: "reimbursement-employee", component: ReimbursementEmployeeComponent, canActivate: [AdminGuard]},
  { path: "upload-file", component: UploadFilesComponent, canActivate: [AdminGuard]},
  { path: "user-edit", component: UserEditComponent, canActivate: [AdminGuard]},
  { path: "reimbursement-edit", component: ReimbursementEditComponent, canActivate: [AdminGuard]},
  { path: "manager", component: ManagerComponent, canActivate: [AdminGuard]}
];

@NgModule({
  imports: [
    CommonModule,
    BrowserModule,
    CollapseModule,
    RouterModule.forRoot(routes, {
      useHash: true
    })
  ],
  exports: [RouterModule]
})
export class AppRoutingModule {}
