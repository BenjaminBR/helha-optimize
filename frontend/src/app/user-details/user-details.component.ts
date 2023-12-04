import { Component, Input } from '@angular/core';
import { User } from '../user';
import { ApiService } from '../api.service';
import { Message } from '../message';

@Component({
  selector: 'app-user-details',
  templateUrl: './user-details.component.html',
  styleUrls: ['./user-details.component.css']
})
export class UserDetailsComponent {

  _user!: User;

  @Input() set user(user: User) {
    if (user) {
      this._user = user;
      this.getMessageForUser(this._user.id)
    } else {
      this._user = {
        id: -1, username: "", jsonProfileObj: { firstName: "", lastName: "" },
        jsonProfile: '',
        messages: []
      };
    }

  }

  userMessages!: Message[];

  constructor(private service: ApiService) { }


  getMessageForUser(userId: number) {
    this.userMessages = [];
    this.service.getMessages().subscribe(messages => {
      this.userMessages = messages.filter(msg => msg.user_id == userId);
    })
  }

  saveUser() {
    this._user.jsonProfile = JSON.stringify(this._user.jsonProfileObj);
    this.service.saveUser(this._user).subscribe();
  }

  addMessage(msg: string) {
    this.service.addMessage({ id: -1, message: msg, user_id: this._user.id, user: this._user }).subscribe(message => {
      this.userMessages.push(message);
    });
  }

}
