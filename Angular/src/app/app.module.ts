import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';

import { AppComponent } from './app.component';
import { HttpClientModule } from '@angular/common/http';
import { MatButtonModule, MatCardModule, MatInputModule, MatListModule, MatToolbarModule, MatTableModule,
MatSidenavModule, MatCheckboxModule } from '@angular/material';
import {MatSelectModule} from '@angular/material/select';
import {MatDatepickerModule} from '@angular/material/datepicker';
import { MatNativeDateModule} from '@angular/material';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { FormsModule } from '@angular/forms';
import { RouterModule, Routes } from '@angular/router';
import { PromotionComponent } from './promotion/promotion.component';
import { PromotionService } from './service/promotion.service';
import { ViewPromotionComponent } from './view-promotion/view-promotion.component';
import { ViewPromotionService } from './service/view-promotion.service';
import { MainComponent } from './main/main.component';
import { ReservationsService } from './service/reservations.service';
import { ReservationsComponent } from './reservations/reservations.component';

const appRoutes: Routes = [
  {path:'' , component:MainComponent},
  {path:'view-promotion' , component:ViewPromotionComponent},
  {path:'promotion' , component:PromotionComponent},
  {path:'reservation' , component:ReservationsComponent}
];

@NgModule({
  declarations: [
    AppComponent,
    PromotionComponent,
    ViewPromotionComponent,
    MainComponent,
    ReservationsComponent
  ],
  imports: [
    BrowserModule,
    HttpClientModule,
    BrowserAnimationsModule,
    MatButtonModule,
    MatCardModule,
    MatInputModule,
    MatListModule,
    MatToolbarModule,
    MatSelectModule,
    MatDatepickerModule,
    MatNativeDateModule,
    FormsModule,
    MatTableModule,
    MatCheckboxModule,
    MatSidenavModule,
    RouterModule.forRoot(appRoutes)
  ],
  providers: [PromotionService,ViewPromotionService,ReservationsService],
  bootstrap: [AppComponent]
})
export class AppModule { }
