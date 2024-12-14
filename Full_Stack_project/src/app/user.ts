export class User {
    usersId: number;
    usersName: string;
    userPassword: string;
    userEmail: String;
    userInterest: string;
    subscriptionStatus: string;
    userRole: string;

    constructor(usersId: number, usersName: string, userPassword: string, userEmail: String, subscriptionStatus: string, userInterest: string, userRole: string) {
        this.usersId = usersId;
        this.usersName = usersName;
        this.userPassword = userPassword;
        this.subscriptionStatus = subscriptionStatus;
        this.userEmail = userEmail;
        this.userInterest = userInterest;
        this.userRole = userRole;
    }
}
