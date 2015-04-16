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
        'CHANGE_LANGUAGE':'Change language'
    });

    $translateProvider.translations('bs', {
        'HEADER': 'Naslov',
        'LOGIN': 'Prijavi se',
        'USERNAME':'Korisnicko ime',
        'PASSWORD':'Sifra',
        'CHANGE_LANGUAGE':'Promijeni jezik'
    });

    $translateProvider.preferredLanguage('bs');
});

app.controller('TranslateController', function ($translate, $scope) {
    $scope.toggleLang = function () {
        $translate.use() == 'en' ? $translate.use('bs') : $translate.use('en');
    };

});