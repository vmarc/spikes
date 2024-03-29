import { NgModule } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { BrowserModule } from '@angular/platform-browser';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { ChatBoxComponent } from './chat/chat-box.component';
import { ChatMessagesComponent } from './chat/chat-messages.component';
import { ChatRoomsComponent } from './chat/chat-rooms.component';
import { ChatUsersComponent } from './chat/chat-users.component';
import { ChatComponent } from './chat/chat.component';
import { LoginComponent } from './login.component';

@NgModule({
  declarations: [
    AppComponent,
    LoginComponent,
    ChatComponent,
    ChatRoomsComponent,
    ChatUsersComponent,
    ChatMessagesComponent,
    ChatBoxComponent,
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    FormsModule
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule {
}
