import { Message } from "./message";

export interface User {
  id: number,
  username: string,
  jsonProfile: string,
  jsonProfileObj: {
    firstName: string,
    lastName: string
  },
  messages: Message[]
}
