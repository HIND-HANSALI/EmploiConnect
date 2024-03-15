import { Role } from "./Role";
import { AuthorityEnum } from "./enums/AuthorityEnum"; 

export interface Authority {
  id: number;
  roles: Role[];
  name: AuthorityEnum;
}