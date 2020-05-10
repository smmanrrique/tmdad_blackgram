import { BaseModel } from "src/app/core/models/base-model";

export class Topic extends BaseModel {
    id: number;
    name: string;
    description: string;
} 