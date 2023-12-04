import { User } from "./user";

export interface Message {
  id: number,
  message: string,
  user_id: number,
  user: User
}
