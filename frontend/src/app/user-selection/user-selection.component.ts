import { Component, EventEmitter, OnInit, Output } from '@angular/core';
import { ApiService } from '../api.service';
import { Observable } from 'rxjs';
import { User } from '../user';

@Component({
  selector: 'app-user-selection',
  templateUrl: './user-selection.component.html',
  styleUrls: ['./user-selection.component.css']
})
export class UserSelectionComponent implements OnInit {


  users!: User[];

  @Output()
  userSelected: EventEmitter<User> = new EventEmitter();

  constructor(private service: ApiService) { }

  ngOnInit(): void {
    this.service.getUsers().subscribe(users => {
      this.users = users;
    })
  }

  selectUser(userSelected: any) {
    const user = this.users.filter(u => u.id == userSelected.value);
    this.userSelected.emit(user[0]);
  }

}
