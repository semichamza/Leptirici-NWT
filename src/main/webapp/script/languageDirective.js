/**
 * Created by Jasmin on 12-May-15.
 */

var languageDirective=function()
{
  return{
      template: '<a href="" class="navbar-brand" style="font-size: 14px" ng-click="toggleLang()">{{currentLng}}</a>'
  }
};
app.directive('languageDirective',languageDirective);