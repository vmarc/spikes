import { Message } from './message';

export interface ChatRoom {
  users: string[];
  messages: Message[];
}
