import { Injectable } from '@angular/core';

@Injectable({
  providedIn: 'root',
})
export class AppService {
  private broadcastChannel: BroadcastChannel;

  constructor() {
    this.broadcastChannel = new BroadcastChannel(
      'knooppuntnet-broadcast-channel'
    );
    this.broadcastChannel.onmessage = (message: MessageEvent) => {
      console.log('Received message: ');
      console.log('  type=' + message.type);
      console.log('  data=' + message.data);
      console.log('  origin=' + message.origin);
    };
  }

  send(message: string): void {
    console.log('Send message: ', message);
    this.broadcastChannel.postMessage(message);
  }
}
