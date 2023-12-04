import { Component } from '@angular/core';
import { User } from './user';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent {
  title = 'helha-optimize';
  userSelected: User = {
    id: -1, username: "", jsonProfileObj: { firstName: "", lastName: "" },
    jsonProfile: '',
    messages: []
  };
}
