import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { AgGridModule } from 'ag-grid-angular';
import { Table5Component } from './table5/table5.component';
import { NetworksComponent } from './networks/networks.component';
import { NetworksService } from './networks/networks.service';
import { PagingComponent } from './paging/paging.component';
import { Table3Component } from './table3/table3.component';
import { Table4Component } from './table4/table4.component';
import { MatTabsModule } from '@angular/material/tabs';
import { MenuComponent } from './shared/menu.component';
import { HttpClientModule } from '@angular/common/http';
import { MatPaginatorModule } from '@angular/material/paginator';

@NgModule({
  imports: [
    BrowserModule,
    HttpClientModule,
    AppRoutingModule,
    BrowserAnimationsModule,
    AgGridModule.withComponents([]),
    MatTabsModule,
    MatPaginatorModule,
  ],
  declarations: [
    AppComponent,
    MenuComponent,
    NetworksComponent,
    PagingComponent,
    Table3Component,
    Table4Component,
    Table5Component,
  ],
  providers: [NetworksService],
  bootstrap: [AppComponent],
})
export class AppModule {}
