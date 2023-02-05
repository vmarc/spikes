import { Injectable } from '@nestjs/common';
import { Logger } from '@nestjs/common';
import { ChatRoom } from './chat-room';
import { Message } from './message';

@Injectable()
export class ChatService {
  users: Record<string, string> = {};
  chatRooms: Record<string, ChatRoom> = {
    General: { users: [], messages: [] },
    Angular: { users: [], messages: [] },
    NestJS: { users: [], messages: [] },
  };

  private readonly logger = new Logger('ChatService');

  identify(user: string, clientId: string) {
    this.logger.log(` user=${user}: identify clientId=${clientId}`);
    this.users[user] = clientId;
  }

  disconnect(clientId: string) {
    //look up user by clientId:
    const users = Object.keys(this.users);
    let userToRemove = '';
    users.forEach((user) => {
      if (this.users[user] === clientId) {
        userToRemove = user;
        return;
      }
    });
    this.logger.log(`user=${userToRemove}: disconnect clientId=${clientId}`);
    if (userToRemove) {
      delete this.users[userToRemove];
      // remove user from any joined rooms
      const rooms = Object.keys(this.chatRooms);
      rooms.forEach((room) => {
        this.leaveRoom(room, userToRemove);
      });
    }
    return userToRemove;
  }

  joinRoom(room: string, user: string) {
    this.logger.log(`user=${user}: joinRoom room=${room}`);
    this.chatRooms[room].users.push(user);
    // sort the users alphabetically
    this.chatRooms[room].users.sort((a, b) => {
      return a.toLowerCase() >= b.toLowerCase() ? 1 : -1;
    });
  }

  leaveRoom(room: string, user: string) {
    this.logger.log(`user=${user}: leaveRoom room=${room}`);
    this.chatRooms[room].users = this.chatRooms[room].users.filter(
      (u) => u !== user,
    );
  }

  getChatRoom(room: string) {
    return this.chatRooms[room];
  }

  getChatRooms() {
    return Object.keys(this.chatRooms);
  }

  addMessage(room: string, message: Message) {
    this.logger.log(`user=${message.user}: addMessage room=${room}, content=${message.content}`);
    this.chatRooms[room].messages.push(message);
  }
}
