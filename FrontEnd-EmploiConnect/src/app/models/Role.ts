import { AuthorityEnum } from "./enums/AuthorityEnum"; 

export interface Role {
  id: number;
  name: string;
  authorities: AuthorityEnum[];
  isDefault: boolean;
}