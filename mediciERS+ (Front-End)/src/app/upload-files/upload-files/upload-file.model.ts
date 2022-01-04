import { SafeUrl } from "@angular/platform-browser";

export class RequestImageModel {
    rbId: number = 0;
    userId: number = 0;
    rbDate: string = '';
    rbAmount: number = 0;
    rbStatus: string = '';
    rbResolved: boolean = false;
    rbRemoved: boolean = false;
    imageBlob: SafeUrl = "";
}