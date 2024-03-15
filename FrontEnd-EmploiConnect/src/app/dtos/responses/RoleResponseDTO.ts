import { AuthorityEnum } from "src/app/models/enums/AuthorityEnum"; 

export interface RoleResponseDTO {
    name: string;
    authorities: AuthorityEnum[];
    isDefault: boolean;
}