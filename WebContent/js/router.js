function Router(routes) {
    try {
        if(!routes) {
            throw "Error: 012 - no routes available."
        }
        this.constructor(routes);
        this.init();
    } catch(e) {
        console.error(e);
    }
}

Router.prototype = {
    routes: undefined,
    rootElement: undefined,
    constructor: function(routes) {
        this.routes = routes;
        this.rootElement = document.getElementById("esdebitContainer");
    },
    init: function() {
        var r = this.routes;
        (function(scope, r) {
            window.addEventListener('hashchange', function(e) {
                scope.hasChanged(scope, r);
            });
        })(this, r);
        this.hasChanged(this, r);
    },
    hasChanged: function(scope, r) {
        if(window.location.hash.length > 0) {
            for(var i = 0, lenght = r.length; i < lenght; i++) {
                var route = r[i];
                if(route.isActiveRoute(window.location.hash.substr(1))) {
                    scope.goToRoute(route.htmlName);
                }
            }
        } else {
            for(var i = 0, lenght = r.length; i < lenght; i++) {
                var route = r[i];
                if(route.default) {
                    scope.goToRoute(route.htmlName);
                }
            }
        }
    },
    goToRoute: function(htmlName) {
        (function(scope) {
            var path = "views";
            if(htmlName.split('.')[1] === 'jsp'){
                path = "jsp";
            }
            var url = path + "/" + htmlName,
                xhttp = new XMLHttpRequest();
            xhttp.onreadystatechange = function() {
                if(this.readyState === 4 && this.status === 200) {
                    scope.rootElement.innerHTML = this.responseText;
                }
            };
            xhttp.open('GET', url, true);
            xhttp.send();
        })(this);
    }
};