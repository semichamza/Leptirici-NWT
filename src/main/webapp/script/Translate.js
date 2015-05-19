/**
 * Created by Jasmin on 16-Apr-15.
 */
app.config( function ($translateProvider) {

    // Simply register translation table as object hash
    $translateProvider.translations('en', {
        'HEADER': 'Header',
        'LOGIN': 'Login',
        'USERNAME': 'Username',
        'PASSWORD': 'Password',
        'CHANGE_LANGUAGE':'Language changed',
        'INBOX':'Inbox',
        'MAIN_NAV':'MAIN NAVIGATION',
        'LOGIN_MESSAGE':'Wellcome',
        'MESSAGE_LABEL':'Message:',
        'EMPTY_SUBHEADER':'',
        'DASHBOADR_LABEL':'Dashboard',
        'STATISTICS_LABEL':'Statistics',
        'SETTINGS':'Settings',
        'MESSAGES':'Messages',
        'MESSAGE_SENT':'Message sent',
        'NEW_MESSAGE':'New message',
        'MESSAGE':'Message',
        'SEND':'Send',
        'USER_UPDATED':'User detalis updated.',
        'TASK_LABEL':'Tasks',
        'PROJECT_LABEL':'Projects',
        'PROJECT_DETAIL_LABEL':'Project details',
        'RESET_PASS_MESSAGE':'New password is send on email!',
        'LINK_SENT':'Link for reset is send on email!',
        'ACTIVATION_LINK':'Activation link is send on email!',
        'REGISTRATION_ERROR':'Error while registration process',
        'INVALID_LOGIN':'Invalid username or password',
        'USER_BLOCKED':'User blocked',
        'USER_UNBLOCKED':'User unblocked',
        '1':'Invalid username or password',
        '2':'Invalid username',
        '3':'Your account is blocked',
        'TO_LABEL':'To',
        'NO_MESSAGES':'No messages',
        'TASKS_PER_PROJECT':'Tasks per project',
        'DETAILS':'Details ',
        'TASKS_PER_PROJECT_DETAILS':"Show number of task assigned to each project",
        'USER_TASKS':"User tasks",
        'USER_TASKS_DETAILS':"Shows number of tasks for selected user",
        'USER':'User',
        'MESSAGE_FOR_USER':"Number of user messages",
        'MESSAGES_FOR_USER_DETAILS':'Shows number of send received and sent messages for selected user',
        'MESSAGES_FOR_USERS_DETAILS':'Shows number of send received and sent messages for all users',
        'MODAL_HEADER_RESET_PASSWORD':'Reset password'
    });

    $translateProvider.translations('en', {
        'REGISTER': 'Register'

    });

    $translateProvider.translations('bs', {
        'HEADER': 'Naslov',
        'MESSAGES':'Poruke',
        'MESSAGE_SENT':'Poruka poslana',
        'SEND':'Pošalji',
        'TO_LABEL':'Do',
        'INBOX':'Inbox',
        'NO_MESSAGES':'Nema poruka',
        'LOGIN': 'Prijavi se',
        'REGISTER': 'Registruj se',
        'USERNAME':'Korisnicko ime',
        'PASSWORD':'Sifra',
        'NEW_MESSAGE':'Nova poruka',
        'MESSAGE':'Poruka',
        'STATISTICS_LABEL':'Statistika',
        'CHANGE_LANGUAGE':'Jezik promijenjen',
        'USER_BLOCKED':'Korisnik blokiran',
        'USER_UPDATED':'Korisnički podaci ažurirani.',
        'USER_UNBLOCKED':'Korisnik odblokiran',
        'MAIN_NAV':'GLAVNA NAVIGACIJA',
        'LOGIN_MESSAGE':'Dobrodosli',
        'MESSAGE_LABEL':'Poruka:',
        'EMPTY_SUBHEADER':'',
        'DASHBOADR_LABEL':'Komandna tabla',
        'TASK_LABEL':'Taskovi',
        'SETTINGS':'Postavke',
        'PROJECT_LABEL':'Projekti',
        'PROJECT_DETAIL_LABEL':'Detalji projekta',
        'RESET_PASS_MESSAGE':'Nova sifra je poslana na mail!',
        'LINK_SENT':'Link za reset je poslan na mail',
        'ACTIVATION_LINK':'Aktivacijski link je poslan na mail!',
        'REGISTRATION_ERROR':'Greška prilikom registracije',
        'INVALID_LOGIN':'Pogresano korisnicko ime ili sifra!',
        '1':'Pogresano korisnicko ime ili sifra!',
        '2':'Pogresano korisnicko ime!',
        '3':'Vas racun je blokiran!',
        'TASKS_PER_PROJECT':'Taskova po projektu',
        'DETAILS':'Detalji ',
        'TASKS_PER_PROJECT_DETAILS':"Prikazuje broj taskova koji su dodjeljenu projektu",
        'USER_TASKS':"Korisnički taskovi",
        'USER_TASKS_DETAILS':"Prikazuje broj taskova za izabranog korisnika",
        'MESSAGE_FOR_USER':"Broj korisničkih poruka",
        'MESSAGES_FOR_USER_DETAILS':'Prkazuje broj primljenih i poslanih poruka za izabranog korisnika',
        'MESSAGES_FOR_USERS_DETAILS':'Prkazuje broj primljenih i poslanih poruka za sve korisnike',
        'USER':'Korisnik',
        'MODAL_HEADER_RESET_PASSWORD':'Resetovanje sifre'
    });

    //$translateProvider.preferredLanguage($rootScope.currentLng);
});