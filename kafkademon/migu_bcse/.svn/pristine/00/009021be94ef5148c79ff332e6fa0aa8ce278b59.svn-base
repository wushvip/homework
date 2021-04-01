angular.module('doc')
  .controller("docCtrl", ['$scope','$state','$filter','Menus', function($scope, $state, $filter, Menus) {
    $scope.docMenus = Menus.docs;
    /*追踪路由状态变化，当为doc时自动跳转用户管理文档界面，并展开相应的目录*/
    $scope.$on('$stateChangeSuccess', function(event, toState) {
      var isNavigatingTo = toState.name;
      if(isNavigatingTo === "manage.doc"){
        $state.go('manage.doc.um');
      }
      $scope.autoOpenMenu(isNavigatingTo);
    });
    //点击可展开目录时，只展示当前目录
    $scope.openMenu = function(firstMenu,secondMenu) {
      if (!secondMenu) {
        firstMenu.isOpen = !firstMenu.isOpen;
      }
      if (secondMenu) {
        secondMenu.isOpen = !secondMenu.isOpen;
      }
      angular.forEach($scope.docMenus,function(docMenu) {
        if (!secondMenu) {
          if (docMenu.head !== firstMenu.head) {
            docMenu.isOpen = false;
          }
        }
        if (secondMenu) {
          if (docMenu.head !== firstMenu.head) {
            docMenu.isOpen = false;
          }
          if(docMenu.subMenus) {
            angular.forEach(docMenu.subMenus,function(subMenu) {
              if (subMenu.isOpen !== undefined && subMenu.head !== secondMenu.head) {
                // docMenu.isOpen = false;
                subMenu.isOpen = false;
              }
            });
          }
        }
      });
    };
    $scope.autoOpenMenu = function(state) {
      angular.forEach($scope.docMenus,function(docMenu) {
        if (docMenu.subMenus) {
          angular.forEach(docMenu.subMenus,function(subMenu) {
            //检查二级目录是否匹配state，如果匹配，则展开对应的一级目录；否则检查三级目录
            if (subMenu.subMenus === null && subMenu.surl === state) {
              docMenu.isOpen = true;
            }
            if (subMenu.subMenus) {
              angular.forEach(subMenu.subMenus,function(menu) {
                if (menu.subMenus === null && menu.surl === state) {
                  docMenu.isOpen = true;
                  subMenu.isOpen = true;
                }
              });
            }
          });
        }
      });
    };
  }])
