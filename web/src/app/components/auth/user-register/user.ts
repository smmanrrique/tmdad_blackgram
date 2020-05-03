import { BaseModel } from "src/app/core/models/base-model";

export class User extends BaseModel {
	userName: string;
	firstName: string;
	lastName: string;
	email: string;
	password: string;
	birthDay: Date;
} 
