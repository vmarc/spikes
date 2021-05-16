import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { AgGridModule } from 'ag-grid-angular';
import { Table5Component } from './table5/table5.component';
import { NetworksComponent } from './table1/networks.component';
import { NetworksService } from './table1/networks.service';
import { Table2Component } from './table2/table2.component';
import { Table3Component } from './table3/table3.component';
import { Table4Component } from './table4/table4.component';
import { MatTabsModule } from '@angular/material/tabs';
import { MenuComponent } from './shared/menu.component';
import { HttpClientModule } from '@angular/common/http';

@NgModule({
  imports: [
    BrowserModule,
    HttpClientModule,
    AppRoutingModule,
    BrowserAnimationsModule,
    AgGridModule.withComponents([]),
    MatTabsModule,
  ],
  declarations: [
    AppComponent,
    MenuComponent,
    NetworksComponent,
    Table2Component,
    Table3Component,
    Table4Component,
    Table5Component,
  ],
  providers: [NetworksService],
  bootstrap: [AppComponent],
})
export class AppModule {}
