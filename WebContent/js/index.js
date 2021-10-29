(function () {
    function init() {
        var router = new Router([
            new Route("home", "home.html", true),
            new Route("test", "test.html"),
            new Route("results","results.html"),
            new Route("contacts","contacts.html")
        ]);
    }
    init();

    history.replaceState(null, '', window.location.href.substring(0,window.location.href.lastIndexOf("/"))+"/#home");
})();