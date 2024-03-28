import { AuthorityEnum } from "src/app/models/enums/AuthorityEnum"; 

export interface RoleResponseDTO {
    id:number;
    name: string;
    authorities: AuthorityEnum[];
    isDefault: boolean;
}